/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jun 11, 2014, 2:16:47 AM (GMT)]
 */
package vazkii.botania.common.item.material;

import java.util.List;

import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.lib.LibItemNames;

public class ItemQuartz extends ItemMod implements IElvenItem {

	private static final int SUBTYPES = 7;
	Icon[] icons;

	public ItemQuartz(int id) {
		super(id);
		setUnlocalizedName(LibItemNames.QUARTZ);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(int item, CreativeTabs tab, List list) {
		for(int i = ConfigHandler.darkQuartzEnabled ? 0 : 1; i < SUBTYPES; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[SUBTYPES];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.getItemDamage();
	}

	String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack);
	}

	@Override
	public boolean isElvenItem(ItemStack stack) {
		return stack.getItemDamage() == 5;
	}
}
