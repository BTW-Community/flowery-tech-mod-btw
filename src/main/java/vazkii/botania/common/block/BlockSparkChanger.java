/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jun 28, 2015, 10:01:01 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.block.tile.TileSparkChanger;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockSparkChanger extends BlockModContainer<TileSparkChanger> implements ILexiconable {

	Icon[] icons;
	Random random;

	public BlockSparkChanger(int id) {
		super(id, Material.rock);
		initBlockBounds(0F, 0F, 0F, 1F, 3F / 16F, 1F);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.SPARK_CHANGER);

		random = new Random();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[3];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(2, par1)];
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int block) {
		boolean power = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
		int meta = world.getBlockMetadata(x, y, z);
		boolean powered = (meta & 8) != 0;

		if(power && !powered) {
			((TileSparkChanger) world.getTileEntity(x, y, z)).doSwap();
			world.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
		} else if(!power && powered)
			world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
		TileSparkChanger changer = (TileSparkChanger) world.getTileEntity(x, y, z);
		ItemStack cstack = changer.getStackInSlot(0);
		ItemStack pstack = player.getCurrentEquippedItem();
		if(cstack != null) {
			changer.setInventorySlotContents(0, null);
			world.func_96440_m(x, y, z, this.blockID);
			changer.onInventoryChanged();
			if(!player.inventory.addItemStackToInventory(cstack))
				player.dropPlayerItemWithRandomChoice(cstack, false);
			return true;
		} else if(pstack != null && pstack.getItem() == ModItems.sparkUpgrade) {
			changer.setInventorySlotContents(0, pstack.copy().splitStack(1));
			world.func_96440_m(x, y, z, this.blockID);
			changer.onInventoryChanged();

			pstack.stackSize--;
			if(pstack.stackSize == 0)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

			return true;
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
	public int getComparatorInputOverride(World world, int x, int y, int z, int s) {
		TileSparkChanger changer = (TileSparkChanger) world.getTileEntity(x, y, z);
		ItemStack stack = changer.getStackInSlot(0);
		if(stack == null)
			return 0;
		return stack.getItemDamage() + 1;
	}

	@Override
	public TileSparkChanger createNewTileEntityT(World world) {
		return new TileSparkChanger();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.sparkChanger;
	}

}
