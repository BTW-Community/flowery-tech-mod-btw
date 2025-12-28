/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 19, 2014, 4:30:32 PM (GMT)]
 */
package vazkii.botania.common.lib;

import btw.block.BTWBlocks;
import btw.item.BTWItems;
import api.item.tag.Tag;
import dev.bagel.util.Items;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.item.ModItems;

import static vazkii.botania.common.Botania.loc;

public final class LibOreDict {

	public static final ItemStack LEXICON = new ItemStack(ModItems.lexicon);
	public static final ItemStack PESTLE_AND_MORTAR = new ItemStack(ModItems.pestleAndMortar);
	public static final ItemStack TWIG_WAND = new ItemStack(ModItems.twigWand);
	public static final ItemStack LIVING_WOOD = new ItemStack(ModBlocks.livingwood);
	public static final ItemStack LIVING_ROCK = new ItemStack(ModBlocks.livingrock);
	public static final ItemStack MANA_STEEL = new ItemStack(ModItems.manaResource, 1, 0);
	public static final ItemStack MANA_PEARL = new ItemStack(ModItems.manaResource, 1, 1);
	public static final ItemStack MANA_DIAMOND = new ItemStack(ModItems.manaResource, 1, 2);
	public static final ItemStack LIVINGWOOD_TWIG = new ItemStack(ModItems.manaResource, 1, 3);
	public static final ItemStack TERRA_STEEL = new ItemStack(ModItems.manaResource, 1, 4);
	public static final ItemStack LIFE_ESSENCE = new ItemStack(ModItems.manaResource, 1, 5);
	public static final ItemStack REDSTONE_ROOT = new ItemStack(ModItems.manaResource, 1, 6);
	public static final ItemStack DREAM_WOOD = new ItemStack(ModBlocks.dreamwood);
	public static final ItemStack ELEMENTIUM = new ItemStack(ModItems.manaResource, 1, 7);
	public static final ItemStack PIXIE_DUST = new ItemStack(ModItems.manaResource, 1, 8);
	public static final ItemStack DRAGONSTONE = new ItemStack(ModItems.manaResource, 1, 9);
	public static final ItemStack PRISMARINE_SHARD = new ItemStack(ModItems.manaResource, 1, 10);
	public static final ItemStack PLACEHOLDER = new ItemStack(ModItems.manaResource, 1, 11);
	public static final ItemStack RED_STRING = new ItemStack(ModItems.manaResource, 1, 12);
	public static final ItemStack DREAMWOOD_TWIG = new ItemStack(ModItems.manaResource, 1, 13);
	public static final ItemStack GAIA_INGOT = new ItemStack(ModItems.manaResource, 1, 14);
	public static final ItemStack ENDER_AIR_BOTTLE = new ItemStack(ModItems.manaResource, 1, 15);
	public static final ItemStack MANA_STRING = new ItemStack(ModItems.manaResource, 1, 16);
	public static final ItemStack MANASTEEL_NUGGET = new ItemStack(ModItems.manaResource, 1, 17);
	public static final ItemStack TERRASTEEL_NUGGET = new ItemStack(ModItems.manaResource, 1, 18);
	public static final ItemStack ELEMENTIUM_NUGGET = new ItemStack(ModItems.manaResource, 1, 19);
	public static final ItemStack ROOT = new ItemStack(ModItems.manaResource, 1, 20);
	public static final ItemStack PEBBLE = new ItemStack(ModItems.manaResource, 1, 21);
	public static final ItemStack MANAWEAVE_CLOTH = new ItemStack(ModItems.manaResource, 1, 22);
	public static final ItemStack MANA_POWDER = new ItemStack(ModItems.manaResource, 1, 23);

	public static final String VIAL = "bVial";
	public static final String FLASK = "bFlask";

	public static final String PRISMARINE_BLOCK = "blockPrismarine";
	public static final Block BLAZE_BLOCK = ModBlocks.blazeBlock;

	private static final Item FLOWER_ITEM = Items.getItemFromBlock(ModBlocks.flower);
	public static final ItemStack[] FLOWER = new ItemStack[] {
			new ItemStack(FLOWER_ITEM, 1, 0), new ItemStack(FLOWER_ITEM, 1, 1), new ItemStack(FLOWER_ITEM, 1, 2), new ItemStack(FLOWER_ITEM, 1, 3),
			new ItemStack(FLOWER_ITEM, 1, 4), new ItemStack(FLOWER_ITEM, 1, 5), new ItemStack(FLOWER_ITEM, 1, 6), new ItemStack(FLOWER_ITEM, 1, 7),
			new ItemStack(FLOWER_ITEM, 1, 8), new ItemStack(FLOWER_ITEM, 1, 9), new ItemStack(FLOWER_ITEM, 1, 10), new ItemStack(FLOWER_ITEM, 1, 11),
			new ItemStack(FLOWER_ITEM, 1, 12), new ItemStack(FLOWER_ITEM, 1, 13), new ItemStack(FLOWER_ITEM, 1, 14), new ItemStack(FLOWER_ITEM, 1, 15)
	};

