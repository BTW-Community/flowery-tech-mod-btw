/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 3, 2015, 5:55:36 PM (GMT)]
 */
package vazkii.botania.client.gui;

import net.minecraft.src.GuiAchievements;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StatFileWriter;
import vazkii.botania.common.achievement.ModAchievements;

public class GuiAchievementsHacky extends GuiAchievements {

	public GuiAchievementsHacky(StatFileWriter p_i45026_2_) {
		super(p_i45026_2_);
//		ReflectionHelper.setPrivateValue(GuiAchievements.class, this, ModAchievements.pageIndex, "currentPage");
	}

	@Override
	public void initGui() {
		super.initGui();
		((GuiButton) buttonList.get(1)).displayString = ModAchievements.botaniaPage.getName();
	}

}
