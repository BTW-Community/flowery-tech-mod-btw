/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 17, 2015, 5:07:25 PM (GMT)]
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.item.tag.Tag;
import api.item.tag.TagInstance;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.subtile.SubTileEntity;

public class RecipePureDaisy {

	private static final Map<Tag, List<ItemStack>> oreMap = new HashMap<>();

	Object input;
	Block output;
	int outputMeta;

	public RecipePureDaisy(Object input, Block output, int outputMeta) {
		this.input = input;
		this.output = output;
		this.outputMeta = outputMeta;

		if(input != null && !(input instanceof TagInstance || input instanceof Tag || input instanceof Block))
			throw new IllegalArgumentException("input must be a Tag, TagInstance or a Block.");
	}

	/**
	 * This gets called every tick, please be careful with your checks.
	 */
	public boolean matches(World world, int x, int y, int z, SubTileEntity pureDaisy, Block block, int meta) {
		if(input instanceof Block)
			return block == input;

		ItemStack stack = new ItemStack(block, 1, meta);
		Tag tag;
		if (input instanceof TagInstance ti) {
			tag = ti.tag();
		}
		else {
			tag = (Tag) input;
		}
		return isTag(stack, tag);
	}

	public boolean isTag(ItemStack stack, Tag entry) {
		if(stack == null || stack.getItem() == null)
			return false;

		List<ItemStack> ores;
		if(oreMap.containsKey(entry))
			ores = oreMap.get(entry);
		else {
			//todo Once block tags are added, create support for that
//			ores = new ArrayList<>();
			ores = entry.getItems();

			oreMap.put(entry, ores);
		}

		for(ItemStack ostack : ores) {
			ItemStack cstack = ostack.copy();
			if(cstack.getItemDamage() == Short.MAX_VALUE)
				cstack.setItemDamage(stack.getItemDamage());

			if(stack.isItemEqual(cstack))
				return true;
		}

		return false;
	}

	/**
	 * Returns true if the block was placed (and if the Pure Daisy should do particles and stuffs).
	 * Should only place the block if !world.isRemote, but should return true if it would've placed
	 * it otherwise. You may return false to cancel the normal particles and do your own.
	 */
	public boolean set(World world, int x, int y, int z, SubTileEntity pureDaisy) {
		if(!world.isRemote)
			world.setBlock(x, y, z, output, outputMeta, 1 | 2);
		return true;
	}

	public Object getInput() {
		return input;
	}

	public Block getOutput() {
		return output;
	}

	public int getOutputMeta() {
		return outputMeta;
	}

}
