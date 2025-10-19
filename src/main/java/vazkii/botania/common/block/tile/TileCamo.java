/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Jun 7, 2014, 2:21:28 PM (GMT)]
 */
package vazkii.botania.common.block.tile;


import btw.block.tileentity.TileEntityDataPacketHandler;
import net.minecraft.src.Block;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet132TileEntityData;

public class TileCamo extends TileMod implements TileEntityDataPacketHandler {

	private static final String TAG_CAMO = "camo";
	private static final String TAG_CAMO_META = "camoMeta";

	public Block camo;
	public int camoMeta;

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp) {
		if(camo != null) {
			cmp.setInteger(TAG_CAMO, camo.blockID);
			cmp.setInteger(TAG_CAMO_META, camoMeta);
		}
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp) {
		camo = Block.blocksList[cmp.getInteger(TAG_CAMO)];
		camoMeta = cmp.getInteger(TAG_CAMO_META);
	}

	@Override
	public void readNBTFromPacket(NBTTagCompound tag) {
		super.readNBTFromPacket(tag);
		worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
	}
/*
	@Override
	public void onDataPacket(NetworkManager manager, Packet132TileEntityData packet) {
		super.onDataPacket(manager, packet);
		worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
	}*/
}
