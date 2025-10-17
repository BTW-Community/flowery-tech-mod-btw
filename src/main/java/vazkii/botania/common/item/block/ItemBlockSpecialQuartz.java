/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 11, 2014, 1:13:36 AM (GMT)]
 */
package vazkii.botania.common.item.block;

import net.minecraft.src.Block;
import net.minecraft.src.ItemMultiTextureTile;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartz;

public class ItemBlockSpecialQuartz extends ItemMultiTextureTile {

	public ItemBlockSpecialQuartz(Block par1) {
		super(par1.blockID - 256, par1, new String[]{ "" });
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() >= 3 ? "" : ((BlockSpecialQuartz) Block.blocksList[blockID]).getNames()[par1ItemStack.getItemDamage()];
	}

	@Override
	public String getModId() {
		return "botania";
	}
}