/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 14, 2014, 5:10:16 PM (GMT)]
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.Minecraft;
import net.minecraft.src.World;
import vazkii.botania.api.corporea.CorporeaHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.ArrayList;
import java.util.List;

public final class CommonTickHandler {
	static {
		TickEvent.ClientTickEvent.EVENT.register(CommonTickHandler::onTick);
		TickEvent.WorldTickEvent.EVENT.register(CommonTickHandler::onTick);
	}
	public static void init() {}

	@SubscribeEvent
	public static void onTick(WorldTickEvent event) {
		if(event.phase == Phase.END) {
			List<Entity> entities = new ArrayList(event.world.loadedEntityList);
			for(Entity entity : entities)
				if(entity instanceof EntityItem ei)
					TerrasteelCraftingHandler.onEntityUpdate(ei);

			CorporeaHelper.clearCache();
		}
	}

//	@Environment(EnvType.CLIENT)
	public static void onTick(ClientTickEvent event) {
		if(event.phase == Phase.END) {
			World world = Minecraft.getMinecraft().theWorld;
			if(world != null) {
				List<Entity> entities = new ArrayList(world.loadedEntityList);
				for(Entity entity : entities)
					if(entity instanceof EntityItem)
						TerrasteelCraftingHandler.onEntityUpdate((EntityItem) entity);
			}

			CorporeaHelper.clearCache();
		}
	}

}
