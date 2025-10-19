package dev.bagel.emi;

import dev.bagel.emi.recipe.*;
import emi.dev.emi.emi.api.EmiPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.Comparison;
import emi.dev.emi.emi.api.stack.EmiStack;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.*;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.ArrayList;
import java.util.List;

public class BotaniaEmiPlugin implements EmiPlugin {
    public static EmiRecipeCategory PETAL_APOTHECARY = new EmiRecipeCategory(Botania.loc("petal_apothecary"), EmiStack.of(new ItemStack(ModBlocks.altar)));
    public static EmiRecipeCategory MANA_POOL = new EmiRecipeCategory(Botania.loc("mana_pool"), EmiStack.of(new ItemStack(ModBlocks.pool)));

    public static EmiRecipeCategory PURE_DAISY = new EmiRecipeCategory(Botania.loc("pure_daisy"), EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
    public static EmiRecipeCategory RUNIC_ALTAR = new EmiRecipeCategory(Botania.loc("runic_altar"), EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
    public static EmiRecipeCategory ELVEN_TRADE = new EmiRecipeCategory(Botania.loc("elven_trade"), EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
    public static EmiRecipeCategory BREWING = new EmiRecipeCategory(Botania.loc("brewing"), EmiStack.of(new ItemStack(ModBlocks.brewery)));

    @Override
    public void register(EmiRegistry reg) {
        reg.addCategory(PETAL_APOTHECARY);
        reg.addCategory(MANA_POOL);
        reg.addCategory(PURE_DAISY);
        reg.addCategory(RUNIC_ALTAR);
        reg.addCategory(ELVEN_TRADE);
        reg.addCategory(BREWING);

        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModBlocks.specialFlower)), Comparison.compareNbt());
        for (var s : BotaniaAPI.subtilesForCreativeMenu) {
            reg.addEmiStack(EmiStack.of(ItemBlockSpecialFlower.ofType(s)));
                if(BotaniaAPI.miniFlowers.containsKey(s))
                    reg.addEmiStack(EmiStack.of(ItemBlockSpecialFlower.ofType(BotaniaAPI.miniFlowers.get(s))));
        }
        for (var recipe : BotaniaAPI.petalRecipes) {
            reg.addRecipe(new EmiPetalRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.manaInfusionRecipes) {
            reg.addRecipe(new EmiPoolRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.pureDaisyRecipes) {
            reg.addRecipe(new EmiPureDaisyRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.runeAltarRecipes) {
            reg.addRecipe(new EmiRunicAltarRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.elvenTradeRecipes) {
            reg.addRecipe(new EmiElvenTradeRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.brewRecipes) {

        }
        //todo potentially lexica botania recipes?
        // also there is a keybind for requesting an item from corporea it would seem
        for (int i = 0; i < 9; i++) {
            reg.addWorkstation(PETAL_APOTHECARY, EmiStack.of(new ItemStack(ModBlocks.altar, 1, i)));
        }
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool)));
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool, 1, 2)));
        reg.addWorkstation(PURE_DAISY, EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
        reg.addWorkstation(RUNIC_ALTAR, EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
        reg.addWorkstation(ELVEN_TRADE, EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
        reg.addWorkstation(BREWING, EmiStack.of(new ItemStack(ModBlocks.brewery)));
    }
}
