/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 26, 2014, 4:11:03 PM (GMT)]
 */
package vazkii.botania.common.entity;

import btw.client.network.packet.handler.CustomEntityPacketHandler;
import btw.network.packet.BTWPacketManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import vazkii.botania.common.block.tile.TileLightRelay.EntityPlayerMover;
import vazkii.botania.common.lib.LibEntityNames;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class ModEntities {

	private static int id = 30;
	public static final int EntityManaBurstId = id++;
	public static final int EntitySignalFlareId = id++;
	public static final int EntityPixieId = id++;
	public static final int EntityFlameRingId = id++;
	public static final int EntityVineBallId = id++;
	public static final int EntityDopplegangerId = id++;
	public static final int EntityMagicLandmineId = id++;
	public static final int EntitySparkId = id++;
	public static final int EntityThrownItemId = id++;
	public static final int EntityMagicMissileId = id++;
	public static final int EntityThornChakramId = id++;
	public static final int EntityCorporeaSparkId = id++;
	public static final int EntityEnderAirBottleId = id++;
	public static final int EntityPoolMinecartId = id++;
	public static final int EntityPinkWitherId = id++;
	public static final int EntityPlayerMoverId = id++;
	public static final int EntityManaStormId = id++;
	public static final int EntityBabylonWeaponId = id++;
	public static final int EntityFallingStarId = id++;
	public static void init() {
		registerEntity(EntityManaBurst.class, LibEntityNames.MANA_BURST, EntityManaBurstId);
		registerEntity(EntitySignalFlare.class, LibEntityNames.SIGNAL_FLARE, EntitySignalFlareId);
		registerEntity(EntityPixie.class, LibEntityNames.PIXIE, EntityPixieId);
		registerEntity(EntityFlameRing.class, LibEntityNames.FLAME_RING, EntityFlameRingId);
		registerEntity(EntityVineBall.class, LibEntityNames.VINE_BALL, EntityVineBallId);
		registerEntity(EntityDoppleganger.class, LibEntityNames.DOPPLEGANGER, EntityDopplegangerId);
		registerEntity(EntityMagicLandmine.class, LibEntityNames.MAGIC_LANDMINE, EntityMagicLandmineId);
		registerEntity(EntitySpark.class, LibEntityNames.SPARK, EntitySparkId);
		registerEntity(EntityThrownItem.class, LibEntityNames.THROWN_ITEM, EntityThrownItemId);
		registerEntity(EntityMagicMissile.class, LibEntityNames.MAGIC_MISSILE, EntityMagicMissileId);
		registerEntity(EntityThornChakram.class, LibEntityNames.THORN_CHAKRAM, EntityThornChakramId);
		registerEntity(EntityCorporeaSpark.class, LibEntityNames.CORPOREA_SPARK, EntityCorporeaSparkId);
		registerEntity(EntityEnderAirBottle.class, LibEntityNames.ENDER_AIR_BOTTLE, EntityEnderAirBottleId);
		registerEntity(EntityPoolMinecart.class, LibEntityNames.POOL_MINECART, EntityPoolMinecartId);
		registerEntity(EntityPinkWither.class, LibEntityNames.PINK_WITHER, EntityPinkWitherId);
		registerEntity(EntityPlayerMover.class, LibEntityNames.PLAYER_MOVER, EntityPlayerMoverId);
		registerEntity(EntityManaStorm.class, LibEntityNames.MANA_STORM, EntityManaStormId);
		registerEntity(EntityBabylonWeapon.class, LibEntityNames.BABYLON_WEAPON, EntityBabylonWeaponId);
		registerEntity(EntityFallingStar.class, LibEntityNames.FALLING_STAR, EntityFallingStarId);
	}

	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id) {
		EntityList.addMapping(entityClass, entityName, id);
		if (!MinecraftServer.getIsServer()) {
			CustomEntityPacketHandler.entryMap.put(id, ((world, data, packet) -> {
				try {
					Entity entity = entityClass.getConstructor(World.class).newInstance(world);
					return getEntity(entity, data);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
						 NoSuchMethodException e) {//wew
					throw new RuntimeException(e);
				}
			}));
		}
	}

	private static Entity getEntity(Entity entity, DataInputStream data) throws IOException {
		int serverX = data.readInt();
		int serverY = data.readInt();
		int serverZ = data.readInt();
		double x = serverX / 32D;
		double y = serverY / 32D;
		double z = serverZ / 32D;
		float yaw = data.readByte() * 360F / 256F;
		float pitch = data.readByte() * 360F / 256F;
		entity.setPositionAndRotation(x, y, z, yaw, pitch);
		entity.serverPosX = serverX;
		entity.serverPosY = serverY;
		entity.serverPosZ = serverZ;
		return entity;
	}
	
	public static Packet getSpawnPacket(Entity entity, int entId) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		try {
			dataStream.writeInt(entId);
			dataStream.writeInt(entity.entityId);
			//pox, posy, posz
			dataStream.writeInt(MathHelper.floor_double(entity.posX * 32D));
			dataStream.writeInt(MathHelper.floor_double(entity.posY * 32D));
			dataStream.writeInt(MathHelper.floor_double(entity.posZ * 32D));
			// yaw, pitch
			dataStream.writeByte((byte) (entity.rotationYaw * 256.0F / 360.0F));
			dataStream.writeByte((byte) (entity.rotationPitch * 256.0F / 360.0F));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new Packet250CustomPayload(BTWPacketManager.SPAWN_CUSTOM_ENTITY_PACKET_CHANNEL, byteStream.toByteArray());
	}
}
