package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.ItemExtensions;
import net.minecraft.src.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemExtensionMixin implements ItemExtensions {
}
