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

import btw.achievement.AchievementHandler;
import btw.achievement.AchievementTab;
import net.minecraft.src.*;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibAchievementNames;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibOreDict;

import java.util.Arrays;
import java.util.function.Predicate;

public final class ModAchievements {
	
	public static final AchievementTab botaniaPage = new AchievementTab("botania").setIcon(ModBlocks.flower)/*.setIcon(BTWItems.firePlough)*/;
//	public static int pageIndex;

	public static Achievement<ItemStack> flowerPickup;
	public static Achievement<ItemStack> lexiconUse;
	public static Achievement<ItemStack> daybloomPickup;
	public static Achievement<ItemStack> cacophoniumCraft;
	public static Achievement<ItemStack> manaPoolPickup;

	public static Achievement<ItemStack> endoflamePickup;
	public static Achievement<ItemStack> tinyPotatoPet;
	public static Achievement<ItemStack> sparkCraft;
	public static Achievement<ItemStack> baubleWear;
	public static Achievement<ItemStack> manaCookieEat;
	public static Achievement<ItemStack> manaweaveArmorCraft;
	public static Achievement<ItemStack> craftingHaloCraft;
	public static Achievement<ItemStack> manaCartCraft;
	public static Achievement<ItemStack> enchanterMake;
	public static Achievement<ItemStack> runePickup;

	public static Achievement<ItemStack> dirtRodCraft;
	public static Achievement<ItemStack> terraformRodCraft;
	public static Achievement<ItemStack> manaBlasterShoot;
	public static Achievement<ItemStack> pollidisiacPickup;
	public static Achievement<ItemStack> brewPickup;
	public static Achievement<ItemStack> terrasteelPickup;

	public static Achievement<ItemStack> terrasteelWeaponCraft;
	public static Achievement<ItemStack> elfPortalOpen;

	public static Achievement<ItemStack> kekimurusPickup;
	public static Achievement<ItemStack> heiseiDreamPickup;
	public static Achievement<ItemStack> bubbellPickup;
	public static Achievement<ItemStack> luminizerRide;

	public static Achievement<ItemStack> enderAirMake;
	public static Achievement<ItemStack> corporeaCraft;

	public static Achievement<ItemStack> gaiaGuardianKill;

	public static Achievement<ItemStack> spawnerMoverUse;
	public static Achievement<ItemStack> tiaraWings;
	public static Achievement<ItemStack> manaBombIgnite;
	public static Achievement<ItemStack> dandelifeonPickup;

	public static Achievement<ItemStack> signalFlareStun;
	public static Achievement<ItemStack> l20ShardUse;
	public static Achievement<ItemStack> gaiaGuardianNoArmor;
	public static Achievement<ItemStack> rankSSPick;
	public static Achievement<ItemStack> superCorporeaRequest;
	public static Achievement<ItemStack> pinkinator;

	public static Achievement<ItemStack> relicInfiniteFruit;
	public static Achievement<ItemStack> relicKingKey;
	public static Achievement<ItemStack> relicFlugelEye;
	public static Achievement<ItemStack> relicThorRing;
	public static Achievement<ItemStack> relicOdinRing;
	public static Achievement<ItemStack> relicLokiRing;
	public static Achievement<ItemStack> relicAesirRing;

	public static Achievement<ItemStack> nullFlower;
	public static Achievement<ItemStack> desuGun;

	public static void trigger(EntityPlayer player, Achievement<?> achievement) {
		if (achievement == null) {
			new RuntimeException("Achievement is null!").printStackTrace();
			return;
		}
//		player.addStat(achievement, 1);
		player.getData(AchievementHandler.ACHIEVEMENTS_DATA).getDataForPlayer(player.username).triggerAchievement(achievement);
	}

	private static Predicate<ItemStack> reqItem(ItemStack stack) {
		return other -> other.getItem().itemID == stack.getItem().itemID;
	}

	private static Predicate<ItemStack> reqItem(Item item) {
		return reqItem(new ItemStack(item));
	}

