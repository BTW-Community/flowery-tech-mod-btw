package dev.bagel;

import net.minecraft.src.World;

import java.util.Random;

public interface IGrowable
{
    /** is valid*/
    boolean func_149851_a(World worldIn, int x, int y, int z, boolean isClient);

    /** is valid (without client check)*/
    boolean func_149852_a(World worldIn, Random random, int x, int y, int z);

    /** the actual grow method*/
    void func_149853_b(World worldIn, Random random, int x, int y, int z);
}