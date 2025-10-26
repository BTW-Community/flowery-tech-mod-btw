/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Sep 23, 2015, 11:44:35 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockSkull;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySkull;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lib.LibBlockNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockGaiaHead extends BlockSkull {

	public BlockGaiaHead(int id) {
		super(id);
		setUnlocalizedName(LibBlockNames.GAIA_HEAD);
		setHardness(1.0F);
		var item = new ItemBlockMod(this);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
//		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	//todofix getItem
//	@Override
//	@Environment(EnvType.CLIENT)
//	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
//		return ModItems.gaiaHead;
//	}

	@Override
	public void registerIcons(IconRegister p_149651_1_) {
		// NO-OP
	}

	@Override
	public ArrayList<ItemStack> getDrops(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, int p_149749_6_, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if((p_149749_6_ & 8) == 0) {
			ItemStack itemstack = new ItemStack(ModItems.gaiaHead, 1);
			TileEntitySkull tileentityskull = (TileEntitySkull)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

			if(tileentityskull == null)
				return ret;

			ret.add(itemstack);
		}
		return ret;
	}

	@Override
	public int idDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return ModItems.gaiaHead.itemID;
	}

	@Override
	public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)  {
		return 0;
	}

	@Override
	public int damageDropped(int p_149692_1_) {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_) {
		return new TileGaiaHead();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int p_149691_1_, int p_149691_2_) {
		return Block.coalBlock.getBlockTextureFromSide(p_149691_1_);
	}

}
