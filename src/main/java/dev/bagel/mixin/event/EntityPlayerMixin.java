package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

import static dev.bagel.util.Persisted.PERSISTED_NBT_TAG;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {
    @Shadow
    protected abstract void joinEntityItemWithWorld(EntityItem par1EntityItem);

    @Shadow private ItemStack itemInUse;
    @Unique
    private boolean isCancelled = false;

    @Unique
    private int itemInUseCount;

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


    @Redirect(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/InventoryPlayer;dropAllItems()V"))
    private void forge$onDeathCapture(InventoryPlayer instance, @Local(argsOnly = true) DamageSource damageSource) {
        this.setCaptureDrops(true);
        this.getCapturedDrops().clear();
        instance.dropAllItems();
        this.setCaptureDrops(false);
        if (!onPlayerDrops(((EntityPlayer) (Object) this), damageSource, this.getCapturedDrops())) {
            for (EntityItem item : this.getCapturedDrops()) {
                joinEntityItemWithWorld(item);
            }
        }
    }

    @Inject(method = "dropPlayerItemWithRandomChoice", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayer;joinEntityItemWithWorld(Lnet/minecraft/src/EntityItem;)V"), cancellable = true)
    private void forge$onDropItem(ItemStack par1ItemStack, boolean par2, CallbackInfoReturnable<EntityItem> cir, @Local EntityItem entityItem) {
        if (this.getCaptureDrops()) {
            this.getCapturedDrops().add((EntityItem) entityItem);
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "clonePlayer", at = @At("TAIL"))
    private void clone(EntityPlayer player, boolean playerLeavingTheEnd, CallbackInfo ci) {
        NBTTagCompound old = player.getEntityData();
        if (old.hasKey(PERSISTED_NBT_TAG)) {
            getEntityData().setTag(PERSISTED_NBT_TAG, old.getCompoundTag(PERSISTED_NBT_TAG));
        }
    }

    @Unique
    public boolean onPlayerDrops(EntityPlayer entity, DamageSource source, ArrayList<EntityItem> drops) {
        PlayerDropsEvent event = new PlayerDropsEvent(entity, source, drops, this.recentlyHit > 0);
        PlayerDropsEvent.PLAYER_DROPS.invoker().onLivingDropsEvent(event);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            for (EntityItem item : drops) {
                joinEntityItemWithWorld(item);
            }
            return false;
        }
        return true;
    }

    @ModifyReturnValue(method = "getItemIcon", at = @At("RETURN"))
    private Icon getItemIcon(Icon original, ItemStack stack, int pass) {
        return stack.getItem().getIcon(stack, pass, ((EntityPlayer) (Object) this), this.itemInUse, itemInUseCount);
    }
}
