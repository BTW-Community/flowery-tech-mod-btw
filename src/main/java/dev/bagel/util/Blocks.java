package dev.bagel.util;

import btw.item.items.PlaceAsBlockItem;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Blocks {
    public static @Nullable Block getBlockFromItem(Item item) {
        if (item instanceof PlaceAsBlockItem pibi) {
            return Block.blocksList[pibi.getBlockID()];
        }
        return null;
    }

    public static @Nullable Block getBlockFromItem(ItemStack item) {
        return getBlockFromItem(item.getItem());
    }
}
