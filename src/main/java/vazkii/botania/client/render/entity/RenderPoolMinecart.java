/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 17, 2015, 11:17:48 PM (GMT)]
 */
package vazkii.botania.client.render.entity;

import net.minecraft.src.Block;
import net.minecraft.src.RenderMinecart;
import net.minecraft.src.EntityMinecart;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.common.entity.EntityPoolMinecart;

public class RenderPoolMinecart extends RenderMinecart {

	@Override
	protected void renderBlockInMinecart(EntityMinecart p_147910_1_, float p_147910_2_, Block p_147910_3_, int p_147910_4_) {
		EntityPoolMinecart poolCart = (EntityPoolMinecart) p_147910_1_;
		RenderTilePool.forceManaNumber = poolCart.getMana();
		super.renderBlockInMinecart(p_147910_1_, p_147910_2_, p_147910_3_, p_147910_4_);
	}


}
