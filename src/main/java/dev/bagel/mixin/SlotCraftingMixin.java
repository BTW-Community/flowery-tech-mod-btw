package dev.bagel.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlotCrafting.class)
public abstract class SlotCraftingMixin extends Slot {

    public SlotCraftingMixin(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Redirect(method = "onPickupFromSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;hasContainerItem()Z"))
    private boolean forge$hasContainerItem(Item instance, @Local(ordinal = 1) ItemStack stack) {
        return instance.hasContainerItem(stack);
    }

    @Redirect(method = "onPickupFromSlot", at = @At(value = "NEW", target = "net/minecraft/src/ItemStack"))
    private ItemStack forge$getContainerItem(Item instance, @Local(ordinal = 1) ItemStack stack) {
        return stack.getItem().getContainerItem(stack);
    }
}
