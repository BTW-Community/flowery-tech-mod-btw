package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
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

}
