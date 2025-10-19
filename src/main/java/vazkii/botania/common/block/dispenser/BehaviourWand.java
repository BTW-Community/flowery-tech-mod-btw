/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 16, 2014, 11:11:31 PM (GMT)]
 */
package vazkii.botania.common.block.dispenser;

import net.minecraft.src.Block;
import net.minecraft.src.BlockDispenser;
import net.minecraft.src.BehaviorDefaultDispenseItem;
import net.minecraft.src.IBlockSource;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.wand.IWandable;

public class BehaviourWand extends BehaviorDefaultDispenseItem {

	@Override
	protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
		ForgeDirection facing = ForgeDirection.getOrientation(BlockDispenser.getDispenserFacing(par1IBlockSource.getBlockMetadata()).ordinal());
		int x = par1IBlockSource.getXInt() + facing.offsetX;
		int y = par1IBlockSource.getYInt() + facing.offsetY;
		int z = par1IBlockSource.getZInt() + facing.offsetZ;
		World world = par1IBlockSource.getWorld();
		Block block = world.getBlock(x, y, z);
		if(block instanceof IWandable) {
			((IWandable) block).onUsedByWand(null, par2ItemStack, world, x, y, z, facing.getOpposite().ordinal());
			return par2ItemStack;
		}

		return super.dispenseStack(par1IBlockSource, par2ItemStack);
	}

}
