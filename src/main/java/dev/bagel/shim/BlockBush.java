package dev.bagel.shim;

import btw.block.blocks.PlantsBlock;
import com.prupe.mcpatcher.mal.block.BlockAPI;
import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.item.ModItems;

import java.util.Random;

public class BlockBush extends PlantsBlock
{
    private static final String __OBFID = "CL_00000208";

    protected BlockBush(int id, Material materialIn)
    {
        super(id, materialIn);
        this.setTickRandomly(true);
        float f = 0.2F;
        this.initBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setCreativeTab(ModItems.botaniaTab);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z)
    {
        return super.canPlaceBlockAt(worldIn, x, y, z) && this.canBlockStay(worldIn, x, y, z);
    }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == Block.grass || ground == Block.dirt || ground == Block.tilledField;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, int neighbor)
    {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World worldIn, int x, int y, int z)
    {
        if (!this.canBlockStay(worldIn, x, y, z))
        {
            this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
            worldIn.setBlock(x, y, z, BlockAPI.getBlockById(0), 0, 2);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }

}
