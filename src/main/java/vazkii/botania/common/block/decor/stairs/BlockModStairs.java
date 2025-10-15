package vazkii.botania.common.block.decor.stairs;

import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;


public class BlockModStairs extends BlockStairs implements ILexiconable {

	public BlockModStairs(int id, Block source, int meta, String name) {
		super(id, source, meta);

		setCreativeTab(CreativeTabs.tabMisc);
		useNeighborBrightness[0] = true;
		setUnlocalizedName(name);
	}

//	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return this;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

}
