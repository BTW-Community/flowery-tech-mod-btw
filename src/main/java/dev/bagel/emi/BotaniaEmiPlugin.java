package dev.bagel.emi;

import baubles.api.expanded.BaubleExpandedSlots;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.BaublesConfig;
import baubles.common.container.SlotBauble;
import dev.bagel.emi.recipe.*;
import dev.bagel.emi.recipe.custom.CustomBotaniaEmiRecipe;
import emi.dev.emi.emi.api.EmiApi;
import emi.dev.emi.emi.api.EmiExclusionArea;
import emi.dev.emi.emi.api.EmiPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiRecipeCategory;
import emi.dev.emi.emi.api.recipe.EmiRecipeSorting;
import emi.dev.emi.emi.api.stack.Comparison;
import emi.dev.emi.emi.api.stack.EmiIngredient;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.dev.emi.emi.screen.Bounds;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.*;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemManaTablet;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.item.brew.ItemBrewBase;
import vazkii.botania.common.item.brew.ItemIncenseStick;
import vazkii.botania.common.item.brew.ItemVial;
import vazkii.botania.common.item.equipment.bauble.ItemBloodPendant;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static baubles.common.BaublesConfig.useOldGuiRendering;

public class BotaniaEmiPlugin implements EmiPlugin {
    public static EmiRecipeCategory PETAL_APOTHECARY = new EmiRecipeCategory(Botania.loc("petal_apothecary"), EmiStack.of(new ItemStack(ModBlocks.altar)));
    public static EmiRecipeCategory MANA_POOL = new EmiRecipeCategory(Botania.loc("mana_pool"), EmiStack.of(new ItemStack(ModBlocks.pool)));

