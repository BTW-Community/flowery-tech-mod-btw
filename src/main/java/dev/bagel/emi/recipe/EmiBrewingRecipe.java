package dev.bagel.emi.recipe;

import btw.item.tag.TagInstance;
import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.dev.emi.emi.runtime.EmiDrawContext;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.client.lib.LibResources;

import java.util.ArrayList;
import java.util.List;

public class EmiBrewingRecipe extends BotaniaEmiRecipe {
    private final List<EmiIngredient> inputWithFlask;
    public EmiBrewingRecipe(RecipeBrew recipe, ItemStack flask) {
        super(BotaniaEmiPlugin.BREWING, null, recipe.getInputs().stream().map(tagOrStack -> {
            if (tagOrStack instanceof TagInstance ti) {
                return EmiIngredient.of(ti);
            }
            else return EmiStack.of((ItemStack) tagOrStack);
        }).toList(), EmiStack.of(recipe.getOutput(flask)));
        this.inputWithFlask = new ArrayList<>(this.inputs);
        this.inputWithFlask.add(EmiStack.of(flask));
    }

    @Override
    public int getDisplayWidth() {
        return 166;
    }

    @Override
    public int getDisplayHeight() {
        return 65;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputWithFlask;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, 0, 0, (draw, x, y, partial) -> {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            EmiDrawContext.wrap(draw).drawTexture(new ResourceLocation(LibResources.GUI_NEI_BREWERY), 0, 0, 0, 0, 166, 65);
        });
        int left = 95 - inputs.size() * 18 / 2;
        int i = 0;
        for (EmiIngredient ingredient : inputs) {
            widgets.addSlot(ingredient, left + i * 18, 5);
            i++;
        }
        widgets.addSlot(this.inputWithFlask.get(inputWithFlask.size() - 1), 38, 41);
        i = 0;
        for (EmiStack ingredient : outputs) {
            widgets.addSlot(ingredient, 86, 41).recipeContext(this);
            i++;
        }
    }
}
