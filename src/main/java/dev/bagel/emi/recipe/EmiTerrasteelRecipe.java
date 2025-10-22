package dev.bagel.emi.recipe;

import dev.bagel.emi.BlendTextureWidget;
import dev.bagel.emi.BotaniaEmiPlugin;
import dev.bagel.emi.ManaWidget;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.src.ResourceLocation;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

import java.util.List;
import java.util.stream.Stream;

public class EmiTerrasteelRecipe extends BotaniaEmiRecipe {
    private static final ResourceLocation TEXTURE = Botania.loc("textures/gui/terrasteel_jei_overlay.png");
    private static final EmiStack PLATE = EmiStack.of(ModBlocks.terraPlate);
    public static final int CENTER_X = 45;
    public static final int CENTER_Y = 30;
    private final int mana;

    public EmiTerrasteelRecipe() {
        super(BotaniaEmiPlugin.TERRESTRIAL_AGGLOMERATION, Botania.loc("terrasteel"), Stream.of(LibOreDict.MANA_STEEL, LibOreDict.MANA_DIAMOND, LibOreDict.MANA_PEARL).map(stack -> (EmiIngredient) EmiStack.of(stack)).toList(), List.of(EmiStack.of(LibOreDict.TERRA_STEEL)));
        this.mana = TileTerraPlate.MAX_MANA;
    }

    @Override
    public int getDisplayHeight() {
        return 107;
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(PLATE);
    }

    @Override
    public int getDisplayWidth() {
        return 106;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.add(new ManaWidget(2, 100, mana, TilePool.MAX_MANA));
        double step = 360.0 / inputs.size();
        widgets.add(new BlendTextureWidget(TEXTURE, CENTER_X - 23, CENTER_Y - 23, 64, 64, 42, 29));
        for (int i = 0; i < inputs.size(); i++) {
            EmiIngredient ing = inputs.get(i);
            widgets.addSlot(ing, BotaniaEmiPlugin.rotateXAround(CENTER_X, CENTER_Y - 30, CENTER_X, CENTER_Y, step * i),
                    BotaniaEmiPlugin.rotateYAround(CENTER_X, CENTER_Y - 30, CENTER_X, CENTER_Y, step * i)).drawBack(false);
        }
        widgets.addSlot(PLATE, CENTER_X, 80).drawBack(false).catalyst(true);
        widgets.addSlot(outputs.get(0), CENTER_X, CENTER_Y).drawBack(false).recipeContext(this);
    }
}
