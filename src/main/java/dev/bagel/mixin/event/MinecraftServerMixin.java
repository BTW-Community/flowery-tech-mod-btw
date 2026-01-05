package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.fabricmc.api.EnvType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityTracker;
import net.minecraft.src.WorldClient;
import net.minecraft.src.WorldServer;
import net.minecraft.src.WorldType;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow
    public WorldServer[] worldServers;
    private WorldServer forge$server;
    @WrapOperation(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/WorldServer;getEntityTracker()Lnet/minecraft/src/EntityTracker;"))
    private EntityTracker forge$grabServer(WorldServer instance, Operation<EntityTracker> original) {
        forge$server = instance;
        return original.call(instance);
    }
    @Inject(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityTracker;updateTrackedEntities()V"))
    private void forge$onWorldTick(CallbackInfo ci) {
        TickEvent.WorldTickEvent.EVENT.invoker().onTick(new TickEvent.WorldTickEvent(EnvType.SERVER, TickEvent.Phase.END, forge$server));
    }

    //WorldEvent.Load
    @Inject(method = "loadAllWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ServerConfigurationManager;setPlayerManager([Lnet/minecraft/src/WorldServer;)V"))
    private void forge$onWorldLoad(String par1Str, String par2Str, long par3, WorldType par5WorldType, String par6Str, CallbackInfo ci, @Local int worldId) {
        WorldEvent.Load.EVENT.invoker().onWorldLoad(new WorldEvent.Load(this.worldServers[worldId]));
    }

    //WorldEvent.Unload
    @Inject(method = "stopServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/WorldServer;flush()V"))
    private void forge$onWorldStopUnload(CallbackInfo ci, @Local WorldServer worldServer) {
        WorldEvent.Unload.EVENT.invoker().onWorldUnload(new WorldEvent.Unload(worldServer));
    }

    //WorldEvent.Unload
    @Inject(method = "deleteWorldAndStopServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/WorldServer;flush()V"))
    private void forge$onWorldDeleteUnload(CallbackInfo ci, @Local WorldServer worldServer) {
        WorldEvent.Unload.EVENT.invoker().onWorldUnload(new WorldEvent.Unload(worldServer));
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void forge$onPostServerTick(CallbackInfo ci) {
        TickEvent.ServerTickEvent.EVENT.invoker().onServerTick(new TickEvent.ServerTickEvent(TickEvent.Phase.END));
    }
}
