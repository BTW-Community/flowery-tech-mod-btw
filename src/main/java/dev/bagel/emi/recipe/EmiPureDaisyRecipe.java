package dev.bagel.emi.recipe;

import btw.item.tag.Tag;
import btw.item.tag.TagInstance;
import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.dev.emi.emi.runtime.EmiDrawContext;
import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.recipe.RecipePureDaisy;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.List;

public class EmiPureDaisyRecipe extends BotaniaEmiRecipe {
    public EmiPureDaisyRecipe(RecipePureDaisy recipe) {
        super(BotaniaEmiPlugin.PURE_DAISY, null, getIngredient(recipe.getInput()), EmiStack.of(recipe.getOutput()));
    }

    @Override
    public int getDisplayWidth() {
        return 83;
    }

    @Override
    public int getDisplayHeight() {
        return 49;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, 0, 0, ((drawContext, i, i1, v) -> {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            EmiDrawContext.wrap(drawContext).drawTexture(new ResourceLocation(LibResources.GUI_PURE_DAISY_OVERLAY), 10, 2, 0, 0, 65, 44);
        }));

        widgets.addSlot(inputs.get(0), 2, 15);

        widgets.addSlot(EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)), 32, 15);

        widgets.addSlot(outputs.get(0), 62, 15).recipeContext(this);
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
    }

    private static EmiIngredient getIngredient(Object tagOrStackOrBlock) {
        if (tagOrStackOrBlock instanceof ItemStack stack) {
            return EmiStack.of(stack);
        }
        else if (tagOrStackOrBlock instanceof Block block) {
            return EmiStack.of(block);
        }
        else if (tagOrStackOrBlock instanceof TagInstance tagInstance) {
            return EmiIngredient.of(tagInstance);
        }
        else return EmiIngredient.of((Tag) tagOrStackOrBlock);
    }
}
