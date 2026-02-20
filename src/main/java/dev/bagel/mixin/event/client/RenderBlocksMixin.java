package dev.bagel.mixin.event.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.RenderBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class RenderBlocksMixin {

    @Inject(method = "getBlockIcon(Lnet/minecraft/src/Block;Lnet/minecraft/src/IBlockAccess;IIII)Lnet/minecraft/src/Icon;", at = @At("HEAD"), cancellable = true)
    private void forge$getDetailedBlockIcon(Block block, IBlockAccess world, int x, int y, int z, int side, CallbackInfoReturnable<Icon> cir) {
        if (block != null) {
            var icon = block.getIcon(world, x, y, z, side);
            if (icon != null) {
                cir.setReturnValue(icon);
            }
        }
    }
}