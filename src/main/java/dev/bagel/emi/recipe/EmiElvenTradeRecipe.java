package dev.bagel.emi.recipe;

import dev.bagel.client.RenderInstances;
import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import emi.dev.emi.emi.runtime.EmiDrawContext;
import emi.shims.java.com.unascribed.retroemi.RetroEMI;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.TextureMap;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.BlockAlfPortal;


public class EmiElvenTradeRecipe extends BotaniaEmiRecipe {
    public EmiElvenTradeRecipe(RecipeElvenTrade recipe) {
        super(BotaniaEmiPlugin.ELVEN_TRADE, null, recipe.getInputs().stream().map(RetroEMI::wildcardIngredient).toList(), EmiStack.of(recipe.getOutput()));
    }

    @Override
    public int getDisplayWidth() {
        return 110;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addDrawable(0, 0, 0, 0, ((drawContext, i, i1, v) -> {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
            EmiDrawContext.wrap(drawContext).drawTexture(new ResourceLocation(LibResources.GUI_ELVEN_TRADE_OVERLAY), 5, 10, 17, 17, 100, 80);
            GL11.glDisable(GL11.GL_BLEND);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
            RenderInstances.getItemInstance().renderIcon(10, 29, BlockAlfPortal.portalTex, 48, 48);
        }));
        int i = 0;
        for(EmiIngredient o : inputs) {
            widgets.addSlot(o, 35 + i * 18, 6);
            i++;
        }
        widgets.addSlot(outputs.get(0), 81, 45).recipeContext(this).drawBack(false);
    }
}
