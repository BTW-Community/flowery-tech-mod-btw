/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 31, 2015, 9:00:38 PM (GMT)]
 */
package vazkii.botania.common.item.block;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

public interface IRarityBlock {

	public EnumRarity getRarity(ItemStack stack);

}
