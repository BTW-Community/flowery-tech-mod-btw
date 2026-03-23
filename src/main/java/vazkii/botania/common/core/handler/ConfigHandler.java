/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 13, 2014, 9:01:32 PM (GMT)]
 */
package vazkii.botania.common.core.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.config.AddonConfig;
import emi.shims.java.net.minecraft.text.Text;
import emi.shims.java.net.minecraft.util.Formatting;
import net.minecraft.src.EntityPlayer;
import vazkii.botania.common.lib.LibPotionNames;
//todofix move config to new system
public final class ConfigHandler {

//	public static Configuration config;
	public static ConfigAdaptor adaptor;

	private static final String CATEGORY_POTIONS = "potions";
	private static final String GENERAL = "general";

	public static int hardcorePassiveGeneration = 72000;

	public static boolean useAdaptativeConfig = true;

	public static boolean enableDefaultRecipes = true;

	public static boolean useShaders = true;
	public static boolean lexiconRotatingItems = true;
	public static boolean lexiconJustifiedText = false;
	public static boolean subtlePowerSystem = false;
	public static boolean staticWandBeam = false;
	public static boolean boundBlockWireframe = true;
	public static boolean lexicon3dModel = true;
	public static boolean oldPylonModel = false;
	public static double flowerParticleFrequency = 0.75D;
	public static boolean blockBreakParticles = true;
	public static boolean blockBreakParticlesTool = true;
	public static boolean elfPortalParticlesEnabled = true;
	public static boolean chargingAnimationEnabled = true;
	public static boolean useVanillaParticleLimiter = true;
	public static boolean silentSpreaders = false;
	public static boolean renderBaubles = true;
	public static boolean enableSeasonalFeatures = true;
	public static boolean useShiftForQuickLookup = false;
	public static boolean enableArmorModels = true;
	public static boolean enableFancySkybox = true;
	public static boolean enableFancySkyboxInNormalWorlds = false;
	
	public static int manaBarHeight = 29;
	public static int flightBarHeight = 49;
	public static int flightBarBreathHeight = 59;
	public static int glSecondaryTextureUnit = 8;

	public static boolean altFlowerTextures = false;
	public static boolean matrixMode = false;
	public static boolean referencesEnabled = true;

	public static int spreaderPositionShift = 1;
	public static boolean flowerForceCheck = true;
	public static boolean enderPickpocketEnabled = true;

	public static boolean fallenKanadeEnabled = true;
	public static boolean darkQuartzEnabled = true;
	public static boolean enchanterEnabled = true;
	public static boolean fluxfieldEnabled = false;
	public static boolean relicsEnabled = true;
	public static boolean stones18Enabled = true;
	public static boolean ringOfOdinFireResist = true;
	public static boolean enderStuff19Enabled = true;
	public static boolean invertMagnetRing = false;
	public static boolean enableThaumcraftStablizers = false;
	
	public static int harvestLevelWeight = 2;
	public static int harvestLevelBore = 3;

	public static int flowerQuantity = 10;
	public static int flowerDensity = 2;
	public static int flowerPatchSize = 6;
	public static int flowerPatchChance = 16;
	public static double flowerTallChance = 0.05D;
	public static int mushroomQuantity = 40;

	private static boolean verifiedPotionArray = false;
	private static int potionArrayLimit = 0;

	public static int potionIDSoulCross = 24;
	public static int potionIDFeatherfeet = 25;
	public static int potionIDEmptiness = 26;
	public static int potionIDBloodthirst = 27;
	public static int potionIDAllure = 28;
	public static int potionIDClear = 29;

