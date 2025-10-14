/**
 * This class was created by <Flaxbeard>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [? (GMT)]
 */
package vazkii.botania.common.entity;

import java.util.List;

import btw.entity.EntityWithCustomPacket;
import net.minecraft.src.*;
import vazkii.botania.common.core.helper.Vector3;

public class EntityThrownItem extends EntityItem implements EntityWithCustomPacket {

	public EntityThrownItem(World par1World) {
		super(par1World);
	}

	public EntityThrownItem(World p_i1710_1_, double p_i1710_2_,
			double p_i1710_4_, double p_i1710_6_, EntityItem item) {
		super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, item.getEntityItem());

		delayBeforeCanPickup = item.delayBeforeCanPickup;
		motionX = item.motionX;
		motionY = item.motionY;
		motionZ = item.motionZ;
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);

		MovingObjectPosition movingobjectposition = worldObj.mouseOverRayTrace(vec3, vec31);


		if (!worldObj.isRemote)
		{
			Entity entity = null;
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX*2, motionY*2, motionZ*2).expand(2.0D, 2.0D, 2.0D));
			double d0 = 0.0D;

			for (int j = 0; j < list.size(); ++j)
			{
				Entity entity1 = (Entity)list.get(j);

				if (entity1.canBeCollidedWith() && (!(entity1 instanceof EntityPlayer) || delayBeforeCanPickup == 0))
				{
					float f = 1.0F;
					AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null)
					{
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D)
						{
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}

		if (movingobjectposition != null)
		{
			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE && worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Block.portal)
			{
				setInPortal();
			}
			else
			{
				if (movingobjectposition.entityHit != null) {
					movingobjectposition.entityHit.attackEntityFrom(DamageSource.magic, 2.0F);
					if (!worldObj.isRemote) {
						//todoforge create entity for item, not used by botania
						Entity item = null/*getEntityItem().getItem().createEntity(worldObj, this, getEntityItem())*/;
						if (item == null) {
							item = new EntityItem(worldObj, posX, posY, posZ, getEntityItem());
							worldObj.spawnEntityInWorld(item);
							item.motionX = motionX*0.25F;
							item.motionY = motionY*0.25F;
							item.motionZ = motionZ*0.25F;

						}
						else
						{
							item.motionX = motionX*0.25F;
							item.motionY = motionY*0.25F;
							item.motionZ = motionZ*0.25F;
						}
					}
					setDead();

				}
			}
		}

		Vector3 vec3m = new Vector3(motionX, motionY, motionZ);
		if (vec3m.mag() < 1.0F) {
			if (!worldObj.isRemote) {
				//todoforge create entity for item, not used by botania
				Entity item = null/*getEntityItem().getItem().createEntity(worldObj, this, getEntityItem())*/;
				if (item == null) {
					item = new EntityItem(worldObj, posX, posY, posZ, getEntityItem());
					worldObj.spawnEntityInWorld(item);
					item.motionX = motionX;
					item.motionY = motionY;
					item.motionZ = motionZ;
				}
				else
				{
					item.motionX = motionX;
					item.motionY = motionY;
					item.motionZ = motionZ;
				}
			}
			setDead();
		}
	}

	@Override
	public Packet getSpawnPacketForThisEntity() {
		return null;
	}

	@Override
	public int getTrackerViewDistance() {
		return 64;
	}

	@Override
	public int getTrackerUpdateFrequency() {
		return 20;
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
