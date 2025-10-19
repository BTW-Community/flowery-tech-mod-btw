/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Nov 1, 2014, 9:15:15 PM (GMT)]
 */
package vazkii.botania.common.crafting;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.common.brew.ModBrews;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;

public class ModBrewRecipes {

	public static RecipeBrew speedBrew;
	public static RecipeBrew strengthBrew;
	public static RecipeBrew hasteBrew;
	public static RecipeBrew healingBrew;
	public static RecipeBrew jumpBoostBrew;
	public static RecipeBrew regenerationBrew;
	public static RecipeBrew weakRegenerationBrew;
	public static RecipeBrew resistanceBrew;
	public static RecipeBrew fireResistanceBrew;
	public static RecipeBrew waterBreathingBrew;
	public static RecipeBrew invisibilityBrew;
	public static RecipeBrew nightVisionBrew;
	public static RecipeBrew absorptionBrew;

	public static RecipeBrew overloadBrew;
	public static RecipeBrew soulCrossBrew;
	public static RecipeBrew featherFeetBrew;
	public static RecipeBrew emptinessBrew;
	public static RecipeBrew bloodthirstBrew;
	public static RecipeBrew allureBrew;
	public static RecipeBrew clearBrew;

	public static RecipeBrew warpWardBrew;

	public static void init() {
		if (!ConfigHandler.enableDefaultRecipes) return;

		speedBrew = BotaniaAPI.registerBrewRecipe(ModBrews.speed, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.sugar), new ItemStack(Item.redstone));
		strengthBrew = BotaniaAPI.registerBrewRecipe(ModBrews.strength, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.blazePowder), new ItemStack(Item.glowstone));
		hasteBrew = BotaniaAPI.registerBrewRecipe(ModBrews.haste, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.sugar), new ItemStack(Item.goldNugget));
		healingBrew = BotaniaAPI.registerBrewRecipe(ModBrews.healing, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.speckledMelon), new ItemStack(Item.potato));
		jumpBoostBrew = BotaniaAPI.registerBrewRecipe(ModBrews.jumpBoost, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.feather), new ItemStack(Item.carrot));
		regenerationBrew = BotaniaAPI.registerBrewRecipe(ModBrews.regen, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.ghastTear), new ItemStack(Item.glowstone));
		weakRegenerationBrew = BotaniaAPI.registerBrewRecipe(ModBrews.regenWeak, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.ghastTear), new ItemStack(Item.redstone));
		resistanceBrew = BotaniaAPI.registerBrewRecipe(ModBrews.resistance, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.ingotIron), new ItemStack(Item.leather));
		fireResistanceBrew = BotaniaAPI.registerBrewRecipe(ModBrews.fireResistance, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.magmaCream), new ItemStack(Block.netherrack));
		waterBreathingBrew = BotaniaAPI.registerBrewRecipe(ModBrews.waterBreathing, new ItemStack(Item.netherStalkSeeds), new ItemStack(ModItems.manaResource, 1, 10), new ItemStack(Item.glowstone));
		invisibilityBrew = BotaniaAPI.registerBrewRecipe(ModBrews.invisibility, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.snowball), new ItemStack(Item.glowstone));
		nightVisionBrew = BotaniaAPI.registerBrewRecipe(ModBrews.nightVision, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.spiderEye), new ItemStack(Item.goldenCarrot));
		absorptionBrew = BotaniaAPI.registerBrewRecipe(ModBrews.absorption, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.appleGold), new ItemStack(Item.potato));

		overloadBrew = BotaniaAPI.registerBrewRecipe(ModBrews.overload, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.blazePowder), new ItemStack(Item.sugar), new ItemStack(Item.glowstone), new ItemStack(ModItems.manaResource), new ItemStack(Item.spiderEye));
		soulCrossBrew = BotaniaAPI.registerBrewRecipe(ModBrews.soulCross, new ItemStack(Item.netherStalkSeeds), new ItemStack(Block.slowSand), new ItemStack(Item.paper), new ItemStack(Item.appleRed), new ItemStack(Item.bone));
		featherFeetBrew = BotaniaAPI.registerBrewRecipe(ModBrews.featherfeet, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.feather), new ItemStack(Item.leather), new ItemStack(Block.cloth, 1, -1));
		emptinessBrew = BotaniaAPI.registerBrewRecipe(ModBrews.emptiness, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.gunpowder), new ItemStack(Item.rottenFlesh), new ItemStack(Item.bone), new ItemStack(Item.silk), new ItemStack(Item.enderPearl));
		bloodthirstBrew = BotaniaAPI.registerBrewRecipe(ModBrews.bloodthirst, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.fermentedSpiderEye), new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.fireballCharge), new ItemStack(Item.ingotIron));
		allureBrew = BotaniaAPI.registerBrewRecipe(ModBrews.allure, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.fishRaw), new ItemStack(Item.netherQuartz), new ItemStack(Item.goldenCarrot));
		clearBrew = BotaniaAPI.registerBrewRecipe(ModBrews.clear, new ItemStack(Item.netherStalkSeeds), new ItemStack(Item.netherQuartz), new ItemStack(Item.emerald), new ItemStack(Item.melon));
	}
}
