/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 17, 2015, 4:46:36 PM (GMT)]
 */
package vazkii.botania.common.item;

import net.minecraft.src.CraftingManager;
import vazkii.botania.common.Botania;
import vazkii.botania.common.crafting.recipe.PhantomInkRecipe;
import vazkii.botania.common.lib.LibItemNames;


public class ItemPhantomInk extends ItemMod {

	public ItemPhantomInk(int id) {
        super(id);
        setUnlocalizedName(LibItemNames.PHANTOM_INK);
		CraftingManager.getInstance().getRecipeList().add(new PhantomInkRecipe(Botania.loc("phantom_ink")));
//		RecipeSorter.register("botania:phantomInk", PhantomInkRecipe.class, Category.SHAPELESS, "");
	}

}
