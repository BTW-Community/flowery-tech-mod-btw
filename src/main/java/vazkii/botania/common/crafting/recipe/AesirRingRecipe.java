/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Mar 31, 2015, 10:39:58 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import api.crafting.IdentifiableRecipe;
import net.minecraft.src.*;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.ModItems;

public class AesirRingRecipe extends BotaniaIRecipe {

	public AesirRingRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundThorRing = false;
		boolean foundOdinRing = false;
		boolean foundLokiRing = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() == ModItems.thorRing && !foundThorRing)
					foundThorRing = true;
				else if(stack.getItem() == ModItems.odinRing && !foundOdinRing)
					foundOdinRing = true;
				else if(stack.getItem() == ModItems.lokiRing && !foundLokiRing)
					foundLokiRing = true;
				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundThorRing && foundOdinRing && foundLokiRing;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		String soulbind = null;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof IRelic) {
					String bind = ((IRelic) stack.getItem()).getSoulbindUsername(stack);
					if(soulbind == null)
						soulbind = bind;
					else if(!soulbind.equals(bind))
						return null;
				} else return null;
			}
		}

		ItemStack stack = new ItemStack(ModItems.aesirRing);
		((IRelic) ModItems.aesirRing).bindToUsername(soulbind, stack);
		return stack;
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
