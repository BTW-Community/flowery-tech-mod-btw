package dev.bagel.mixin;

import net.minecraft.src.Icon;
import net.minecraft.src.RenderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class RenderItemMixin {
    @Inject(method = "renderIcon", at = @At("HEAD"), cancellable = true)
    private void init(int par1, int par2, Icon par3Icon, int par4, int par5, CallbackInfo ci) {
        if (par3Icon == null) {
             ci.cancel();
        }
    }
}
