package dev.bagel.mixin.event;

import net.minecraft.src.ResourceManager;
import net.minecraft.src.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;

@Mixin(TextureMap.class)
public class TextureMapMixin {
    @Inject(method = "loadTextureAtlas", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V", ordinal = 0, shift = At.Shift.AFTER))
    private void forge$TextureStitchEventPre(ResourceManager resourceManager, CallbackInfo ci) {
        TextureStitchEvent.Pre event = new TextureStitchEvent.Pre(((TextureMap) (Object) this));
        ModBlocks.bifrost.loadTextures(event);
        ModBlocks.enchantedSoil.loadTextures(event);
        ModBlocks.prismarine.loadTextures(event);
        ModBlocks.shimmerrock.loadTextures(event);
        ModBlocks.shimmerwoodPlanks.loadTextures(event);
        ModFluffBlocks.bifrostPane.loadTextures(event);
    }
}
