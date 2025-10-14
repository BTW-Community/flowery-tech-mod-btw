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

import net.minecraft.src.EntityList;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileLightRelay.EntityPlayerMover;
import vazkii.botania.common.lib.LibEntityNames;

public final class ModEntities {

	public static void init() {
		int id = 0;
		EntityList.addMapping(EntityManaBurst.class, LibEntityNames.MANA_BURST, id++);
		EntityList.addMapping(EntitySignalFlare.class, LibEntityNames.SIGNAL_FLARE, id++);
		EntityList.addMapping(EntityPixie.class, LibEntityNames.PIXIE, id++);
		EntityList.addMapping(EntityFlameRing.class, LibEntityNames.FLAME_RING, id++);
		EntityList.addMapping(EntityVineBall.class, LibEntityNames.VINE_BALL, id++);//todofix HIGH PRIO: Entity ids
		EntityList.addMapping(EntityDoppleganger.class, LibEntityNames.DOPPLEGANGER, id++, 128, 3, true);
		EntityList.addMapping(EntityMagicLandmine.class, LibEntityNames.MAGIC_LANDMINE, id++, 128, 40, false);
		EntityList.addMapping(EntitySpark.class, LibEntityNames.SPARK, id++, 64, 10, false);
		EntityList.addMapping(EntityThrownItem.class, LibEntityNames.THROWN_ITEM, id++, 64, 20, true);
		EntityList.addMapping(EntityMagicMissile.class, LibEntityNames.MAGIC_MISSILE, id++, 64, 2, true);
		EntityList.addMapping(EntityThornChakram.class, LibEntityNames.THORN_CHAKRAM, id++, 64, 10, true);
		EntityList.addMapping(EntityCorporeaSpark.class, LibEntityNames.CORPOREA_SPARK, id++, 64, 10, false);
		EntityList.addMapping(EntityEnderAirBottle.class, LibEntityNames.ENDER_AIR_BOTTLE, id++, 64, 10, true);
		EntityList.addMapping(EntityPoolMinecart.class, LibEntityNames.POOL_MINECART, id++, 80, 3, true);
		EntityList.addMapping(EntityPinkWither.class, LibEntityNames.PINK_WITHER, id++, 80, 3, false);
		EntityList.addMapping(EntityPlayerMover.class, LibEntityNames.PLAYER_MOVER, id++, 40, 3, true);
		EntityList.addMapping(EntityManaStorm.class, LibEntityNames.MANA_STORM, id++, 64, 10, false);
		EntityList.addMapping(EntityBabylonWeapon.class, LibEntityNames.BABYLON_WEAPON, id++, 64, 10, true);
		EntityList.addMapping(EntityFallingStar.class, LibEntityNames.FALLING_STAR, id++, 64, 10, true);

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

}
