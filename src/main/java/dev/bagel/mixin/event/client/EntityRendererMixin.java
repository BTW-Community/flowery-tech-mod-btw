package dev.bagel.mixin.event.client;

import net.minecraft.src.EntityRenderer;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Profiler;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Shadow private Minecraft mc;

    //
    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Profiler;endStartSection(Ljava/lang/String;)V", ordinal = 19))
    private void renderWorld(float partials, long par2, CallbackInfo ci) {
        this.mc.mcProfiler.endStartSection("FRenderLast");
        var event = new RenderWorldLastEvent(this.mc.renderGlobal, partials);
        RenderWorldLastEvent.EVENT.invoker().renderWorldLast(event);
    }
}
