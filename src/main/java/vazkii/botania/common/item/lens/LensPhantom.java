/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Jan 24, 2015, 4:39:24 PM (GMT)]
 */
package vazkii.botania.common.item.lens;

import net.minecraft.src.EntityThrowable;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;

public class LensPhantom extends Lens {

	@Override
	public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
		if(!isManaBlock) {
			dead = false;
			burst.setMinManaLoss(Math.max(0, burst.getMinManaLoss() - 4));
		}

		return dead;
	}

}
