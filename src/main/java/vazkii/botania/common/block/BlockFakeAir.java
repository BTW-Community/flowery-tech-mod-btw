/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Apr 10, 2015, 10:22:38 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Explosion;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.common.block.tile.TileFakeAir;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockFakeAir extends BlockModContainer<TileFakeAir> {

	public BlockFakeAir(int id) {
		super(id, Material.air);
		setUnlocalizedName(LibBlockNames.FAKE_AIR);
		initBlockBounds(0, 0, 0, 0, 0, 0);
		setTickRandomly(true);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int block) {
		if(shouldRemove(world, x, y, z))
			world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
	}

	private boolean shouldRemove(World world, int x, int y, int z) {
		return !world.isRemote && world.getTileEntity(x, y, z) == null || !(world.getTileEntity(x, y, z) instanceof TileFakeAir) || !((TileFakeAir) world.getTileEntity(x, y, z)).canStay();
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if(shouldRemove(world, x, y, z))
			world.setBlock(x, y, z, Block.waterMoving);
	}

	@Override
	public int tickRate(World p_149738_1_) {
		return 4;
	}

	@Override
	public boolean registerInCreative() {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockAccess blockAccess, int i, int j, int k) {
		return false;
	}

//	@Override
//	public boolean isBlockNormalCube() {
//		return false;
//	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canWitherDestroyBlock() {
		return false;
	}


//	@Override
//	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity e) {
//		return false;
//	}

	@Override
	public boolean canCollideCheck(int par1, boolean par2) {
		return false;
	}

	@Override
	public boolean isReplaceableVegetation(World world, int i, int j, int k) {
		return true;
	}

	//	@Override
//	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
//		return true;
//	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion) {
		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<>(); // Empty List
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isAirBlock() {
		return true;
	}
//
//	@Override
//	public boolean isAir(IBlockAccess world, int x, int y, int z) {
//		return true;
//	}

	@Override
	public TileFakeAir createNewTileEntityT(World world, int meta) {
		return new TileFakeAir();
	}

}
