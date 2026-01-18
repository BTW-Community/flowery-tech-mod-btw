package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(EntityPlayerMP.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer {

    public EntityPlayerMPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/InventoryPlayer;dropAllItems()V"))
    private void forge$onDeathCapture(DamageSource par1DamageSource, CallbackInfo ci, @Local(argsOnly = true) DamageSource damageSource) {
        this.setCaptureDrops(true);
        this.getCapturedDrops().clear();
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Scoreboard;func_96520_a(Lnet/minecraft/src/ScoreObjectiveCriteria;)Ljava/util/Collection;"))
    private void forge$onDeathDrop(DamageSource damageSource, CallbackInfo ci) {
        this.setCaptureDrops(false);
        onPlayerDrops(((EntityPlayer) (Object) this), damageSource, this.getCapturedDrops());
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    private void forge$onDeath(DamageSource source, CallbackInfo ci) {
        LivingDeathEvent event = new LivingDeathEvent((EntityLivingBase) (Object) this, source);
        LivingDeathEvent.EVENT.invoker().accept(event);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            ci.cancel();
        }
    }

    @Unique
    private boolean onPlayerDrops(EntityPlayer entity, DamageSource source, ArrayList<EntityItem> drops) {
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
}
