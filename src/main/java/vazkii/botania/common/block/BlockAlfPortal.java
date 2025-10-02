/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jun 9, 2014, 7:17:46 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockAlfPortal extends BlockModContainer implements IWandable, ILexiconable {

	Icon iconOff, iconOn;
	public static Icon portalTex;

	public BlockAlfPortal() {
		super(Material.wood);
		setHardness(10F);
		setStepSound(soundWoodFootstep);
		setBlockName(LibBlockNames.ALF_PORTAL);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		iconOff = IconHelper.forBlock(par1IconRegister, this, 0);
		iconOn = IconHelper.forBlock(par1IconRegister, this, 1);
		portalTex = IconHelper.forBlock(par1IconRegister, this, "Inside");
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return meta == 0 ? iconOff : iconOn;
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileAlfPortal();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.alfhomancyIntro;
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		boolean did = ((TileAlfPortal) world.getTileEntity(x, y, z)).onWanded();
		if(did && player != null)
			player.addStat(ModAchievements.elfPortalOpen, 1);
		return did;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 0 ? 0 : 15;
	}

}
