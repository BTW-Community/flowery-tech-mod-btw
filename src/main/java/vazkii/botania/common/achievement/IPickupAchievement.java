/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 28, 2015, 5:59:03 PM (GMT)]
 */
package vazkii.botania.common.achievement;

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;

public interface IPickupAchievement {

	public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item);

}
