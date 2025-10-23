/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Sep 6, 2015, 4:03:56 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;

import net.minecraft.src.Material;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.tile.TileCell;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockCell extends BlockModContainer<TileCell> implements ILexiconable {

	public BlockCell(int id) {
		super(id, Material.pumpkin);
		setUnlocalizedName(LibBlockNames.CELL_BLOCK);
		setStepSound(soundClothFootstep);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public TileCell createNewTileEntityT(World world, int meta) {
		return new TileCell();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.dandelifeon;
	}

}
