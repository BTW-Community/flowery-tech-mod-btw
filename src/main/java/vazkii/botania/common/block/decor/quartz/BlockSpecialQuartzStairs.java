/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jun 11, 2014, 1:12:50 AM (GMT)]
 */
package vazkii.botania.common.block.decor.quartz;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.stairs.BlockModStairs;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSpecialQuartzStairs extends BlockModStairs {

	public BlockSpecialQuartzStairs(int id, Block source) {
		super(id, source, 0, "quartzStairs" + ((BlockSpecialQuartz) source).type);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return this == ModFluffBlocks.elfQuartzStairs ? LexiconData.elvenResources : super.getEntry(world, x, y, z, player, lexicon);
	}

}