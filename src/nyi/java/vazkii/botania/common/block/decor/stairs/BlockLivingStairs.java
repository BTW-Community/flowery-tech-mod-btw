package vazkii.botania.common.block.decor.stairs;

import net.minecraft.src.Block;

public class BlockLivingStairs extends BlockModStairs {

	public BlockLivingStairs(Block source, int meta) {
		super(source, meta, source.getUnlocalizedName().replaceAll("tile.", "") + meta + "Stairs");
	}

}
