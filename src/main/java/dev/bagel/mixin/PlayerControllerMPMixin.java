package dev.bagel.mixin;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Minecraft;
import net.minecraft.src.PlayerControllerMP;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class PlayerControllerMPMixin {
    @Shadow @Final private Minecraft mc;

    @Inject(method = "onPlayerDestroyBlock", at = @At("HEAD"), cancellable = true)
    private void forge$onBlockStartBreak(int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
        if (stack != null && stack.getItem() != null && stack.getItem().onBlockStartBreak(stack, x, y, z, this.mc.thePlayer))
        {
            cir.setReturnValue(false);
        }
    }
}
