package vazkii.botania.client.model.block;

import btw.block.model.BlockModel;
import net.minecraft.src.AxisAlignedBB;

public class RuneAltarModel extends CustomBoundingBoxModel {
    public BlockModel base;
    @Override
    protected void init() {
        addBox(0, 0.375, 0, 1, 0.75, 1);
        addBox(0.25, 0.25, 0.25, 0.75, 0.375, 0.75);
        bounds.add(AxisAlignedBB.getBoundingBox(0.125, 0, 0.125, 0.875, 0.25, 0.875));
        base = new BlockModel();
        base.addBox(0.125, 0, 0.125, 0.875, 0.25, 0.875);
    }
}
