/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Oct 11, 2015, 3:47:47 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockShimmerwoodPlankSlab extends BlockLivingSlab {

	public BlockShimmerwoodPlankSlab(int id, boolean full) {
		super(id, full, ModBlocks.shimmerwoodPlanks, 0);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundWoodFootstep);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.shimmerwoodPlankSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.shimmerwoodPlankSlab;
	}
}
