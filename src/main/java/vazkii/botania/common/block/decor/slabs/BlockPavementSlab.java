/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 1, 2015, 7:17:45 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs;

import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPavementSlab extends BlockLivingSlab {

	int index;

	public BlockPavementSlab(int id, boolean full, int meta, int index) {
		super(id, full, ModFluffBlocks.pavement, meta);
		this.index = index;
		setHardness(2F);
		setResistance(10F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.pavementFullSlabs[index];
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.pavementSlabs[index];
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.pavement;
	}


}
