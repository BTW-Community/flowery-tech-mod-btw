/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 28, 2014, 11:49:42 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs.bricks;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModFluffBlocks;

public class BlockTileSlab extends BlockCustomBrickSlab {

	public BlockTileSlab(int id, boolean full) {
		super(id, full, 3);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.tileSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.tileSlab;
	}

}
