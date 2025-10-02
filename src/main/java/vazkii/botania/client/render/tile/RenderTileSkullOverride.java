/**
 * This class was created by <Kihira>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [? (GMT)]
 */
package vazkii.botania.client.render.tile;

import java.util.Map;

import net.minecraft.src.Minecraft;
import net.minecraft.src.AbstractClientPlayer;
import net.minecraft.src.TileEntitySkullRenderer;
import net.minecraft.src.TileEntitySkull;
import net.minecraft.src.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.ModelSkullOverride;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.common.block.tile.TileGaiaHead;

public class RenderTileSkullOverride extends TileEntitySkullRenderer {

	public static final ModelSkullOverride modelSkull = new ModelSkullOverride();

	@Override
	public void renderTileEntitySkullAt(TileEntitySkull skull, double u, double v, double w, float part) {

		render(skull, (float) u, (float) v, (float) w, skull.getBlockMetadata() & 7, (float)(skull.func_82119_b() * 360) / 16.0f, skull.getSkullType(), skull.getExtraType());

	}
/* render method from TileEntitySkullRenderer
   public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6, String par7Str) {
        ModelSkeletonHead var8 = this.field_82396_c;
        switch (par6) {
            default: {
                this.bindTexture(field_110642_c);
                break;
            }
            case 1: {
                this.bindTexture(field_110640_d);
                break;
            }
            case 2: {
                this.bindTexture(field_110641_e);
                var8 = this.field_82395_d;
                break;
            }
            case 3: {
                ResourceLocation var9 = AbstractClientPlayer.locationStevePng;
                if (par7Str != null && par7Str.length() > 0) {
                    var9 = AbstractClientPlayer.getLocationSkull(par7Str);
                    AbstractClientPlayer.getDownloadImageSkin(var9, par7Str);
                }
                this.bindTexture(var9);
                var8 = this.field_82395_d;
                break;
            }
            case 4: {
                this.bindTexture(field_110639_f);
                break;
            }
            case 5: {
                this.bindTexture(INFUSED_SKULL_TEXTURES);
                var8 = this.infusedModel;
            }
        }
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        if (par4 != 1) {
            switch (par4) {
                case 2: {
                    GL11.glTranslatef(par1 + 0.5f, par2 + 0.25f, par3 + 0.74f);
                    break;
                }
                case 3: {
                    GL11.glTranslatef(par1 + 0.5f, par2 + 0.25f, par3 + 0.26f);
                    par5 = 180.0f;
                    break;
                }
                case 4: {
                    GL11.glTranslatef(par1 + 0.74f, par2 + 0.25f, par3 + 0.5f);
                    par5 = 270.0f;
                    break;
                }
                default: {
                    GL11.glTranslatef(par1 + 0.26f, par2 + 0.25f, par3 + 0.5f);
                    par5 = 90.0f;
                    break;
                }
            }
        } else {
            GL11.glTranslatef(par1 + 0.5f, par2, par3 + 0.5f);
        }
        float var10 = 0.0625f;
        GL11.glEnable(32826);
        GL11.glScalef(-1.0f, -1.0f, 1.0f);
        GL11.glEnable(3008);
        var8.render(null, 0.0f, 0.0f, 0.0f, par5, 0.0f, var10);
        if (par6 == 5) {
            this.renderInfusedEyes(var8, par5);
        }
        GL11.glPopMatrix();
    }*/
	//todofix render gaia head
	public void render(TileEntitySkull skull, float par1, float par2, float par3, int par4, float par5, int par6, String playerName) {
		/*boolean gaia = skull instanceof TileGaiaHead;
		if(par6 == 3 || gaia) {
			ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
			Minecraft minecraft = Minecraft.getMinecraft();
			if(gaia)
				resourcelocation = minecraft.thePlayer.getLocationSkin();
			else if(playerName != null) {
				Map map = minecraft.func_152342_ad().func_152788_a(playerName);

				if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
					resourcelocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
				}
			}
			bindTexture(resourcelocation);
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			if (par4 != 1) {
				switch (par4) {
				case 2:
					GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.74F);
					break;
				case 3:
					GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.26F);
					par5 = 180.0F;
					break;
				case 4:
					GL11.glTranslatef(par1 + 0.74F, par2 + 0.25F, par3 + 0.5F);
					par5 = 270.0F;
					break;
				case 5:
				default:
					GL11.glTranslatef(par1 + 0.26F, par2 + 0.25F, par3 + 0.5F);
					par5 = 90.0F;
				}
			} else GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);

			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			if(gaia)
				ShaderHelper.useShader(ShaderHelper.doppleganger, RenderDoppleganger.defaultCallback);

			modelSkull.render(null, 0F, 0F, 0F, par5, 0F, 0.0625F);

			if(gaia)
				ShaderHelper.releaseShader();
			GL11.glPopMatrix();
		} else super.func_152674_a(par1, par2, par3, par4, par5, par6, playerName);*/
	}
}