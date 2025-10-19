/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Feb 16, 2015, 8:17:12 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;


public class BlockEndStoneBrick extends BlockMod implements ILexiconable {

	private static Icon[] icons = new Icon[5];

	public BlockEndStoneBrick(int id) {
		super(id, Material.rock);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.END_STONE_BRICK);
	}

	@Override
	public void registerIcons(IconRegister register) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = IconHelper.forBlock(register, this, i);
		}
	}

	@Override
	public void getSubBlocks(int item, CreativeTabs tab, List list) {
		for(int i = 0; i < 4; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return meta == 3 ? icons[side == 0 || side == 1 ? 4 : 3] : icons[Math.min(icons.length - 1, meta)];
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockWithMetadataAndName(this);
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(this, 1, meta);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.endStoneDecor;
	}

}