	public static void loadConfig(File configFile) {
/*		config = new Configuration(configFile);

		config.load();
		load();

		FMLCommonHandler.instance().bus().register(new ChangeListener());*/
	}
	private static AddonConfig config;
	public static void register(AddonConfig config) {
		String desc;
		ConfigHandler.config = config;

		desc = "Set this to false to disable the Adaptative Config. Adaptative Config changes any default config values from old versions to the new defaults to make sure you aren't missing out on changes because of old configs. It will not touch any values that were changed manually.";
		useAdaptativeConfig = registerBool("adaptativeConfig.enabled", desc, useAdaptativeConfig);
		adaptor = new ConfigAdaptor(useAdaptativeConfig);

		desc = "Set this to false to disable the use of shaders for some of the mod's renders.";
		useShaders = registerBool("shaders.enabled", desc, useShaders);

		desc = "Set this to false to disable the rotating items in the petal and rune entries in the Lexica Botania.";
		lexiconRotatingItems = registerBool("lexicon.enable.rotatingItems", desc, lexiconRotatingItems);

		desc = "Set this to true to enable justified text in the Lexica Botania's text pages.";
		lexiconJustifiedText = registerBool("lexicon.enable.justifiedText", desc, lexiconJustifiedText);

		desc = "Set this to true to set the power system's particles to be a lot more subtle. Good for low-end systems, if the particles are causing lag.";
		subtlePowerSystem = registerBool("powerSystem.subtle", desc, subtlePowerSystem);

		desc = "Set this to true to use a static wand beam that shows every single position of the burst, similar to the way it used to work on old Botania versions. Warning: Disabled by default because it may be laggy.";
		staticWandBeam = registerBool("wandBeam.static", desc, staticWandBeam);

		desc = "Set this to false to disable the wireframe when looking a block bound to something (spreaders, flowers, etc).";
		boundBlockWireframe = registerBool("boundBlock.wireframe.enabled", desc, boundBlockWireframe);

		desc = "Set this to false to disable the animated 3D render for the Lexica Botania.";
		lexicon3dModel = registerBool("lexicon.render.3D", desc, lexicon3dModel);

		desc = "Set this to true to use the old (non-.obj, pre beta18) pylon model";
		oldPylonModel = registerBool("pylonModel.old", desc, oldPylonModel);

		desc = "The frequency in which particles spawn from normal (worldgen) mystical flowers";
		flowerParticleFrequency = registerDouble("flowerParticles.frequency", desc, flowerParticleFrequency, 0d, 1d);

		desc = "Set this to false to remove the block breaking particles from the flowers and other items in the mod.";
		blockBreakParticles = registerBool("blockBreakingParticles.enabled", desc, blockBreakParticles);

		desc = "Set this to false to remove the block breaking particles from the Mana Shatterer, as there can be a good amount in higher levels.";
		blockBreakParticlesTool = registerBool("blockBreakingParticlesTool.enabled", desc, blockBreakParticlesTool);

		desc = "Set this to false to disable the particles in the elven portal.";
		elfPortalParticlesEnabled = registerBool("elfPortal.particles.enabled", desc, elfPortalParticlesEnabled);

		desc = "Set this to false to disable the animation when an item is charging on top of a mana pool.";
		chargingAnimationEnabled = registerBool("chargeAnimation.enabled", desc, chargingAnimationEnabled);

		desc = "Set this to false to always display all particles regardless of the \"Particles\" setting in the Vanilla options menu.";
		useVanillaParticleLimiter = registerBool("vanillaParticleConfig.enabled", desc, useVanillaParticleLimiter);

		desc = "Set this to true to disable the mana spreader shooting sound.";
		silentSpreaders = registerBool("manaSpreaders.silent", desc, silentSpreaders);

		desc = "Set this to false to disable rendering of baubles in the player.";
		renderBaubles = registerBool("baubleRender.enabled", desc, renderBaubles);

		desc = "Set this to false to disable seasonal features, such as halloween and christmas.";
		enableSeasonalFeatures = registerBool("seasonalFeatures.enabled", desc, enableSeasonalFeatures);

		desc = "Set this to true to use Shift instead of Ctrl for the inventory lexica botania quick lookup feature.";
		useShiftForQuickLookup = registerBool("quickLookup.useShift", desc, useShiftForQuickLookup);

		desc = "Set this to false to disable custom armor models.";
		enableArmorModels = registerBool("armorModels.enable", desc, enableArmorModels);

		desc = "Set this to false to disable the fancy skybox in Garden of Glass.";
		enableFancySkybox = registerBool("fancySkybox.enable", desc, enableFancySkybox);
		
		desc = "Set this to true to enable the fancy skybox in non Garden of Glass worlds. (Does not require Garden of Glass loaded to use, needs 'fancySkybox.enable' to be true as well)";
		enableFancySkyboxInNormalWorlds = registerBool("fancySkybox.normalWorlds", desc, enableFancySkyboxInNormalWorlds);
		
		desc = "The height of the mana display bar in above the XP bar. You can change this if you have a mod that changes where the XP bar is.";
		manaBarHeight = registerInt("manaBar.height", desc, manaBarHeight);

		desc = "The height of the Flugel Tiara flight bar. You can change this if you have a mod that adds a bar in that spot.";
		flightBarHeight = registerInt("flightBar.height", desc, flightBarHeight);

		desc = "The height of the Flugel Tiara flight bar if your breath bar is shown. You can change this if you have a mod that adds a bar in that spot.";
		flightBarBreathHeight = registerInt("flightBarBreath.height", desc, flightBarBreathHeight);
		
		desc = "The GL Texture Unit to use for the secondary sampler passed in to the Lexica Botania's category button shader. DO NOT TOUCH THIS IF YOU DON'T KNOW WHAT YOU'RE DOING";
		glSecondaryTextureUnit = registerInt("shaders.secondaryUnit", desc, glSecondaryTextureUnit);

		desc = "Set this to true to use alternate flower textures by Futureazoo, not all flowers are textured. http://redd.it/2b3o3f";
		altFlowerTextures = registerBool("flowerTextures.alt", desc, altFlowerTextures);

		desc = "Set this to true if you are the chosen one. For lovers of glitch art and just general mad people.";
		matrixMode = registerBool("matrixMode.enabled", desc, matrixMode);

		desc = "Set this to false to disable the references in the flower tooltips. (You monster D:)";
		referencesEnabled = registerBool("references.enabled", desc, referencesEnabled);

		desc = "Do not ever touch this value if not asked to. Possible symptoms of doing so include your head turning backwards, the appearance of Titans near the walls or you being trapped in a game of Sword Art Online.";
		spreaderPositionShift = registerInt("spreader.posShift", desc, spreaderPositionShift);

		desc = "Turn this off ONLY IF you're on an extremely large world with an exaggerated count of Mana Spreaders/Mana Pools and are experiencing TPS lag. This toggles whether flowers are strict with their checking for connecting to pools/spreaders or just check whenever possible.";
		flowerForceCheck = registerBool("flower.forceCheck", desc, flowerForceCheck);

		desc = "Set to false to disable the ability for the Hand of Ender to pickpocket other players' ender chests.";
		enderPickpocketEnabled = registerBool("enderPickpocket.enabled", desc, enderPickpocketEnabled);

		desc = "Set this to false to disable the Fallen Kanade flower (gives Regeneration). This config option is here for those using Blood Magic. Note: Turning this off will not remove ones already in the world, it'll simply prevent the crafting.";
		fallenKanadeEnabled = registerBool("fallenKanade.enabled", desc, fallenKanadeEnabled);

		desc = "Set this to false to disable the Smokey Quartz blocks. This config option is here for those using Thaumic Tinkerer";
		darkQuartzEnabled = registerBool("darkQuartz.enabled", desc, darkQuartzEnabled);

		desc = "Set this to false to disable the Mana Enchanter. Since some people find it OP or something. This only disables the entry and creation. Old ones that are already in the world will stay.";
		enchanterEnabled = registerBool("manaEnchanter.enabled", desc, enchanterEnabled);

//		desc = "Set this to false to disable the Mana Fluxfield (generates RF from mana). This only disables the entry and creation. Old ones that are already in the world will stay.";
//		fluxfieldEnabled = loadPropBool("manaFluxfield.enabled", desc, fluxfieldEnabled);

		desc = "Set this to false to disable the Relic System. This only disables the entries, drops and achievements. Old ones that are already in the world will stay.";
		relicsEnabled = registerBool("relics.enabled", desc, relicsEnabled);

		desc = "Set this to false to disable the 1.8 Stones available as mana alchemy recipes. This only disables the recipes and entries. Old ones that are already in the world will stay.";
		stones18Enabled = registerBool("18stones.enabled", desc, stones18Enabled);

		desc = "Set this to false to make the Ring of Odin not apply fire resistance. Mostly for people who use Witchery transformations.";
		ringOfOdinFireResist = registerBool("ringOfOdin.fireResist", desc, ringOfOdinFireResist);

		desc = "Set this to false to disable the 1.9 Ender features available as recipes. This only disables the recipes and entries. Old ones that are already in the world will stay.";
		enderStuff19Enabled = registerBool("19enderStuff.enabled", desc, enderStuff19Enabled);

		desc = "Set this to true to invert the Ring of Magnetization's controls (from shift to stop to shift to work)";
		invertMagnetRing = registerBool("magnetRing.invert", desc, invertMagnetRing);

//		desc = "Set this to false to disable Thaumcraft Infusion Stabilizing in botania blocks";
//		enableThaumcraftStablizers = loadPropBool("thaumraftStabilizers.enabled", desc, enableThaumcraftStablizers);
		
		desc = "The harvest level of the Mana Lens: Weight. 3 is diamond level. Defaults to 2 (iron level)";
		harvestLevelWeight = registerInt("harvestLevel.weightLens", desc, harvestLevelWeight);

		desc = "The harvest level of the Mana Lens: Bore. 3 is diamond level. Defaults to 3";
		harvestLevelBore = registerInt("harvestLevel.boreLens", desc, harvestLevelBore);
		
		desc = "The quantity of Botania flower patches to generate in the world, defaults to 2, the lower the number the less patches generate.";
		flowerQuantity = registerInt("worldgen.flower.quantity", desc, flowerQuantity);

		desc = "The amount of time it takes a Passive flower to decay and turn into a dead bush. Defaults to 72000, 60 minutes. Setting this to -1 disables the feature altogether.";
		hardcorePassiveGeneration = registerInt("passiveDecay.time", desc, hardcorePassiveGeneration);
		
		desc = "The density of each Botania flower patch generated, defaults to 2, the lower the number, the less each patch will have.";
		adaptor.addMappingInt(0, "worldgen.flower.density", 16);
		adaptor.addMappingInt(238, "worldgen.flower.density", 2);
		flowerDensity = registerInt("worldgen.flower.density", desc, flowerDensity);

		desc = "The size of each Botania flower patch, defaults to 6. The larger this is the farther the each patch can spread";
		flowerPatchSize = registerInt("worldgen.flower.patchSize", desc, flowerPatchSize);

		desc = "The inverse chance for a Botania flower patch to be generated, defaults to 16. The higher this value is the less patches will exist and the more flower each will have.";
		adaptor.addMappingInt(0, "worldgen.flower.patchChance", 4);
		adaptor.addMappingInt(238, "worldgen.flower.patchChance", 16);
		flowerPatchChance = registerInt("worldgen.flower.patchChance", desc, flowerPatchChance);

		desc = "The chance for a Botania flower generated in a patch to be a tall flower. 0.1 is 10%, 1 is 100%. Defaults to 0.05";
		adaptor.addMappingDouble(0, "worldgen.flower.tallChance", 0.1);
		adaptor.addMappingDouble(238, "worldgen.flower.tallChance", 0.05);
		flowerTallChance = registerDouble("worldgen.flower.tallChance", desc, flowerTallChance, 0d, 1d);

		desc = "The quantity of Botania mushrooms to generate underground, in the world, defaults to 40, the lower the number the less patches generate.";
		mushroomQuantity = registerInt("worldgen.mushroom.quantity", desc, mushroomQuantity);

		desc = "Enables all built-in recipes. This can be false for expert modpacks that wish to supply their own.";
		enableDefaultRecipes = registerBool("recipes.enabled", desc, enableDefaultRecipes);

		potionIDSoulCross = registerPotionId(LibPotionNames.SOUL_CROSS, potionIDSoulCross);
		potionIDFeatherfeet = registerPotionId(LibPotionNames.FEATHER_FEET, potionIDFeatherfeet);
		potionIDEmptiness = registerPotionId(LibPotionNames.EMPTINESS, potionIDEmptiness);
		potionIDBloodthirst = registerPotionId(LibPotionNames.BLOODTHIRST, potionIDBloodthirst);
		potionIDAllure = registerPotionId(LibPotionNames.ALLURE, potionIDAllure);
		potionIDClear = registerPotionId(LibPotionNames.CLEAR, potionIDClear);

/*		if(config.hasChanged())
			config.save();*/
	}

