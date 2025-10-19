/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 24, 2014, 4:43:47 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.bauble;

import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.lib.LibItemNames;
import baubles.api.BaubleType;

public class ItemAuraRing extends ItemBauble implements IManaGivingItem {

	public ItemAuraRing(int id, String name) {
		super(id, name);
	}

	public ItemAuraRing(int id) {
		this(id, LibItemNames.AURA_RING);
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		super.onWornTick(stack, player);
		if(player instanceof EntityPlayer && player.ticksExisted % getDelay() == 0)
			ManaItemHandler.dispatchManaExact(stack, (EntityPlayer) player, 1, true);
	}

	int getDelay() {
		return 10;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

}