	private static Predicate<ItemStack> reqItem(Block block) {
		return reqItem(new ItemStack(block));
	}

	private static Predicate<ItemStack> reqItems(ItemStack... stack) {
		return other -> {
			for (ItemStack s : stack) {
				if (other.getItem().itemID == s.getItem().itemID) {
					return true;
				}
			}
			return false;
		};
	}

	private static Predicate<ItemStack> reqItems(Item... item) {
		return reqItems(Arrays.stream(item).map(ItemStack::new).toArray(ItemStack[]::new));
	}

	private static Predicate<ItemStack> reqItems(Block... block) {
		return reqItems(Arrays.stream(block).map(ItemStack::new).toArray(ItemStack[]::new));
	}

	private static Predicate<ItemStack> reqItemFlower(ItemStack stack) {
		return other -> ItemBlockSpecialFlower.getType(stack).equals(ItemBlockSpecialFlower.getType(other));
	}

	private static Predicate<ItemStack> reqItemWithDamage(ItemStack stack) {
		return other -> other.isItemEqual(stack);
	}

	private static Predicate<ItemStack> reqItemWithDamage(Item item) {
		return reqItemWithDamage(new ItemStack(item));
	}

	private static Predicate<ItemStack> reqItemWithDamage(Block block) {
		return reqItemWithDamage(new ItemStack(block));
	}

	public static Predicate<ItemStack> LATER = s -> false;

