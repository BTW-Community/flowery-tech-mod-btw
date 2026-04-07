/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Jan 26, 2014, 12:22:58 AM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.item.block.ItemBlockPool;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockPool extends BlockModContainer<TilePool> implements IWandHUD, IWandable, ILexiconable, ICraftAchievement {

	boolean lastFragile = false;

	public static Icon manaIcon;

	public BlockPool(int id) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.POOL);
		initBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);

		BotaniaAPI.blacklistBlockFromMagnet(this, Short.MAX_VALUE);
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockPool(this);
//		GameRegistry.registerBlock(this, ItemBlockPool.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		manaIcon = IconHelper.forName(par1IconRegister, "manaWater");
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		if (par1World.getTileEntity(par2, par3, par4) instanceof TilePool pool) {
//			TilePool pool = (TilePool) par1World.getTileEntity(par2, par3, par4);
			lastFragile = pool.fragile;
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();

		if(!lastFragile)
			drops.add(new ItemStack(this, 1, metadata));

		return drops;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2, List par3) {
		par3.add(new ItemStack(par1, 1, 0));
		par3.add(new ItemStack(par1, 1, 2));
		par3.add(new ItemStack(par1, 1, 3));
		par3.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public TilePool createNewTileEntityT(World world) {
		return new TilePool();
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity instanceof EntityItem item && par1World.getTileEntity(par2, par3, par4) instanceof TilePool tile) {
			if(tile.collideEntityItem(item))
				VanillaPacketDispatcher.dispatchTEToNearbyPlayers(par1World, par2, par3, par4);
		}
	}

	@Override
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		return false;
	}

	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
	}

	//todo what should i use instead
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB intersecting, List list, Entity entity) {
		float f = 1F / 16F;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, intersecting, list, entity);
		setBlockBounds(0.0F, 0.0F, 0.0F, f, 0.5F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, intersecting, list, entity);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, f);
		super.addCollisionBoxesToList(world, x, y, z, intersecting, list, entity);
		setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, intersecting, list, entity);
		setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 0.5F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, intersecting, list, entity);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return super.getCollisionBoundingBoxFromPool(world, i, j, k);
	}

	@Override
	public boolean hasLargeCenterHardPointToFacing(IBlockAccess blockAccess, int i, int j, int k, int iFacing, boolean bIgnoreTransparency) {
		return iFacing == EnumFacing.DOWN.ordinal();
	}

	@Override
	public boolean hasCenterHardPointToFacing(IBlockAccess blockAccess, int i, int j, int k, int iFacing) {
		return super.hasCenterHardPointToFacing(blockAccess, i, j, k, iFacing);
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
	public Icon getIcon(int par1, int par2) {
		return ModBlocks.livingrock.getIcon(par1, 0);
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idPool;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TilePool pool = (TilePool) par1World.getTileEntity(par2, par3, par4);
		int val = (int) ((double) pool.getCurrentMana() / (double) pool.manaCap * 15.0);
		if(pool.getCurrentMana() > 0)
			val = Math.max(val, 1);

		return val;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TilePool) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		((TilePool) world.getTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return world.getBlockMetadata(x, y, z) == 3 ? LexiconData.rainbowRod : LexiconData.pool;
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return ModAchievements.MANA_POOL_PICKUP;
	}
}
