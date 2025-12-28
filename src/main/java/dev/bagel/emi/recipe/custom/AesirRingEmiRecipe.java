package dev.bagel.emi.recipe.custom;

import dev.bagel.emi.recipe.BotaniaEmiRecipe;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.SlotWidget;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.src.IRecipe;
import net.minecraft.src.ResourceLocation;
import vazkii.botania.common.crafting.recipe.AesirRingRecipe;
import vazkii.botania.common.item.ModItems;

import java.util.List;

public class AesirRingEmiRecipe extends CustomBotaniaEmiRecipe {

    public AesirRingEmiRecipe(AesirRingRecipe recipe) {
        super(recipe, List.of(EmiStack.of(ModItems.thorRing), EmiStack.of(ModItems.lokiRing), EmiStack.of(ModItems.odinRing)), EmiStack.of(ModItems.aesirRing));
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        return switch (slot) {
            case 0, 1, 2 -> inputSlot(slot, x, y);
            default -> empty(x, y);
        };
    }
}
