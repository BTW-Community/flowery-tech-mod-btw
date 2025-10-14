package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.TileEntityExtensions;
import net.minecraft.src.TileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TileEntity.class)
public abstract class TileEntityExtensionsMixin implements TileEntityExtensions {
    public boolean canUpdate() {
        return true;
    }

    public void onChunkUnload() {

    }
}
