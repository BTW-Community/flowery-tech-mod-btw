package dev.bagel.mixin;

import dev.bagel.emi.BotaniaEmiPlugin;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class GuiScreenMixin {
    @Inject(method = "handleKeyboardInput", at = @At("TAIL"))
    private void forge$handleKeyboardInput(CallbackInfo ci) {
        BotaniaEmiPlugin.KeyBindings.handleKey();
    }
}
