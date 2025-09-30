package vazkii.botania.common.item.block;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.recipe.IElvenItem;

public class ItemBlockDreamwood extends ItemBlockWithMetadataAndName implements IElvenItem {

	public ItemBlockDreamwood(Block block) {
		super(block);
	}

	@Override
	public boolean isElvenItem(ItemStack stack) {
		return true;
	}

}
