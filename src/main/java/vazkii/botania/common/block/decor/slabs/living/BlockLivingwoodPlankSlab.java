package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockLivingwoodPlankSlab extends BlockLivingSlab {

	public BlockLivingwoodPlankSlab(int id, boolean full) {
		super(id, full, ModBlocks.livingwood, 1);
		setHardness(2.0F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingwoodPlankSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingwoodPlankSlab;
	}

}
