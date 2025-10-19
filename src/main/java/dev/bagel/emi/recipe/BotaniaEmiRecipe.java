package dev.bagel.emi.recipe;

import emi.dev.emi.emi.api.recipe.EmiRecipe;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import net.minecraft.src.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public abstract class BotaniaEmiRecipe implements EmiRecipe {
    private final EmiRecipeCategory category;
    private final ResourceLocation id;
    protected final List<EmiIngredient> inputs;
    protected final List<EmiStack> outputs;

    public BotaniaEmiRecipe(EmiRecipeCategory category, ResourceLocation id, List<EmiIngredient> inputs, List<EmiStack> outputs) {
        this.category = category;
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public BotaniaEmiRecipe(EmiRecipeCategory category, ResourceLocation id, List<EmiIngredient> inputs, EmiStack output) {
        this(category, id, inputs, Collections.singletonList(output));
    }

    public BotaniaEmiRecipe(EmiRecipeCategory category, ResourceLocation id, EmiIngredient input, EmiStack output) {
        this(category, id, Collections.singletonList(input), Collections.singletonList(output));
    }


    @Override
    public EmiRecipeCategory getCategory() {
        return category;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }
}
