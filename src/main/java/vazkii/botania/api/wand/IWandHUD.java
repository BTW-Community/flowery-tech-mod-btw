/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Feb 5, 2014, 1:34:44 PM (GMT)]
 */
package vazkii.botania.api.wand;

import net.minecraft.src.Minecraft;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.World;

/**
 * Any block that implements this has a HUD rendered when being hovered
 * with a Wand of the Forest.
 */
public interface IWandHUD {

	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z);

}
