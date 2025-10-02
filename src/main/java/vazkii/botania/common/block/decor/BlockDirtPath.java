/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 18, 2015, 1:47:03 AM (GMT)]
 */
package vazkii.botania.common.block.decor;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockDirtPath extends BlockMod implements ILexiconable {

	public BlockDirtPath(int id) {
		super(id, Material.ground);
		setBlockBounds(0F, 0F, 0F, 1F, 15F / 16F, 1F);
		setLightOpacity(255);
		setHardness(0.6F);
		setStepSound(soundGravelFootstep);
		setBlockName(LibBlockNames.DIRT_PATH);
		useNeighborBrightness[0] = true;
	}

	@Override
	public boolean areShovelsEffectiveOn() {
		return true;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		float speed = 2F;
		float max = 0.4F;

		double motionX = Math.abs(entity.motionX);
		double motionZ = Math.abs(entity.motionZ);
		if(motionX < max)
			entity.motionX *= speed;
		if(motionZ < max)
			entity.motionZ *= speed;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		Block blockAbove = ((World) world).getBlock(x, y + 1, z);
		if(!blockAbove.isAir(((World) world), x, y + 1, z))
			setBlockBounds(0F, 0F, 0F, 1F, 1, 1F);
		else setBlockBounds(0F, 0F, 0F, 1F, 15F / 16F, 1F);
	}

	@Override
	public boolean isSideSolid(World worldObj, int xCoord, int i, int zCoord, ForgeDirection side) {
		return side == ForgeDirection.DOWN;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int block) {
		setBlockBoundsBasedOnState(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	//todofix canSustainPlant
/*	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return plantable.getPlantType(world, x, y - 1, z) == EnumPlantType.Plains;
	}*/

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.dirtPath;
	}

}
