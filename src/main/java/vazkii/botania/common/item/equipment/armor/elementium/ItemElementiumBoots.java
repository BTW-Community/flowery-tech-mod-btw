package vazkii.botania.common.item.equipment.armor.elementium;

import net.minecraft.src.ItemStack;
import vazkii.botania.common.lib.LibItemNames;

public class ItemElementiumBoots extends ItemElementiumArmor {

	public ItemElementiumBoots(int id) {
		super(id, 3, LibItemNames.ELEMENTIUM_BOOTS);
	}

	@Override
	public float getPixieChance(ItemStack stack) {
		return 0.09F;
	}

}
