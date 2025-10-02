package dev.bagel.util;

import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public class Items {
    public static Item getItemFromBlock(Block block)
    {
        if (block == null) return null;
        return Item.itemsList[BlockExtensions.getIdFromBlock(block) - 256];
    }

    public static Item getItemFromBlock(int blockId)
    {
        return Item.itemsList[blockId - 256];
    }
}
