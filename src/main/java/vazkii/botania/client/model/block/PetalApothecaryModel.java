package vazkii.botania.client.model.block;

import btw.block.model.BlockModel;
import net.minecraft.src.AxisAlignedBB;

public class PetalApothecaryModel extends CustomBoundingBoxModel {
    public BlockModel base;
    @Override
    protected void init() {
        base = new BlockModel();
        base.addBox(0.125, 0, 0.125, 0.875, 0.125, 0.875);
        bounds.add(AxisAlignedBB.getBoundingBox(0.125, 0, 0.125, 0.875, 0.125, 0.875));
        bounds.add(AxisAlignedBB.getBoundingBox(0.125, 0.6875, 0.125, 0.875, 1, 0.875));
        addBox(0.25, 0.125, 0.25, 0.75, 0.6875, 0.75);
        addBox(0.125, 0.6875, 0.125, 0.875, 0.75, 0.875, false);
        addBox(0.125, 0.75, 0.8125, 0.875, 1, 0.875, false);
        addBox(0.125, 0.75, 0.125, 0.875, 1, 0.1875, false);
        addBox(0.125, 0.75, 0.1875, 0.1875, 1, 0.8125, false);
        addBox(0.8125, 0.75, 0.1875, 0.875, 1, 0.8125, false);
    }
}
