package dev.bagel.emi.recipe;

import btw.item.tag.BTWTags;
import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.recipe.EmiRecipe;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.dev.emi.emi.runtime.EmiDrawContext;
import emi.shims.java.com.unascribed.retroemi.RetroEMI;
import net.minecraft.src.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmiPetalRecipe implements EmiRecipe {

    private final List<EmiIngredient> inputs;
    private final EmiStack output;
    private final ResourceLocation recipeId;

    public EmiPetalRecipe(RecipePetals recipe) {
        this.inputs = recipe.getInputs().stream().map(RetroEMI::wildcardIngredient).collect(Collectors.toList());
        this.output = EmiStack.of(recipe.getOutput());
        String type = ItemBlockSpecialFlower.getType(recipe.getOutput());
        this.recipeId = Botania.loc(type.isEmpty() ? output.getId().getResourcePath() : type);
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return BotaniaEmiPlugin.PETAL_APOTHECARY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return recipeId;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public int getDisplayWidth() {
        return 110;
    }

    @Override
    public int getDisplayHeight() {
        return 95;
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(EmiIngredient.of(BTWTags.seeds));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, 45, 10, (draw, mx, my, partials) -> {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            EmiDrawContext.wrap(draw).drawTexture(new ResourceLocation(LibResources.GUI_PETAL_OVERLAY), 15, 0, 38, 7, 92, 92);
//            GuiDraw.drawTexturedModalRect(45, 10, 38, 7, 92, 92);
        });


        float degreePerInput = 360F / inputs.size();
        float currentDegree = -90F;

        for(EmiIngredient o : inputs) {
            int posX = (int) Math.round(43 + Math.cos(currentDegree * Math.PI / 180D) * 32);
            int posY = (int) Math.round(45 + Math.sin(currentDegree * Math.PI / 180D) * 32);

            widgets.addSlot(o, posX, posY).drawBack(false);
            currentDegree += degreePerInput;
        }
        widgets.addSlot(output, 80, 10).drawBack(false);
    }
}
