package vazkii.botania.common.block.decor.slabs;

import java.util.Random;

import net.minecraft.src.BlockSlab;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockModSlab;
import vazkii.botania.common.lexicon.LexiconData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public abstract class BlockModSlab extends BlockSlab implements ILexiconable {

	String name;

	public BlockModSlab(boolean full, Material mat, String name) {
		super(full, mat);
		this.name = name;
		setBlockName(name);
		if(!full) {
			setCreativeTab(BotaniaCreativeTab.INSTANCE);
			useNeighborBrightness = true;
		}
	}

	public abstract BlockSlab getFullBlock();

	public abstract BlockSlab getSingleBlock();

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(getSingleBlock());
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return super.quantityDropped(meta, fortune, random);
	}

	@Override
	public ItemStack createStackedBlock(int par1) {
		return new ItemStack(getSingleBlock());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerBlockIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	public void register() {
		GameRegistry.registerBlock(this, ItemBlockModSlab.class, name);
	}

	@Override
	public String func_150002_b(int i) {
		return name;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

}
