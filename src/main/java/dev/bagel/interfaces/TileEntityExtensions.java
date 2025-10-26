package dev.bagel.interfaces;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.AxisAlignedBB;

public interface TileEntityExtensions {
    public default boolean canUpdate() {
        return true;
    }

    public default void onChunkUnload() {

    }

    /**
     * Sometimes default render bounding box: infinite in scope. Used to control rendering on {@link TileEntitySpecialRenderer}.
     */
    public static final AxisAlignedBB INFINITE_EXTENT_AABB = AxisAlignedBB.getBoundingBox(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    /**
     * Return an {@link AxisAlignedBB} that controls the visible scope of a {@link TileEntitySpecialRenderer} associated with this {@link TileEntity}
     * Defaults to the collision bounding box {@link Block#getCollisionBoundingBoxFromPool(World, int, int, int)} associated with the block
     * at this location.
     *
     * @return an appropriately size {@link AxisAlignedBB} for the {@link TileEntity}
     */
    @Environment(EnvType.CLIENT)
    public default AxisAlignedBB getRenderBoundingBox()
    {
        return null;
    }
}
