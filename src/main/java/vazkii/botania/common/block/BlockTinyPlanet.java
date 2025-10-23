/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 1, 2014, 3:49:12 PM (GMT)]
 */
package vazkii.botania.common.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.tile.TileTinyPlanet;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockTinyPlanet extends BlockModContainer<TileTinyPlanet> implements ILexiconable {

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
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
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
	public TileTinyPlanet createNewTileEntityT(World world, int meta) {
		return new TileTinyPlanet();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.tinyPlanet;
	}

}
