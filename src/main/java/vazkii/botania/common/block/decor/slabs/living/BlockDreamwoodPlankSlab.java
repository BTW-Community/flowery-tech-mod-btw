package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockDreamwoodPlankSlab extends BlockLivingSlab {

	public BlockDreamwoodPlankSlab(boolean full) {
		super(full, ModBlocks.dreamwood, 1);
		setHardness(2.0F);
	}

	@Override
	public BlockSlab getFullBlock() {
		return (BlockSlab) ModFluffBlocks.dreamwoodPlankSlabFull;
	}

	@Override
	public BlockSlab getSingleBlock() {
		return (BlockSlab) ModFluffBlocks.dreamwoodPlankSlab;
	}

}
