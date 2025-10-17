/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 7, 2014, 6:36:37 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileStarfield;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockStarfield extends BlockModContainer implements ILexiconable {

	Icon[] icons;

	public BlockStarfield(int id) {
		super(id, Material.iron);
		setHardness(5F);
		setResistance(2000F);
		setStepSound(soundMetalFootstep);
		setUnlocalizedName(LibBlockNames.STARFIELD);

		initBlockBounds(0F, 0F, 0F, 1F, 0.25F, 1F);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[3];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(2, par1)];
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
		return new TileStarfield();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.starfield;
	}

}
