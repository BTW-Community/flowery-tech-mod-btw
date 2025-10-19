/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 28, 2015, 4:27:39 PM (GMT)]
 */
package vazkii.botania.common.achievement;

import btw.achievement.AchievementTab;
import btw.item.BTWItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import vazkii.botania.common.lib.LibAchievementNames;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibMisc;

public final class ModAchievements {
	
	public static final AchievementTab botaniaPage = new AchievementTab("botania")/*.setIcon(BTWItems.firePlough)*/;
	public static int pageIndex;

	public static Achievement flowerPickup;
	public static Achievement lexiconUse;
	public static Achievement daybloomPickup;
	public static Achievement cacophoniumCraft;
	public static Achievement manaPoolPickup;

	public static Achievement endoflamePickup;
	public static Achievement tinyPotatoPet;
	public static Achievement sparkCraft;
	public static Achievement baubleWear;
	public static Achievement manaCookieEat;
	public static Achievement manaweaveArmorCraft;
	public static Achievement craftingHaloCraft;
	public static Achievement manaCartCraft;
	public static Achievement enchanterMake;
	public static Achievement runePickup;

	public static Achievement dirtRodCraft;
	public static Achievement terraformRodCraft;
	public static Achievement manaBlasterShoot;
	public static Achievement pollidisiacPickup;
	public static Achievement brewPickup;
	public static Achievement terrasteelPickup;

	public static Achievement terrasteelWeaponCraft;
	public static Achievement elfPortalOpen;

	public static Achievement kekimurusPickup;
	public static Achievement heiseiDreamPickup;
	public static Achievement bubbellPickup;
	public static Achievement luminizerRide;

	public static Achievement enderAirMake;
	public static Achievement corporeaCraft;

	public static Achievement gaiaGuardianKill;

	public static Achievement spawnerMoverUse;
	public static Achievement tiaraWings;
	public static Achievement manaBombIgnite;
	public static Achievement dandelifeonPickup;

	public static Achievement signalFlareStun;
	public static Achievement l20ShardUse;
	public static Achievement gaiaGuardianNoArmor;
	public static Achievement rankSSPick;
	public static Achievement superCorporeaRequest;
	public static Achievement pinkinator;

	public static Achievement relicInfiniteFruit;
	public static Achievement relicKingKey;
	public static Achievement relicFlugelEye;
	public static Achievement relicThorRing;
	public static Achievement relicOdinRing;
	public static Achievement relicLokiRing;
	public static Achievement relicAesirRing;

	public static Achievement nullFlower;
	public static Achievement desuGun;

