/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Dec 15, 2014, 6:02:04 PM (GMT)]
 */
package vazkii.botania.client.gui.crafting;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.I18n;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.World;

import org.lwjgl.opengl.GL11;

// This is pretty much a copypasta of GuiCrafting >_>
public class GuiCraftingHalo extends GuiContainer {

	private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

	public GuiCraftingHalo(InventoryPlayer p_i1084_1_, World p_i1084_2_) {
		super(new ContainerCraftingHalo(p_i1084_1_, p_i1084_2_));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		fontRenderer.drawString(I18n.getStringParams("container.crafting", new Object[0]), 28, 6, 4210752);
		fontRenderer.drawString(I18n.getStringParams("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(craftingTableGuiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

}
