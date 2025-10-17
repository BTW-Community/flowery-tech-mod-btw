/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 7, 2014, 5:53:16 PM (GMT)]
 */
package vazkii.botania.client.gui.lexicon.button;

import dev.bagel.util.BotaniaSounds;
import dev.bagel.util.GuiButtonSound;
import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;

public class GuiButtonLexicon extends GuiButtonSound {

	public GuiButtonLexicon(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
	}

	//todofix implement sounds?
	@Override
	public void func_146113_a()  {

		Minecraft.getMinecraft().sndManager.playSoundFX("botania:lexiconPage", 1.0f, 1.0f);
	}

}
