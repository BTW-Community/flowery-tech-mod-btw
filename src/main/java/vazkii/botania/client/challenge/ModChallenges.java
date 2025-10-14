/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 29, 2015, 4:44:49 PM (GMT)]
 */
package vazkii.botania.client.challenge;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

public final class ModChallenges {

	public static final EnumMap<EnumChallengeLevel, List<Challenge>> challenges = new EnumMap(EnumChallengeLevel.class);
	public static final HashMap<String, Challenge> challengeLookup = new HashMap<>();

	public static void init() {
		for(EnumChallengeLevel level : EnumChallengeLevel.class.getEnumConstants())
			challenges.put(level, new ArrayList<>());

		addChallenge(EnumChallengeLevel.EASY, "flowerFarm", new ItemStack(ModBlocks.flower, 1, 6));
		addChallenge(EnumChallengeLevel.EASY, "recordFarm", new ItemStack(Item.record13));
		addChallenge(EnumChallengeLevel.EASY, "reedFarm", new ItemStack(Item.reed));
		addChallenge(EnumChallengeLevel.EASY, "cobbleGen", new ItemStack(Block.cobblestone));
		addChallenge(EnumChallengeLevel.EASY, "pureDaisy", ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY));
		addChallenge(EnumChallengeLevel.EASY, "battery", new ItemStack(ModBlocks.pool));

		addChallenge(EnumChallengeLevel.NORMAL, "apothecaryRefill", new ItemStack(ModBlocks.altar));
		addChallenge(EnumChallengeLevel.NORMAL, "treeFarm", new ItemStack(Block.sapling));
		addChallenge(EnumChallengeLevel.NORMAL, "fullCropFarm", new ItemStack(Item.seeds));
		addChallenge(EnumChallengeLevel.NORMAL, "animalFarm", new ItemStack(Item.leather));
		addChallenge(EnumChallengeLevel.NORMAL, "boneMealFarm", new ItemStack(Item.dyePowder, 1, 15));
		addChallenge(EnumChallengeLevel.NORMAL, "orechid", ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ORECHID));

		addChallenge(EnumChallengeLevel.HARD, "mobTower", new ItemStack(Item.bone));
		addChallenge(EnumChallengeLevel.HARD, "entropinnyumSetup", ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENTROPINNYUM));
		addChallenge(EnumChallengeLevel.HARD, "spectrolusSetup", ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_SPECTROLUS));
		addChallenge(EnumChallengeLevel.HARD, "potionBrewer", new ItemStack(ModBlocks.brewery));

		addChallenge(EnumChallengeLevel.LUNATIC, "kekimurusSetup", ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS));
		addChallenge(EnumChallengeLevel.LUNATIC, "autoQuarry", new ItemStack(Item.pickaxeDiamond));
		addChallenge(EnumChallengeLevel.LUNATIC, "runeCrafter", new ItemStack(ModItems.rune));
	}

	public static void addChallenge(EnumChallengeLevel level, String name, ItemStack icon) {
		Challenge c = new Challenge("botania.challenge." + name, icon, level);
		challenges.get(level).add(c);
		challengeLookup.put(c.unlocalizedName, c);
	}

}
