/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 22, 2014, 2:02:44 PM (GMT)]
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.List;

import btw.item.tag.Tag;
import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public class RecipePetals {

	ItemStack output;
	List<TagOrStack> inputs;

	public RecipePetals(ItemStack output, TagOrStack... inputs) {
		this.output = output;

		List<TagOrStack> inputsToSet = new ArrayList<>();
		for(TagOrStack obj : inputs) {
			if(obj instanceof TagInstance || obj instanceof ItemStack)
				inputsToSet.add(obj);
			else throw new IllegalArgumentException("Invalid input");
		}

		this.inputs = inputsToSet;
	}

	public boolean matches(IInventory inv) {
		List<Object> inputsMissing = new ArrayList(inputs);

		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack == null)
				break;

			int stackIndex = -1, oredictIndex = -1;

			for(int j = 0; j < inputsMissing.size(); j++) {
				Object input = inputsMissing.get(j);
				//todo oredict -> tag
				if(input instanceof TagInstance ti) {
					List<ItemStack> validStacks = ti.tag().getItems();
					boolean found = false;
					for(ItemStack ostack : validStacks) {
						ItemStack cstack = ostack.copy();
						if(cstack.getItemDamage() == Short.MAX_VALUE)
							cstack.setItemDamage(stack.getItemDamage());

						if(stack.isItemEqual(cstack)) {
							oredictIndex = j;
							found = true;
							break;
						}
					}


					if(found)
						break;
				}
				else
					if(input instanceof ItemStack && simpleAreStacksEqual((ItemStack) input, stack)) {
					stackIndex = j;
					break;
				}
			}

			if(stackIndex != -1)
				inputsMissing.remove(stackIndex);
			else if(oredictIndex != -1)
				inputsMissing.remove(oredictIndex);
			else return false;
		}

		return inputsMissing.isEmpty();
	}

	boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}

	public List<TagOrStack> getInputs() {
		return new ArrayList<>(inputs);
	}

	public ItemStack getOutput() {
		return output;
	}

}
