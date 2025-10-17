package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.fabricmc.api.EnvType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityTracker;
import net.minecraft.src.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
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
}
