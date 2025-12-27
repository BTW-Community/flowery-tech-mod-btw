package vazkii.botania.common.crafting.recipe;

import emi.dev.emi.emi.data.EmiData;
import emi.shims.java.net.minecraft.util.SyntheticIdentifier;
import net.minecraft.src.IRecipe;
import net.minecraft.src.ResourceLocation;

public abstract class BotaniaIRecipe implements IRecipe {
    private final ResourceLocation id;

    public BotaniaIRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipe hideFromEMI() {
        EmiData.hideRecipe(getId());
        return this;
    }
}
