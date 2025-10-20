/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 17, 2015, 5:18:06 PM (GMT)]
 */
package vazkii.botania.common.crafting;

import btw.block.BTWBlocks;
import btw.item.tag.BTWTags;
import btw.item.tag.Tag;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibOreDict;

public final class ModPureDaisyRecipes {

	public static void init() {
		if (!ConfigHandler.enableDefaultRecipes) return;

		BotaniaAPI.registerPureDaisyRecipe(ModCraftingRecipes.stones, ModBlocks.livingrock, 0);
		BotaniaAPI.registerPureDaisyRecipe(BTWTags.logs, ModBlocks.livingwood, 0);

		BotaniaAPI.registerPureDaisyRecipe(Block.netherrack, Block.cobblestone, 0);
		BotaniaAPI.registerPureDaisyRecipe(Block.slowSand, Block.sand, 0);
//		BotaniaAPI.registerPureDaisyRecipe(Block.ice, Block.packedIce, 0); No packed ice in this version
		BotaniaAPI.registerPureDaisyRecipe(LibOreDict.BLAZE_BLOCK, Block.obsidian, 0);
		BotaniaAPI.registerPureDaisyRecipe(Block.waterMoving, Block.snow, 0);
		BotaniaAPI.registerPureDaisyRecipe(Block.waterStill, Block.snow, 0);
	}

}
