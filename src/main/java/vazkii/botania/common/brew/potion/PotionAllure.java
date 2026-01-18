/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 3, 2014, 12:15:09 AM (GMT)]
 */
package vazkii.botania.common.brew.potion;

import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityFishHook;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibPotionNames;

public class PotionAllure extends PotionMod {

	public PotionAllure() {
		super(ConfigHandler.potionIDAllure, LibPotionNames.ALLURE, false, 0x0034E4, 5);
//		MinecraftForge.EVENT_BUS.register(this);
		LivingEvent.LivingUpdateEvent.EVENT.register(this::onEntityUpdate);
	}

//	@SubscribeEvent
	public boolean onEntityUpdate(LivingUpdateEvent event) {
		EntityLivingBase e = event.entityLiving;
		if(e instanceof EntityPlayer && hasEffect(e)) {
			EntityFishHook hook = ((EntityPlayer) e).fishEntity;
			if(hook != null)
				hook.onUpdate();
		}
		return false;
	}

}
