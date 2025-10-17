package dev.bagel.mixin.event;

import net.minecraft.src.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    private void forge$onUpdate(final CallbackInfo ci) {
        if (LivingEvent.LivingUpdateEvent.EVENT.invoker().onLivingUpdate(new LivingEvent.LivingUpdateEvent(((EntityLivingBase) (Object) this)))) {
            ci.cancel();
        }
    }
}
