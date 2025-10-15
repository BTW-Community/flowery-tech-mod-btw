package dev.bagel.util;

import btw.item.items.PlaceAsBlockItem;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Blocks {
    private static Map<String, Block> blocks;
    public static @Nullable Block getBlockFromItem(Item item) {
        if (item instanceof PlaceAsBlockItem pibi) {
            return Block.blocksList[pibi.getBlockID()];
        }
        return null;
    }

    public static Block getBlockFromName(String name) {
        return blocks.get(name);
    }

    public static @Nullable Block getBlockFromItem(ItemStack item) {
        return getBlockFromItem(item.getItem());
    }
}
