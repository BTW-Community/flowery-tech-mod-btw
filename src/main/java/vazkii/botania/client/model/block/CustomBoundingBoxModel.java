package vazkii.botania.client.model.block;

import btw.block.model.BlockModel;
import net.minecraft.src.AxisAlignedBB;

import java.util.LinkedList;
import java.util.List;

public abstract class CustomBoundingBoxModel extends BlockModel {
    public List<AxisAlignedBB> bounds;

    @Override
    protected void initModel() {
        bounds = new LinkedList<>();
        init();
    }

    abstract protected void init();

    @Override
    public void addBox(double dMinX, double dMinY, double dMinZ, double dMaxX, double dMaxY, double dMaxZ) {
        super.addBox(dMinX, dMinY, dMinZ, dMaxX, dMaxY, dMaxZ);
        bounds.add(AxisAlignedBB.getBoundingBox(dMinX, dMinY, dMinZ, dMaxX, dMaxY, dMaxZ));
    }

    public void addBox(double dMinX, double dMinY, double dMinZ, double dMaxX, double dMaxY, double dMaxZ, boolean addToBounds) {
        super.addBox(dMinX, dMinY, dMinZ, dMaxX, dMaxY, dMaxZ);
        if (addToBounds)
            bounds.add(AxisAlignedBB.getBoundingBox(dMinX, dMinY, dMinZ, dMaxX, dMaxY, dMaxZ));
    }
}
