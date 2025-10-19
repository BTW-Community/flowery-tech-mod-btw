/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 18, 2015, 8:15:36 PM (GMT)]
 */
package vazkii.botania.common.block.decor.walls;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.BlockWall;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;


public class BlockModWall extends BlockWall implements ILexiconable {

	Block block;
	int meta;

	public BlockModWall(int id, Block block, int meta) {
		super(id, block);
		this.block = block;
		this.meta = meta;
		setUnlocalizedName(block.getUnlocalizedName().replaceAll("tile.", "") + meta + "Wall");
	}

	// Should already be covered by hasCenterHardPointToFacing
//	@Override
//	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
//		return true;
//	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		register(par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	public void register(String name) {
		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, name);
	}

	@Override
	public void getSubBlocks(int item, CreativeTabs tabs, List list) {
		list.add(new ItemStack(Item.itemsList[item]));
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return block.getIcon(side, this.meta);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

	@Override
	public void registerIcons(IconRegister p_149651_1_) {
		// NO-OP
	}

}
