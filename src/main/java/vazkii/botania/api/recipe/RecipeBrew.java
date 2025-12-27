/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 1, 2014, 8:52:00 PM (GMT)]
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.List;

import api.item.tag.TagInstance;
import api.item.tag.TagOrStack;
import net.minecraft.src.Item;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewContainer;

public class RecipeBrew {

	Brew brew;
	List<TagOrStack> inputs;

	public RecipeBrew(Brew brew, TagOrStack... inputs) {
		this.brew = brew;

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

			if(stack.getItem() instanceof IBrewContainer)
				continue;

			int stackIndex = -1, oredictIndex = -1;

			for(int j = 0; j < inputsMissing.size(); j++) {
				Object input = inputsMissing.get(j);
				if(input instanceof TagInstance tagInstance) {
					List<ItemStack> validStacks = tagInstance.tag().getItems();
					boolean found = false;
					for(ItemStack ostack : validStacks) {
						ItemStack cstack = ostack.copy();
						if(cstack.getItemDamage() == Short.MAX_VALUE)
							cstack.setItemDamage(stack.getItemDamage());

						if(stack.isItemEqual(cstack, true)) {
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
		return new ArrayList(inputs);
	}

	public Brew getBrew() {
		return brew;
	}

	public int getManaUsage() {
		return brew.getManaCost();
	}

	public ItemStack getOutput(ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof IBrewContainer container))
			return new ItemStack(Item.glassBottle); // Fallback...

        return container.getItemForBrew(brew, stack);
	}

}
