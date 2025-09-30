package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class BlockExtensionMixin implements BlockExtensions {
    @Shadow public abstract int idPicked(World par1World, int par2, int par3, int par4);

    @Shadow public abstract boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5);

    @Shadow public abstract boolean isAirBlock();

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(Item.itemsList[idPicked(world, x, y, z)]);
    }

    @Override
    public boolean isSideSolid(World worldObj, int xCoord, int yCoord, int zCoord, ForgeDirection forgeDirection) {
        return isBlockSolid(worldObj, xCoord, yCoord, zCoord, forgeDirection.ordinal());
    }

    @Override
    public boolean isAir(World world, int x, int y, int z) {
        return isAirBlock();
    }
}
