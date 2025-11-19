/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 16, 2014, 6:13:08 PM (GMT)]
 */
package vazkii.botania.common.lexicon.page;

import net.minecraft.src.ResourceLocation;

import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class PageImage extends LexiconPage {

	ResourceLocation resource;

	public PageImage(String unlocalizedName, String resource) {
		super(unlocalizedName);
		this.resource = new ResourceLocation(resource);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
		renderLexiconTexture(gui, resource);

		int width = gui.getWidth() - 30;
		int height = gui.getHeight();
		int x = gui.getLeft() + 16;
		int y = gui.getTop() + height - 40;
		PageText.renderText(x, y, width, height, getUnlocalizedName());
	}

}
