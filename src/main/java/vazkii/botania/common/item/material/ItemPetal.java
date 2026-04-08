/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 19, 2014, 3:28:21 PM (GMT)]
 */
package vazkii.botania.common.item.material;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.IInventory;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.Item16Colors;
import vazkii.botania.common.lib.LibItemNames;

import java.awt.*;

public class ItemPetal extends Item16Colors implements IFlowerComponent {
	private Icon[] icons;
	public ItemPetal(int id) {
		super(id, LibItemNames.PETAL);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		ItemStack stackToPlace = new ItemStack(ModBlocks.buriedPetals, 1, par1ItemStack.getItemDamage());
		stackToPlace.tryPlaceItemIntoWorld(par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);

		if(stackToPlace.stackSize == 0) {
			if(!par2EntityPlayer.capabilities.isCreativeMode)
				par1ItemStack.stackSize--;

			return true;
		}
		return false;
	}

	@Override
	public boolean canFit(ItemStack stack, IInventory apothecary) {
		return true;
	}

	@Override
	public int getParticleColor(ItemStack stack) {
		return getaColorFromItemStack(stack, 0);
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return 0xFFFFFF;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[15];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(par1IconRegister, this, i);
	}

	public int getaColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par1ItemStack.getItemDamage() >= EntitySheep.fleeceColorTable.length)
			return 0xFFFFFF;

		float[] color = EntitySheep.fleeceColorTable[par1ItemStack.getItemDamage()];
		return new Color(color[0], color[1], color[2]).getRGB();
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		if (damage > icons.length - 1) return icons[0];
		return icons[damage];
	}
}
