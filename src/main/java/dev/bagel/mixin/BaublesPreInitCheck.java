package dev.bagel.mixin;

import api.AddonHandler;
import baubles.common.BaublesExpanded;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AddonHandler.class, remap = false)
public class BaublesPreInitCheck {

    @Inject(method = "initializeMods", at = @At(value = "INVOKE", target = "Lapi/AddonHandler;preInitializeMods()V", shift = At.Shift.AFTER))
    private static void afterPreInitializeMods(CallbackInfo ci) {
        BaublesExpanded.noLongerPreInit();
    }
}
