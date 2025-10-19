/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 26, 2015, 10:15:05 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs;

import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockDirtPathSlab extends BlockLivingSlab {

	public BlockDirtPathSlab(int id, boolean full) {
		super(id, full, ModBlocks.dirtPath, 0);
		setHardness(0.6F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.dirtPathSlabFull;
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.dirtPathSlab;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.dirtPath;
	}

}
