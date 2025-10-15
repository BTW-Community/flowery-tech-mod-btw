package dev.bagel.interfaces;

import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import org.jetbrains.annotations.Nullable;

public interface IBlockAccessExtensions {

    default boolean setBlock(int x, int y, int z, Block blockIn, int metadataIn, int flags) {
        return false;
    }

    default boolean setBlock(int x, int y, int z, Block blockIn) {
        return setBlock(x, y, z, blockIn, 0, 3);
    }

    default @Nullable Block getBlock(int x, int y, int z) {
        return null;
    }

    default TileEntity getTileEntity(int x, int y, int z) {
        return null;
    }
}
