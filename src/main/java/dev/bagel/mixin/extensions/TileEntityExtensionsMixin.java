package dev.bagel.mixin.extensions;

import btw.block.BTWBlocks;
import dev.bagel.interfaces.TileEntityExtensions;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntity.class)
public abstract class TileEntityExtensionsMixin implements TileEntityExtensions {
    @Shadow public abstract Block getBlockType();

    @Shadow public int xCoord;

    @Shadow public int yCoord;

    @Shadow public int zCoord;

    @Shadow public World worldObj;

    public boolean canUpdate() {
        return true;
    }

    public void onChunkUnload() {

    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        Block type = this.getBlockType();
        if (type == Block.enchantmentTable)
        {
            bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
        }
        else if (type == Block.chest || type == BTWBlocks.chest)
        {
            bb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
        }
        else if (type != null && type != Block.beacon)
        {
            AxisAlignedBB cbb = type.getCollisionBoundingBoxFromPool(worldObj, xCoord, yCoord, zCoord);
            if (cbb != null)
            {
                bb = cbb;
            }
        }
        return bb;
    }
}
