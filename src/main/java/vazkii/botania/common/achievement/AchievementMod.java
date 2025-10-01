/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 28, 2015, 4:41:43 PM (GMT)]
 */
package vazkii.botania.common.achievement;

import java.util.ArrayList;
import java.util.List;

import btw.achievement.AchievementProvider;
import btw.achievement.event.BTWAchievementEvents;
import net.minecraft.src.*;
import vazkii.botania.api.item.IRelic;

public class AchievementMod {

	public static List<Achievement> achievements = new ArrayList<>();

	public static <T> Achievement<ItemStack> AchievementModd(String name, int x, int y, ItemStack icon, Achievement<?> parent) {
		var achievement = AchievementProvider.getBuilder(BTWAchievementEvents.ItemEvent.class).name(new ResourceLocation("botania", name)).icon(icon).displayLocation(x, y).alwaysTrigger();
		if (parent != null) {
			achievement = achievement.parents(parent);
		}
		return achievement.build();
	}

/*	public AchievementMod(String name, int x, int y, ItemStack icon, Achievement parent) {
		super(new ResourceLocation("botania:", name), "botania:" + name, x, y, icon, parent);
		achievements.add(this);
		registerStat();

		if(icon.getItem() instanceof IRelic)
			((IRelic) icon.getItem()).setBindAchievement(this);
	}

	public AchievementMod(String name, int x, int y, Item icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}

	public AchievementMod(String name, int x, int y, Block icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}*/

}
