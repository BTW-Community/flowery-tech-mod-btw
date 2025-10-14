package dev.bagel.interfaces;

public interface TileEntityExtensions {
    public default boolean canUpdate() {
        return true;
    }

    public default void onChunkUnload() {

    }
}
