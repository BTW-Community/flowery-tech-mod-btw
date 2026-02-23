package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.src.*;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ItemFlowerBag;
import vazkii.botania.common.item.ModItems;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {
    @Shadow
    public abstract ItemStack getEntityItem();

    @Unique
    int lifespan = 6000;
    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "<init>(Lnet/minecraft/src/World;DDDLnet/minecraft/src/ItemStack;)V", at = @At("TAIL"))
    private void init(World worldIn, double par2, double par4, double par6, ItemStack stack, CallbackInfo ci) {
        this.lifespan = (stack.getItem() == null ? 6000 : stack.getItem().getEntityLifespan(stack, worldIn));
    }

    @ModifyConstant(method = "checkForItemDespawn", constant = @Constant(intValue = 6000))
    private int checkForItemDespawn(int original) {
        var stack = this.getEntityItem();
        return (stack.getItem() == null ? 6000 : stack.getItem().getEntityLifespan(stack, this.worldObj));
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void readEntityFromNBT(NBTTagCompound tagCompund, CallbackInfo ci) {
        if (tagCompund.hasKey("Lifespan"))
        {
            lifespan = tagCompund.getInteger("Lifespan");
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void writeEntityToNBT(NBTTagCompound tagCompound, CallbackInfo ci) {
        tagCompound.setInteger("Lifespan", lifespan);
    }

    @WrapOperation(method = "onCollideWithPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/InventoryPlayer;addItemStackToInventory(Lnet/minecraft/src/ItemStack;)Z"))
    private boolean onCollideWithPlayer(InventoryPlayer instance, ItemStack var2, Operation<Boolean> original) {
        var event = new EntityItemPickupEvent(instance.player, (EntityItem) (Object) this);
        ((ItemFlowerBag) ModItems.flowerBag).onPickupItem(event);
        if (event.isCanceled() || event.getResult() == Event.Result.DENY) {
            return false;
        }
        return original.call(instance, var2);
    }
}
