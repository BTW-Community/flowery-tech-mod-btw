/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 18, 2014, 7:58:08 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;


public class BlockTinyPotato extends BlockModContainer implements ILexiconable {

	public BlockTinyPotato(int id) {
		super(id, Material.cloth);
		setHardness(0.25F);
		setBlockName(LibBlockNames.TINY_POTATO);
		float f = 1F / 16F * 6F;
		setBlockBounds(f, 0, f, 1F - f, f, 1F - f);
	}

	@Override
	public Block setBlockName(String par1Str) {
		var item = new ItemBlockTinyPotato(this);
//		GameRegistry.registerBlock(this, ItemBlockTinyPotato.class, par1Str);
		return super.setBlockName(par1Str);
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return Block.hardenedClay.getIcon(0, 0);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if(tile instanceof TileTinyPotato) {
			((TileTinyPotato) tile).interact();
			par5EntityPlayer.addStat(ModAchievements.tinyPotatoPet, 1);
			par1World.spawnParticle("heart", par2 + minX + Math.random() * (maxX - minX), par3 + maxY, par4 + minZ + Math.random() * (maxZ - minZ), 0, 0 ,0);
		}
		return true;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int l1 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		par1World.setBlockMetadataWithNotify(par2, par3, par4, l1, 2);
		if (par6ItemStack.hasDisplayName())
			((TileTinyPotato) par1World.getTileEntity(par2, par3, par4)).name = par6ItemStack.getDisplayName();
	}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		if(!par6EntityPlayer.capabilities.isCreativeMode)
			dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<>();
		TileEntity tile = world.getTileEntity(x, y, z);

		if(tile != null) {
			ItemStack stack = new ItemStack(this);
			String name = ((TileTinyPotato) tile).name;
			if(!name.isEmpty()) {
				stack.setItemName(name);
			}
			list.add(stack);
		}

		return list;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idTinyPotato;
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileTinyPotato();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.tinyPotato;
	}
}
