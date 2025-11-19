/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 14, 2014, 5:57:26 PM (GMT)]
 */
package vazkii.botania.common.lexicon.page;

import java.util.ArrayList;
import java.util.List;

import btw.item.BTWItems;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.ModBlocks;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import vazkii.botania.common.lib.LibOreDict;

public class PageTerrasteel extends PageRecipe {

	private static final ResourceLocation terrasteelOverlay = new ResourceLocation(LibResources.GUI_TERRASTEEL_OVERLAY);

	public PageTerrasteel(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
		Block block1 = ModBlocks.livingrock;
		Block block2 = Block.blockLapis;
		Block block3 = ModBlocks.terraPlate;

		GL11.glTranslatef(0F, 0F, -10F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8, gui.getTop() + 103, new ItemStack(block1), false);

		GL11.glTranslatef(0F, 0F, 5F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 + 7, gui.getTop() + 106, new ItemStack(block2), false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 - 6, gui.getTop() + 106, new ItemStack(block2), false);

		GL11.glTranslatef(0F, 0F, 5F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8, gui.getTop() + 110, new ItemStack(block1), false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 + 14, gui.getTop() + 110, new ItemStack(block1), false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 - 13, gui.getTop() + 110, new ItemStack(block1), false);

		GL11.glTranslatef(0F, 0F, 5F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 - 6, gui.getTop() + 114, new ItemStack(block2), false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 + 7, gui.getTop() + 114, new ItemStack(block2), false);

		GL11.glTranslatef(0F, 0F, 5F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 + 1, gui.getTop() + 117, new ItemStack(block1), false);

		GL11.glTranslatef(0F, 0F, 5F);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8, gui.getTop() + 102, new ItemStack(block3), false);
		GL11.glTranslatef(0F, 0F, -10F);

		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8, gui.getTop() + 30, LibOreDict.TERRA_STEEL, false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8, gui.getTop() + 80, new ItemStack(BTWItems.soulforgedSteelIngot, 1, 0), false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 - 20, gui.getTop() + 86, LibOreDict.MANA_PEARL, false);
		renderItem(gui, gui.getLeft() + (double) gui.getWidth() / 2 - 8 + 19, gui.getTop() + 86, LibOreDict.MANA_PEARL, false);

		renderLexiconTexture(gui, terrasteelOverlay);
	}

	@Override
	public void onPageAdded(LexiconEntry entry, int index) {
		LexiconRecipeMappings.map(LibOreDict.TERRA_STEEL, entry, index);
	}

	@Override
	public List<ItemStack> getDisplayedRecipes() {
		ArrayList<ItemStack> list = new ArrayList<>();
		list.add(LibOreDict.TERRA_STEEL);
		return list;
	}

}
