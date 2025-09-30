package dev.bagel.interfaces;

import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;

public interface WorldExtensions {

    default boolean setBlock(int x, int y, int z, Block blockIn, int metadataIn, int flags) {
        return false;
    }

    default Block getBlock(int x, int y, int z) {
        return null;
    }

    default TileEntity getTileEntity(int x, int y, int z) {
        return null;
    }
}
