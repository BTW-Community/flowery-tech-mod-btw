/**
 * This class was created by <SoundLogic>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Aug 31, 2015, 11:40:59 PM (GMT)]
 */
package vazkii.botania.client.core.handler;

import dev.bagel.interfaces.IBlockAccessExtensions;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

/**
 * This class acts as a wrapper around a block access to
 * replace blocks with the blocks involved in the multiblock specified
 */
public class MultiblockBlockAccess implements IBlockAccess, IBlockAccessExtensions {

    protected IBlockAccess originalBlockAccess;
    protected boolean hasBlockAccess = false;
    protected Multiblock multiblock;
    protected int anchorX, anchorY, anchorZ;

    @Override
    public Block getBlock(int x, int y, int z) {
        MultiblockComponent comp = getComponent(x, y, z);
        if (comp != null)
            return comp.getBlock();
        if (hasBlockAccess)
            return Block.blocksList[originalBlockAccess.getBlockId(x, y, z)];
        return null;
    }

    @Override
    public int getBlockId(int x, int y, int z) {
        MultiblockComponent comp = getComponent(x, y, z);
        if (comp != null)
            return comp.getBlock().blockID;
        if (hasBlockAccess)
            return originalBlockAccess.getBlockId(x, y, z);
        return 0;
    }

    @Override
    public TileEntity getBlockTileEntity(int x, int y, int z) {
        MultiblockComponent comp = getComponent(x, y, z);
        if (comp != null)
            return comp.getTileEntity();
        if (hasBlockAccess)
            return originalBlockAccess.getBlockTileEntity(x, y, z);
        return null;
    }

    @Override
    public int getLightBrightnessForSkyBlocks(int x, int y, int z, int p_72802_4_) {
        if (hasBlockAccess)
            return originalBlockAccess.getLightBrightnessForSkyBlocks(x, y, z, p_72802_4_);
        return 15728640;
    }

    @Override
    public float getBrightness(int x, int y, int z, int idk) {
        if (hasBlockAccess)
            return originalBlockAccess.getBrightness(x, y, z, idk);
        return 15;
    }

    @Override
    public float getLightBrightness(int x, int y, int z) {
        if (hasBlockAccess)
            return originalBlockAccess.getLightBrightness(x, y, z);
        return 0;
    }

    @Override
    public int getBlockMetadata(int x, int y, int z) {
        MultiblockComponent comp = getComponent(x, y, z);
        if (comp != null)
            return comp.getMeta();
        if (hasBlockAccess)
            return originalBlockAccess.getBlockMetadata(x, y, z);
        return 0;
    }

    @Override
    public Material getBlockMaterial(int var1, int var2, int var3) {
        if (hasBlockAccess)
            return originalBlockAccess.getBlockMaterial(var1, var2, var3);
        return Material.air;
    }

    @Override
    public boolean isBlockOpaqueCube(int var1, int var2, int var3) {
        if (hasBlockAccess)
            return originalBlockAccess.isBlockOpaqueCube(var1, var2, var3);
        return false;
    }

    @Override
    public boolean isBlockNormalCube(int var1, int var2, int var3) {
        if (hasBlockAccess)
            return originalBlockAccess.isBlockNormalCube(var1, var2, var3);
        return false;
    }

    @Override
    public int isBlockProvidingPowerTo(int x, int y, int z, int direction) {
        if (hasBlockAccess)
            return originalBlockAccess.isBlockProvidingPowerTo(x, y, z, direction);
        return 0;
    }

    @Override
    public boolean isAirBlock(int x, int y, int z) {
        MultiblockComponent comp = getComponent(x, y, z);
        if (comp != null)
            return false;
        if (hasBlockAccess)
            return originalBlockAccess.isAirBlock(x, y, z);
        return true;
    }

    @Override
    public BiomeGenBase getBiomeGenForCoords(int x, int z) {
        if (hasBlockAccess)
            return originalBlockAccess.getBiomeGenForCoords(x, z);
        return null;
    }

    @Override
    public int getHeight() {
        if (hasBlockAccess)
            return originalBlockAccess.getHeight();
        return 256;
    }

    @Override
    public boolean extendedLevelsInChunkCache() {
        if (hasBlockAccess)
            return originalBlockAccess.extendedLevelsInChunkCache();
        return false;
    }

    @Override
    public boolean doesBlockHaveSolidTopSurface(int var1, int var2, int var3) {
        return false;
    }

    @Override
    public Vec3Pool getWorldVec3Pool() {
        if (hasBlockAccess)
            return originalBlockAccess.getWorldVec3Pool();
        //todofix please dont cause any issues :sob:
        return null;
    }

//	@Override
//	public boolean isSideSolid(int x, int y, int z, ForgeDirection side, boolean _default) {
//		if(hasBlockAccess)
//			return originalBlockAccess.isSideSolid(x, y, z, side, _default);
//		return _default;
//	}

    /**
     * Updates the block access to the new parameters
     */
    public void update(IBlockAccess access, Multiblock mb, int anchorX, int anchorY, int anchorZ) {
        originalBlockAccess = access;
        multiblock = mb;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        this.anchorZ = anchorZ;
        hasBlockAccess = access != null;
    }

    /**
     * Returns the multiblock component for the coordinates, adjusted based on the anchor
     */
    protected MultiblockComponent getComponent(int x, int y, int z) {
        MultiblockComponent comp = multiblock.getComponentForLocation(x - anchorX, y - anchorY, z - anchorZ);
        return comp;
    }
}
