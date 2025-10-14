/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 28, 2014, 10:19:46 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs.bricks;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModFluffBlocks;

public class BlockSoulBrickSlab extends BlockCustomBrickSlab {

	public BlockSoulBrickSlab(int id, boolean full) {
		super(id, full, 1);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.soulBrickSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.soulBrickSlab;
	}

}
