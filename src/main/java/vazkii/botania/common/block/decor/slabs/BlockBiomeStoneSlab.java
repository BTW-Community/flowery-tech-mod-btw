/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 29, 2015, 7:34:16 PM (GMT)]
 */
package vazkii.botania.common.block.decor.slabs;

import net.minecraft.src.Block;
import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBiomeStoneSlab extends BlockLivingSlab {

	int index;

	public BlockBiomeStoneSlab(int id, boolean full, Block source, int meta, int index) {
		super(id, full, source, meta);
		this.index = index;
		setHardness(1.5F);
		setResistance(10F);
	}

	@Override
	public BlockHalfSlab getFullBlock() {
		return (BlockHalfSlab) ModFluffBlocks.biomeStoneFullSlabs[index];
	}

	@Override
	public BlockHalfSlab getSingleBlock() {
		return (BlockHalfSlab) ModFluffBlocks.biomeStoneSlabs[index];
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.marimorphosis;
	}

}
