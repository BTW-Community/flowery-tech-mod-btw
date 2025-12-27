/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 23, 2014, 3:02:17 PM (GMT)]
 */
package vazkii.botania.common.crafting.recipe;

import api.item.tag.TagOrStack;
import net.minecraft.src.Item;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class HeadRecipe extends RecipeRuneAltar {

	String name = "";

	public HeadRecipe(ItemStack output, int mana, TagOrStack... inputs) {
		super(output, mana, inputs);
	}

	@Override
	public boolean matches(IInventory inv) {
		boolean matches = super.matches(inv);

		if(matches) {
			for(int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if(stack == null)
					break;

				if(stack.getItem() == Item.nameTag) {
					name = stack.getDisplayName();
					if(name.equals(StatCollector.translateToLocal("item.nameTag.name")))
						return false;
				}
			}
		}

		return matches;
	}

	@Override
	public ItemStack getOutput() {
		ItemStack stack = new ItemStack(Item.skull, 1, 3);
		if(!name.isEmpty())
			ItemNBTHelper.setString(stack, "SkullOwner", name);
		return stack;
	}

}
