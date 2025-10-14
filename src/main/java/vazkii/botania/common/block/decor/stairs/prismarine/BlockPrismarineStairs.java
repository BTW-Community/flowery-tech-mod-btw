package vazkii.botania.common.block.decor.stairs.prismarine;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.decor.stairs.BlockLivingStairs;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPrismarineStairs extends BlockLivingStairs {

	public BlockPrismarineStairs(int id) {
		this(id, 0);
	}

	public BlockPrismarineStairs(int id, int meta) {
		super(id, ModBlocks.prismarine, meta);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z,	EntityPlayer player, ItemStack lexicon) {
		return LexiconData.prismarine;
	}

}
