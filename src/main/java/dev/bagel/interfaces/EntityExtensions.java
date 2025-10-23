package dev.bagel.interfaces;

import net.minecraft.src.EntityItem;
import net.minecraft.src.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public interface EntityExtensions {
    public default NBTTagCompound getEntityData() {
        return null;
    }

    public default ArrayList<EntityItem> getCapturedDrops() {
        throw new IllegalStateException("This should be overridden via mixin. What?");
    }

    public default void setCaptureDrops(boolean flag) {

    }

    public default boolean getCaptureDrops() {
        return false;
    }
}
