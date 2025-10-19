package dev.bagel.emi;

import dev.bagel.emi.recipe.EmiPetalRecipe;
import dev.bagel.emi.recipe.EmiPoolRecipe;
import emi.dev.emi.emi.api.EmiPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.Comparison;
import emi.dev.emi.emi.api.stack.EmiStack;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BotaniaEmiPlugin implements EmiPlugin {
    public static EmiRecipeCategory PETAL_APOTHECARY = new EmiRecipeCategory(Botania.loc("petal_apothecary"), EmiStack.of(new ItemStack(ModBlocks.altar)));
    public static EmiRecipeCategory MANA_POOL = new EmiRecipeCategory(Botania.loc("petal_apothecary"), EmiStack.of(new ItemStack(ModBlocks.pool)));

    @Override
    public void register(EmiRegistry reg) {
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
        reg.addCategory(PETAL_APOTHECARY);
        reg.addWorkstation(PETAL_APOTHECARY, EmiStack.of(new ItemStack(ModBlocks.altar)));
        reg.addCategory(MANA_POOL);
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool)));
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool, 1, 2)));
    }
}
