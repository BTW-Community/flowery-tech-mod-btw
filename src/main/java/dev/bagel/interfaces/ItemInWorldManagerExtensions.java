package dev.bagel.interfaces;

public interface ItemInWorldManagerExtensions {
    public default double getBlockReachDistance() {
        return 0d;
    }

    public default void setBlockReachDistance(double distance) {
    }
}
