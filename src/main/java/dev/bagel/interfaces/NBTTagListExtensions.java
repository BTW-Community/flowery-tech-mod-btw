package dev.bagel.interfaces;

import net.minecraft.src.NBTTagCompound;

public interface NBTTagListExtensions {
    /**
     * Retrieves the NBTTagCompound at the specified index in the list
     */
    default public NBTTagCompound getCompoundTagAt(int i) {
        return new NBTTagCompound();
    }
}
