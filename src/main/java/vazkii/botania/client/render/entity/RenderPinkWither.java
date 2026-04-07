/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 25, 2015, 5:55:59 PM (GMT)]
 */
package vazkii.botania.client.render.entity;

import net.minecraft.src.*;
import vazkii.botania.client.lib.LibResources;

public class RenderPinkWither extends RenderWither {

	private static final ResourceLocation resource = new ResourceLocation(LibResources.MODEL_PINK_WITHER);

	int idk = -1;

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		if(BossStatus.bossName.equals(par1Entity.getEntityName())) {
			BossStatus.statusBarLength = -1;
			//was BossStatus.hasColorModifier
			BossStatus.field_82825_d = false;
		}
	}

	@Override //getEntityTexture
	protected ResourceLocation func_110911_a(EntityWither p_110775_1_) {
		return resource;
	}

}
