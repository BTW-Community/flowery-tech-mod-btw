/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 28, 2014, 8:02:49 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import net.minecraft.src.Block;
import net.minecraft.src.BlockRotatedPillar;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockReeds extends BlockRotatedPillar implements ILexiconable {

	Icon topIcon;

	public BlockReeds() {
		super(Material.wood);
		setHardness(1.0F);
		setStepSound(soundWoodFootstep);
		setBlockName(LibBlockNames.REED_BLOCK);
		setCreativeTab(CreativeTabs.tabMisc);
	}


	@Override
	public Block setBlockName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setBlockName(par1Str);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this, 0);
		topIcon = IconHelper.forBlock(par1IconRegister, this, 1);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

	@Override
	protected Icon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}

	@Override
	protected Icon getTopIcon(int p_150161_1_) {
		return topIcon;
	}

}
