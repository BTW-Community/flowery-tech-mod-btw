package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.equipment.bauble.ItemTravelBelt;

import java.util.ArrayList;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
    public EntityLivingBaseMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public abstract ItemStack getHeldItem();

    @Shadow
    protected int recentlyHit;

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    private void forge$onUpdate(final CallbackInfo ci) {
        if (LivingEvent.LivingUpdateEvent.EVENT.invoker().onLivingUpdate(new LivingEvent.LivingUpdateEvent(((EntityLivingBase) (Object) this)))) {
            ci.cancel();
        }
    }

    @Inject(method = "swingItem", at = @At("HEAD"), cancellable = true)
    private void forge$onSwing(final CallbackInfo ci) {
        ItemStack stack = this.getHeldItem();

        if (stack != null && stack.getItem() != null) {
            Item item = stack.getItem();
            if (item.onEntitySwing((EntityLivingBase) (Object) this, stack)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "jump", at = @At("TAIL"))
    private void forge$onJump(final CallbackInfo ci) {
        ItemTravelBelt.onPlayerJump(new LivingEvent.LivingJumpEvent((EntityLivingBase) (Object) this));
    }

    @Inject(method = "entityLivingOnDeath", at = @At(opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/src/DamageSource;inWall:Lnet/minecraft/src/DamageSource;", value = "FIELD"))
    private void forge$onDeathCapture(DamageSource source, CallbackInfo ci) {
        ((Entity) (Object) this).setCaptureDrops(true);
        ((Entity) (Object) this).getCapturedDrops().clear();
    }

    @Inject(method = "entityLivingOnDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLiving;getLeashed()Z"))
    private void forge$onDeathDrop(DamageSource source, CallbackInfo ci, @Local int looting) {
        this.setCaptureDrops(false);
        if (!onLivingDrops(((EntityLivingBase) (Object) this), source, this.getCapturedDrops(), looting, this.recentlyHit > 0, this.rand.nextInt(200) - looting)) {
            for (EntityItem item : this.getCapturedDrops()) {
                worldObj.spawnEntityInWorld(item);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    private void forge$onDeath(DamageSource source, CallbackInfo ci) {
        LivingDeathEvent event = new LivingDeathEvent((EntityLivingBase) (Object) this, source);
        LivingDeathEvent.EVENT.invoker().accept(event);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            ci.cancel();
        }
    }

    private boolean onLivingDrops(EntityLivingBase entity, DamageSource source, ArrayList<EntityItem> drops, int lootingLevel, boolean recentlyHit, int specialDropValue) {
        var event = new LivingDropsEvent(entity, source, drops, lootingLevel, recentlyHit, specialDropValue);
        LivingDropsEvent.LIVING_DROPS.invoker().onLivingDropsEvent(new LivingDropsEvent(entity, source, drops, lootingLevel, recentlyHit, specialDropValue));
        return event.isCanceled();
    }
}
