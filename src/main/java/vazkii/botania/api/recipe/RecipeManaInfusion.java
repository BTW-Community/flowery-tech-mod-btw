/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 30, 2014, 5:57:07 PM (GMT)]
 */
package vazkii.botania.api.recipe;

import java.util.List;

import api.item.tag.TagInstance;
import api.item.tag.TagOrStack;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class RecipeManaInfusion {

	ItemStack output;
	TagOrStack input;
	int mana;
	boolean isAlchemy = false;
	boolean isConjuration = false;

	public RecipeManaInfusion(ItemStack output, TagOrStack input, int mana) {
		if (input instanceof Item i) {
			input = new ItemStack(i);
		}
		this.output = output;
		this.input = input;
		this.mana = mana;
	}

	public boolean matches(ItemStack stack) {
		if(input instanceof ItemStack is) {
			ItemStack inputCopy = is.copy();
			if(inputCopy.getItemDamage() == Short.MAX_VALUE)
				inputCopy.setItemDamage(stack.getItemDamage());

			return stack.isItemEqual(inputCopy);
		}

		//todo replace with tags
		if(input instanceof TagInstance ti) {
			List<ItemStack> validStacks = ti.tag().getItems();

			for(ItemStack ostack : validStacks) {
				ItemStack cstack = ostack.copy();
				if(cstack.getItemDamage() == Short.MAX_VALUE)
					cstack.setItemDamage(stack.getItemDamage());

				if(stack.isItemEqual(cstack))
					return true;
			}
		}

		return false;
	}

	public void setAlchemy(boolean alchemy) {
		isAlchemy = alchemy;
	}

	public boolean isAlchemy() {
		return isAlchemy;
	}

	public void setConjuration(boolean conjuration) {
		isConjuration = conjuration;
	}

	public boolean isConjuration() {
		return isConjuration;
	}

	public TagOrStack getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public int getManaToConsume() {
		return mana;
	}
}

