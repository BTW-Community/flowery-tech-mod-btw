package vazkii.botania.common.block.decor.slabs.prismarine;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModFluffBlocks;

public class BlockDarkPrismarineSlab extends BlockPrismarineSlab {

	public BlockDarkPrismarineSlab(int id, boolean full) {
		super(id, full, 2);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.darkPrismarineSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.darkPrismarineSlab;
	}

}
