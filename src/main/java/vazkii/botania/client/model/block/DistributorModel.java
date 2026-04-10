package vazkii.botania.client.model.block;


public class DistributorModel extends CustomBoundingBoxModel {
    @Override
    protected void init() {
        addBox(0.25, 0, 0.25, 0.75, 1, 0.75);

        addBox(0, 0, 0.25, 0.25, 0.5, 0.75);

        addBox(0.75, 0, 0.25, 1, 0.5, 0.75);

        addBox(0.25, 0, 0.75, 0.75, 0.5, 1);

        addBox(0.25, 0, 0, 0.75, 0.5, 0.25);

    }
}
