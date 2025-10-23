/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 14, 2014, 5:29:56 PM (GMT)]
 */
package vazkii.botania.common.block.string;

import net.minecraft.src.World;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.block.tile.string.TileRedStringContainer;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockRedStringContainer extends BlockRedString {

	public BlockRedStringContainer(int id) {
		super(id, LibBlockNames.RED_STRING_CONTAINER);
	}

	@Override
	public TileRedString createNewTileEntityT(World world) {
		return new TileRedStringContainer();
	}

}
