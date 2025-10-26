package dev.bagel.mixin;

import net.minecraft.src.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.lib.LibItemNames;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {
    @Shadow public abstract void addStreaming(String par1Str);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void botania$registerMusic(CallbackInfo ci) {
        this.addStreaming("botania:gaia1.ogg");
        this.addStreaming("botania:gaia2.ogg");
    }
}
