package dev.bagel.emi.recipe.custom;

import api.item.tag.TagOrStack;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.SlotWidget;
import emi.shims.java.com.unascribed.retroemi.RetroEMI;
import net.minecraft.src.*;
import vazkii.botania.common.crafting.recipe.AesirRingRecipe;
import vazkii.botania.common.crafting.recipe.AncientWillRecipe;

import java.util.List;

public abstract class CustomBotaniaEmiRecipe extends EmiPatternCraftingRecipe {
    protected final int unique = 2048629234;
    public CustomBotaniaEmiRecipe(IRecipe recipe, List<EmiIngredient> input, EmiStack output) {
        super(input, output, recipe.getId());
    }

    @Override
    public abstract SlotWidget getInputWidget(int slot, int x, int y);

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new SlotWidget(output, x, y);
    }

    protected SlotWidget inputSlot(int slot, int x, int y) {
        if (slot > input.size()) {
            return empty(x, y);
        }
        return new SlotWidget(input.get(slot), x, y);
    }

    protected SlotWidget slot(TagOrStack stack, int x, int y) {
        return new SlotWidget(RetroEMI.wildcardIngredient(stack), x, y);
    }

    protected SlotWidget slot(Item item, int x, int y) {
        return slot(new ItemStack(item), x, y);
    }

    protected SlotWidget empty(int x, int y) {
        return new SlotWidget(EmiStack.EMPTY, x, y);
    }

    public static void initCustomRecipes(EmiRegistry reg) {
        for (IRecipe irecipe : (List<IRecipe>) CraftingManager.getInstance().getRecipes()) {
            if (irecipe instanceof AesirRingRecipe recipe) {
                reg.addRecipe(new AesirRingEmiRecipe(recipe));
            } else if (irecipe instanceof AncientWillRecipe recipe) {
                reg.addRecipe(new AncientWillEmiRecipe(recipe));
            }
        }
    }
}
