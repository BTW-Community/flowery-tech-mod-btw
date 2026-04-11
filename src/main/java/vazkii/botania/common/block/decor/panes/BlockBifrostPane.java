/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 30, 2015, 10:18:50 PM (GMT)]
 */
package vazkii.botania.common.block.decor.panes;

import net.minecraft.src.IconRegister;
import net.minecraft.src.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.block.ModBlocks;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockBifrostPane extends BlockModPane {

	public BlockBifrostPane(int id) {
		super(id, ModBlocks.bifrostPerm);
		MinecraftForge.EVENT_BUS.register(this);
		TextureStitchEvent.Pre.EVENT.register(this::loadTextures);
	}

	@Environment(EnvType.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {
		if(event.map.getTextureType() == 0) {
			TextureAtlasSprite icon = new InterpolatedIcon("botania:bifrostPermPane");
			if(event.map.registerIcon("botania:bifrostPermPane", icon) != null)
				iconTop = icon;
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister reg) {
		// NO-OP
	}

}
