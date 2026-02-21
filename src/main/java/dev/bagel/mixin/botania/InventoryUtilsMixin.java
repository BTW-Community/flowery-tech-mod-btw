package dev.bagel.mixin.botania;

import api.inventory.InventoryUtils;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(InventoryUtils.class)
public class InventoryUtilsMixin {
    /**
     * @author TheWinABagel
     * @reason The normal implementation does not check if the stack is able to be put into the slot.
     */
    @Overwrite
    private static boolean attemptToPlaceInEmptySlotInSlotRange(IInventory inventory, ItemStack stack, int iMinSlotIndex, int iMaxSlotIndex) {
        int iItemID = stack.itemID;
        int iItemDamage = stack.getItemDamage();
        int iEmptySlot = getFirstEmptyStackInSlotRange(inventory, iMinSlotIndex, iMaxSlotIndex, stack);
        while (iEmptySlot >= 0) {
            int iNumItemsToStore = stack.stackSize;
            if (iNumItemsToStore > inventory.getInventoryStackLimit()) {
                iNumItemsToStore = inventory.getInventoryStackLimit();
            }
            ItemStack newStack = new ItemStack(iItemID, iNumItemsToStore, iItemDamage);
            InventoryUtils.copyEnchantments(newStack, stack);
            if (inventory.isItemValidForSlot(iEmptySlot, newStack)) {
                inventory.setInventorySlotContents(iEmptySlot, newStack);
                stack.stackSize -= iNumItemsToStore;
            }
            if (stack.stackSize <= 0) {
                return true;
            }
            iEmptySlot = getFirstEmptyStackInSlotRange(inventory, iMinSlotIndex, iMaxSlotIndex, stack);
        }
        return false;
    }

    private static int getFirstEmptyStackInSlotRange(IInventory inventory, int iMinSlotIndex, int iMaxSlotIndex, ItemStack stack) {
        for (int iTempSlot = iMinSlotIndex; iTempSlot <= iMaxSlotIndex; ++iTempSlot) {
            if (inventory.getStackInSlot(iTempSlot) != null || !inventory.isItemValidForSlot(iTempSlot, stack))
                continue;
            return iTempSlot;
        }
        return -1;
    }
}
