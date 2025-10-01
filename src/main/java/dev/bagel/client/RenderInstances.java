package dev.bagel.client;

import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderItem;

public class RenderInstances {
    private static final RenderBlocks renderBlocks = new RenderBlocks();
    private static final RenderItem renderItem = new RenderItem();

    public static RenderBlocks getBlocksInstance() {
        return renderBlocks;
    }
    public static RenderItem getItemInstance() {
        return renderItem;
    }
}
