/**
 * This class was created by <SoundLogic>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 4, 2014, 10:38:50 PM (GMT)]
 */
package vazkii.botania.common.core.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import api.config.AddonConfig;
import api.config.ConfigUtils;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntitySquid;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lexicon.page.PageShedding;

public final class SheddingHandler {

	public SheddingHandler() {
		LivingEvent.LivingUpdateEvent.EVENT.register(this::onLivingUpdate);
	}

	public static ArrayList<ShedPattern> patterns = new ArrayList<>();
	public static ArrayList<ShedPattern> defaultPatterns = new ArrayList<>();

//	@SubscribeEvent
	public boolean onLivingUpdate(LivingUpdateEvent event) {
		if(event.entity.worldObj.isRemote)
			return false;

		ShedPattern pattern = getShedPattern(event.entity);

		if(pattern != null) {
			if(event.entity.worldObj.rand.nextInt(pattern.rate()) == 0)
				event.entity.entityDropItem(pattern.itemStack(), 0.0F);
		}
		return false;
	}

	public static ShedPattern getShedPattern(Entity entity) {
		for(ShedPattern pattern : patterns)
			if(pattern.entityClass.isInstance(entity))
				return pattern;

		return null;
	}

	public static boolean hasShedding() {
		return !patterns.isEmpty();
	}

	public static void addToLexicon() {
		if(!hasShedding())
			return;

		int i = 1;
		for(ShedPattern pattern : patterns) {
			PageShedding page = new PageShedding(String.valueOf(i), (String)EntityList.classToStringMapping.get(pattern.entityClass), pattern.lexiconSize, pattern.itemStack());
			LexiconData.shedding.addPage(page);
		}
	}

	public static void loadFromConfig(AddonConfig config, ArrayList<String> defaultNames) {

		for(Object o : EntityList.stringToClassMapping.entrySet()) {
			Entry<String, Class<? extends Entity>> entry = (Entry<String, Class<? extends Entity>>) o;

			if(EntityLiving.class.isAssignableFrom(entry.getValue())) {
				String name = entry.getKey();
				if(!defaultNames.contains(name))
					loadFromConfig(config, name, null);
			}
		}
	}

	public static void loadFromConfig(AddonConfig config, String key, ShedPattern defaultPattern) {
		int itemId;
		int metadata;
		int rate;
		int lexiconSize;
		String keyFixed = key.replace(":", ".");
		if(defaultPattern != null) {
			itemId = defaultPattern.itemStack().getItem().itemID;
			metadata = defaultPattern.itemStack().getItemDamage();
			rate = defaultPattern.rate;
			lexiconSize = defaultPattern.lexiconSize;
		}
		else {
			itemId = config.getInt("shedding." + keyFixed + ".item");
			rate = config.getInt("shedding." + keyFixed + ".rate");
			metadata = config.getInt("shedding." + keyFixed + ".metadata");
			lexiconSize = config.getInt("shedding." + keyFixed + ".lexiconDisplaySize");
		}

		if(itemId != 0 && !(itemId > Item.itemsList.length) && rate != -1)
			patterns.add(new ShedPattern((Class<? extends Entity>) EntityList.stringToClassMapping.get(key), new ItemStack(Item.itemsList[itemId], 1, metadata), rate, lexiconSize));
	}


	public static void writeToConfig(AddonConfig config) {
		defaultPatterns.add(new ShedPattern(EntityChicken.class, new ItemStack(Item.feather), 26000, 20));
//		defaultPatterns.add(new ShedPattern(EntityVillager.class, new ItemStack(Item.emerald), 226000, 40));
		defaultPatterns.add(new ShedPattern(EntitySpider.class, new ItemStack(Item.silk), 12000, 40));
		defaultPatterns.add(new ShedPattern(EntityBlaze.class, new ItemStack(Item.blazePowder), 8000, 40));
		defaultPatterns.add(new ShedPattern(EntityGhast.class, new ItemStack(Item.ghastTear), 9001, 30));
		defaultPatterns.add(new ShedPattern(EntitySkeleton.class, new ItemStack(Item.bone), 36000, 40));
		defaultPatterns.add(new ShedPattern(EntitySlime.class, new ItemStack(Item.slimeBall), 21000, 40));

		ArrayList<String> defaultNames = new ArrayList<>();
		try {
			var field = ConfigUtils.class.getDeclaredField("hasFinishedLoading");
			field.setAccessible(true);
			field.set(null, Boolean.FALSE);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		config.registerCategoryComment("shedding", "Shedding allows entities to drop items at set tick intervals. Item ID can be found with F3 + H and EMI. Read the lexica botania for more information.");
		for(ShedPattern pattern : defaultPatterns) {
			writeToConfig(config, pattern.getEntityString(), pattern);
			defaultNames.add(pattern.getEntityString());
		}

		for(Object o : EntityList.stringToClassMapping.entrySet()) {
			Entry<String, Class<? extends Entity>> entry = (Entry<String, Class<? extends Entity>>) o;

			if(EntityLiving.class.isAssignableFrom(entry.getValue())) {
				String name = entry.getKey();
				if(!defaultNames.contains(name))
					writeToConfig(config, name, null);
			}
		}

		config.readAndWriteConfig();
		ConfigUtils.finishedLoading();
		loadFromConfig(config, defaultNames);
	}

	public static void writeToConfig(AddonConfig config, String key, ShedPattern defaultPattern) {
		int itemId = 0;
		int metadata = 0;
		int rate = -1;
		int lexiconSize = 40;

		List<String> comment = new ArrayList<>();
		if(defaultPattern != null) {
			itemId = defaultPattern.itemStack().getItem().itemID;
			metadata = defaultPattern.itemStack().getItemDamage();
			rate = defaultPattern.rate;
			lexiconSize = defaultPattern.lexiconSize;
			comment.add("Original item: " + defaultPattern.itemStack().getDisplayName());
		}
		String keyFixed = key.replace(":", ".");
		config.registerCategoryComment("shedding." + keyFixed, "Configuration of Shedding for " + key);
		config.registerInt("shedding." + keyFixed + ".item", itemId, 0, Integer.MAX_VALUE, comment);
		config.registerInt("shedding." + keyFixed + ".rate", rate, -1, Integer.MAX_VALUE);
		config.registerInt("shedding." + keyFixed + ".metadata", metadata, 0, Integer.MAX_VALUE);
		config.registerInt("shedding." + keyFixed + ".lexiconDisplaySize", lexiconSize, 1, Integer.MAX_VALUE);
	}

	public record ShedPattern(Class<? extends Entity> entityClass, ItemStack itemStack, int rate, int lexiconSize) {

		@Override
		public ItemStack itemStack() {
			return itemStack.copy();
		}

		public String getEntityString() {
			return (String) EntityList.classToStringMapping.get(entityClass);
		}
	}

}
