package dev.bagel.mixin;

import baubles.common.event.KeyHandler;
import net.minecraft.src.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class BaublesMinecraftMixin {
    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Minecraft;sendClickBlockToController(IZ)V"))
    private void baubles$onTick(CallbackInfo ci) {
        KeyHandler.INSTANCE.onKeyEvent();
    }
}
