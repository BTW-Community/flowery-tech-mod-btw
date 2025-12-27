/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Jan 25, 2015, 6:16:13 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.src.*;
import vazkii.botania.common.item.ModItems;

public class SpellClothRecipe extends BotaniaIRecipe {

	public SpellClothRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundCloth = false;
		boolean foundEnchanted = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.isItemEnchanted() && !foundEnchanted)
					foundEnchanted = true;

				else if(stack.getItem() == ModItems.spellCloth && !foundCloth)
					foundCloth = true;

				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundCloth && foundEnchanted;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack stackToDisenchant = null;
		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null && stack.isItemEnchanted()) {
				stackToDisenchant = stack.copy();
				break;
			}
		}

		if(stackToDisenchant == null)
			return null;

		NBTTagCompound cmp = (NBTTagCompound) stackToDisenchant.getTagCompound().copy();
		cmp.removeTag("ench"); // Remove enchantments
		stackToDisenchant.setTagCompound(cmp);

		return stackToDisenchant;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	@Override
	public boolean matches(IRecipe var1) {
		return false;
	}

	@Override
	public boolean hasSecondaryOutput() {
		return false;
	}

	@Override
	public ItemStack[] getSecondaryOutput(IInventory var1) {
		return null;
	}

}
