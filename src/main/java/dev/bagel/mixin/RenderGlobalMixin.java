package dev.bagel.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.bagel.interfaces.WorldProviderExtensions;
import net.minecraft.src.*;
import net.minecraftforge.client.IRenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {
    @Shadow
    private Minecraft mc;

    @Shadow
    private WorldClient theWorld;

    @WrapOperation(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/TileEntityRenderer;renderTileEntity(Lnet/minecraft/src/TileEntity;F)V"))
    private void test(TileEntityRenderer instance, TileEntity tile, float floatVal, Operation<Void> original, @Local(argsOnly = true) ICamera camera) {
        if (camera.isBoundingBoxInFrustum(tile.getRenderBoundingBox())) {
            original.call(instance, tile, floatVal);
        }
    }

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void forge$onRenderSky(float partial, CallbackInfo ci) {
        IRenderHandler skyProvider;
        if ((skyProvider = ((WorldProviderExtensions) this.mc.theWorld.provider).getSkyRenderer()) != null)
        {
            skyProvider.render(partial, this.theWorld, mc);
            ci.cancel();
        }
    }
}
