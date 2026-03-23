package dev.bagel.mixin;

import net.minecraft.src.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderGlobal.class)
public interface RenderGlobalAccessor {
    @Accessor
    int getGlSkyList();

    @Accessor
    int getGlSkyList2();

    @Accessor
    int getStarGLCallList();
}
