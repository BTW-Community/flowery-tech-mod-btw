/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 28, 2014, 8:02:49 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockReeds extends BlockRotatedPillar implements ILexiconable {

	Icon topIcon;

	public BlockReeds(int id) {
		super(id, Material.wood);
		setHardness(1.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(LibBlockNames.REED_BLOCK);
		setCreativeTab(ModItems.botaniaTab);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		Item registered = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setUnlocalizedName(par1Str);
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
	protected Icon getEndIcon(int meta) {
		return topIcon;
	}

}
