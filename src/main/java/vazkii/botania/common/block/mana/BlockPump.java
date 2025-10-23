/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 17, 2015, 9:46:53 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockPump extends BlockModContainer<TilePump> implements ILexiconable {

	private static final int[] META_ROTATIONS = new int[] { 2, 5, 3, 4 };

	public BlockPump(int id) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.PUMP);
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
	}

	@Override
	public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess w, int x, int y, int z) {
		boolean horiz = w.getBlockMetadata(x, y, z) < 4;
		if (horiz) {
			return AxisAlignedBB.getAABBPool().getAABB(0.25F, 0F, 0F, 0.75F, 0.5F, 1F);
		}
		return AxisAlignedBB.getAABBPool().getAABB(0F, 0F, 0.25F, 1F, 0.5F, 0.75F);
	}

//	@Override
//	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
//		return RotationHelper.rotateVanillaBlock(Blocks.furnace, worldObj, x, y, z, axis);
//	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return ModBlocks.livingrock.getIcon(0, 0);
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idPump;
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
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return ((TilePump) world.getTileEntity(x, y, z)).comparator;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.poolCart;
	}

	@Override
	public TilePump createNewTileEntityT(World world, int meta) {
		return new TilePump();
	}
}
