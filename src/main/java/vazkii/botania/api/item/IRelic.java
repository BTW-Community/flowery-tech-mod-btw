/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 29, 2015, 7:17:41 PM (GMT)]
 */
package vazkii.botania.api.item;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;

/**
 * An item that implements this counts as a Relic item. This is purely for interaction
 * and other mod items should not implement this interface.
 */
public interface IRelic {

	/**
	 * Binds to the player name passed in.
	 */
	public void bindToUsername(String playerName, ItemStack stack);

	/**
	 * Gets the username of the person this relic is bound to.
	 */
	public String getSoulbindUsername(ItemStack stack);

	//todo new achievements
	/**
	 * Sets the achievement that this relic binds to.
	 */
	public void setBindAchievement(Achievement achievement);

	/**
	 * Gets the achievement that this relic binds to.
	 */
	public Achievement getBindAchievement();

}
