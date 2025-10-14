/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Feb 16, 2015, 1:39:10 PM (GMT)]
 */
package vazkii.botania.common.lexicon.page;

import java.awt.Desktop;
import java.net.URI;

import net.minecraft.src.*;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class PageTutorial extends PageText {

	// Turn this on once we have an up to date video
	private static final boolean VIDEO_ENABLED = false;

	GuiButton buttonText, buttonVideo;

	public PageTutorial(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	public void onOpened(IGuiLexiconEntry gui) {
		buttonText = new GuiButton(101, gui.getLeft() + 20, gui.getTop() + gui.getHeight() - 40, 50, 20, StatCollector.translateToLocal("botaniamisc.tutorialText"));
		if(VIDEO_ENABLED)
			buttonVideo = new GuiButton(101, gui.getLeft() + 75, gui.getTop() + gui.getHeight() - 40, 50, 20, StatCollector.translateToLocal("botaniamisc.tutorialVideo"));

		gui.getButtonList().add(buttonText);
		if(VIDEO_ENABLED)
			gui.getButtonList().add(buttonVideo);
	}

	@Override
	public void onClosed(IGuiLexiconEntry gui) {
		gui.getButtonList().remove(buttonText);
		if(VIDEO_ENABLED)
			gui.getButtonList().remove(buttonVideo);
	}

	@Override
	public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
		super.renderScreen(gui, mx, my);

		if(!VIDEO_ENABLED)
			PageText.renderText(buttonText.xPosition + buttonText.width + 4, buttonText.yPosition - 14, 65, 100, "botaniamisc.noVideo");
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
		if(button == buttonText) {
			GuiLexicon.startTutorial();
			Minecraft.getMinecraft().displayGuiScreen(new GuiLexicon());
			Minecraft.getMinecraft().thePlayer.addChatMessage(ChatMessageComponent.createFromTranslationKey("botaniamisc.tutorialStarted").setColor(EnumChatFormatting.GREEN).toString());
		} else if(button == buttonVideo && Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=rx0xyejC6fI"));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
