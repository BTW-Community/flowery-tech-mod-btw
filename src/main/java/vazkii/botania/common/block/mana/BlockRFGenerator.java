/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 29, 2014, 9:51:58 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import net.minecraft.src.Material;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.mana.TileRFGenerator;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockRFGenerator extends BlockModContainer implements ILexiconable {

	public BlockRFGenerator() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setBlockName(LibBlockNames.RF_GENERATOR);
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof TileRFGenerator)
			((TileRFGenerator) tile).onNeighborTileChange(tileX, tileY, tileZ);
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileRFGenerator();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.rfGenerator;
	}

}
