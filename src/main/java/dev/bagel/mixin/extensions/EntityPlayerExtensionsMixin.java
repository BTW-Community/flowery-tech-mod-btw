package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.EntityPlayerExtensions;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayer.class)
public class EntityPlayerExtensionsMixin implements EntityPlayerExtensions {
    @Override
    public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {

    }
}
