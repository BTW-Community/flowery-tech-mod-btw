/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 10, 2015, 7:34:31 PM (GMT)]
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


public class Block18Stone extends BlockMod implements ILexiconable {

	private static Icon[] icons = new Icon[16];

	public Block18Stone(int id) {
		super(id, Material.rock);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName("stone");
	}

	@Override
	public void registerIcons(IconRegister register) {
		for(int i = 0; i < 16; i++)
			icons[i] = IconHelper.forBlock(register, this, i);
	}

	@Override
	public void getSubBlocks(int item, CreativeTabs tab, List list) {
		for(int i = 0; i < 16; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
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
		return LexiconData.stoneAlchemy;
	}

}
