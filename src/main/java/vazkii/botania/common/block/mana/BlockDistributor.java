/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 3, 2014, 1:44:29 AM (GMT)]
 */
package vazkii.botania.common.block.mana;

import api.block.util.RayTraceUtils;
import btw.block.model.BlockModel;
import dev.bagel.interfaces.CustomBoundingBoxBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.model.block.CustomBoundingBoxModel;
import vazkii.botania.client.model.block.DistributorModel;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileDistributor;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.LinkedList;
import java.util.List;

public class BlockDistributor extends BlockModContainer<TileDistributor> implements ILexiconable, CustomBoundingBoxBlock {

	CustomBoundingBoxModel model = new DistributorModel();
	Icon iconSide, iconTop, iconBottom;

	public BlockDistributor(int id) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.DISTRIBUTOR);

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		iconSide = IconHelper.forBlock(par1IconRegister, this, 0);
		iconTop = IconHelper.forBlock(par1IconRegister, this, 1);
		iconBottom = IconHelper.forBlock(par1IconRegister, this, 2);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 == 0 ? iconBottom : par1 == 1 ? iconTop : iconSide;
	}

	@Override
	public TileDistributor createNewTileEntityT(World world) {
		return new TileDistributor();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.distributor;
	}

	@Override
	@Environment(value= EnvType.CLIENT)
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		BlockModel transformedModel = this.model.makeTemporaryCopy();
        return transformedModel.renderAsBlock(renderer, this, i, j, k);
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
	@Environment(value=EnvType.CLIENT)
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		this.model.renderAsItemBlock(renderBlocks, this, iItemDamage);
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
