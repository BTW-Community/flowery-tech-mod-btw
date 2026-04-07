/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [18/12/2015, 02:19:53 (GMT)]
 */
package vazkii.botania.client.render.world;

import dev.bagel.interfaces.WorldProviderExtensions;
import net.minecraft.src.Minecraft;
import net.minecraft.src.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.world.WorldTypeSkyblock;

public final class SkyblockRenderEvents {

	public SkyblockRenderEvents() {
		RenderWorldLastEvent.EVENT.register(this::onRender);
	}

	public void onRender(RenderWorldLastEvent event) {
		World world = Minecraft.getMinecraft().theWorld;
		if(ConfigHandler.enableFancySkybox && world.provider.dimensionId == 0 && (ConfigHandler.enableFancySkyboxInNormalWorlds || WorldTypeSkyblock.isWorldSkyblock(Minecraft.getMinecraft().theWorld))) {
			if(!(((WorldProviderExtensions) world.provider).getSkyRenderer() instanceof SkyblockSkyRenderer))
                ((WorldProviderExtensions) world.provider).setSkyRenderer(new SkyblockSkyRenderer());
		}
	}
	
}
