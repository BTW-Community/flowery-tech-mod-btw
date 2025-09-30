/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 25, 2014, 10:04:25 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.block.material.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLivingwood extends BlockMod implements ILexiconable {

	private static final int TYPES = 6;
	Icon[] icons;

	public BlockLivingwood() {
		this(LibBlockNames.LIVING_WOOD);
	}

	public BlockLivingwood(String name) {
		super(Material.wood);
		setHardness(2.0F);
		setStepSound(soundTypeWood);
		setBlockName(name);
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public Block setBlockName(String par1Str) {
		register(par1Str);
		return super.setBlockName(par1Str);
	}

	void register(String name) {
		GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, name);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < TYPES; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public void registerBlockIcons(IconRegister par1IconRegister) {
		icons = new Icon[TYPES];
		for(int i = 0; i < TYPES; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(TYPES - 1, par2)];
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(this, 1, meta);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 5 ? 12 : 0;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? LexiconData.pureDaisy : LexiconData.decorativeBlocks;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 0;
	}

}
