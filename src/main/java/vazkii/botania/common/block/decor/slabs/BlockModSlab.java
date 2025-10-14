package vazkii.botania.common.block.decor.slabs;

import java.util.Random;

import dev.bagel.util.Items;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockModSlab;
import vazkii.botania.common.lexicon.LexiconData;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public abstract class BlockModSlab extends BlockHalfSlab implements ILexiconable {

	String name;

	public BlockModSlab(int id, boolean full, Material mat, String name) {
		super(id, full, mat);
		this.name = name;
//		setUnlocalizedName(name);
		if(!full) {
			setCreativeTab(CreativeTabs.tabMisc);
			useNeighborBrightness[0] = true;
		}
	}

	public abstract BlockHalfSlab getFullBlock();

	public abstract BlockHalfSlab getSingleBlock();

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	public int idDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return new ItemStack(getSingleBlock()).itemID;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return super.quantityDropped(par1Random);
	}

	@Override
	public ItemStack createStackedBlock(int par1) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	public void register() {
		var item = new ItemBlockModSlab(0, this);
//		GameRegistry.registerBlock(this, ItemBlockModSlab.class, name);
	}

	@Override
	public String getUnlocalizedName2() {
		return name;
	}

/*	@Override
	public String func_150002_b(int i) {
		return name;
	}*/

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

}
