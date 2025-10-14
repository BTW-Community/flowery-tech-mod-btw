/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 15, 2014, 4:08:26 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.Random;

import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockEnchanter extends BlockModContainer implements IWandable, ILexiconable, IWandHUD {

	Random random;
	public static Icon overlay;

	public BlockEnchanter(int id) {
		super(id, Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setLightValue(1.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.ENCHANTER);

		random = new Random();
	}

	@Override
	public boolean registerInCreative() {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		overlay = IconHelper.forBlock(par1IconRegister, this, "Overlay");
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileEnchanter();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return new ItemStack(Block.blockLapis).itemID;
	}

//	@Override
//	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
//		return Items.getItemFromBlock(Block.blockLapis);
//	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEnchanter enchanter = (TileEnchanter) par1World.getTileEntity(par2, par3, par4);
		ItemStack stack = par5EntityPlayer.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == ModItems.twigWand)
			return false;

		boolean stackEnchantable = stack != null && stack.getItem() != Item.book && stack.isItemEnchantable() && stack.stackSize == 1 && stack.getItem().getItemEnchantability(/*stack*/) > 0;

		if(enchanter.itemToEnchant == null) {
			if(stackEnchantable) {
				enchanter.itemToEnchant = stack.copy();
				par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
				enchanter.sync();
			}
		} else if(enchanter.stage == 0) {
			if(par5EntityPlayer.inventory.addItemStackToInventory(enchanter.itemToEnchant.copy())) {
				enchanter.itemToEnchant = null;
				enchanter.sync();
			} else par5EntityPlayer.addChatMessage(StatCollector.translateToLocal("botaniamisc.invFull"));
		}

		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileEnchanter enchanter = (TileEnchanter) par1World.getTileEntity(par2, par3, par4);

		ItemStack itemstack = enchanter.itemToEnchant;

		if (itemstack != null) {
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			EntityItem entityitem;

			for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem)) {
				int k1 = random.nextInt(21) + 10;

				if (k1 > itemstack.stackSize)
					k1 = itemstack.stackSize;

				itemstack.stackSize -= k1;
				entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float)random.nextGaussian() * f3 * 0.5;
				entityitem.motionY = (float)random.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float)random.nextGaussian() * f3 * 0.5;

				if (itemstack.hasTagCompound())
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
			}
		}

		par1World.func_96440_m(par2, par3, par4, block);

		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		((TileEnchanter) world.getTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.manaEnchanting;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileEnchanter) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

}
