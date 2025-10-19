package dev.bagel.emi.recipe;

import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.shims.java.com.unascribed.retroemi.RetroEMI;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;

import java.util.List;

public class EmiRunicAltarRecipe extends EmiPetalRecipe {
    private final int manaUsage;

    public EmiRunicAltarRecipe(RecipeRuneAltar recipe) {
        super(BotaniaEmiPlugin.RUNIC_ALTAR, null, recipe.getInputs().stream().map(RetroEMI::wildcardIngredient).toList(), List.of(EmiStack.of(recipe.getOutput())));
        this.manaUsage = recipe.getManaUsage();
    }

    @Override
    public int getDisplayWidth() {
        return 110;
    }

    @Override
    public int getDisplayHeight() {
        return 105;
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        super.addWidgets(widgets);
        widgets.addDrawable(0, 0, 0, 0, (drawContext, i, i1, v) -> {
            HUDHandler.renderManaBar(5, 93, 0x0000FF, 0.75F, manaUsage, TilePool.MAX_MANA / 10);
        });
        widgets.addSlot(EmiStack.of(new ItemStack(ModBlocks.runeAltar)), 43, 45).drawBack(false);
    }
}
