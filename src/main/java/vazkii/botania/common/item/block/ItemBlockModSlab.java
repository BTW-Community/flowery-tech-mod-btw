/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 11, 2014, 1:16:59 AM (GMT)]
 */
package vazkii.botania.common.item.block;

import net.minecraft.src.Block;
import net.minecraft.src.ItemSlab;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;

public class ItemBlockModSlab extends ItemSlab {

	public ItemBlockModSlab(int id, Block par1) {
		super(id, ((BlockModSlab)par1).getSingleBlock(), ((BlockModSlab)par1).getFullBlock(), false);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return Block.blocksList[getBlockID()].getUnlocalizedName().replaceAll("tile.", "tile.botania:");
	}

}