    public static EmiRecipeCategory PURE_DAISY = new EmiRecipeCategory(Botania.loc("pure_daisy"), EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
    public static EmiRecipeCategory RUNIC_ALTAR = new EmiRecipeCategory(Botania.loc("runic_altar"), EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
    public static EmiRecipeCategory ELVEN_TRADE = new EmiRecipeCategory(Botania.loc("elven_trade"), EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
    public static EmiRecipeCategory BREWING = new EmiRecipeCategory(Botania.loc("brewing"), EmiStack.of(new ItemStack(ModBlocks.brewery)), EmiStack.of(new ItemStack(ModBlocks.brewery)), EmiRecipeSorting.identifier());
    public static EmiRecipeCategory LEXICA_BOTANIA = new EmiRecipeCategory(Botania.loc("lexica_botania"), EmiStack.of(new ItemStack(ModItems.lexicon)));
    public static EmiRecipeCategory TERRESTRIAL_AGGLOMERATION = new EmiRecipeCategory(Botania.loc("terrestrial_agglomeration"), EmiStack.of(new ItemStack(ModBlocks.terraPlate)), EmiStack.of(new ItemStack(ModBlocks.terraPlate)), EmiRecipeSorting.identifier());

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
        for(int i = 15; i > 0; i--) { // reverse! reverse!
            reg.addEmiStackAfter(EmiStack.of(ItemTwigWand.forColors(i, i)), EmiStack.of(ItemTwigWand.forColors(i-1, i-1)));
        }
        // Full tablet
        ItemStack fullPower = new ItemStack(ModItems.manaTablet, 1, 0);
        ItemManaTablet.setMana(fullPower, ItemManaTablet.MAX_MANA);
        reg.addEmiStackAfter(EmiStack.of(fullPower), EmiStack.of(ModItems.manaTablet));

        // Creative Tablet
        ItemStack creative = new ItemStack(ModItems.manaTablet, 1, 0);
        ItemManaTablet.setMana(creative, ItemManaTablet.MAX_MANA);
        ItemManaTablet.setStackCreative(creative);
        reg.addEmiStackAfter(EmiStack.of(creative), EmiStack.of(fullPower));
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

        addComparisons(reg);

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
            if (((ItemVial) ModItems.vial).getItemForBrew(recipe.getBrew(), new ItemStack(ModItems.vial, 1, 0)) != null) {
                reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.vial, 1, 0), "vial"));
                reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.vial, 1, 1), "flask"));
            }
            if (((ItemIncenseStick) ModItems.incenseStick).getItemForBrew(recipe.getBrew(), new ItemStack(ModItems.incenseStick, 1)) != null)
                reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.incenseStick, 1), "incense_stick"));
            if (((ItemBloodPendant) ModItems.bloodPendant).getItemForBrew(recipe.getBrew(), new ItemStack(ModItems.bloodPendant, 1)) != null)
                reg.addRecipe(new EmiBrewingRecipe(recipe, new ItemStack(ModItems.bloodPendant, 1), "blood_pendant"));
        }
        reg.addRecipe(new EmiTerrasteelRecipe());

        for(LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            List<ItemStack> stacks = entry.getDisplayedRecipes();
            int i = 1;
            for(ItemStack stack : stacks) {
                reg.addRecipe(new EmiLexicaBotaniaRecipe(entry, stack, i));
                i++;
            }
        }

        for (int i = 0; i < 9; i++) {
            reg.addWorkstation(PETAL_APOTHECARY, EmiStack.of(new ItemStack(ModBlocks.altar, 1, i)));
        }
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool)));
        reg.addWorkstation(MANA_POOL, EmiStack.of(new ItemStack(ModBlocks.pool, 1, 2)));
        reg.addWorkstation(PURE_DAISY, EmiStack.of(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY)));
        reg.addWorkstation(RUNIC_ALTAR, EmiStack.of(new ItemStack(ModBlocks.runeAltar)));
        reg.addWorkstation(ELVEN_TRADE, EmiStack.of(new ItemStack(ModBlocks.alfPortal)));
        reg.addWorkstation(BREWING, EmiStack.of(new ItemStack(ModBlocks.brewery)));

        CustomBotaniaEmiRecipe.initCustomRecipes(reg);
    }

    private void addComparisons(EmiRegistry reg) {
        var comparison = Comparison.of((es1, es2) -> {
            String s1 = ItemNBTHelper.getString(es1.getItemStack(), ItemBrewBase.TAG_BREW_KEY, "");
            String s2 = ItemNBTHelper.getString(es2.getItemStack(), ItemBrewBase.TAG_BREW_KEY, "");
            if ((s1.isEmpty() || s2.isEmpty()) && !s1.equals(s2)) {
                return false;
            }
            return s1.equals(s2);
        });
        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModItems.brewFlask, 1, 0)), comparison);
        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModItems.brewVial, 1, 0)), comparison);
        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModItems.incenseStick, 1)), comparison);
        reg.setDefaultComparison(EmiStack.of(new ItemStack(ModItems.bloodPendant, 1)), comparison);
        EmiStack[] initals = new EmiStack[]{
                EmiStack.of(new ItemStack(ModItems.brewVial, 1, 0)),
                EmiStack.of(new ItemStack(ModItems.brewFlask, 1, 0)),
                EmiStack.of(new ItemStack(ModItems.incenseStick, 1, 0)),
                EmiStack.of(new ItemStack(ModItems.bloodPendant, 1, 0)),
        };
        //iterate brews and add all valid ones to emi
        for (var brew : BotaniaAPI.brewMap.values()) {
            if (((ItemVial) ModItems.vial).getItemForBrew(brew, new ItemStack(ModItems.vial, 1, 0)) != null) {
                var newStack1 = EmiStack.of(((ItemVial) ModItems.vial).getItemForBrew(brew, new ItemStack(ModItems.vial, 1, 0)));
                reg.addEmiStackAfter(newStack1.copy(), initals[0].copy());
                initals[0] = newStack1;
                var newStack2 = EmiStack.of(((ItemVial) ModItems.vial).getItemForBrew(brew, new ItemStack(ModItems.vial, 1, 1)));
                reg.addEmiStackAfter(newStack2.copy(), initals[1].copy());
                initals[1] = newStack2;
            }
            if (((ItemIncenseStick) ModItems.incenseStick).getItemForBrew(brew, new ItemStack(ModItems.incenseStick, 1)) != null) {
                var newStack = EmiStack.of(((ItemIncenseStick) ModItems.incenseStick).getItemForBrew(brew, new ItemStack(ModItems.incenseStick, 1, 0)));
                reg.addEmiStackAfter(newStack.copy(), initals[2].copy());
                initals[2] = newStack;
            }
            if (((ItemBloodPendant) ModItems.bloodPendant).getItemForBrew(brew, new ItemStack(ModItems.bloodPendant, 1)) != null) {
                var newStack = EmiStack.of(((ItemBloodPendant) ModItems.bloodPendant).getItemForBrew(brew, new ItemStack(ModItems.bloodPendant, 1, 0)));
                reg.addEmiStackAfter(newStack.copy(), initals[3].copy());
                initals[3] = newStack;
            }
        }
    }


    private static final Supplier<ItemStack> HOVERED_STACK_GETTER = () -> {
        EmiIngredient ingr = EmiApi.getHoveredStack(true).getStack();
        if (!ingr.getEmiStacks().isEmpty()) {
            return ingr.getEmiStacks().get(0).getItemStack();
        }
        return null;
    };
    public static KeyBinding KEY = new KeyBinding("key.botania.search", Keyboard.KEY_N);

    public static boolean handleKey() {
        Minecraft mc = Minecraft.getMinecraft();
        if(TileCorporeaIndex.InputHandler.getNearbyIndexes(mc.thePlayer).isEmpty())
            return false;

        boolean pressed = true;
        while(Keyboard.isKeyDown(KEY.keyCode) && pressed) {
            ItemStack stack = HOVERED_STACK_GETTER.get();
            if(stack != null && stack.getItem() != null) {
                int count = 1;
                int max = stack.getMaxStackSize();
                if(GuiScreen.isShiftKeyDown()) {
                    count = max;
                    if(GuiScreen.isCtrlKeyDown())
                        count /= 4;
                } else if(GuiScreen.isCtrlKeyDown())
                    count = max / 2;

                if(count > 0) {
                    String name = CorporeaHelper.stripControlCodes(stack.getDisplayName());
                    String full = count + " " + name;

                    mc.ingameGUI.getChatGUI().addToSentMessages(full);
                    mc.thePlayer.sendChatMessage(full);
                    return true;
                }
            }
            pressed = false;
        }
        return false;
    }

    private Comparison comparingNbtSpecific(String... nbtKeys) {
        return Comparison.of(((s1, s2) -> {
            if (!Comparison.DEFAULT_COMPARISON.compare(s1, s2)) {
                return false;
            }
            NBTTagCompound an = s1.getNbt();
            NBTTagCompound bn = s2.getNbt();
            if (an == null || bn == null) {
                return an == bn;
            }
            boolean matches;
            for (String key : nbtKeys) {
                var tag1 = an.getTag(key);
                var tag2 = bn.getTag(key);
                if (tag1 == null || tag2 == null) {
                    matches = tag1 == tag2;
                    if (!matches) return false;
                    continue;
                }
                if (!tag1.equals(tag2)) {
                    return false;
                }
            }
            return true;
        }));
    }
}
