/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 21, 2014, 8:44:35 PM (GMT)]
 */
package vazkii.botania.common.item;

import btw.item.items.FoodItem;
import net.minecraft.src.*;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.lib.LibItemNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class ItemManaCookie extends FoodItem {

	private Icon totalBiscuitIcon;

	public ItemManaCookie(int id) {
		super(id, 0, 0.1F, false, LibItemNames.MANA_COOKIE, true);
		setPotionEffect(Potion.field_76443_y.id, 1,  0, 1F);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		ModAchievements.trigger(player, ModAchievements.manaCookieEat);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
//		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item.", "item." + LibResources.PREFIX_MOD);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		return getIconIndex(stack);
	}

	@Override
	public Icon getIconIndex(ItemStack stack) {
		return stack.getDisplayName().toLowerCase().equals("totalbiscuit") ? totalBiscuitIcon : super.getIconIndex(stack);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = IconHelper.forItem(par1IconRegister, this);
		totalBiscuitIcon = IconHelper.forName(par1IconRegister, "totalBiscuit");
	}

	@Override
	public String getModId() {
		return "botania";
	}
}
