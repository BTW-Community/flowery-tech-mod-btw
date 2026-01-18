/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 3, 2014, 12:12:04 AM (GMT)]
 */
package vazkii.botania.common.brew.potion;

import net.minecraft.src.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibPotionNames;

public class PotionFeatherfeet extends PotionMod {

	public PotionFeatherfeet() {
		super(ConfigHandler.potionIDFeatherfeet, LibPotionNames.FEATHER_FEET, false, 0x26ADFF, 1);
//		MinecraftForge.EVENT_BUS.register(this);
		LivingEvent.LivingUpdateEvent.EVENT.register(this::onEntityUpdate);
	}

//	@SubscribeEvent
	public boolean onEntityUpdate(LivingUpdateEvent event) {
		EntityLivingBase e = event.entityLiving;
		if(hasEffect(e))
			e.fallDistance = 2.5F;
		return false;
	}

}
