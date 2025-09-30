/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 27, 2014, 8:32:52 PM (GMT)]
 */
package vazkii.botania.common.item.block;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;

public class ItemBlockPool extends ItemBlockWithMetadataAndName {

	public ItemBlockPool(Block par2Block) {
		super(par2Block);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.getItemDamage() == 1)
			for(int i = 0; i < 2; i++)
				par3List.add(StatCollector.translateToLocal("botaniamisc.creativePool" + i));
	}

}
