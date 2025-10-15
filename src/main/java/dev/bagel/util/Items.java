package dev.bagel.util;

import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public class Items {
    public static Item getItemFromBlock(Block block)
    {
        if (block == null) return null;
        return Item.itemsList[BlockExtensions.getIdFromBlock(block)];
    }

    public static Item getItemFromBlock(int blockId)
    {
        return Item.itemsList[blockId - 256];
    }

    public static int getIdFromItem(Item item) {
        return item == null ? 0 : item.itemID;
    }
}
