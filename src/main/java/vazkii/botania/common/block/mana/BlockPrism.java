/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 17, 2015, 7:16:48 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.Random;

import net.minecraft.src.*;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.mana.TilePrism;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockPrism extends BlockModContainer<TilePrism> implements IManaTrigger, ILexiconable {

	Random random;
	Icon[] icons;

	public BlockPrism(int id) {
		super(id, Material.glass);
		setHardness(0.3F);
		setStepSound(soundGlassFootstep);
		setLightValue(1.0F);
		setUnlocalizedName(LibBlockNames.PRISM);
		float f = 0.25F;
		initBlockBounds(f, 0F, f, 1F - f, 1F, 1F - f);

		random = new Random();
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side > 1 ? icons[1] : icons[0];
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

//	@Override
//	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
//		return false;
//	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if(!(tile instanceof TilePrism prism))
			return false;

        ItemStack lens = prism.getStackInSlot(0);
		ItemStack heldItem = par5EntityPlayer.getCurrentEquippedItem();
		boolean isHeldItemLens = heldItem != null && heldItem.getItem() instanceof ILens;
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if(lens == null && isHeldItemLens) {
			if(!par5EntityPlayer.capabilities.isCreativeMode)
				par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);

			prism.setInventorySlotContents(0, heldItem.copy());
			prism.onInventoryChanged();
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 1, 1 | 2);
		} else if(lens != null) {
			ItemStack add = lens.copy();
			if(!par5EntityPlayer.inventory.addItemStackToInventory(add))
				par5EntityPlayer.dropPlayerItemWithRandomChoice(add, false);
			prism.setInventorySlotContents(0, null);
			prism.onInventoryChanged();
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta & 14, 1 | 2);
		}

		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int block) {
		boolean power = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
		int meta = world.getBlockMetadata(x, y, z);
		boolean powered = (meta & 8) != 0;

		if(!world.isRemote) {
			if(power && !powered)
				world.setBlockMetadataWithNotify(x, y, z, meta | 8, 1 | 2);
			else if(!power && powered)
				world.setBlockMetadataWithNotify(x, y, z, meta & -9, 1 | 2);
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
        super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public TilePrism createNewTileEntityT(World world) {
		return new TilePrism();
	}

	@Override
	public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TilePrism)
			((TilePrism) tile).onBurstCollision(burst);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.prism;
	}

}
