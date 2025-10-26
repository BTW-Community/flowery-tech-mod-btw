package dev.bagel.emi.recipe;

import dev.bagel.emi.BotaniaEmiPlugin;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.page.PageText;

import java.util.List;

public class EmiLexicaBotaniaRecipe extends BotaniaEmiRecipe{
    private final LexiconEntry entry;
    private final ItemStack stack;
    public EmiLexicaBotaniaRecipe(LexiconEntry entry, ItemStack stack, int page) {
        super(BotaniaEmiPlugin.LEXICA_BOTANIA, new ResourceLocation(entry.getUnlocalizedName().replace('.', '_') + "/page_" + page), List.of(EmiStack.of(stack)), List.of());
        this.entry = entry;
        this.stack = stack;
    }

    @Override
    public int getDisplayWidth() {
        return 160;
    }

    @Override
    public int getDisplayHeight() {
        return 200;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {

        widgets.addDrawable(0, 0, 0, 0, (draw, x, y, partial) -> {
            FontRenderer font = Minecraft.getMinecraft().fontRenderer;

            String s = EnumChatFormatting.UNDERLINE + StatCollector.translateToLocal(entry.getUnlocalizedName());
            font.drawString(s, 82 - font.getStringWidth(s) / 2, 30, 4210752);

            KnowledgeType type = entry.getKnowledgeType();
            s = type.color + StatCollector.translateToLocal(type.getUnlocalizedName()).replaceAll("\\&.", "");
            font.drawString(s, 82 - font.getStringWidth(s) / 2, 42, 4210752);

            s = "\"" + StatCollector.translateToLocal(entry.getTagline()) + "\"";
            PageText.renderText(5, 42, 160, 200, s);

            String key = LexiconRecipeMappings.stackToString(stack);
            String quickInfo = "botania.nei.quickInfo:" + key;
            String quickInfoLocal = StatCollector.translateToLocal(quickInfo);

            if(GuiScreen.isShiftKeyDown() && GuiScreen.isCtrlKeyDown() && Minecraft.getMinecraft().gameSettings.advancedItemTooltips)
                s = "name: " + key;
            else if(quickInfo.equals(quickInfoLocal))
                s = StatCollector.translateToLocal("botania.nei.lexicaNoInfo");
            else {
                s = StatCollector.translateToLocal("botania.nei.lexicaSeparator");
                font.drawString(s, 82 - font.getStringWidth(s) / 2, 80, 4210752);
                s = quickInfoLocal;
            }

            PageText.renderText(5, 80, 160, 200, s);
        });

        widgets.addSlot(EmiStack.of(stack), 91, 5);
        widgets.addSlot(EmiStack.of(new ItemStack(ModItems.lexicon)), 51, 5);
    }
}