	public static void load(AddonConfig config) {
		ConfigHandler.config = config;

		useAdaptativeConfig = getBool("adaptativeConfig.enabled");
		adaptor = new ConfigAdaptor(useAdaptativeConfig);

		useShaders = getBool("shaders.enabled");

		lexiconRotatingItems = getBool("lexicon.enable.rotatingItems");

		lexiconJustifiedText = getBool("lexicon.enable.justifiedText");

		subtlePowerSystem = getBool("powerSystem.subtle");

		staticWandBeam = getBool("wandBeam.static");

		boundBlockWireframe = getBool("boundBlock.wireframe.enabled");

		lexicon3dModel = getBool("lexicon.render.3D");

		oldPylonModel = getBool("pylonModel.old");

		flowerParticleFrequency = getDouble("flowerParticles.frequency");

		blockBreakParticles = getBool("blockBreakingParticles.enabled");

		blockBreakParticlesTool = getBool("blockBreakingParticlesTool.enabled");

		elfPortalParticlesEnabled = getBool("elfPortal.particles.enabled");

		chargingAnimationEnabled = getBool("chargeAnimation.enabled");

		useVanillaParticleLimiter = getBool("vanillaParticleConfig.enabled");

		silentSpreaders = getBool("manaSpreaders.silent");

		renderBaubles = getBool("baubleRender.enabled");

		enableSeasonalFeatures = getBool("seasonalFeatures.enabled");

		useShiftForQuickLookup = getBool("quickLookup.useShift");

		enableArmorModels = getBool("armorModels.enable");

		enableFancySkybox = getBool("fancySkybox.enable");

		enableFancySkyboxInNormalWorlds = getBool("fancySkybox.normalWorlds");

		manaBarHeight = getInt("manaBar.height");

		flightBarHeight = getInt("flightBar.height");

		flightBarBreathHeight = getInt("flightBarBreath.height");

		glSecondaryTextureUnit = getInt("shaders.secondaryUnit");

		altFlowerTextures = getBool("flowerTextures.alt");

		matrixMode = getBool("matrixMode.enabled");

		referencesEnabled = getBool("references.enabled");

		spreaderPositionShift = getInt("spreader.posShift");

		flowerForceCheck = getBool("flower.forceCheck");

		enderPickpocketEnabled = getBool("enderPickpocket.enabled");

		fallenKanadeEnabled = getBool("fallenKanade.enabled");

		darkQuartzEnabled = getBool("darkQuartz.enabled");

		enchanterEnabled = getBool("manaEnchanter.enabled");

//		desc = "Set this to false to disable the Mana Fluxfield (generates RF from mana). This only disables the entry and creation. Old ones that are already in the world will stay.";
//		fluxfieldEnabled = loadPropBool("manaFluxfield.enabled");

		relicsEnabled = getBool("relics.enabled");

		stones18Enabled = getBool("18stones.enabled");

		ringOfOdinFireResist = getBool("ringOfOdin.fireResist");

		enderStuff19Enabled = getBool("19enderStuff.enabled");

		invertMagnetRing = getBool("magnetRing.invert");

//		desc = "Set this to false to disable Thaumcraft Infusion Stabilizing in botania blocks";
//		enableThaumcraftStablizers = loadPropBool("thaumraftStabilizers.enabled");

		harvestLevelWeight = getInt("harvestLevel.weightLens");

		harvestLevelBore = getInt("harvestLevel.boreLens");

		flowerQuantity = getInt("worldgen.flower.quantity");

		hardcorePassiveGeneration = getInt("passiveDecay.time");

		adaptor.addMappingInt(0, "worldgen.flower.density", 16);
		adaptor.addMappingInt(238, "worldgen.flower.density", 2);
		flowerDensity = getInt("worldgen.flower.density");

		flowerPatchSize = getInt("worldgen.flower.patchSize");

		adaptor.addMappingInt(0, "worldgen.flower.patchChance", 4);
		adaptor.addMappingInt(238, "worldgen.flower.patchChance", 16);
		flowerPatchChance = getInt("worldgen.flower.patchChance");

		adaptor.addMappingDouble(0, "worldgen.flower.tallChance", 0.1);
		adaptor.addMappingDouble(238, "worldgen.flower.tallChance", 0.05);
		flowerTallChance = getDouble("worldgen.flower.tallChance");

		mushroomQuantity = getInt("worldgen.mushroom.quantity");

		enableDefaultRecipes = getBool("recipes.enabled");

		potionIDSoulCross = getPotionId(LibPotionNames.SOUL_CROSS);
		potionIDFeatherfeet = getPotionId(LibPotionNames.FEATHER_FEET);
		potionIDEmptiness = getPotionId(LibPotionNames.EMPTINESS);
		potionIDBloodthirst = getPotionId(LibPotionNames.BLOODTHIRST);
		potionIDAllure = getPotionId(LibPotionNames.ALLURE);
		potionIDClear = getPotionId(LibPotionNames.CLEAR);
	}

