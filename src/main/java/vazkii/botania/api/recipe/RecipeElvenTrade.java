package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.List;

import api.item.tag.TagInstance;
import api.item.tag.TagOrStack;
import net.minecraft.src.ItemStack;

public class RecipeElvenTrade {

	ItemStack output;
	List<TagOrStack> inputs;

	public RecipeElvenTrade(ItemStack output, TagOrStack... inputs) {
		this.output = output;

		List<TagOrStack> inputsToSet = new ArrayList<>();
		for(TagOrStack obj : inputs) {
			if(obj instanceof TagInstance || obj instanceof ItemStack)
				inputsToSet.add(obj);
			else throw new IllegalArgumentException("Invalid input");
		}

		this.inputs = inputsToSet;
	}

	public boolean matches(List<ItemStack> stacks, boolean remove) {
		List<Object> inputsMissing = new ArrayList(inputs);
		List<ItemStack> stacksToRemove = new ArrayList<>();

		for(ItemStack stack : stacks) {
			if(stack == null) {
				continue;
			}
			if(inputsMissing.isEmpty())
				break;

			int stackIndex = -1, oredictIndex = -1;

			for(int j = 0; j < inputsMissing.size(); j++) {
				Object input = inputsMissing.get(j);
				//todo oredict -> tag
//				if(input instanceof String) {
//					List<ItemStack> validStacks = OreDictionary.getOres((String) input);
//					boolean found = false;
//					for(ItemStack ostack : validStacks) {
//						ItemStack cstack = ostack.copy();
//						if(cstack.getItemDamage() == Short.MAX_VALUE)
//							cstack.setItemDamage(stack.getItemDamage());
//
//						if(stack.isItemEqual(cstack)) {
//							if(!stacksToRemove.contains(stack))
//								stacksToRemove.add(stack);
//							oredictIndex = j;
//							found = true;
//							break;
//						}
//					}
//
//					if(found)
//						break;
//				}
//				else
					if(input instanceof ItemStack && simpleAreStacksEqual((ItemStack) input, stack)) {
					if(!stacksToRemove.contains(stack))
						stacksToRemove.add(stack);
					stackIndex = j;
					break;
				}
			}

			if(stackIndex != -1)
				inputsMissing.remove(stackIndex);
			else if(oredictIndex != -1)
				inputsMissing.remove(oredictIndex);
		}

		if(remove)
			for(ItemStack r : stacksToRemove)
				stacks.remove(r);

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
