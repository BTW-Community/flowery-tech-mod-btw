package dev.bagel.interfaces;

import net.minecraft.src.World;

public interface EntityPlayerExtensions {
    default void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {
//        FMLNetworkHandler.openGui(this, mod, modGuiId, world, x, y, z);
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
