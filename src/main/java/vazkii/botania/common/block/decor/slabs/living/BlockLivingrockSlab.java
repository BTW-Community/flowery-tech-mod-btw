package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.src.BlockHalfSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockLivingrockSlab extends BlockLivingSlab {

	public BlockLivingrockSlab(int id, boolean full) {
		super(id, full, ModBlocks.livingrock, 0);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingrockSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.livingrockSlab;
	}

}
