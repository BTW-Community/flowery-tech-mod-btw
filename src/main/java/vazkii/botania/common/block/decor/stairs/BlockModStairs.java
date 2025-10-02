package vazkii.botania.common.block.decor.stairs;

import net.minecraft.src.Block;
import net.minecraft.src.BlockStairs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;


public class BlockModStairs extends BlockStairs implements ILexiconable {

	public BlockModStairs(Block source, int meta, String name) {
		super(source, meta);
		setBlockName(name);
		setCreativeTab(CreativeTabs.tabMisc);
		useNeighborBrightness = true;
	}

	@Override
	public Block setBlockName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setBlockName(par1Str);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

}
