/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Nov 14, 2014, 4:43:14 PM (GMT)]
 */
package vazkii.botania.common.block.string;

import net.minecraft.src.BlockPistonBase;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.lexicon.LexiconData;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public abstract class BlockRedString extends BlockModContainer<TileRedString> implements ILexiconable {

	Icon senderIcon;
	Icon sideIcon;

	public BlockRedString(String name) {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(name);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int orientation = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, orientation, 1 | 2);
	}

	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
		return RotationHelper.rotateVanillaBlock(Blocks.piston, worldObj, x, y, z, axis);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerBlockIcons(IconRegister par1IconRegister) {
		senderIcon = IconHelper.forName(par1IconRegister, "redStringSender");
		sideIcon = registerSideIcon(par1IconRegister);
	}

	@Environment(EnvType.CLIENT)
	public Icon registerSideIcon(IconRegister register) {
		return IconHelper.forBlock(register, this);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side == meta ? senderIcon : sideIcon;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.redString;
	}

}
