/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Feb 22, 2015, 8:49:53 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.src.*;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.ICosmeticBauble;

public class CosmeticAttachRecipe extends BotaniaIRecipe {

	public CosmeticAttachRecipe(ResourceLocation id) {
		super(id);
	}

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundCosmetic = false;
		boolean foundAttachable = false;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ICosmeticBauble && !foundCosmetic)
					foundCosmetic = true;
				else if(!foundAttachable) {
					if(stack.getItem() instanceof ICosmeticAttachable && !(stack.getItem() instanceof ICosmeticBauble) && ((ICosmeticAttachable) stack.getItem()).getCosmeticItem(stack) == null)
						foundAttachable = true;
					else return false;
				}
			}
		}

		return foundCosmetic && foundAttachable;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack cosmeticItem = null;
		ItemStack attachableItem = null;

		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.getItem() instanceof ICosmeticBauble && cosmeticItem == null)
					cosmeticItem = stack;
				else attachableItem = stack;
			}
		}

		ICosmeticAttachable attachable = (ICosmeticAttachable) attachableItem.getItem();
		if(attachable.getCosmeticItem(attachableItem) != null)
			return null;

		ItemStack copy = attachableItem.copy();
		attachable.setCosmeticItem(copy, cosmeticItem);
		return copy;
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
