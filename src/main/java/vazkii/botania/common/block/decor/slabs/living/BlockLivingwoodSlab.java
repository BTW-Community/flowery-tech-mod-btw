package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockLivingwoodSlab extends BlockLivingSlab {

	public BlockLivingwoodSlab(boolean full) {
		super(full, ModBlocks.livingwood, 0);
		setHardness(2.0F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingwoodSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingwoodSlab;
	}

}
