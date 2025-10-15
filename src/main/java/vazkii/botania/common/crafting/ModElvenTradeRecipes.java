package vazkii.botania.common.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public class ModElvenTradeRecipes {

	public static RecipeElvenTrade dreamwoodRecipe;
	public static List<RecipeElvenTrade> elementiumRecipes;
	public static RecipeElvenTrade pixieDustRecipe;
	public static List<RecipeElvenTrade> dragonstoneRecipes;
	public static RecipeElvenTrade elvenQuartzRecipe;
	public static RecipeElvenTrade alfglassRecipe;

	public static void init() {
		if (!ConfigHandler.enableDefaultRecipes) return;

		dreamwoodRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.dreamwood), LibOreDict.LIVING_WOOD);

		elementiumRecipes = new ArrayList<>();
		elementiumRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 7), LibOreDict.MANA_STEEL, LibOreDict.MANA_STEEL));
		elementiumRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.storage, 1, 2), new ItemStack(ModBlocks.storage), new ItemStack(ModBlocks.storage)));

		pixieDustRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 8), LibOreDict.MANA_PEARL);
		dragonstoneRecipes = new ArrayList<>();
		dragonstoneRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 9), LibOreDict.MANA_DIAMOND));
		dragonstoneRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.storage, 1, 4), new ItemStack(ModBlocks.storage, 1, 3)));

		elvenQuartzRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.quartz, 1, 5), new ItemStack(Item.netherQuartz));
		alfglassRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.elfGlass), new ItemStack(ModBlocks.manaGlass));

		BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron));
		BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Block.blockIron), new ItemStack(Block.blockIron));
		BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Item.enderPearl), new ItemStack(Item.enderPearl));
		BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Item.diamond), new ItemStack(Item.diamond));
		BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Block.blockDiamond), new ItemStack(Block.blockDiamond));
	}

}
