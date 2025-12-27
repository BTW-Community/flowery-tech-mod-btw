/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 25, 2015, 6:14:13 PM (GMT)]
 */
package vazkii.botania.common.item;

import java.awt.Color;

import net.minecraft.src.CraftingManager;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.Botania;
import vazkii.botania.common.crafting.recipe.SpellClothRecipe;
import vazkii.botania.common.lib.LibItemNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class ItemSpellCloth extends ItemMod {

	public ItemSpellCloth(int id) {
        super(id);
        setMaxDamage(35);
		setMaxStackSize(1);
		setNoRepair();
		setUnlocalizedName(LibItemNames.SPELL_CLOTH);

		CraftingManager.getInstance().getRecipeList().add(new SpellClothRecipe(Botania.loc("spellbinding_cloth")));
//		RecipeSorter.register("botania:spellCloth", SpellClothRecipe.class, Category.SHAPELESS, "");
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return Color.HSBtoRGB(0.55F, ((float) par1ItemStack.getMaxDamage() - (float) par1ItemStack.getItemDamage()) / par1ItemStack.getMaxDamage() * 0.5F, 1F);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack stack = itemStack.copy();
		stack.setItemDamage(stack.getItemDamage() + 1);
		return stack;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

}
