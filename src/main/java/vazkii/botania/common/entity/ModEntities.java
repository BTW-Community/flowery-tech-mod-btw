/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 26, 2014, 4:11:03 PM (GMT)]
 */
package vazkii.botania.common.entity;

import btw.client.network.packet.handler.CustomEntityPacketHandler;
import btw.network.packet.BTWPacketManager;
import net.minecraft.src.*;
import vazkii.botania.common.block.tile.TileLightRelay.EntityPlayerMover;
import vazkii.botania.common.lib.LibEntityNames;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
		int id = 600;
		//todofix HIGH PRIO: Entity have null packets on spawn
		EntityList.addMapping(EntityManaBurst.class, LibEntityNames.MANA_BURST, EntityManaBurstId);
		CustomEntityPacketHandler.entryMap.put(EntityManaBurstId, ((world, data, packet) ->{
			var entity = new EntityManaBurst(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntitySignalFlare.class, LibEntityNames.SIGNAL_FLARE, EntitySignalFlareId);
		CustomEntityPacketHandler.entryMap.put(EntitySignalFlareId, ((world, data, packet) ->{
			var entity = new EntitySignalFlare(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityPixie.class, LibEntityNames.PIXIE, EntityPixieId);
		CustomEntityPacketHandler.entryMap.put(EntityPixieId, ((world, data, packet) ->{
			var entity = new EntityPixie(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityFlameRing.class, LibEntityNames.FLAME_RING, EntityFlameRingId);
		CustomEntityPacketHandler.entryMap.put(EntityFlameRingId, ((world, data, packet) ->{
			var entity = new EntityFlameRing(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityVineBall.class, LibEntityNames.VINE_BALL, EntityVineBallId);
		CustomEntityPacketHandler.entryMap.put(EntityVineBallId, ((world, data, packet) ->{
			var entity = new EntityVineBall(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityDoppleganger.class, LibEntityNames.DOPPLEGANGER, EntityDopplegangerId);
		CustomEntityPacketHandler.entryMap.put(EntityDopplegangerId, ((world, data, packet) ->{
			var entity = new EntityDoppleganger(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityMagicLandmine.class, LibEntityNames.MAGIC_LANDMINE, EntityMagicLandmineId);
		CustomEntityPacketHandler.entryMap.put(EntityMagicLandmineId, ((world, data, packet) ->{
			var entity = new EntityMagicLandmine(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntitySpark.class, LibEntityNames.SPARK, EntitySparkId);
		CustomEntityPacketHandler.entryMap.put(EntitySparkId, ((world, data, packet) ->{
			var entity = new EntitySpark(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityThrownItem.class, LibEntityNames.THROWN_ITEM, EntityThrownItemId);
		CustomEntityPacketHandler.entryMap.put(EntityThrownItemId, ((world, data, packet) ->{
			var entity = new EntityThrownItem(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityMagicMissile.class, LibEntityNames.MAGIC_MISSILE, EntityMagicMissileId);
		CustomEntityPacketHandler.entryMap.put(EntityMagicMissileId, ((world, data, packet) ->{
			var entity = new EntityMagicMissile(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityThornChakram.class, LibEntityNames.THORN_CHAKRAM, EntityThornChakramId);
		CustomEntityPacketHandler.entryMap.put(EntityThornChakramId, ((world, data, packet) ->{
			var entity = new EntityThornChakram(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityCorporeaSpark.class, LibEntityNames.CORPOREA_SPARK, EntityCorporeaSparkId);
		CustomEntityPacketHandler.entryMap.put(EntityCorporeaSparkId, ((world, data, packet) ->{
			var entity = new EntityCorporeaSpark(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityEnderAirBottle.class, LibEntityNames.ENDER_AIR_BOTTLE, EntityEnderAirBottleId);
		CustomEntityPacketHandler.entryMap.put(EntityEnderAirBottleId, ((world, data, packet) ->{
			var entity = new EntityEnderAirBottle(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityPoolMinecart.class, LibEntityNames.POOL_MINECART, EntityPoolMinecartId);
		CustomEntityPacketHandler.entryMap.put(EntityPoolMinecartId, ((world, data, packet) ->{
			var entity = new EntityPoolMinecart(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityPinkWither.class, LibEntityNames.PINK_WITHER, EntityPinkWitherId);
		CustomEntityPacketHandler.entryMap.put(EntityPinkWitherId, ((world, data, packet) ->{
			var entity = new EntityPinkWither(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityPlayerMover.class, LibEntityNames.PLAYER_MOVER, EntityPlayerMoverId);
		CustomEntityPacketHandler.entryMap.put(EntityPlayerMoverId, ((world, data, packet) ->{
			var entity = new EntityPlayerMover(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityManaStorm.class, LibEntityNames.MANA_STORM, EntityManaStormId);
		CustomEntityPacketHandler.entryMap.put(EntityManaStormId, ((world, data, packet) ->{
			var entity = new EntityManaStorm(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityBabylonWeapon.class, LibEntityNames.BABYLON_WEAPON, EntityBabylonWeaponId);
		CustomEntityPacketHandler.entryMap.put(EntityBabylonWeaponId, ((world, data, packet) ->{
			var entity = new EntityBabylonWeapon(world);
			return getEntity(entity, data);
		}));
		EntityList.addMapping(EntityFallingStar.class, LibEntityNames.FALLING_STAR, EntityFallingStarId);
		CustomEntityPacketHandler.entryMap.put(EntityFallingStarId, ((world, data, packet) ->{
			var entity = new EntityFallingStar(world);
			return getEntity(entity, data);
		}));

		//		EntityRegistry.registerModEntity(EntityManaBurst.class, LibEntityNames.MANA_BURST, id++, 64, 10, true);
		/*EntityRegistry.registerModEntity(EntitySignalFlare.class, LibEntityNames.SIGNAL_FLARE, id++, 2048, 10, false);
		EntityRegistry.registerModEntity(EntityPixie.class, LibEntityNames.PIXIE, id++, 16, 3, true);
		EntityRegistry.registerModEntity(EntityFlameRing.class, LibEntityNames.FLAME_RING, id++, 32, 40, false);
		EntityRegistry.registerModEntity(EntityVineBall.class, LibEntityNames.VINE_BALL, id++, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDoppleganger.class, LibEntityNames.DOPPLEGANGER, id++, 128, 3, true);
		EntityRegistry.registerModEntity(EntityMagicLandmine.class, LibEntityNames.MAGIC_LANDMINE, id++, 128, 40, false);
		EntityRegistry.registerModEntity(EntitySpark.class, LibEntityNames.SPARK, id++, 64, 10, false);
		EntityRegistry.registerModEntity(EntityThrownItem.class, LibEntityNames.THROWN_ITEM, id++, 64, 20, true);
		EntityRegistry.registerModEntity(EntityMagicMissile.class, LibEntityNames.MAGIC_MISSILE, id++, 64, 2, true);
		EntityRegistry.registerModEntity(EntityThornChakram.class, LibEntityNames.THORN_CHAKRAM, id++, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCorporeaSpark.class, LibEntityNames.CORPOREA_SPARK, id++, 64, 10, false);
		EntityRegistry.registerModEntity(EntityEnderAirBottle.class, LibEntityNames.ENDER_AIR_BOTTLE, id++, 64, 10, true);
		EntityRegistry.registerModEntity(EntityPoolMinecart.class, LibEntityNames.POOL_MINECART, id++, 80, 3, true);
		EntityRegistry.registerModEntity(EntityPinkWither.class, LibEntityNames.PINK_WITHER, id++, 80, 3, false);
		EntityRegistry.registerModEntity(EntityPlayerMover.class, LibEntityNames.PLAYER_MOVER, id++, 40, 3, true);
		EntityRegistry.registerModEntity(EntityManaStorm.class, LibEntityNames.MANA_STORM, id++, 64, 10, false);
		EntityRegistry.registerModEntity(EntityBabylonWeapon.class, LibEntityNames.BABYLON_WEAPON, id++, 64, 10, true);
		EntityRegistry.registerModEntity(EntityFallingStar.class, LibEntityNames.FALLING_STAR, id++, 64, 10, true);*/
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
