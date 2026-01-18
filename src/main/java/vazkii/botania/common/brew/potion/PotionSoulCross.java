/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Nov 2, 2014, 10:31:48 PM (GMT)]
 */
package vazkii.botania.common.brew.potion;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibPotionNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionSoulCross extends PotionMod {

	public PotionSoulCross() {
		super(ConfigHandler.potionIDSoulCross, LibPotionNames.SOUL_CROSS, false, 0x47453d, 0);
		MinecraftForge.EVENT_BUS.register(this);
        LivingDeathEvent.EVENT.register(this::onEntityKill);
	}

//	@SubscribeEvent
	public void onEntityKill(LivingDeathEvent event) {
		Entity e = event.source.getEntity();
		if(e instanceof EntityLivingBase living) {
            if(hasEffect(living))
				living.heal(event.entityLiving.getMaxHealth() / 20);
		}
	}

}
