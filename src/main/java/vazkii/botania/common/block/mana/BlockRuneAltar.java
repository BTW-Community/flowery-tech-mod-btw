/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Feb 2, 2014, 2:10:14 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockRuneAltar extends BlockModContainer<TileRuneAltar> implements IWandable, ILexiconable {

	Random random;
	Icon[] icons;

	public BlockRuneAltar(int id) {
		super(id, Material.rock);
		initBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.RUNE_ALTAR);

		BotaniaAPI.blacklistBlockFromMagnet(this, Short.MAX_VALUE);

		random = new Random();
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
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[3];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileRuneAltar altar = (TileRuneAltar) par1World.getTileEntity(par2, par3, par4);
		ItemStack stack = par5EntityPlayer.getCurrentEquippedItem();

		if(par5EntityPlayer.isSneaking()) {
			if(altar.manaToGet == 0)
				for(int i = altar.getSizeInventory() - 1; i >= 0; i--) {
					ItemStack stackAt = altar.getStackInSlot(i);
					if(stackAt != null) {
						ItemStack copy = stackAt.copy();
						if(!par5EntityPlayer.inventory.addItemStackToInventory(copy))
							par5EntityPlayer.dropPlayerItemWithRandomChoice(copy, false);
						altar.setInventorySlotContents(i, null);
						par1World.func_96440_m(par2, par3, par4, this.blockID);
						break;
					}
				}
		} else if(altar.isEmpty() && stack == null)
			altar.trySetLastRecipe(par5EntityPlayer);
		else if(stack != null)
			return altar.addItem(par5EntityPlayer, stack);
		return false;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(2, par1)];
	}

	@Override
	public TileRuneAltar createNewTileEntityT(World world) {
		return new TileRuneAltar();
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileRuneAltar altar = (TileRuneAltar) par1World.getTileEntity(par2, par3, par4);
		return altar.signal;
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		((TileRuneAltar) world.getTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.runicAltar;
	}

}
