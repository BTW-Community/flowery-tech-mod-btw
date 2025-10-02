package vazkii.botania.common.block.decor.slabs.prismarine;

import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPrismarineSlab extends BlockLivingSlab {

	public BlockPrismarineSlab(boolean full) {
		this(full, 0);
	}

	public BlockPrismarineSlab(boolean full, int meta) {
		super(full, ModBlocks.prismarine, meta);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.prismarineSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.prismarineSlab;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.prismarine;
	}

}
