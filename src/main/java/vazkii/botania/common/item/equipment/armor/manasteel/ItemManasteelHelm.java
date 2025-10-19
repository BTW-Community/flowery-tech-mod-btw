/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 13, 2014, 10:22:24 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.armor.manasteel;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.common.lib.LibItemNames;

public class ItemManasteelHelm extends ItemManasteelArmor implements IManaDiscountArmor {

	public ItemManasteelHelm(int id) {
		this(id, LibItemNames.MANASTEEL_HELM);
	}

	public ItemManasteelHelm(int id, String name) {
		super(id, 0, name);
	}

	@Override
	public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
		return hasArmorSet(player) ? 0.1F : 0F;
	}

}
