/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 30, 2015, 10:59:45 PM (GMT)]
 */
package vazkii.botania.common.item;

import java.util.List;

import net.minecraft.src.*;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.crafting.recipe.AncientWillRecipe;
import vazkii.botania.common.lib.LibItemNames;

public class ItemAncientWill extends ItemMod {

	private static final int SUBTYPES = 6;

	Icon[] icons;

	public ItemAncientWill(int id) {
        super(id);
        setUnlocalizedName(LibItemNames.ANCIENT_WILL);
		setHasSubtypes(true);
		setMaxStackSize(1);

		CraftingManager.getInstance().getRecipeList().add(new AncientWillRecipe());
//		RecipeSorter.register("botania:ancientWill", AncientWillRecipe.class, Category.SHAPELESS, "");
	}

	@Override
	public void getSubItems(int item, CreativeTabs tab, List list) {
		for(int i = 0; i < SUBTYPES; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[SUBTYPES];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public Icon getIconFromDamage(int dmg) {
		return icons[Math.min(icons.length - 1, dmg)];
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		addStringToTooltip(StatCollector.translateToLocal("botaniamisc.craftToAddWill"), list);
		addStringToTooltip(StatCollector.translateToLocal("botania.armorset.will" + stack.getItemDamage() + ".shortDesc"), list);
	}

	public void addStringToTooltip(String s, List<String> tooltip) {
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
	}


}
