package dev.bagel.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.ICamera;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {
    @WrapOperation(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/TileEntityRenderer;renderTileEntity(Lnet/minecraft/src/TileEntity;F)V"))
    private void test(TileEntityRenderer instance, TileEntity tile, float floatVal, Operation<Void> original, @Local(argsOnly = true) ICamera camera) {
        if (camera.isBoundingBoxInFrustum(tile.getRenderBoundingBox())) {
            original.call(instance, tile, floatVal);
        }
    }
}
