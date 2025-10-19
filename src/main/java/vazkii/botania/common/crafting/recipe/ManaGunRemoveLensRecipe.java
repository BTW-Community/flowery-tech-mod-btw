/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Oct 6, 2014, 5:56:24 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.src.*;
import vazkii.botania.common.item.ItemManaGun;

public class ManaGunRemoveLensRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundGun = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ItemManaGun && ItemManaGun.getLens(stack) != null)
					foundGun = true;

				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundGun;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack gun = null;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ItemManaGun)
					gun = stack;
			}
		}

		ItemStack gunCopy = gun.copy();
		ItemManaGun.setLens(gunCopy, null);

		return gunCopy;
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
	public boolean matches(IRecipe iRecipe) {
		return false;
	}

	@Override
	public boolean hasSecondaryOutput() {
		return false;
	}

	@Override
	public ItemStack[] getSecondaryOutput(IInventory iInventory) {
		return null;
	}
}
