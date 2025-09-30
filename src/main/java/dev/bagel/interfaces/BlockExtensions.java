package dev.bagel.interfaces;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface BlockExtensions {

    default ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    default boolean isSideSolid(World worldObj, int xCoord, int i, int zCoord, ForgeDirection forgeDirection) {
        return false;
    }

    static int getIdFromBlock(Block block) {
        if (block == null) {
            return 0;
        }
        return block.blockID;
    }

    default boolean isAir(World world, int x, int y, int z) {
        return false;
    }
}
