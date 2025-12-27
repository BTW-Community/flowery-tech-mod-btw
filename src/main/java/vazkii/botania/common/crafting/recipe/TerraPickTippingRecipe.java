/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Aug 22, 2014, 7:45:56 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.src.*;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public class TerraPickTippingRecipe extends BotaniaIRecipe {

	public TerraPickTippingRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundTerraPick = false;
		boolean foundElementiumPick = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ItemTerraPick && !ItemTerraPick.isTipped(stack))
					foundTerraPick = true;

				else if(stack.getItem() == ModItems.elementiumPick)
					foundElementiumPick = true;

				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundTerraPick && foundElementiumPick;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack terraPick = null;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null && stack.getItem() instanceof ItemTerraPick)
				terraPick = stack;
		}

		if(terraPick == null)
			return null;

		ItemStack terraPickCopy = terraPick.copy();
		ItemTerraPick.setTipped(terraPickCopy);
		return terraPickCopy;
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
