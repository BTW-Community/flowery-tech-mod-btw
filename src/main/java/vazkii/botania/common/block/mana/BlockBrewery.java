/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Oct 31, 2014, 4:37:29 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileBrewery;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockBrewery extends BlockModContainer<TileBrewery> implements ILexiconable, IWandHUD {

	Random random;

	public BlockBrewery(int id) {
		super(id, Material.rock);
		float f = 6F / 16F;
		initBlockBounds(f, 0.05F, f, 1F - f, 0.95F, 1F - f);
		setUnlocalizedName(LibBlockNames.BREWERY);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);

		random = new Random();
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return Block.cobblestone.getIcon(side, meta);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileBrewery brew = (TileBrewery) par1World.getTileEntity(par2, par3, par4);

		if(par5EntityPlayer.isSneaking()) {
			if(brew.recipe == null && par1World.getBlockMetadata(par2, par3, par4) == 0)
				for(int i = brew.getSizeInventory() - 1; i >= 0; i--) {
					ItemStack stackAt = brew.getStackInSlot(i);
					if(stackAt != null) {
						ItemStack copy = stackAt.copy();
						if(!par5EntityPlayer.inventory.addItemStackToInventory(copy))
							par5EntityPlayer.dropPlayerItemWithRandomChoice(copy, false);
						brew.setInventorySlotContents(i, null);
						par1World.func_96440_m(par2, par3, par4, this.blockID);
						break;
					}
				}
		} else {
			ItemStack stack = par5EntityPlayer.getCurrentEquippedItem();
			if(stack != null)
				return brew.addItem(par5EntityPlayer, stack);
		}
		return false;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileBrewery brew = (TileBrewery) par1World.getTileEntity(par2, par3, par4);
		return brew.signal;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idBrewery;
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
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		return false;
	}

	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
	}

	@Override
	public TileBrewery createNewTileEntityT(World world, int meta) {
		return new TileBrewery();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.brewery;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileBrewery) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

}
