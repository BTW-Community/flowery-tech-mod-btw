package baubles.common.container;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.Baubles;
import baubles.common.ItemDebugger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Slot;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;

public class SlotBauble extends Slot {

	private final String slotType;

	@Deprecated
    public SlotBauble(IInventory inventory, BaubleType legacyType, int slot, int x, int y) {
        super(inventory, slot, x, y);
		slotType = BaubleExpandedSlots.getTypeFromBaubleType(legacyType);

    }

    public SlotBauble(IInventory inventory, String type, int slot, int x, int y) {
        super(inventory, slot, x, y);
        if(type == null) {
        	slotType = BaubleExpandedSlots.unknownType;
        } else {
        	slotType = type;
        }
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack stack) {
    	if (stack == null) {
			return false;
		}

		Item item = stack.getItem();
		if(!(item instanceof IBauble bauble) || !bauble.canEquip(stack, ((InventoryBaubles) inventory).player)) {
			return false;
		}

        String[] types;
        if(item instanceof IBaubleExpanded expanded) {
            types = expanded.getBaubleTypes(stack);
        } else {
            BaubleType legacyType = bauble.getBaubleType(stack);
            types = new String[] {BaubleExpandedSlots.getTypeFromBaubleType(legacyType)};
        }

        for(String type : types) {
            if(type.equals(BaubleExpandedSlots.universalType) || type.equals(slotType)) {
                return true;
            }
        }

		return false;
    }

	@Override
	public boolean canTakeStack(EntityPlayer player) {
		ItemStack itemStack = getStack();
		return itemStack != null && ((IBauble)itemStack.getItem()).canUnequip(itemStack, player);
	}

	@Override
    public int getSlotStackLimit() {
        return 1;
    }

	@Override
	@Environment(EnvType.CLIENT)
    public Icon getBackgroundIconIndex() {
        return ((ItemDebugger) Baubles.itemDebugger).getBackgroundIconForSlotType(slotType);
    }

}
