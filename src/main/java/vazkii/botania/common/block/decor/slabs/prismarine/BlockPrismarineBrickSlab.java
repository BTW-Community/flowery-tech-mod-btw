package vazkii.botania.common.block.decor.slabs.prismarine;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModFluffBlocks;

public class BlockPrismarineBrickSlab extends BlockPrismarineSlab {

	public BlockPrismarineBrickSlab(boolean full) {
		super(full, 1);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.prismarineBrickSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.prismarineBrickSlab;
	}

}
