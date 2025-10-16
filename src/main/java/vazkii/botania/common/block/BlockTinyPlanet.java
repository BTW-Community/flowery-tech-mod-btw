/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [May 1, 2014, 3:49:12 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.Material;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.tile.TileTinyPlanet;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockTinyPlanet extends BlockModContainer implements ILexiconable {

	protected BlockTinyPlanet(int id) {
		super(id, Material.rock);
		setHardness(20F);
		setResistance(100F);
		setStepSound(soundStoneFootstep);
		float size = 3F / 16F;
		initBlockBounds(size, size, size, 1F - size, 1F - size, 1F - size);
		setUnlocalizedName(LibBlockNames.TINY_PLANET);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileTinyPlanet();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.tinyPlanet;
	}

}
