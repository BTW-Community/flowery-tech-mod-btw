package dev.bagel.emi;

import baubles.api.expanded.BaubleExpandedSlots;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.BaublesConfig;
import baubles.common.container.SlotBauble;
import dev.bagel.emi.recipe.*;
import emi.dev.emi.emi.api.EmiExclusionArea;
import emi.dev.emi.emi.api.EmiPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.stack.Comparison;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.screen.Bounds;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.*;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.ArrayList;
import java.util.List;

import static baubles.common.BaublesConfig.useOldGuiRendering;

public class BotaniaEmiPlugin implements EmiPlugin {
    public static EmiRecipeCategory PETAL_APOTHECARY = new EmiRecipeCategory(Botania.loc("petal_apothecary"), EmiStack.of(new ItemStack(ModBlocks.altar)));
    public static EmiRecipeCategory MANA_POOL = new EmiRecipeCategory(Botania.loc("mana_pool"), EmiStack.of(new ItemStack(ModBlocks.pool)));

    public static EmiRecipeCategory PURE_DAISY = new EmiRecipeCategory(Botania.loc("pure_daisy"), EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
    public static EmiRecipeCategory RUNIC_ALTAR = new EmiRecipeCategory(Botania.loc("runic_altar"), EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
    public static EmiRecipeCategory ELVEN_TRADE = new EmiRecipeCategory(Botania.loc("elven_trade"), EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
    public static EmiRecipeCategory BREWING = new EmiRecipeCategory(Botania.loc("brewing"), EmiStack.of(new ItemStack(ModBlocks.brewery)));
    public static EmiRecipeCategory LEXICA_BOTANIA = new EmiRecipeCategory(Botania.loc("lexica_botania"), EmiStack.of(new ItemStack(ModItems.lexicon)));
    public static EmiRecipeCategory TERRESTRIAL_AGGLOMERATION = new EmiRecipeCategory(Botania.loc("terrestrial_agglomeration"), EmiStack.of(new ItemStack(ModBlocks.terraPlate)));

    public static int rotateXAround(int x, int y, int cx, int cy, double degrees) {
        double rad = Math.toRadians(degrees);
        return (int) (Math.cos(rad) * (x - cx) - Math.sin(rad) * (y - cy) + cx);
    }

    public static int rotateYAround(int x, int y, int cx, int cy, double degrees) {
        double rad = Math.toRadians(degrees);
        return (int) (Math.sin(rad) * (x - cx) - Math.cos(rad) * (y - cy) + cy);
    }

    @Override
    public void register(EmiRegistry reg) {
        reg.addExclusionArea(GuiPlayerExpanded.class, (screen, boundsConsumer) ->{
            final int slotOffset = 18;
            final int slotStartX = 80;
            final int slotStartY = 8;

            for (int i = 0; i < BaubleExpandedSlots.slotLimit; i++) {
                String slotType = BaubleExpandedSlots.getSlotType(i);
                if (BaublesConfig.showUnusedSlots || !slotType.equals(BaubleExpandedSlots.unknownType)) {
                    if (useOldGuiRendering) {
                        boundsConsumer.accept(new Bounds(slotStartX + (slotOffset * (i / 4)), slotStartY + (slotOffset * (i % 4)), 18, 18));
                    } else {
                        boundsConsumer.accept(new Bounds(-18, 12 + (slotOffset * i), 18, 18));
                    }
                }
            }
        });
        reg.addCategory(PETAL_APOTHECARY);
        reg.addCategory(MANA_POOL);
        reg.addCategory(PURE_DAISY);
        reg.addCategory(RUNIC_ALTAR);
        reg.addCategory(ELVEN_TRADE);
        reg.addCategory(BREWING);
        reg.addCategory(LEXICA_BOTANIA);
        reg.addCategory(TERRESTRIAL_AGGLOMERATION);

        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModBlocks.specialFlower)), Comparison.of((es1, es2) -> {
            String s1 = ItemBlockSpecialFlower.getType(es1.getItemStack());
            String s2 = ItemBlockSpecialFlower.getType(es2.getItemStack());
            if (s1.isEmpty() || s2.isEmpty()) {
                return false;
            }
            return s1.equals(s2);
        }));
        for (var s : BotaniaAPI.subtilesForCreativeMenu) {
            reg.addEmiStack(EmiStack.of(ItemBlockSpecialFlower.ofType(s)));
                if(BotaniaAPI.miniFlowers.containsKey(s))
                    reg.addEmiStack(EmiStack.of(ItemBlockSpecialFlower.ofType(BotaniaAPI.miniFlowers.get(s))));
        }
        for (var recipe : BotaniaAPI.petalRecipes) {
            reg.addRecipe(new EmiPetalRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.manaInfusionRecipes) {
            reg.addRecipe(new EmiPoolRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.pureDaisyRecipes) {
            reg.addRecipe(new EmiPureDaisyRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.runeAltarRecipes) {
            reg.addRecipe(new EmiRunicAltarRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.elvenTradeRecipes) {
            reg.addRecipe(new EmiElvenTradeRecipe(recipe));
        }
        for (var recipe : BotaniaAPI.brewRecipes) {
            reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.vial, 1, 0)));
            reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.vial, 1, 1)));
            reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.incenseStick, 1, 0)));
        }
        reg.addRecipe(new EmiTerrasteelRecipe());

        for(LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            List<ItemStack> stacks = entry.getDisplayedRecipes();
            for(ItemStack stack : stacks)
                reg.addRecipe(new EmiLexicaBotaniaRecipe(entry, stack));
        }

        //todo potentially lexica botania recipes?
        // also there is a keybind for requesting an item from corporea it would seem
        for (int i = 0; i < 9; i++) {
            reg.addWorkstation(PETAL_APOTHECARY, EmiStack.of(new ItemStack(ModBlocks.altar, 1, i)));
        }
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool)));
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool, 1, 2)));
        reg.addWorkstation(PURE_DAISY, EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
        reg.addWorkstation(RUNIC_ALTAR, EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
        reg.addWorkstation(ELVEN_TRADE, EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
        reg.addWorkstation(BREWING, EmiStack.of(new ItemStack(ModBlocks.brewery)));
    }
}
