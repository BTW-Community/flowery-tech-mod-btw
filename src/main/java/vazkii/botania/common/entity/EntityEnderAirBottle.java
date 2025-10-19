/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Feb 16, 2015, 5:15:31 PM (GMT)]
 */
package vazkii.botania.common.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import btw.entity.EntityWithCustomPacket;
import btw.network.packet.BTWPacketManager;
import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.*;
import net.minecraft.src.Block;

public class EntityEnderAirBottle extends EntityThrowable implements EntityWithCustomPacket {

	public EntityEnderAirBottle(World world) {
		super(world);
	}

	public EntityEnderAirBottle(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	@Override
	protected void onImpact(MovingObjectPosition pos) {
		if(pos.entityHit == null && !worldObj.isRemote) {
			List<ChunkCoordinates> coordsList = getCoordsToPut(pos.blockX, pos.blockY, pos.blockZ);
			worldObj.playAuxSFX(2002, (int)Math.round(posX), (int)Math.round(posY), (int)Math.round(posZ), 8);

			for(ChunkCoordinates coords : coordsList) {
				worldObj.setBlock(coords.posX, coords.posY, coords.posZ, Block.whiteStone);
				if(Math.random() < 0.1)
					worldObj.playAuxSFX(2001, coords.posX, coords.posY, coords.posZ, BlockExtensions.getIdFromBlock(Block.whiteStone));
			}
			setDead();
		}
	}

	public List<ChunkCoordinates> getCoordsToPut(int xCoord, int yCoord, int zCoord) {
		List<ChunkCoordinates> possibleCoords = new ArrayList<>();
		List<ChunkCoordinates> selectedCoords = new ArrayList<>();
		int range = 4;
		int rangeY = 4;

		for(int i = -range; i < range + 1; i++)
			for(int j = -rangeY; j < rangeY; j++)
				for(int k = -range; k < range + 1; k++) {
					int x = xCoord + i;
					int y = yCoord + j;
					int z = zCoord + k;
					Block block = worldObj.getBlock(x, y, z);
					if(block != null && block == Block.stone /* block.isReplaceableOreGen(worldObj, x, y, z, Blocks.stone)*/)
						possibleCoords.add(new ChunkCoordinates(x, y, z));
				}

		int count = 64;
		while(!possibleCoords.isEmpty() && count > 0) {
			ChunkCoordinates coords = possibleCoords.get(worldObj.rand.nextInt(possibleCoords.size()));
			possibleCoords.remove(coords);
			selectedCoords.add(coords);
			count--;
		}
		return selectedCoords;
	}

	@Override
	public Packet getSpawnPacketForThisEntity() {
		return ModEntities.getSpawnPacket(this, ModEntities.EntityEnderAirBottleId);
	}

	@Override
	public int getTrackerViewDistance() {
		return 64;
	}

	@Override
	public int getTrackerUpdateFrequency() {
		return 10;
	}

	@Override
	public boolean getTrackMotion() {
		return true;
	}

	@Override
	public boolean shouldServerTreatAsOversized() {
		return false;
	}
}
