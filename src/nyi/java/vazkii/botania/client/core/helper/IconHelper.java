/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 5:28:21 PM (GMT)]
 */
package vazkii.botania.client.core.helper;

import net.minecraft.src.Block;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Item;
import net.minecraft.src.Icon;
import vazkii.botania.client.lib.LibResources;

public final class IconHelper {

	public static Icon forName(IconRegister ir, String name) {
		return ir.registerIcon(LibResources.PREFIX_MOD + name);
	}

	public static Icon forName(IconRegister ir, String name, String dir) {
		return ir.registerIcon(LibResources.PREFIX_MOD + dir + "/" + name);
	}

	public static Icon forBlock(IconRegister ir, Block block) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", ""));
	}

	public static Icon forBlock(IconRegister ir, Block block, int i) {
		return forBlock(ir, block, Integer.toString(i));
	}

	public static Icon forBlock(IconRegister ir, Block block, int i, String dir) {
		return forBlock(ir, block, Integer.toString(i), dir);
	}

	public static Icon forBlock(IconRegister ir, Block block, String s) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s);
	}

	public static Icon forBlock(IconRegister ir, Block block, String s, String dir) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s, dir);
	}

	public static Icon forItem(IconRegister ir, Item item) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", ""));
	}

	public static Icon forItem(IconRegister ir, Item item, int i) {
		return forItem(ir, item, Integer.toString(i));
	}

	public static Icon forItem(IconRegister ir, Item item, String s) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", "") + s);
	}

}