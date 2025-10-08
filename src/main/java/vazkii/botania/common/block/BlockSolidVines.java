/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 26, 2014, 11:31:51 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.Random;

import net.minecraft.src.*;
import net.minecraft.src.Block;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;


public class BlockSolidVines extends BlockVine implements ILexiconable {

	public BlockSolidVines(int id) {
		super(id);
		setBlockName(LibBlockNames.SOLID_VINE);
		setHardness(0.5F);
		setStepSound(soundGrassFootstep);
		setTextureName("vine");
		setCreativeTab(null);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
		setBlockBoundsBasedOnState(w, x, y, z);
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}

	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
		// NO-OP
	}

//	@Override
	public Block setBlockName(String par1Str) {
		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return this;
	}

	//todofix not shearable
//	@Override
//	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
//		return false;
//	}
	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		par2EntityPlayer.addHarvestBlockExhaustion(this.blockID, par3, par4, par5, par6);
		if (this.canSilkHarvest(par6) && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer)) {
			ItemStack var8 = this.createStackedBlock(par6);
			if (var8 != null) {
				this.dropBlockAsItem_do(par1World, par3, par4, par5, var8);
			}
		} else {
			int var7 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
			this.dropBlockAsItem(par1World, par3, par4, par5, par6, var7);
		}

	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Block.vine);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.vineBall;
	}

}
