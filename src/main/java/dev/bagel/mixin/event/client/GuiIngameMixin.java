package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.client.core.handler.HUDHandler;

@Mixin(GuiIngame.class)
public class GuiIngameMixin {
    private RenderGameOverlayEvent forge$eventParent;
    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ScaledResolution;getScaledHeight()I"))
    private void forge$getLocal(float par1, boolean par2, int mouseX, int mouseY, CallbackInfo ci, @Local ScaledResolution res) {
        forge$eventParent = new RenderGameOverlayEvent(par1, res, mouseX, mouseY);
    }

    @Inject(method = "renderGameOverlay", at = @At("TAIL"))
    private void forge$postAll(float par1, boolean par2, int mouseX, int mouseY, CallbackInfo ci, @Local ScaledResolution res) {
        HUDHandler.onDrawScreenPost(new RenderGameOverlayEvent.Post(forge$eventParent, RenderGameOverlayEvent.ElementType.HEALTH));
    }

    @Inject(method = "func_110327_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Profiler;endStartSection(Ljava/lang/String;)V"))
    private void forge$onRenderHealthPre(int width, int height, CallbackInfo ci) {
        HUDHandler.onDrawScreenPre(new RenderGameOverlayEvent.Pre(forge$eventParent, RenderGameOverlayEvent.ElementType.HEALTH));
    }
}
