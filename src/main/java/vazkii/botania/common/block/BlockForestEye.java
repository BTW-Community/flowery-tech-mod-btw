/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 9, 2014, 10:55:17 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileForestEye;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockForestEye extends BlockModContainer<TileForestEye> implements ILexiconable {

	Icon[] icons;

	public BlockForestEye(int id) {
		super(id, Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundMetalFootstep);
		initBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
		setUnlocalizedName(LibBlockNames.FOREST_EYE);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[6];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(icons.length - 1, par1)];
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
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileForestEye eye = (TileForestEye) par1World.getTileEntity(par2, par3, par4);
		return Math.min(15, Math.max(0, eye.entities - 1));
	}

	@Override
	public TileForestEye createNewTileEntityT(World world, int meta) {
		return new TileForestEye();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.forestEye;
	}

}
