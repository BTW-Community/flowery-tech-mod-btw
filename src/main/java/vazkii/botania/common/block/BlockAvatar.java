/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Oct 24, 2015, 3:15:11 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
//import net.minecraftforge.common.util.RotationHelper;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TileAvatar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockAvatar extends BlockModContainer<TileAvatar> implements ILexiconable {

	private static final int[] META_ROTATIONS = new int[] { 2, 5, 3, 4 };

	Random random;

	protected BlockAvatar(int id) {
		super(id, Material.wood);
		setHardness(2.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(LibBlockNames.AVATAR);
		setBlockBounds(true);

		random = new Random();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
		TileAvatar avatar = (TileAvatar) world.getTileEntity(x, y, z);
		ItemStack stackOnAvatar = avatar.getStackInSlot(0);
		ItemStack stackOnPlayer = player.getCurrentEquippedItem();
		if(stackOnAvatar != null) {
			ItemStack copyStack = stackOnAvatar.copy();
			avatar.setInventorySlotContents(0, null);
			if(!player.inventory.addItemStackToInventory(copyStack))
				player.dropPlayerItemWithRandomChoice(copyStack, true);
			return true;
		} else if(stackOnPlayer != null && stackOnPlayer.getItem() instanceof IAvatarWieldable) {
			ItemStack copyStack = stackOnPlayer.copy();
			avatar.setInventorySlotContents(0, copyStack);
			stackOnPlayer.stackSize--;
			return true;
		}

		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, 	int x, int y, int z) {
		setBlockBounds(w.getBlockMetadata(x, y, z) < 4);
	}

	public void setBlockBounds(boolean horiz) {
		float f = 1F / 16F;
		float w = f * 9;
		float l = f * 6;
		float ws = (1F - w) / 2;
		float ls = (1F - l) / 2;

		if(horiz)
			setBlockBounds(ws, 0F, ls, 1F - ws, 1F + f, 1F - ls);
		else setBlockBounds(ls, 0F, ws, 1F - ls, 1F + f, 1F - ws);
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
	}

	//todofix rotation
/*	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
		return RotationHelper.rotateVanillaBlock(Block.furnaceIdle, worldObj, x, y, z, axis);
	}*/

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return ModBlocks.livingwood.getIcon(0, 0);
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
	public int getRenderType() {
		return LibRenderIDs.idAvatar;
	}

	@Override
	public TileAvatar createNewTileEntityT(World world) {
		return new TileAvatar();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.avatar;
	}

}
