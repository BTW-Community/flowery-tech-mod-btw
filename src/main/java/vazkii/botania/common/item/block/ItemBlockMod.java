/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 16, 2014, 7:00:36 PM (GMT)]
 */
package vazkii.botania.common.item.block;

import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public class ItemBlockMod extends ItemBlock implements IPickupAchievement, ICraftAchievement {

	public ItemBlockMod(Block block) {
		super(block.blockID - 256);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return getUnlocalizedNameInefficiently_(par1ItemStack).replaceAll("tile.", "tile." + LibResources.PREFIX_MOD);
	}

	public String getUnlocalizedNameInefficiently_(ItemStack stack) {
		return super.getUnlocalizedNameInefficiently(stack);
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return Block.blocksList[blockID] instanceof ICraftAchievement pa ? pa.getAchievementOnCraft(stack, player, matrix) : null;
	}

	@Override
	public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
		return Block.blocksList[blockID] instanceof IPickupAchievement ca ? ca.getAchievementOnPickup(stack, player, item) : null;
	}

	@Override
	public String getModId() {
		return "botania";
	}
}