	public static void loadPostInit() {
/*		SheddingHandler.loadFromConfig(config);

		if(config.hasChanged())
			config.save();*/
	}

	public static int registerInt(String propName, String desc, int default_) {
		propName = GENERAL + "." + propName;
		config.registerInt(propName, default_, desc);

//		if(adaptor != null)
//			adaptor.adaptPropertyInt(prop, prop.getInt(default_));

		return config.getInt(propName);
	}

	public static int getInt(String propName) {
		propName = GENERAL + "." + propName;
		return config.getInt(propName);
	}

	public static double registerDouble(String propName, String desc, double default_, double min, double max) {
		propName = GENERAL + "." + propName;
		config.registerDouble(propName, default_, min, max, desc);

//		if(adaptor != null)
//			adaptor.adaptPropertyDouble(prop, prop.getDouble(default_));

		return config.getDouble(propName);
	}

	public static double registerDouble(String propName, String desc, double default_) {
		propName = GENERAL + "." + propName;
		config.registerDouble(propName, default_, desc);

//		if(adaptor != null)
//			adaptor.adaptPropertyDouble(prop, prop.getDouble(default_));

		return config.getDouble(propName);
	}

	public static double getDouble(String propName) {
		propName = GENERAL + "." + propName;

		return config.getDouble(propName);
	}

