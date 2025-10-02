package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Random;

@Mixin(Block.class)
public abstract class BlockExtensionMixin implements BlockExtensions {
    @Shadow public abstract int idPicked(World par1World, int par2, int par3, int par4);

    @Shadow public abstract boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5);

    @Shadow public abstract boolean isAirBlock();

    @Shadow public abstract int idDropped(int par1, Random par2Random, int par3);

    @Shadow public abstract int quantityDroppedWithBonus(int par1, Random par2Random);

    @Shadow public abstract int damageDropped(int par1);

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

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        int count = this.quantityDroppedWithBonus(fortune, world.rand);

        for(int i = 0; i < count; ++i) {
            int id = this.idDropped(metadata, world.rand, fortune);
            if (id > 0) {
                ret.add(new ItemStack(id, 1, this.damageDropped(metadata)));
            }
        }

        return ret;
    }
}
