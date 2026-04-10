package dev.bagel.mixin;

import net.minecraft.src.EntityLivingBase;
import org.spongepowered.asm.mixin.gen.Accessor;

@org.spongepowered.asm.mixin.Mixin(net.minecraft.src.EntityThrowable.class)
public interface EntityThrowableAccessor {
    @Accessor
    void setThrower(EntityLivingBase thrower);
}