	//todo achievements
	public static void init() {
		/*flowerPickup = AchievementMod.AchievementModd(LibAchievementNames.FLOWER_PICKUP, 0, 4, new ItemStack(ModBlocks.flower, 1, 6), null);
		lexiconUse = AchievementMod.AchievementModd(LibAchievementNames.LEXICON_USE, 1, 5, ModItems.lexicon, flowerPickup);
		daybloomPickup = AchievementMod.AchievementModd(LibAchievementNames.DAYBLOOM_PICKUP, 3, 5, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAYBLOOM), lexiconUse);
		cacophoniumCraft = AchievementMod.AchievementModd(LibAchievementNames.CACOPHONIUM_CRAFT, -1, 2, ModItems.cacophonium, flowerPickup);
		manaPoolPickup = AchievementMod.AchievementModd(LibAchievementNames.MANA_POOL_PICKUP, 3, 2, ModBlocks.pool, daybloomPickup);

		endoflamePickup = AchievementMod.AchievementModd(LibAchievementNames.ENDOFLAME_PICKUP, 2, 0, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENDOFLAME), manaPoolPickup);
		tinyPotatoPet = AchievementMod.AchievementModd(LibAchievementNames.TINY_POTATO_PET, 2, -2, ModBlocks.tinyPotato, manaPoolPickup);
		sparkCraft = AchievementMod.AchievementModd(LibAchievementNames.SPARK_CRAFT, 4, -2, ModItems.spark, manaPoolPickup);
		baubleWear = AchievementMod.AchievementModd(LibAchievementNames.BAUBLE_WEAR, 4, 0, ModItems.manaRing, manaPoolPickup);
		manaCookieEat = AchievementMod.AchievementModd(LibAchievementNames.MANA_COOKIE_EAT, 2, -4, ModItems.manaCookie, manaPoolPickup);
		manaweaveArmorCraft = AchievementMod.AchievementModd(LibAchievementNames.MANAWEAVE_ARMOR_CRAFT, 4, -4, ModItems.manaweaveChest, manaPoolPickup);
		craftingHaloCraft = AchievementMod.AchievementModd(LibAchievementNames.CRAFTING_HALO_CRAFT, 3, -6, ModItems.craftingHalo, manaPoolPickup);
		manaCartCraft = AchievementMod.AchievementModd(LibAchievementNames.MANA_CART_CRAFT, 5, 3, ModItems.poolMinecart, manaPoolPickup);
		enchanterMake = AchievementMod.AchievementModd(LibAchievementNames.ENCHANTER_MAKE, 1, 2, ModBlocks.enchanter, manaPoolPickup);
		runePickup = AchievementMod.AchievementModd(LibAchievementNames.RUNE_PICKUP, 6, 2, ModBlocks.runeAltar, manaPoolPickup);

		dirtRodCraft = AchievementMod.AchievementModd(LibAchievementNames.DIRT_ROD_CRAFT, 8, 3, ModItems.dirtRod, runePickup);
		terraformRodCraft = AchievementMod.AchievementModd(LibAchievementNames.TERRAFORM_ROD_CRAFT, 10, 3, ModItems.terraformRod, dirtRodCraft);
		manaBlasterShoot = AchievementMod.AchievementModd(LibAchievementNames.MANA_BLASTER_SHOOT, 8, 1, ModItems.manaGun, runePickup);
		pollidisiacPickup = AchievementMod.AchievementModd(LibAchievementNames.POLLIDISIAC_PICKUP, 8, 5, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_POLLIDISIAC), runePickup);
		brewPickup = AchievementMod.AchievementModd(LibAchievementNames.BREW_PICKUP, 6, 0, ModBlocks.brewery, runePickup);
		terrasteelPickup = AchievementMod.AchievementModd(LibAchievementNames.TERRASTEEL_PICKUP, 6, 9, new ItemStack(ModItems.manaResource, 1, 4), runePickup).setSpecial();

		terrasteelWeaponCraft = AchievementMod.AchievementModd(LibAchievementNames.TERRASTEEL_WEAPON_CRAFT, 8, 10, ModItems.terraSword, terrasteelPickup);
		elfPortalOpen = AchievementMod.AchievementModd(LibAchievementNames.ELF_PORTAL_OPEN, 4, 9, ModBlocks.alfPortal, terrasteelPickup).setSpecial();

		kekimurusPickup = AchievementMod.AchievementModd(LibAchievementNames.KEKIMURUS_PICKUP, 3, 11, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS), elfPortalOpen);
		heiseiDreamPickup = AchievementMod.AchievementModd(LibAchievementNames.HEISEI_DREAM_PICKUP, 5, 11, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HEISEI_DREAM), elfPortalOpen);
		bubbellPickup = AchievementMod.AchievementModd(LibAchievementNames.BUBBELL_PICKUP, 6, 12, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BUBBELL), elfPortalOpen);
		enderAirMake = AchievementMod.AchievementModd(LibAchievementNames.ENDER_AIR_MAKE, 4, 14, new ItemStack(ModItems.manaResource, 1, 15), elfPortalOpen);
		corporeaCraft = AchievementMod.AchievementModd(LibAchievementNames.CORPOREA_CRAFT, 2, 14, ModBlocks.corporeaFunnel, enderAirMake);
		luminizerRide = AchievementMod.AchievementModd(LibAchievementNames.LUMINIZER_RIDE, 6, 14, ModBlocks.lightRelay, enderAirMake);

		gaiaGuardianKill = AchievementMod.AchievementModd(LibAchievementNames.GAIA_GUARDIAN_KILL, 2, 9, new ItemStack(ModItems.manaResource, 1, 5), elfPortalOpen).setSpecial();

		spawnerMoverUse = AchievementMod.AchievementModd(LibAchievementNames.SPAWNER_MOVER_USE, -1, 10, ModItems.spawnerMover, gaiaGuardianKill);
		tiaraWings = AchievementMod.AchievementModd(LibAchievementNames.TIARA_WINGS, -1, 8, ModItems.flightTiara, gaiaGuardianKill);
		manaBombIgnite = AchievementMod.AchievementModd(LibAchievementNames.MANA_BOMB_IGNITE, 0, 11, ModBlocks.manaBomb, gaiaGuardianKill);
		dandelifeonPickup = AchievementMod.AchievementModd(LibAchievementNames.DANDELIFEON_PICKUP, 0, 7, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DANDELIFEON), gaiaGuardianKill);

		signalFlareStun = AchievementMod.AchievementModd(LibAchievementNames.SIGNAL_FLARE_STUN, -3, 1, ModItems.signalFlare, null).setSpecial();
		l20ShardUse = AchievementMod.AchievementModd(LibAchievementNames.L20_SHARD_USE, -5, 3, ModItems.laputaShard, null).setSpecial();
		gaiaGuardianNoArmor = AchievementMod.AchievementModd(LibAchievementNames.GAIA_GUARDIAN_NO_ARMOR, -5, 1, new ItemStack(Item.skull, 1, 3), null).setSpecial();
		rankSSPick = AchievementMod.AchievementModd(LibAchievementNames.RANK_SS_PICK, -3, 3, ModItems.terraPick, null).setSpecial();
		superCorporeaRequest = AchievementMod.AchievementModd(LibAchievementNames.SUPER_CORPOREA_REQUEST, -3, -1, ModBlocks.corporeaIndex, null).setSpecial();
		pinkinator = AchievementMod.AchievementModd(LibAchievementNames.PINKINATOR, -5, -1, ModItems.pinkinator, null).setSpecial();

		if(ConfigHandler.relicsEnabled) {
			relicInfiniteFruit = AchievementMod.AchievementModd(LibAchievementNames.RELIC_INFINITE_FRUIT, -9, 8, ModItems.infiniteFruit, null);
			relicKingKey = AchievementMod.AchievementModd(LibAchievementNames.RELIC_KING_KEY, -7, 11, ModItems.kingKey, null);
			relicFlugelEye = AchievementMod.AchievementModd(LibAchievementNames.RELIC_FLUGEL_EYE, -5, 8, ModItems.flugelEye, null);
			relicThorRing = AchievementMod.AchievementModd(LibAchievementNames.RELIC_THOR_RING, -7, 7, ModItems.thorRing, null);
			relicOdinRing = AchievementMod.AchievementModd(LibAchievementNames.RELIC_ODIN_RING, -9, 10, ModItems.odinRing, null);
			relicLokiRing = AchievementMod.AchievementModd(LibAchievementNames.RELIC_LOKI_RING, -5, 10, ModItems.lokiRing, null);
			relicAesirRing = AchievementMod.AchievementModd(LibAchievementNames.RELIC_AESIR_RING, -7, 9, ModItems.aesirRing, null).setSpecial();
		}

		nullFlower = AchievementMod.AchievementModd(LibAchievementNames.NULL_FLOWER, -8, 0, ModBlocks.specialFlower, null).setSpecial();

		ItemStack desu = new ItemStack(ModItems.manaGun);
		desu.setStackDisplayName("desu gun");
		desuGun = AchievementMod.AchievementModd(LibAchievementNames.DESU_GUN, -8, 2, desu, null).setSpecial();

		pageIndex = AchievementPage.getAchievementPages().size();
		botaniaPage = new AchievementPage(LibMisc.MOD_NAME, AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
		AchievementPage.registerAchievementPage(botaniaPage);

		FMLCommonHandler.instance().bus().register(new AchievementTriggerer());*/
	}

}