	private static final Item DOUBLE_FLOWER_ITEM_1 = Items.getItemFromBlock(ModBlocks.doubleFlower1);
	private static final Item DOUBLE_FLOWER_ITEM_2 = Items.getItemFromBlock(ModBlocks.doubleFlower2);
	public static final ItemStack[] DOUBLE_FLOWER = new ItemStack[] {
			new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 0), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 1), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 2), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 3),
			new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 4), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 5), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 6), new ItemStack(DOUBLE_FLOWER_ITEM_1, 1, 7),
			new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 0), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 1), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 2), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 3),
			new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 4), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 5), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 6), new ItemStack(DOUBLE_FLOWER_ITEM_2, 1, 7),
	};
	public static final Tag PETALS = Tag.of(loc("petal")).addUntilDamage(15, ModItems.petal);

	public static final ItemStack[] PETAL = new ItemStack[] {
			new ItemStack(ModItems.petal, 1, 0), new ItemStack(ModItems.petal, 1, 1), new ItemStack(ModItems.petal, 1, 2), new ItemStack(ModItems.petal, 1, 3),
			new ItemStack(ModItems.petal, 1, 4), new ItemStack(ModItems.petal, 1, 5), new ItemStack(ModItems.petal, 1, 6), new ItemStack(ModItems.petal, 1, 7),
			new ItemStack(ModItems.petal, 1, 8), new ItemStack(ModItems.petal, 1, 9), new ItemStack(ModItems.petal, 1, 10), new ItemStack(ModItems.petal, 1, 11),
			new ItemStack(ModItems.petal, 1, 12), new ItemStack(ModItems.petal, 1, 13), new ItemStack(ModItems.petal, 1, 14), new ItemStack(ModItems.petal, 1, 15)
	};

	public static final String[] DYE = new String[] {
		"dyeWhite", "dyeOrange", "dyeMagenta", "dyeLightBlue",
		"dyeYellow", "dyeLime", "dyePink", "dyeGray",
		"dyeLightGray", "dyeCyan", "dyePurple", "dyeBlue",
		"dyeBrown", "dyeGreen", "dyeRed", "dyeBlack"
	};

	public static final ItemStack[] RUNE = new ItemStack[] {
			new ItemStack(ModItems.rune, 1, 0), new ItemStack(ModItems.rune, 1, 1), new ItemStack(ModItems.rune, 1, 2), new ItemStack(ModItems.rune, 1, 3),
			new ItemStack(ModItems.rune, 1, 4), new ItemStack(ModItems.rune, 1, 5), new ItemStack(ModItems.rune, 1, 6), new ItemStack(ModItems.rune, 1, 7),
			new ItemStack(ModItems.rune, 1, 8), new ItemStack(ModItems.rune, 1, 9), new ItemStack(ModItems.rune, 1, 10), new ItemStack(ModItems.rune, 1, 11),
			new ItemStack(ModItems.rune, 1, 12), new ItemStack(ModItems.rune, 1, 13), new ItemStack(ModItems.rune, 1, 14), new ItemStack(ModItems.rune, 1, 15)
	};

	public static final ItemStack[] STONE_18_VARIANTS = new ItemStack[]{
			new ItemStack(ModFluffBlocks.stone, 1, 0), new ItemStack(ModFluffBlocks.stone, 1, 1), new ItemStack(ModFluffBlocks.stone, 1, 2), new ItemStack(ModFluffBlocks.stone, 1, 3),
			new ItemStack(ModFluffBlocks.stone, 1, 4), new ItemStack(ModFluffBlocks.stone, 1, 5), new ItemStack(ModFluffBlocks.stone, 1, 6), new ItemStack(ModFluffBlocks.stone, 1, 7),
			new ItemStack(ModFluffBlocks.stone, 1, 8), new ItemStack(ModFluffBlocks.stone, 1, 9), new ItemStack(ModFluffBlocks.stone, 1, 10), new ItemStack(ModFluffBlocks.stone, 1, 11),
			new ItemStack(ModFluffBlocks.stone, 1, 12), new ItemStack(ModFluffBlocks.stone, 1, 13), new ItemStack(ModFluffBlocks.stone, 1, 14), new ItemStack(ModFluffBlocks.stone, 1, 15)
	};

	public static final ItemStack[] QUARTZ = new ItemStack[] {
			new ItemStack(ModItems.quartz, 1, 0), new ItemStack(ModItems.quartz, 1, 1),
			new ItemStack(ModItems.quartz, 1, 2), new ItemStack(ModItems.rune, 1, 3),
			new ItemStack(ModItems.quartz, 1, 4), new ItemStack(ModItems.quartz, 1, 5),
			new ItemStack(ModItems.quartz, 1, 6), new ItemStack(ModItems.quartz, 1, 7)
//		"quartzDark", "quartzMana", "quartzBlaze",
//		"quartzLavender", "quartzRed", "quartzElven", "quartzSunny"
	};

	public static final Tag MANA_DIAMOND_AND_PEARL = Tag.of(loc("mana_pearl_and_diamond"), MANA_PEARL, MANA_DIAMOND);
	public static final Tag DYE_POWDERS = Tag.of(loc("dye_powders")).addUntilDamage(15, ModItems.dye);


	public static final Tag ANCIENT_WILL_CONTAINERS = Tag.of(loc("ancient_will_compatable")).add(ModItems.terrasteelHelm);
	public static final Tag ANCIENT_WILLS = Tag.of(loc("ancient_wills")).addUntilDamage(5, ModItems.ancientWill);
}
