package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.ItemInWorldManagerExtensions;
import net.minecraft.src.ItemInWorldManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemInWorldManager.class)
public abstract class ItemInWorldManagerExtensionMixin implements ItemInWorldManagerExtensions {
    private double blockReachDistance = 5.0d;
    @Override
    public double getBlockReachDistance() {
        return blockReachDistance;
    }

    @Override
    public void setBlockReachDistance(double distance) {
        blockReachDistance = distance;
    }
}
