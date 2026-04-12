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

import api.block.util.RayTraceUtils;
import btw.block.model.BlockModel;
import dev.bagel.interfaces.CustomBoundingBoxBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.model.block.CustomBoundingBoxModel;
import vazkii.botania.client.model.block.RuneAltarModel;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.List;

public class BlockRuneAltar extends BlockModContainer<TileRuneAltar> implements IWandable, ILexiconable, CustomBoundingBoxBlock {
	RuneAltarModel model = new RuneAltarModel();
	Icon[] icons;

	public BlockRuneAltar(int id) {
		super(id, Material.rock);
		initBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.RUNE_ALTAR);

		BotaniaAPI.blacklistBlockFromMagnet(this, Short.MAX_VALUE);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int iNeighborI, int iNeighborJ, int iNeighborK, int iSide) {
		Block neighborBlock = blocksList[blockAccess.getBlockId(iNeighborI, iNeighborJ, iNeighborK)];
		if (neighborBlock != null) {
			return neighborBlock.shouldRenderNeighborFullFaceSide(blockAccess, iNeighborI, iNeighborJ, iNeighborK, iSide) || iSide == EnumFacing.UP.ordinal();
		}
		return true;
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
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, par1World.rand);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		if (par1 == 1 && secondPass) {
			return icons[0];
		}
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

	private boolean secondPass = false;
	@Override
	@Environment(value= EnvType.CLIENT)
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		BlockModel transformedModel = this.model.makeTemporaryCopy();
		return transformedModel.renderAsBlock(renderer, this, i, j, k);
	}

	@Override
	public void renderBlockSecondPass(RenderBlocks renderBlocks, int i, int j, int k, boolean bFirstPassResult) {
		secondPass = true;
		BlockModel transformedModel = this.model.base.makeTemporaryCopy();
		transformedModel.renderAsBlock(renderBlocks, this, i, j, k);
		secondPass = false;
	}

	@Override
	@Environment(value=EnvType.CLIENT)
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		this.model.renderAsItemBlock(renderBlocks, this, iItemDamage);
		secondPass = true;
		this.model.base.renderAsItemBlock(renderBlocks, this, iItemDamage);
		secondPass = false;
	}


	@Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB boundingBox, List list, Entity entity) {
		this.model.makeTemporaryCopy().addIntersectingBoxesToCollisionList(world, i, j, k, boundingBox, list);
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3 startRay, Vec3 endRay) {
		RayTraceUtils rayTrace = new RayTraceUtils(world, i, j, k, startRay, endRay);
		BlockModel transformedModel = this.model;
		transformedModel.addToRayTrace(rayTrace);
		this.model.base.addToRayTrace(rayTrace);
		return rayTrace.getFirstIntersection();
	}

	@Override
	public List<AxisAlignedBB> getCustomSelectionBoxes(World world, int x, int y, int z) {
		return model.bounds;
	}

	@Override
	public boolean rotatable() {
		return false;
	}
}
