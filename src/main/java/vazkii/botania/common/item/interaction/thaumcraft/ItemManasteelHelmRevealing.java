/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 28, 2014, 6:18:05 PM (GMT)]
 */
package vazkii.botania.common.item.interaction.thaumcraft;

import net.minecraft.src.CraftingManager;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.ItemStack;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.crafting.recipe.HelmRevealingRecipe;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelHelm;
import vazkii.botania.common.lib.LibItemNames;

public class ItemManasteelHelmRevealing extends ItemManasteelHelm {

	public ItemManasteelHelmRevealing(int id) {
		super(id, LibItemNames.MANASTEEL_HELM_R);
		CraftingManager.getInstance().getRecipeList().add(new HelmRevealingRecipe()); //Manasteel is the base so it gets the recipe added in its constructor so that ModItems can call it
//		RecipeSorter.register("botania:helmRevealing", HelmRevealingRecipe.class, Category.SHAPELESS, "");
	}

//	@Override
//	public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
//		return true;
//	}
//
//	@Override
//	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
//		return true;
//	}

	@Override
	public String getArmorTextureAfterInk(ItemStack stack, int slot) {
		return ConfigHandler.enableArmorModels ? LibResources.MODEL_MANASTEEL_NEW : LibResources.MODEL_MANASTEEL_2;
	}

}