	public static boolean registerBool(String propName, String desc, boolean default_) {
		propName = GENERAL + "." + propName;
		config.registerBoolean(propName, default_, desc);

//		if(adaptor != null)
//			adaptor.adaptPropertyBool(prop, prop.getBoolean(default_));

		return config.getBoolean(propName);
	}

	public static boolean getBool(String propName) {
		propName = GENERAL + "." + propName;

		return config.getBoolean(propName);
	}

	public static int registerPotionId(String propName, int default_) {
		if(!verifiedPotionArray)
			verifyPotionArray();
		propName = CATEGORY_POTIONS + "." + propName;
		config.registerInt(propName, default_, 0, potionArrayLimit);

		return config.getInt(propName);
	}

	public static int getPotionId(String propName) {
		propName = CATEGORY_POTIONS + "." + propName;

		return config.getInt(propName);
	}

	private static void verifyPotionArray() {
		//todo check potion array limit
		potionArrayLimit = 127;

		verifiedPotionArray = true;
	}

	public static class ConfigAdaptor {

		private boolean enabled;

		private Map<String, List<AdaptableValue>> adaptableValues = new HashMap<>();
		private List<String> changes = new ArrayList<>();

		public ConfigAdaptor(boolean enabled) {
			this.enabled = enabled;
		}

