/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 29, 2015, 10:13:37 PM (GMT)]
 */
package vazkii.botania.common.item.relic;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibItemNames;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class ItemThorRing extends ItemRelicBauble {

	public ItemThorRing(int id) {
		super(id, LibItemNames.THOR_RING);
		setBindAchievement(ModAchievements.relicThorRing);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public static ItemStack getThorRing(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isThorRing(stack1) ? stack1 : isThorRing(stack2) ? stack2 : null;
	}

	private static boolean isThorRing(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.thorRing || stack.getItem() == ModItems.aesirRing);
	}

}
