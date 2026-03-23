/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 2, 2014, 7:50:07 PM (GMT)]
 */
package vazkii.botania.common.crafting;

import java.util.ArrayList;
import java.util.List;

import api.item.tag.TagInstance;
import btw.item.BTWItems;
import btw.item.BTWTags;
import net.minecraft.src.Block;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;

public final class ModManaAlchemyRecipes {

	public static RecipeManaInfusion leatherRecipe;
	public static List<RecipeManaInfusion> woodRecipes;
	public static List<RecipeManaInfusion> saplingRecipes;
	public static RecipeManaInfusion glowstoneDustRecipe;
	public static List<RecipeManaInfusion> quartzRecipes;
	public static RecipeManaInfusion chiseledBrickRecipe;
	public static RecipeManaInfusion iceRecipe;
	public static List<RecipeManaInfusion> swampFolliageRecipes;
	public static List<RecipeManaInfusion> fishRecipes;
	public static List<RecipeManaInfusion> cropRecipes;
	public static RecipeManaInfusion potatoRecipe;
	public static RecipeManaInfusion netherWartRecipe;
	public static List<RecipeManaInfusion> gunpowderAndFlintRecipes;
	public static RecipeManaInfusion nameTagRecipe;
	public static List<RecipeManaInfusion> stringRecipes;
	public static List<RecipeManaInfusion> slimeballCactusRecipes;
	public static RecipeManaInfusion enderPearlRecipe;
	public static List<RecipeManaInfusion> redstoneToGlowstoneRecipes;
	public static RecipeManaInfusion sandRecipe;
	public static RecipeManaInfusion redSandRecipe;
	public static List<RecipeManaInfusion> clayBreakdownRecipes;
	public static RecipeManaInfusion coarseDirtRecipe;
	public static RecipeManaInfusion prismarineRecipe;
	public static List<RecipeManaInfusion> stoneRecipes;
	public static List<RecipeManaInfusion> tallgrassRecipes;
	public static List<RecipeManaInfusion> flowersRecipes;

	public static void init() {
		if (!ConfigHandler.enableDefaultRecipes) return;

		leatherRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.leather), new ItemStack(Item.rottenFlesh), 600);

		woodRecipes = new ArrayList<>();//todofix log2
//		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.wood, 1, 0), new ItemStack(Block.log2, 1, 1), 40));
		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.wood, 1, 1), new ItemStack(Block.wood, 1, 0), 40));
		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.wood, 1, 2), new ItemStack(Block.wood, 1, 1), 40));
		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.wood, 1, 3), new ItemStack(Block.wood, 1, 2), 40));
//		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.log2, 1, 0), new ItemStack(Block.wood, 1, 3), 40));
//		woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.log2, 1, 1), new ItemStack(Block.log2, 1, 0), 40));

		saplingRecipes = new ArrayList<>();
		for(int i = 0; i < 6; i++)
			saplingRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.sapling, 1, i == 5 ? 0 : i + 1), new ItemStack(Block.sapling, 1, i), 120));

		glowstoneDustRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.glowstone, 4), new ItemStack(Block.glowStone), 25);
		quartzRecipes = new ArrayList<>();
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.netherQuartz, 4), new ItemStack(Block.blockNetherQuartz, 1, Short.MAX_VALUE), 25));
		if(ConfigHandler.darkQuartzEnabled)
			quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 0), new ItemStack(ModFluffBlocks.darkQuartz, 1, Short.MAX_VALUE), 25));
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 1), new ItemStack(ModFluffBlocks.manaQuartz, 1, Short.MAX_VALUE), 25));
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 2), new ItemStack(ModFluffBlocks.blazeQuartz, 1, Short.MAX_VALUE), 25));
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 3), new ItemStack(ModFluffBlocks.lavenderQuartz, 1, Short.MAX_VALUE), 25));
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 4), new ItemStack(ModFluffBlocks.redQuartz, 1, Short.MAX_VALUE), 25));
		quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 5), new ItemStack(ModFluffBlocks.elfQuartz, 1, Short.MAX_VALUE), 25));

		chiseledBrickRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.stoneBrick, 1, 3), new ItemStack(Block.stoneBrick), 150);
		iceRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.ice), new ItemStack(Block.snow), 2250);

		swampFolliageRecipes = new ArrayList<>();
		swampFolliageRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.waterlily), new ItemStack(Block.vine), 320));
		swampFolliageRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.vine), new ItemStack(Block.waterlily), 320));

		fishRecipes = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			fishRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.fishRaw, 1, i == 3 ? 0 : i + 1), new ItemStack(Item.fishRaw, 1, i), 200));

		cropRecipes = new ArrayList<>();
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(BTWItems.wheatSeeds), new ItemStack(Item.dyePowder, 1, 3), 6000));
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.potato), new ItemStack(BTWItems.wheat), 6000));
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(BTWItems.carrot), new ItemStack(Item.potato), 6000));
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.melonSeeds), new ItemStack(BTWItems.carrot), 6000));
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.pumpkinSeeds), new ItemStack(Item.melonSeeds), 6000));
		cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.dyePowder, 1, 3), new ItemStack(Item.pumpkinSeeds), 6000));

		potatoRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.potato), new ItemStack(Item.poisonousPotato), 1200);
		netherWartRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.blazeRod), 4000);

		gunpowderAndFlintRecipes = new ArrayList<>();
		gunpowderAndFlintRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.flint), new ItemStack(Item.gunpowder), 200));
		gunpowderAndFlintRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.gunpowder), new ItemStack(Item.flint), 4000));

		nameTagRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.nameTag), new ItemStack(Item.writableBook), 16000);

		stringRecipes = new ArrayList<>();