		/*public <T> void adaptProperty(Property prop, T val) {
			if(!enabled)
				return;

			String name = prop.getName();

			if(!adaptableValues.containsKey(name))
				return;

			AdaptableValue<T> bestValue = null;
			for(AdaptableValue<T> value : adaptableValues.get(name)) {
				if(bestValue == null || value.version > bestValue.version)
					bestValue = value;
			}

			if(bestValue != null) {
				T expected = bestValue.value;
				T def = (T) prop.getDefault();
				
				if(areEqualNumbers(val, expected) && !areEqualNumbers(val, def)) {
					prop.setValue(def.toString());
					changes.add(" " + prop.getName() + ": " + val + " -> " + def);
				}
			}
		}*/

		public <T> void addMapping(int version, String key, T val) {
			if(!enabled)
				return;

			AdaptableValue<T> adapt = new AdaptableValue<T>(version, val);
			if(!adaptableValues.containsKey(key)) {
				ArrayList list = new ArrayList<>();
				adaptableValues.put(key, list);
			}

			List<AdaptableValue> list = adaptableValues.get(key);
			list.add(adapt);
		}
		
		public boolean areEqualNumbers(Object v1, Object v2) {
			double epsilon = 1.0E-6;
			float v1f = ((Number) v1).floatValue();
			float v2f;
			
			if(v2 instanceof String)
				v2f = Float.parseFloat((String) v2);
			else v2f = ((Number) v2).floatValue();

			return Math.abs(v1f - v2f) < epsilon;
		}

