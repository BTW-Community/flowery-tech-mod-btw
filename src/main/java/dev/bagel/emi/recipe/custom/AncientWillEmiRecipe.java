package dev.bagel.emi.recipe.custom;

import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.GeneratedSlotWidget;
import emi.dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.common.crafting.recipe.AncientWillRecipe;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelHelm;
import vazkii.botania.common.lib.LibOreDict;

import java.util.List;

public class AncientWillEmiRecipe extends CustomBotaniaEmiRecipe {

    public AncientWillEmiRecipe(AncientWillRecipe recipe) {
        super(recipe, List.of(EmiIngredient.of(LibOreDict.ANCIENT_WILLS), EmiIngredient.of(LibOreDict.ANCIENT_WILL_CONTAINERS)), EmiStack.of(ModItems.terrasteelHelm));
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        return switch (slot) {
            case 0 -> new GeneratedSlotWidget(rand -> EmiStack.of(new ItemStack(ModItems.ancientWill, 1, rand.nextInt(6))), unique, x, y);
            case 1 -> inputSlot(slot, x, y);
            default -> empty(x, y);
        };
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new GeneratedSlotWidget(rand -> {
            ItemStack stack = new ItemStack(ModItems.terrasteelHelm);
            ((IAncientWillContainer) stack.getItem()).addAncientWill(stack, rand.nextInt(6));
            return EmiStack.of(stack);
        }, unique, x, y);
    }
}
