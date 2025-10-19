package dev.bagel.emi.recipe;

import btw.item.tag.TagOrStack;
import dev.bagel.emi.BotaniaEmiPlugin;
import dev.bagel.util.Items;
import emi.dev.emi.emi.api.recipe.EmiRecipe;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.dev.emi.emi.runtime.EmiDrawContext;
import emi.shims.java.com.unascribed.retroemi.RetroEMI;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;

import java.util.List;

public class EmiPoolRecipe extends BotaniaEmiRecipe {
    private final int mana;
    private final boolean isAlchemy;
    private final boolean isConjuration;
    private final EmiStack catalyst;

    public EmiPoolRecipe(RecipeManaInfusion recipe) {
        super(BotaniaEmiPlugin.MANA_POOL, null, RetroEMI.wildcardIngredient(recipe.getInput()), EmiStack.of(recipe.getOutput()));
        this.mana = recipe.getManaToConsume();
        this.isAlchemy = recipe.isAlchemy();
        this.isConjuration = recipe.isConjuration();
        if (isAlchemy) {
            this.catalyst = EmiStack.of(ModBlocks.alchemyCatalyst);
        }
        else if (isConjuration) {
            this.catalyst = EmiStack.of(ModBlocks.conjurationCatalyst);
        }
        else  {
            this.catalyst = EmiStack.EMPTY;
        }
    }

    @Override
    public int getDisplayWidth() {
        return 120;
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(catalyst);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, 0, 0, (draw, mx, my, partials) -> {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            EmiDrawContext.wrap(draw).drawTexture(new ResourceLocation(LibResources.GUI_MANA_INFUSION_OVERLAY), 25, 0, 38, 35, 92, 50);
            HUDHandler.renderManaBar(12, 60, 0x0000FF, 0.75F, this.mana, TilePool.MAX_MANA / 10);
            RenderTilePool.forceMana = true;
        });
        if (isConjuration || isAlchemy) {
            widgets.addSlot(catalyst, 51, 34).drawBack(false);
        }
        widgets.addSlot(EmiStack.of(new ItemStack(ModBlocks.pool, 1, outputs.get(0).getItemStack().getItem() == Items.getItemFromBlock(ModBlocks.pool) ? 2 : 0)), 51, 17).drawBack(false);

        widgets.addSlot(inputs.get(0), 22, 17);

        widgets.addSlot(outputs.get(0), 81, 17);
    }
}
