/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 23, 2015, 4:10:24 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import java.util.Arrays;
import java.util.List;

import btw.item.BTWTags;
import net.minecraft.src.*;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.lib.LibOreDict;

public class LensDyeingRecipe extends BotaniaIRecipe {

	private static final int[] DYES = new int[] {
			15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0/*, LibOreDict.MANA_PEARL seems to be intentional*/
	};

	public LensDyeingRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundLens = false;
		boolean foundDye = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ILens && !foundLens)
					foundLens = true;
				else if(!foundDye) {
					int color = getStackColor(stack);
					if(color > -1)
						foundDye = true;
					else return false;
				}
				else return false;//This means we have an additional item in the recipe after the lens and dye
			}
		}

		return foundLens && foundDye;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack lens = null;
		int color = -1;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ILens && lens == null)
					lens = stack;
				else color = getStackColor(stack);//We can assume if its not a lens its a dye because we checked it in matches()
			}
		}

		if(lens != null && lens.getItem() instanceof ILens) {
			ItemStack lensCopy = lens.copy();
			ItemLens.setLensColor(lensCopy, color);

			return lensCopy;
		}

		return null;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	int getStackColor(ItemStack stack) {
		for (int i = 0; i < BTWTags.coloredDyes.length; i++) {
			var dyeTag = BTWTags.coloredDyes[i];
			if (dyeTag.test(stack, true)) {
				return DYES[i];
			}
		}

		return -1;
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
