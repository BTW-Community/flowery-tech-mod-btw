/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Aug 27, 2015, 10:02:12 PM (GMT)]
 */
package vazkii.botania.client.render.entity;

import net.minecraft.src.Tessellator;
import net.minecraft.src.Render;
import net.minecraft.src.TextureMap;
import net.minecraft.src.Entity;
import net.minecraft.src.Icon;
import net.minecraft.src.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.item.ModItems;

// Basically a bit of an extension of RenderSnowball
public class RenderThornChakram extends Render {

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		EntityThornChakram c = (EntityThornChakram) p_76986_1_;
		boolean fire = c.isFire();
		Icon Icon = ModItems.thornChakram.getIconFromDamage(fire ? 1 : 0);

		if(Icon != null)  {
			GL11.glPushMatrix();
			GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			bindEntityTexture(p_76986_1_);
			Tessellator tessellator = Tessellator.instance;

			func_77026_a(tessellator, Icon, fire ? 240 : -1);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return TextureMap.locationItemsTexture;
	}

	private void func_77026_a(Tessellator p_77026_1_, Icon p_77026_2_, int light) {
		float f = p_77026_2_.getMinU();
		float f1 = p_77026_2_.getMaxU();
		float f2 = p_77026_2_.getMinV();
		float f3 = p_77026_2_.getMaxV();
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		p_77026_1_.startDrawingQuads();
		p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
		if(light != -1)
			p_77026_1_.setBrightness(light);
		p_77026_1_.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
		p_77026_1_.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
		p_77026_1_.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
		p_77026_1_.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
		p_77026_1_.draw();
	}

}