		public void tellChanges(EntityPlayer player) {
			if(changes.size() == 0)
				return;

			player.addChatMessage(Text.translatable("botaniamisc.adaptativeConfigChanges").formatted(Formatting.GOLD).toString());
			for(String change : changes)
				player.addChatMessage(Text.translatable(change).formatted(Formatting.LIGHT_PURPLE).toString());
		}

		public void addMappingInt(int version, String key, int val) {
			this.<Integer>addMapping(version, key, val);
		}

		public void addMappingDouble(int version, String key, double val) {
			this.<Double>addMapping(version, key, val);
		}

		public void addMappingBool(int version, String key, boolean val) {
			this.<Boolean>addMapping(version, key, val);
		}

/*		public void adaptPropertyInt(Property prop, int val) {
			this.<Integer>adaptProperty(prop, val);
		}

		public void adaptPropertyDouble(Property prop, double val) {
			this.<Double>adaptProperty(prop, val);
		}

		public void adaptPropertyBool(Property prop, boolean val) {
			this.<Boolean>adaptProperty(prop, val);
		}*/

		public static class AdaptableValue<T> {

			public final int version;
			public final T value;
			public final Class<? extends T> valueType;

			public AdaptableValue(int version, T value) {
				this.version = version;
				this.value = value;
				valueType = (Class<? extends T>) value.getClass();
			}

		}

	}

	public static class ChangeListener {

/*		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals(LibMisc.MOD_ID))
				load();
		}*/

	}
}
