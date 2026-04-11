/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 28, 2015, 11:53:13 AM (GMT)]
 */
package vazkii.botania.common.block.corporea;

import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.corporea.TileCorporeaRetainer;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockCorporeaRetainer extends BlockModContainer<TileCorporeaRetainer> implements ILexiconable {
	Icon[] icons;

	public BlockCorporeaRetainer(int id) {
		super(id, Material.iron);
		setHardness(5.5F);
		setStepSound(soundMetalFootstep);
		setUnlocalizedName(LibBlockNames.CORPOREA_RETAINER);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int block) {
		boolean power = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
		int meta = world.getBlockMetadata(x, y, z);
		boolean powered = (meta & 8) != 0;

		if(power && !powered) {
			((TileCorporeaRetainer) world.getTileEntity(x, y, z)).fulfilRequest();
			world.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
		} else if(!power && powered)
			world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int s) {
		return ((TileCorporeaRetainer) world.getTileEntity(x, y, z)).hasPendingRequest() ? 15 : 0;
	}

	@Override
	public TileCorporeaRetainer createNewTileEntityT(World world) {
		return new TileCorporeaRetainer();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.corporeaRetainer;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? icons[0] : icons[1];
	}
}
