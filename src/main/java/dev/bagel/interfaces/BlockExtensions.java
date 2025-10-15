package dev.bagel.interfaces;

import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

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

    //todofix implement getBlockDropped
    default ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<>();
    }

    public default Icon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        return null;
    }
}
