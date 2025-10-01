/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [May 14, 2014, 8:37:26 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.block.ItemBlockStorage;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockStorage extends BlockMod implements ILexiconable {

	private static final int SUBTYPES = 5;

	Icon[] icons;

	public BlockStorage() {
		super(Material.iron);
		setHardness(3F);
		setResistance(10F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockNames.STORAGE);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
		for(int i = 0; i < SUBTYPES; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public Block setBlockName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockStorage.class, par1Str);
		return super.setBlockName(par1Str);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerBlockIcons(IconRegister par1IconRegister) {
		icons = new Icon[SUBTYPES];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(icons.length - 1, par2)];
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? LexiconData.pool : LexiconData.terrasteel;
	}

}
