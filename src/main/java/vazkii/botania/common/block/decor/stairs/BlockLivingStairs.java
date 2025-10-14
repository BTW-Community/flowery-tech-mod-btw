package vazkii.botania.common.block.decor.stairs;

import net.minecraft.src.Block;

public class BlockLivingStairs extends BlockModStairs {

	public BlockLivingStairs(int id, Block source, int meta) {
		super(id, source, meta, source.getUnlocalizedName().replaceAll("tile.", "") + meta + "Stairs");
	}

}