//		for(int i = 0; i < 16; i++)
//			stringRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.silk, 1), TagInstance.of(BTWTags.wools) /*new ItemStack(Block.cloth, 1, i)*/, 100));

		slimeballCactusRecipes = new ArrayList<>();
		slimeballCactusRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.slimeBall), new ItemStack(Block.cactus), 1200));
		slimeballCactusRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.cactus), new ItemStack(Item.slimeBall), 1200));

		enderPearlRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.enderPearl), new ItemStack(Item.ghastTear), 28000);

		redstoneToGlowstoneRecipes = new ArrayList<>();
		redstoneToGlowstoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.redstone), new ItemStack(Item.glowstone), 300));
		redstoneToGlowstoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.glowstone), new ItemStack(Item.redstone), 300));

		sandRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.sand), new ItemStack(Block.cobblestone), 50);
		redSandRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.sand, 1, 1), new ItemStack(Block.hardenedClay), 50);

		clayBreakdownRecipes = new ArrayList<>();
		clayBreakdownRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.clay, 4), new ItemStack(Block.blockClay), 25));
		clayBreakdownRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Item.brick, 4), new ItemStack(Block.brick), 25));

		coarseDirtRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.dirt, 1, 1), new ItemStack(Block.dirt), 120);

		prismarineRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.manaResource, 1, 10), new ItemStack(Item.netherQuartz), 200);

		if(ConfigHandler.stones18Enabled) {
			stoneRecipes = new ArrayList<>();
			stoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModFluffBlocks.stone), new ItemStack(Block.stone), 200));
			for(int i = 0; i < 4; i++)
				stoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModFluffBlocks.stone, 1, i), new ItemStack(ModFluffBlocks.stone, 1, i == 0 ? 3 : i - 1), 200));
		}

		tallgrassRecipes = new ArrayList<>();
		tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.deadBush), new ItemStack(Block.tallGrass, 1, 2), 500));
		tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.tallGrass, 1, 1), new ItemStack(Block.deadBush), 500));
		tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.tallGrass, 1, 2), new ItemStack(Block.tallGrass, 1, 1), 500));

		flowersRecipes = new ArrayList<>();
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed), new ItemStack(Block.plantYellow), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 1), new ItemStack(Block.plantRed), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 2), new ItemStack(Block.plantRed, 1, 1), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 3), new ItemStack(Block.plantRed, 1, 2), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 4), new ItemStack(Block.plantRed, 1, 3), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 5), new ItemStack(Block.plantRed, 1, 4), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 6), new ItemStack(Block.plantRed, 1, 5), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 7), new ItemStack(Block.plantRed, 1, 6), 400));
		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantRed, 1, 8), new ItemStack(Block.plantRed, 1, 7), 400));
//		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.double_plant), new ItemStack(Block.plantRed, 1, 8), 400));
//		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.double_plant, 1, 1), new ItemStack(Block.double_plant), 400));
//		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.double_plant, 1, 4), new ItemStack(Block.double_plant, 1, 1), 400));
//		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.double_plant, 1, 5), new ItemStack(Block.double_plant, 1, 4), 400));
//		flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.plantYellow), new ItemStack(Block.double_plant, 1, 5), 400));
	}
}
