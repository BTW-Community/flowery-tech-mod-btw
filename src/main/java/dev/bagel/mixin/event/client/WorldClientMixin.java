package dev.bagel.mixin.event.client;

import net.minecraft.src.*;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldClient.class)
public class WorldClientMixin {
    //WorldEvent.Load
    @Inject(method = "<init>", at = @At("TAIL"))
    private void forge$worldEventLoad(NetClientHandler par1NetClientHandler, WorldSettings par2WorldSettings, int par3, int par4, Profiler par5Profiler, ILogAgent par6ILogAgent, CallbackInfo ci) {
        WorldEvent.Load.EVENT.invoker().onWorldLoad(new WorldEvent.Load((WorldClient) (Object) this));
    }
}
