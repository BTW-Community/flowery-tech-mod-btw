/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 10, 2014, 5:50:01 PM (GMT)]
 */
package vazkii.botania.common.block.tile;

import net.minecraft.src.EntityAnimal;
import net.minecraft.src.AxisAlignedBB;

public class TileForestEye extends TileMod {

	public int entities = 0;

	@Override
	public void updateEntity() {
		int range = 6;
		int entityCount = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range + 1, yCoord + range + 1, zCoord + range + 1)).size();
		if(entityCount != entities) {
			entities = entityCount;
			worldObj.func_96440_m(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord));
		}
	}

}
