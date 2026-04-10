package dev.bagel.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(targets = "api.config.ConfigElementValidator$ListValidator", remap = false)
public class ConfigElementValidatorMixin {

    @Redirect(method = "validate", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    public void stopBuggingMeIKNowWhatTheSlotsAre(PrintStream instance, String message) {

    }
}
