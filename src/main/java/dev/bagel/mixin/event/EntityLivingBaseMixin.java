package dev.bagel.mixin.event;

import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow public abstract ItemStack getHeldItem();

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    private void forge$onUpdate(final CallbackInfo ci) {
        if (LivingEvent.LivingUpdateEvent.EVENT.invoker().onLivingUpdate(new LivingEvent.LivingUpdateEvent(((EntityLivingBase) (Object) this)))) {
            ci.cancel();
        }
    }

    @Inject(method = "swingItem", at = @At("HEAD"), cancellable = true)
    private void forge$onSwing(final CallbackInfo ci) {
        ItemStack stack = this.getHeldItem();

        if (stack != null && stack.getItem() != null)
        {
            Item item = stack.getItem();
            if (item.onEntitySwing((EntityLivingBase) (Object) this, stack))
            {
                ci.cancel();
            }
        }
    }
}
