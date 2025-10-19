/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 30, 2015, 6:22:32 PM (GMT)]
 */
package vazkii.botania.client.render.block;

import net.minecraft.src.Block;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.TileEntityRenderer;
import net.minecraft.src.IBlockAccess;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCorporeaCrystalCube implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		TileEntityRenderer.instance.renderTileEntityAt(new TileCorporeaCrystalCube(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return LibRenderIDs.idCorporeaCrystalCybe;
	}

}
