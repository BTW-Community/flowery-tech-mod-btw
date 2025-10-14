package vazkii.botania.common.item.equipment.armor.elementium;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.common.lib.LibItemNames;

public class ItemElementiumHelm extends ItemElementiumArmor implements IManaDiscountArmor {

	public ItemElementiumHelm(int id) {
		this(id, LibItemNames.ELEMENTIUM_HELM);
	}

	public ItemElementiumHelm(int id, String name) {
		super(id, 0, name);
	}

	@Override
	public float getPixieChance(ItemStack stack) {
		return 0.11F;
	}

	@Override
	public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
		return hasArmorSet(player) ? 0.1F : 0F;
	}

}
