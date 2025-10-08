/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [May 2, 2014, 7:18:49 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockAlchemyCatalyst extends BlockMod implements ILexiconable, IPoolOverlayProvider {

	Icon[] icons;

	public BlockAlchemyCatalyst(int id) {
		this(id, LibBlockNames.ALCHEMY_CATALYST);
	}

	public BlockAlchemyCatalyst(int id, String name) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		setBlockName(name);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[4];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(2, par1)];
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.alchemy;
	}

	@Override
	public Icon getIcon(World world, int x, int y, int z) {
		return icons[3];
	}

}
