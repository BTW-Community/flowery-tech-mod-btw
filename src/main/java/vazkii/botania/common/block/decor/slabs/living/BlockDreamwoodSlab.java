package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockDreamwoodSlab extends BlockLivingSlab {

	public BlockDreamwoodSlab(int id, boolean full) {
		super(id, full, ModBlocks.dreamwood, 0);
		setHardness(2.0F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.dreamwoodSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.dreamwoodSlab;
	}

}
