/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 30, 2015, 3:56:19 PM (GMT)]
 */
package vazkii.botania.common.block.corporea;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockCorporeaCrystalCube extends BlockCorporeaBase implements ILexiconable {

	public BlockCorporeaCrystalCube(int id) {
		super(id, Material.iron, LibBlockNames.CORPOREA_CRYSTAL_CUBE);
		setHardness(5.5F);
		setStepSound(soundMetalFootstep);
		float f = (1F - 10F / 16F) / 2F;
		initBlockBounds(f, 0F, f, 1F - f, 1F, 1F - f);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if(!world.isRemote) {
			TileCorporeaCrystalCube cube = (TileCorporeaCrystalCube) world.getTileEntity(x, y, z);
			cube.doRequest(player.isSneaking());
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
		ItemStack stack = player.getCurrentEquippedItem();

		if(stack != null) {
			TileCorporeaCrystalCube cube = (TileCorporeaCrystalCube) world.getTileEntity(x, y, z);
			cube.setRequestTarget(stack);
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		return false;
	}

	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idCorporeaCrystalCybe;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return ModBlocks.storage.getIcon(0, 2);
	}

	@Override
	public TileCorporeaBase createNewTileEntityT(World world, int meta) {
		return new TileCorporeaCrystalCube();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.corporeaCrystalCube;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int s) {
		return ((TileCorporeaCrystalCube) world.getTileEntity(x, y, z)).compValue;
	}

}
