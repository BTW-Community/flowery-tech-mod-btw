/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 11, 2014, 1:08:28 AM (GMT)]
 */
package vazkii.botania.common.block.decor.quartz;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import vazkii.botania.common.lexicon.LexiconData;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockSpecialQuartzSlab extends BlockModSlab {

	Block source;

	public BlockSpecialQuartzSlab(Block source, boolean par2) {
		super(par2, Material.rock, "quartzSlab" + ((BlockSpecialQuartz) source).type + (par2 ? "Full" : "Half"));
		setHardness(0.8F);
		setResistance(10F);
		this.source = source;
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		if(source == ModFluffBlocks.darkQuartz)
			return (BlockHalfSlab) ModFluffBlocks.darkQuartzSlabFull;
		if(source == ModFluffBlocks.manaQuartz)
			return (BlockHalfSlab) ModFluffBlocks.manaQuartzSlabFull;
		if(source == ModFluffBlocks.blazeQuartz)
			return (BlockHalfSlab) ModFluffBlocks.blazeQuartzSlabFull;
		if(source == ModFluffBlocks.lavenderQuartz)
			return (BlockHalfSlab) ModFluffBlocks.lavenderQuartzSlabFull;
		if(source == ModFluffBlocks.redQuartz)
			return (BlockHalfSlab) ModFluffBlocks.redQuartzSlabFull;
		if(source == ModFluffBlocks.elfQuartz)
			return (BlockHalfSlab) ModFluffBlocks.elfQuartzSlabFull;
		if(source == ModFluffBlocks.sunnyQuartz)
			return (BlockHalfSlab) ModFluffBlocks.sunnyQuartzSlabFull;

		return this;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		if(source == ModFluffBlocks.darkQuartz)
			return (BlockHalfSlab) ModFluffBlocks.darkQuartzSlab;
		if(source == ModFluffBlocks.manaQuartz)
			return (BlockHalfSlab) ModFluffBlocks.manaQuartzSlab;
		if(source == ModFluffBlocks.blazeQuartz)
			return (BlockHalfSlab) ModFluffBlocks.blazeQuartzSlab;
		if(source == ModFluffBlocks.lavenderQuartz)
			return (BlockHalfSlab) ModFluffBlocks.lavenderQuartzSlab;
		if(source == ModFluffBlocks.redQuartz)
			return (BlockHalfSlab) ModFluffBlocks.redQuartzSlab;
		if(source == ModFluffBlocks.elfQuartz)
			return (BlockHalfSlab) ModFluffBlocks.elfQuartzSlab;
		if(source == ModFluffBlocks.sunnyQuartz)
			return (BlockHalfSlab) ModFluffBlocks.sunnyQuartzSlab;

		return this;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return source.getBlockTextureFromSide(par1);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.getItemFromBlock(getSingleBlock());
	}

	@Override
	public ItemStack createStackedBlock(int par1) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return this == ModFluffBlocks.elfQuartzSlab ? LexiconData.elvenResources : LexiconData.decorativeBlocks;
	}

}
