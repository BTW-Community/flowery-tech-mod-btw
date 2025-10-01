package dev.bagel.interfaces;

import net.minecraft.src.NBTTagCompound;

public interface EntityExtensions {
    public default NBTTagCompound getEntityData()
    {
        return null;
    }
}