	public static void init() {
		flowerPickup = AchievementMod.basic(LibAchievementNames.FLOWER_PICKUP, 0, 4, new ItemStack(ModBlocks.flower, 1, 6), reqItem(ModBlocks.flower), null);
		lexiconUse = AchievementMod.basic(LibAchievementNames.LEXICON_USE, 1, 5, ModItems.lexicon, flowerPickup);
		daybloomPickup = AchievementMod.basic(LibAchievementNames.DAYBLOOM_PICKUP, 3, 5, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAYBLOOM), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAYBLOOM)), lexiconUse);
		cacophoniumCraft = AchievementMod.basic(LibAchievementNames.CACOPHONIUM_CRAFT, -1, 2, ModItems.cacophonium, reqItemWithDamage(ModItems.cacophonium), flowerPickup);
		manaPoolPickup = AchievementMod.basic(LibAchievementNames.MANA_POOL_PICKUP, 3, 2, ModBlocks.pool, reqItemWithDamage(ModBlocks.pool), daybloomPickup);

		endoflamePickup = AchievementMod.basic(LibAchievementNames.ENDOFLAME_PICKUP, 2, 0, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENDOFLAME), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENDOFLAME)), manaPoolPickup);
		tinyPotatoPet = AchievementMod.basic(LibAchievementNames.TINY_POTATO_PET, 2, -2, ModBlocks.tinyPotato, LATER, manaPoolPickup);
		sparkCraft = AchievementMod.basic(LibAchievementNames.SPARK_CRAFT, 4, -2, ModItems.spark, reqItem(ModItems.spark), manaPoolPickup);
		baubleWear = AchievementMod.basic(LibAchievementNames.BAUBLE_WEAR, 4, 0, ModItems.manaRing, manaPoolPickup);
		manaCookieEat = AchievementMod.basic(LibAchievementNames.MANA_COOKIE_EAT, 2, -4, ModItems.manaCookie, manaPoolPickup);
		manaweaveArmorCraft = AchievementMod.basic(LibAchievementNames.MANAWEAVE_ARMOR_CRAFT, 4, -4, ModItems.manaweaveChest, reqItems(ModItems.manaweaveChest, ModItems.manaweaveHelm, ModItems.manaweaveLegs, ModItems.manaweaveBoots), manaPoolPickup);
		craftingHaloCraft = AchievementMod.basic(LibAchievementNames.CRAFTING_HALO_CRAFT, 3, -6, ModItems.craftingHalo, reqItem(ModItems.craftingHalo), manaPoolPickup);
		manaCartCraft = AchievementMod.basic(LibAchievementNames.MANA_CART_CRAFT, 5, 3, ModItems.poolMinecart, reqItem(ModItems.poolMinecart), manaPoolPickup);
		enchanterMake = AchievementMod.basic(LibAchievementNames.ENCHANTER_MAKE, 1, 2, ModBlocks.enchanter, manaPoolPickup);
		runePickup = AchievementMod.basic(LibAchievementNames.RUNE_PICKUP, 6, 2, ModBlocks.runeAltar, reqItem(ModBlocks.runeAltar), manaPoolPickup);

		dirtRodCraft = AchievementMod.basic(LibAchievementNames.DIRT_ROD_CRAFT, 8, 3, ModItems.dirtRod, reqItem(ModItems.dirtRod), runePickup);
		terraformRodCraft = AchievementMod.basic(LibAchievementNames.TERRAFORM_ROD_CRAFT, 10, 3, ModItems.terraformRod, reqItem(ModItems.terraformRod), dirtRodCraft);
		manaBlasterShoot = AchievementMod.basic(LibAchievementNames.MANA_BLASTER_SHOOT, 8, 1, ModItems.manaGun, runePickup);
		pollidisiacPickup = AchievementMod.basic(LibAchievementNames.POLLIDISIAC_PICKUP, 8, 5, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_POLLIDISIAC), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_POLLIDISIAC)), runePickup);
		brewPickup = AchievementMod.basic(LibAchievementNames.BREW_PICKUP, 6, 0, ModBlocks.brewery, reqItem(ModBlocks.brewery), runePickup);
		terrasteelPickup = AchievementMod.basic(LibAchievementNames.TERRASTEEL_PICKUP, 6, 9, LibOreDict.TERRA_STEEL, reqItemWithDamage(LibOreDict.TERRA_STEEL), runePickup).setSpecial();

		terrasteelWeaponCraft = AchievementMod.basic(LibAchievementNames.TERRASTEEL_WEAPON_CRAFT, 8, 10, ModItems.terraSword, reqItems(ModItems.terraSword, ModItems.thornChakram, ModItems.starSword, ModItems.thunderSword), terrasteelPickup);
		elfPortalOpen = AchievementMod.basic(LibAchievementNames.ELF_PORTAL_OPEN, 4, 9, ModBlocks.alfPortal, terrasteelPickup).setSpecial();

		kekimurusPickup = AchievementMod.basic(LibAchievementNames.KEKIMURUS_PICKUP, 3, 11, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS)), elfPortalOpen);
		heiseiDreamPickup = AchievementMod.basic(LibAchievementNames.HEISEI_DREAM_PICKUP, 5, 11, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HEISEI_DREAM), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HEISEI_DREAM)), elfPortalOpen);
		bubbellPickup = AchievementMod.basic(LibAchievementNames.BUBBELL_PICKUP, 6, 12, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BUBBELL), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BUBBELL)), elfPortalOpen);
		enderAirMake = AchievementMod.basic(LibAchievementNames.ENDER_AIR_MAKE, 4, 14, new ItemStack(ModItems.manaResource, 1, 15), elfPortalOpen);
		corporeaCraft = AchievementMod.basic(LibAchievementNames.CORPOREA_CRAFT, 2, 14, ModBlocks.corporeaFunnel, reqItems(ModBlocks.corporeaRetainer, ModBlocks.corporeaFunnel, ModBlocks.corporeaIndex, ModBlocks.corporeaInterceptor, ModBlocks.corporeaCrystalCube), enderAirMake);
		luminizerRide = AchievementMod.basic(LibAchievementNames.LUMINIZER_RIDE, 6, 14, ModBlocks.lightRelay, enderAirMake);

		gaiaGuardianKill = AchievementMod.basic(LibAchievementNames.GAIA_GUARDIAN_KILL, 2, 9, new ItemStack(ModItems.manaResource, 1, 5), elfPortalOpen).setSpecial();

		spawnerMoverUse = AchievementMod.basic(LibAchievementNames.SPAWNER_MOVER_USE, -1, 10, ModItems.spawnerMover, gaiaGuardianKill);
		tiaraWings = AchievementMod.basic(LibAchievementNames.TIARA_WINGS, -1, 8, ModItems.flightTiara, reqItemWithDamage(new ItemStack(ModItems.flightTiara, 1, 1)), gaiaGuardianKill);
		manaBombIgnite = AchievementMod.basic(LibAchievementNames.MANA_BOMB_IGNITE, 0, 11, ModBlocks.manaBomb, reqItem(new ItemStack(ModBlocks.manaBomb)), gaiaGuardianKill);
		dandelifeonPickup = AchievementMod.basic(LibAchievementNames.DANDELIFEON_PICKUP, 0, 7, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DANDELIFEON), reqItemFlower(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DANDELIFEON)), gaiaGuardianKill);

		signalFlareStun = AchievementMod.basic(LibAchievementNames.SIGNAL_FLARE_STUN, -3, 1, ModItems.signalFlare, null).setSpecial().setSecret();
		l20ShardUse = AchievementMod.basic(LibAchievementNames.L20_SHARD_USE, -5, 3, ModItems.laputaShard, null).setSpecial().setSecret();
		gaiaGuardianNoArmor = AchievementMod.basic(LibAchievementNames.GAIA_GUARDIAN_NO_ARMOR, -5, 1, new ItemStack(Item.skull, 1, 3), null).setSpecial().setSecret();
		rankSSPick = AchievementMod.basic(LibAchievementNames.RANK_SS_PICK, -3, 3, ModItems.terraPick, null).setSpecial().setSecret();
		superCorporeaRequest = AchievementMod.basic(LibAchievementNames.SUPER_CORPOREA_REQUEST, -3, -1, ModBlocks.corporeaIndex, null).setSpecial().setSecret();
		pinkinator = AchievementMod.basic(LibAchievementNames.PINKINATOR, -5, -1, ModItems.pinkinator, null).setSpecial().setSecret();

		if(ConfigHandler.relicsEnabled) {
			relicInfiniteFruit = AchievementMod.basic(LibAchievementNames.RELIC_INFINITE_FRUIT, -9, 8, ModItems.infiniteFruit, reqItem(ModItems.infiniteFruit), null).setSecret();
			relicKingKey = AchievementMod.basic(LibAchievementNames.RELIC_KING_KEY, -7, 11, ModItems.kingKey, reqItem(ModItems.kingKey), null).setSecret();
			relicFlugelEye = AchievementMod.basic(LibAchievementNames.RELIC_FLUGEL_EYE, -5, 8, ModItems.flugelEye, reqItem(ModItems.flugelEye), null).setSecret();
			relicThorRing = AchievementMod.basic(LibAchievementNames.RELIC_THOR_RING, -7, 7, ModItems.thorRing, /*reqItem(ModItems.thorRing),*/ null).setSecret();
			relicOdinRing = AchievementMod.basic(LibAchievementNames.RELIC_ODIN_RING, -9, 10, ModItems.odinRing, /*reqItem(ModItems.odinRing),*/ null).setSecret();
			relicLokiRing = AchievementMod.basic(LibAchievementNames.RELIC_LOKI_RING, -5, 10, ModItems.lokiRing, /*reqItem(ModItems.lokiRing),*/ null).setSecret();
			relicAesirRing = AchievementMod.basic(LibAchievementNames.RELIC_AESIR_RING, -7, 9, ModItems.aesirRing, /*reqItem(ModItems.aesirRing),*/ null).setSecret().setSpecial();
		}

		nullFlower = AchievementMod.basic(LibAchievementNames.NULL_FLOWER, -8, 0, ModBlocks.specialFlower, stack -> stack.isItemEqual(new ItemStack(ModBlocks.specialFlower), true) && ItemBlockSpecialFlower.getType(stack).isEmpty(), null).setSpecial().setHidden();

		ItemStack desu = new ItemStack(ModItems.manaGun);
		desu.setItemName("desu gun");
		desuGun = AchievementMod.basic(LibAchievementNames.DESU_GUN, -8, 2, desu, null).setSpecial();
	}

}

