package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {
    @Unique
    private boolean isCancelled = false;

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "damageEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/DamageSource;isUnblockable()Z"), cancellable = true)
    private void forge$onLivingHurt(DamageSource damageSource, float damage, CallbackInfo ci) {
        if (isCancelled) {
            isCancelled = false;
            ci.cancel();
        }
    }
    
    @ModifyVariable(method = "damageEntity", argsOnly = true, at = @At("HEAD"))
    private float test(float damage, @Local(argsOnly = true) DamageSource damageSource) {
        if (this.isEntityInvulnerable()) return 0;
        LivingHurtEvent event = new LivingHurtEvent((EntityLivingBase) (Object) this, damageSource, damage);
        LivingHurtEvent.EVENT.invoker().onLivingHurt(event);
        if (event.isCanceled() || event.ammount <= 0) {
            isCancelled = true;
            return 0;
        }
        return event.ammount;
    }
}
