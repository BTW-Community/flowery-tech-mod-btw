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

import api.achievement.AchievementEvents;
import api.achievement.AchievementTab;
import api.achievement.AchievementEventDispatcher;
import api.achievement.AchievementEvents;
import net.minecraft.src.*;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.lib.LibAchievementNames;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibOreDict;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static vazkii.botania.common.achievement.BotaniaAchievementProvider.getBuilder;

@SuppressWarnings("unused")
public final class ModAchievements {

	public static Predicate<ItemStack> LATER = s -> false;

	public static final AchievementTab BOTANIA_PAGE = new AchievementTab("botania").setIcon(ModBlocks.flower);

	public static final Achievement<ItemStack> FLOWER_PICKUP = pickedUp(LibAchievementNames.FLOWER_PICKUP, new ItemStack(ModBlocks.flower, 1, 6), 0, 4, reqItem(ModBlocks.flower), null);
	public static final Achievement<AchievementEvents.None> LEXICON_USE = getBuilder(LexiconOpenEvent.class)
			.name(LibAchievementNames.LEXICON_USE)
			.icon(ModItems.lexicon)
			.displayLocation(1, 5)
			.alwaysTrigger()
			.parents(FLOWER_PICKUP)
			.build();
	public static final Achievement<ItemStack> DAYBLOOM_PICKUP = pickedUp(LibAchievementNames.DAYBLOOM_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAYBLOOM), 3, 5, reqItemFlower(LibBlockNames.SUBTILE_DAYBLOOM), LEXICON_USE);
	public static final Achievement<ItemStack> CACOPHONIUM_CRAFT = crafted(LibAchievementNames.CACOPHONIUM_CRAFT, new ItemStack(ModItems.cacophonium), -1, 2, reqItemWithDamage(ModItems.cacophonium), FLOWER_PICKUP);
	public static final Achievement<ItemStack> MANA_POOL_PICKUP = pickedUp(LibAchievementNames.MANA_POOL_PICKUP, new ItemStack(ModBlocks.pool), 3, 2, reqItemWithDamage(ModBlocks.pool), DAYBLOOM_PICKUP);

	public static final Achievement<ItemStack> ENDOFLAME_PICKUP = pickedUp(LibAchievementNames.ENDOFLAME_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENDOFLAME), 2, 0, reqItemFlower(LibBlockNames.SUBTILE_ENDOFLAME), MANA_POOL_PICKUP);
	public static final Achievement<AchievementEvents.None> TINY_POTATO_PET = getBuilder(PetThePotatoPleaseTheyDeserveItEvent.class)
			.name(LibAchievementNames.TINY_POTATO_PET)
			.icon(ModBlocks.tinyPotato)
			.displayLocation(2, -2)
			.alwaysTrigger()
			.parents(MANA_POOL_PICKUP)
			.build();
	public static final Achievement<ItemStack> SPARK_CRAFT = crafted(LibAchievementNames.SPARK_CRAFT, new ItemStack(ModItems.spark), 4, -2, reqItem(ModItems.spark), MANA_POOL_PICKUP);
	public static final Achievement<BaubleWearEventData> BAUBLE_WEAR = getBuilder(BaubleWearEvent.class)
			.name(LibAchievementNames.BAUBLE_WEAR)
			.icon(ModItems.manaRing)
			.displayLocation(4, 0)
			.alwaysTrigger()
			.parents(MANA_POOL_PICKUP)
			.build();
	public static final Achievement<ItemStack> MANA_COOKIE_EAT = getBuilder(AchievementEvents.EatenEvent.class)
			.name(LibAchievementNames.MANA_COOKIE_EAT)
			.icon(ModItems.manaCookie)
			.displayLocation(2, -4)
			.triggerCondition(reqItem(ModItems.manaCookie))
			.parents(MANA_POOL_PICKUP)
			.build();
	public static final Achievement<ItemStack> MANAWEAVE_ARMOR_CRAFT = crafted(LibAchievementNames.MANAWEAVE_ARMOR_CRAFT, new ItemStack(ModItems.manaweaveChest), 4, -4, reqItems(ModItems.manaweaveChest, ModItems.manaweaveHelm, ModItems.manaweaveLegs, ModItems.manaweaveBoots), MANA_POOL_PICKUP);
	public static final Achievement<ItemStack> CRAFTING_HALO_CRAFT = crafted(LibAchievementNames.CRAFTING_HALO_CRAFT, new ItemStack(ModItems.craftingHalo), 3, -6, reqItem(ModItems.craftingHalo), MANA_POOL_PICKUP);
	public static final Achievement<ItemStack> MANA_CART_CRAFT = crafted(LibAchievementNames.MANA_CART_CRAFT, new ItemStack(ModItems.poolMinecart), 5, 3, reqItem(ModItems.poolMinecart), MANA_POOL_PICKUP);
	public static final Achievement<AchievementEvents.None> ENCHANTER_MAKE = getBuilder(EnchanterMakeEvent.class)
			.name(LibAchievementNames.ENCHANTER_MAKE)
			.icon(ModBlocks.enchanter)
			.displayLocation (1, 2)
			.alwaysTrigger()
			.parents(MANA_POOL_PICKUP)
			.build();
	public static final Achievement<ItemStack> RUNE_PICKUP = pickedUp(LibAchievementNames.RUNE_PICKUP, new ItemStack(ModBlocks.runeAltar), 6, 2, reqItem(ModBlocks.runeAltar), MANA_POOL_PICKUP);

	public static final Achievement<ItemStack> DIRT_ROD_CRAFT = crafted(LibAchievementNames.DIRT_ROD_CRAFT, new ItemStack(ModItems.dirtRod), 8, 3, reqItem(ModItems.dirtRod), RUNE_PICKUP);
	public static final Achievement<ItemStack> TERRAFORM_ROD_CRAFT = crafted(LibAchievementNames.TERRAFORM_ROD_CRAFT, new ItemStack(ModItems.terraformRod), 10, 3, reqItem(ModItems.terraformRod), DIRT_ROD_CRAFT);
	public static final Achievement<ManaBlasterFiredEventData> MANA_BLASTER_SHOOT = getBuilder(ManaBlasterFiredEvent.class).name(LibAchievementNames.MANA_BLASTER_SHOOT).icon(ModItems.manaGun).displayLocation(8, 1).alwaysTrigger().parents(RUNE_PICKUP).build();
	public static final Achievement<ItemStack> POLLIDISIAC_PICKUP = pickedUp(LibAchievementNames.POLLIDISIAC_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_POLLIDISIAC), 8, 5, reqItemFlower(LibBlockNames.SUBTILE_POLLIDISIAC), RUNE_PICKUP);
	public static final Achievement<ItemStack> BREW_PICKUP = pickedUp(LibAchievementNames.BREW_PICKUP, new ItemStack(ModBlocks.brewery), 6, 0, reqItem(ModBlocks.brewery), RUNE_PICKUP);
	public static final Achievement<ItemStack> TERRASTEEL_PICKUP = pickedUp(LibAchievementNames.TERRASTEEL_PICKUP, LibOreDict.TERRA_STEEL, 6, 9, reqItemWithDamage(LibOreDict.TERRA_STEEL), RUNE_PICKUP).setSpecial();

	public static final Achievement<ItemStack> terrasteelWeaponCraft = crafted(LibAchievementNames.TERRASTEEL_WEAPON_CRAFT, new ItemStack(ModItems.terraSword), 8, 10, reqItems(ModItems.terraSword, ModItems.thornChakram, ModItems.starSword, ModItems.thunderSword), TERRASTEEL_PICKUP);
	public static final Achievement<AchievementEvents.None> ELF_PORTAL_OPEN = getBuilder(AlfheimPortalOpenEvent.class)
			.name(LibAchievementNames.ELF_PORTAL_OPEN)
			.icon(ModBlocks.alfPortal)
			.displayLocation(4, 9)
			.alwaysTrigger()
			.parents(TERRASTEEL_PICKUP)
			.build()
			.setSpecial();

	public static final Achievement<ItemStack> kekimurusPickup = pickedUp(LibAchievementNames.KEKIMURUS_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS), 3, 11, reqItemFlower(LibBlockNames.SUBTILE_KEKIMURUS), ELF_PORTAL_OPEN);
	public static final Achievement<ItemStack> heiseiDreamPickup = pickedUp(LibAchievementNames.HEISEI_DREAM_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HEISEI_DREAM), 5, 11, reqItemFlower(LibBlockNames.SUBTILE_HEISEI_DREAM), ELF_PORTAL_OPEN);
	public static final Achievement<ItemStack> bubbellPickup = pickedUp(LibAchievementNames.BUBBELL_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BUBBELL), 6, 12, reqItemFlower(LibBlockNames.SUBTILE_BUBBELL), ELF_PORTAL_OPEN);

	public static final Achievement<AchievementEvents.None> ENDER_AIR_MAKE = getBuilder(EnderAirMakeEvent.class)
			.name(LibAchievementNames.ENDER_AIR_MAKE)
			.icon(LibOreDict.ENDER_AIR_BOTTLE)
			.displayLocation(4, 14)
			.alwaysTrigger()
			.parents(ELF_PORTAL_OPEN)
			.build();
	public static final Achievement<AchievementEvents.None> LUMINIZER_RIDE = getBuilder(LuminizerRideEvent.class).name(LibAchievementNames.LUMINIZER_RIDE).icon(ModBlocks.lightRelay).displayLocation(6, 14).alwaysTrigger().parents(ENDER_AIR_MAKE).build();

    public static final Achievement<ItemStack> corporeaCraft = crafted(LibAchievementNames.CORPOREA_CRAFT, new ItemStack(ModBlocks.corporeaFunnel), 2, 14, reqItems(ModBlocks.corporeaRetainer, ModBlocks.corporeaFunnel, ModBlocks.corporeaIndex, ModBlocks.corporeaInterceptor, ModBlocks.corporeaCrystalCube), ENDER_AIR_MAKE);

	public static Achievement<GaiaGuardianKillEventData> gaiaGuardianKill = getBuilder(GaiaGuardianKillEvent.class).name(LibAchievementNames.GAIA_GUARDIAN_KILL).icon(LibOreDict.LIFE_ESSENCE).displayLocation(2, 9).alwaysTrigger().parents(ELF_PORTAL_OPEN).build().setSpecial();

	public static Achievement<AchievementEvents.None> spawnerMoverUse = getBuilder(SpawnerMoverEvent.class)
			.name(LibAchievementNames.SPAWNER_MOVER_USE)
			.icon(ModItems.spawnerMover)
			.displayLocation(-1, 10)
			.alwaysTrigger()
			.parents(gaiaGuardianKill)
			.build();
	public static final Achievement<ItemStack> TIARA_WINGS = AchievementMod.basic(LibAchievementNames.TIARA_WINGS, -1, 8, ModItems.flightTiara, reqItemWithDamage(new ItemStack(ModItems.flightTiara, 1, 1)), gaiaGuardianKill);
	public static final Achievement<ItemStack> manaBombIgnite = crafted(LibAchievementNames.MANA_BOMB_IGNITE, new ItemStack(ModBlocks.manaBomb), 0, 11, reqItem(new ItemStack(ModBlocks.manaBomb)), gaiaGuardianKill);
	public static final Achievement<ItemStack> dandelifeonPickup = pickedUp(LibAchievementNames.DANDELIFEON_PICKUP, ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DANDELIFEON), 0, 7, reqItemFlower(LibBlockNames.SUBTILE_DANDELIFEON), gaiaGuardianKill);

	public static final Achievement<AchievementEvents.None> SIGNAL_FLARE_STUN = getBuilder(SignalFlareStunEvent.class)
			.name(LibAchievementNames.SIGNAL_FLARE_STUN)
			.icon(ModItems.signalFlare)
			.displayLocation(-3, 1)
			.alwaysTrigger()
			.build()
			.setSpecial()
			.setSecret();
	public static final Achievement<AchievementEvents.None> L20_SHARD_USE = getBuilder(LaputaEvent.class)
			.name(LibAchievementNames.L20_SHARD_USE)
			.icon(ModItems.laputaShard)
			.displayLocation(-5, 3)
			.alwaysTrigger()
			.build()
			.setSpecial()
			.setHidden();
	public static final Achievement<GaiaGuardianKillEventData> GAIA_GUARDIAN_NO_ARMOR = getBuilder(GaiaGuardianKillEvent.class)
			.name(LibAchievementNames.GAIA_GUARDIAN_NO_ARMOR)
			.icon(new ItemStack(Item.skull, 1, 3))
			.displayLocation(-5, 1)
			.triggerCondition(GaiaGuardianKillEventData::noArmor)
			.build()
			.setSpecial()
			.setHidden();
	public static final Achievement<AchievementEvents.None> RANK_SS_PICK = getBuilder(TerraPickEvent.class)
			.name(LibAchievementNames.RANK_SS_PICK)
			.icon(new ItemStack(ModItems.terraPick, 1))
			.displayLocation(-3, 3)
			.alwaysTrigger()
			.build()
			.setSpecial()
			.setHidden();
	public static final Achievement<AchievementEvents.None> SUPER_CORPOREA_REQUEST = getBuilder(SuperCorporeaRequestEvent.class)
			.name(LibAchievementNames.SUPER_CORPOREA_REQUEST)
			.icon(ModBlocks.corporeaIndex)
			.displayLocation(-3, -1)
			.alwaysTrigger()
			.build()
			.setSpecial()
			.setHidden();
	public static final Achievement<AchievementEvents.None> PINKINATOR = getBuilder(PinkificationEvent.class)
			.name(LibAchievementNames.PINKINATOR)
			.icon(ModItems.pinkinator)
			.displayLocation(-5, -1)
			.alwaysTrigger()
			.build()
			.setSpecial()
			.setHidden();

	public static final Achievement<ItemStack> RELIC_INFINITE_FRUIT = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_INFINITE_FRUIT)
			.icon(ModItems.infiniteFruit)
			.displayLocation(-9, 8)
			.triggerCondition(reqItem(ModItems.infiniteFruit))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_KING_KEY = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_KING_KEY)
			.icon(ModItems.kingKey)
			.displayLocation(-7, 11)
			.triggerCondition(reqItem(ModItems.kingKey))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_FLUGEL_EYE = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_FLUGEL_EYE)
			.icon(ModItems.flugelEye)
			.displayLocation(-5, 8)
			.triggerCondition(reqItem(ModItems.flugelEye))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_THOR_RING = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_THOR_RING).icon(ModItems.thorRing)
			.displayLocation(-7, 7)
			.triggerCondition(reqItem(ModItems.thorRing))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_ODIN_RING = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_ODIN_RING).icon(ModItems.odinRing)
			.displayLocation(-9, 10)
			.triggerCondition(reqItem(ModItems.odinRing))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_LOKI_RING = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_LOKI_RING)
			.icon(ModItems.lokiRing)
			.displayLocation(-5, 10)
			.triggerCondition(reqItem(ModItems.lokiRing))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> RELIC_AESIR_RING = getBuilder(RelicEvent.class)
			.name(LibAchievementNames.RELIC_AESIR_RING)
			.icon(ModItems.aesirRing)
			.displayLocation(-7, 9)
			.triggerCondition(reqItem(ModItems.aesirRing))
			.build()
			.setSecret()
			.setSpecial();

	public static final Achievement<ItemStack> nullFlower = getBuilder(AchievementEvents.ItemEvent.class)
			.name(LibAchievementNames.NULL_FLOWER)
			.icon(ModBlocks.specialFlower)
			.displayLocation(-8, 0)
			.triggerCondition(stack -> stack.isItemEqual(new ItemStack(ModBlocks.specialFlower), true) && ItemBlockSpecialFlower.getType(stack).isEmpty())
			.build()
			.setSpecial()
			.setHidden();

	public static final Achievement<ManaBlasterFiredEventData> desuGun = getBuilder(ManaBlasterFiredEvent.class)
			.name(LibAchievementNames.MANA_BLASTER_SHOOT)
			.icon(withName("desu gun", new ItemStack(ModItems.manaGun)))
			.displayLocation(-8, 2)
			.triggerCondition(ManaBlasterFiredEventData::isDesuGun)
			.build()
			.setSpecial();

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

	private static Predicate<ItemStack> reqItemFlower(String type) {
		return other -> type.equals(ItemBlockSpecialFlower.getType(other));
	}

	private static Predicate<ItemStack> reqItemWithDamage(ItemStack stack) {
		return other -> other.isItemEqual(stack, false);
	}

	private static Predicate<ItemStack> reqItemWithDamage(Item item) {
		return reqItemWithDamage(new ItemStack(item));
	}

	private static Predicate<ItemStack> reqItemWithDamage(Block block) {
		return reqItemWithDamage(new ItemStack(block));
	}

	public static void init() {

        if(ConfigHandler.relicsEnabled) {
//			relicInfiniteFruit = getBuilder(RelicEvent.class).name(LibAchievementNames.RELIC_INFINITE_FRUIT).icon(ModItems.infiniteFruit).displayLocation(-9, 8).triggerCondition(reqItem(ModItems.infiniteFruit)).build().setSecret();
//			relicKingKey = AchievementMod.basic(LibAchievementNames.RELIC_KING_KEY, -7, 11, ModItems.kingKey, reqItem(ModItems.kingKey), null).setSecret();
//			relicFlugelEye = AchievementMod.basic(LibAchievementNames.RELIC_FLUGEL_EYE, -5, 8, ModItems.flugelEye, reqItem(ModItems.flugelEye), null).setSecret();
//			relicThorRing = AchievementMod.basic(LibAchievementNames.RELIC_THOR_RING, -7, 7, ModItems.thorRing, /*reqItem(ModItems.thorRing),*/ null).setSecret();
//			relicOdinRing = AchievementMod.basic(LibAchievementNames.RELIC_ODIN_RING, -9, 10, ModItems.odinRing, /*reqItem(ModItems.odinRing),*/ null).setSecret();
//			relicLokiRing = AchievementMod.basic(LibAchievementNames.RELIC_LOKI_RING, -5, 10, ModItems.lokiRing, /*reqItem(ModItems.lokiRing),*/ null).setSecret();
//			relicAesirRing = AchievementMod.basic(LibAchievementNames.RELIC_AESIR_RING, -7, 9, ModItems.aesirRing, /*reqItem(ModItems.aesirRing),*/ null).setSecret().setSpecial();
		}
	}

	public static ItemStack withName(String name, ItemStack original) {
		original.setItemName(name);
		return original;
	}

	public static Achievement<ItemStack> crafted(String name, ItemStack icon, int x, int y, Predicate<ItemStack> pred, Achievement<?> parent) {
		return getBuilder(AchievementEvents.ItemEvent.class)
				.name(name)
				.icon(icon)
				.displayLocation(x, y)
				.triggerCondition(pred)
				.parents(
						Stream.of(parent)
								.filter(Objects::nonNull)
								.toArray(Achievement[]::new))
				.build();
	}

	public static Achievement<ItemStack> pickedUp(String name, ItemStack icon, int x, int y, Predicate<ItemStack> pred, Achievement<?> parent) {
		return getBuilder(AchievementEvents.ItemEvent.class)
				.name(name)
				.icon(icon)
				.displayLocation(x, y)
				.triggerCondition(pred)
				.parents(
						Stream.of(parent)
								.filter(Objects::nonNull)
								.toArray(Achievement[]::new))
				.build();
	}

	public record BaubleWearEventData(EntityPlayer player, ItemBauble bauble) {}

	public record ManaBlasterFiredEventData(boolean isDesuGun) {}

	public record GaiaGuardianKillEventData(boolean noArmor) {}

	public static class TerraPickEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class LaputaEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class PinkificationEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class SignalFlareStunEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class SpawnerMoverEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class EnchanterMakeEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class GaiaGuardianKillEvent extends AchievementEventDispatcher.AchievementEvent<GaiaGuardianKillEventData> { }

	public static class LuminizerRideEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class SuperCorporeaRequestEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class RelicEvent extends AchievementEventDispatcher.AchievementEvent<ItemStack> { }

	public static class AlfheimPortalOpenEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class ManaBlasterFiredEvent extends AchievementEventDispatcher.AchievementEvent<ManaBlasterFiredEventData> { }

	public static class PetThePotatoPleaseTheyDeserveItEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class LexiconOpenEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }

	public static class BaubleWearEvent extends AchievementEventDispatcher.AchievementEvent<BaubleWearEventData> { }

	public static class EnderAirMakeEvent extends AchievementEventDispatcher.AchievementEvent<AchievementEvents.None> { }
}

