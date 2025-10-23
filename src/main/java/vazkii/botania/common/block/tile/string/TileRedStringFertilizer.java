/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 16, 2014, 7:17:16 PM (GMT)]
 */
package vazkii.botania.common.block.tile.string;

import java.util.Random;

import btw.block.BTWBlocks;
import dev.bagel.IGrowable;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.World;
public class TileRedStringFertilizer extends TileRedString {

	public boolean func_149851_a(World p_149851_1_, boolean p_149851_5_) {
		ChunkCoordinates binding = getBinding();
		Block block = getBlockAtBinding();

		return block instanceof IGrowable ? ((IGrowable) block).func_149851_a(p_149851_1_, binding.posX, binding.posY, binding.posZ, p_149851_5_) : false;
	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_) {
		ChunkCoordinates binding = getBinding();
		Block block = getBlockAtBinding();
		return block instanceof IGrowable ? ((IGrowable) block).func_149852_a(p_149852_1_, p_149852_2_, binding.posX, binding.posY, binding.posZ) : false;
	}

	//try grow
	public boolean func_149853_b(World world) {
		ChunkCoordinates binding = getBinding();
		Block block = getBlockAtBinding();
		if (binding == null || block == null) return false;
		return block.attemptToApplyFertilizerTo(world, binding.posX, binding.posY, binding.posZ);
//		if(block instanceof IGrowable)
//			((IGrowable) block).func_149853_b(world, p_149853_2_, binding.posX, binding.posY, binding.posZ);
	}

	@Override
	public boolean acceptBlock(int x, int y, int z) {
		var block = worldObj.getBlock(x, y, z);
		if (block == null) return false;
		return block instanceof IGrowable || block == Block.tilledField || block == BTWBlocks.farmland || block == BTWBlocks.hempCrop || block == BTWBlocks.carrotCrop || block == BTWBlocks.wheatCrop || block == Block.pumpkinStem || block == Block.melonStem;
	}

}
