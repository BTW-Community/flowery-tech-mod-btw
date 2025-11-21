/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 28, 2015, 4:41:43 PM (GMT)]
 */
package vazkii.botania.common.achievement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import btw.achievement.AchievementProvider;
import btw.achievement.event.BTWAchievementEvents;
import net.minecraft.src.*;
import vazkii.botania.common.Botania;

public class AchievementMod {

	public static List<Achievement<?>> achievements = new ArrayList<>();
	public static <T> Achievement<ItemStack> basic(String name, int x, int y, ItemStack icon, Achievement<?> parent) {
		var achievement = AchievementProvider.getBuilder(BTWAchievementEvents.ItemEvent.class).name(Botania.loc(name)).icon(icon).displayLocation(x, y).triggerCondition(ModAchievements.LATER);
		if (parent != null) {
			achievement = achievement.parents(parent);
		}
		var ach = achievement.build().registerAchievement(ModAchievements.BOTANIA_PAGE);
		achievements.add(ach);
		return ach;
	}

	public static <T> Achievement<ItemStack> basic(String name, int x, int y, Item icon, Achievement<?> parent) {
		return basic(name, x, y, new ItemStack(icon), parent);
	}

	public static <T> Achievement<ItemStack> basic(String name, int x, int y, Block icon, Achievement<?> parent) {
		return basic(name, x, y, new ItemStack(icon), parent);
	}

	public static <T> Achievement<ItemStack> basic(String name, int x, int y, ItemStack icon, Predicate<ItemStack> unlock, Achievement<?> parent) {
		var achievement = AchievementProvider.getBuilder(BTWAchievementEvents.ItemEvent.class).name(Botania.loc(name)).icon(icon).displayLocation(x, y).triggerCondition(unlock);
		if (parent != null) {
			achievement = achievement.parents(parent);
		}
		var ach = achievement.build().registerAchievement(ModAchievements.BOTANIA_PAGE);
		achievements.add(ach);
		return ach;
	}

	public static <T> Achievement<ItemStack> basic(String name, int x, int y, Item icon, Predicate<ItemStack> unlock, Achievement<?> parent) {
		return basic(name, x, y, new ItemStack(icon), unlock, parent);
	}

	public static <T> Achievement<ItemStack> basic(String name, int x, int y, Block icon, Predicate<ItemStack> unlock, Achievement<?> parent) {
		return basic(name, x, y, new ItemStack(icon), unlock, parent);
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
