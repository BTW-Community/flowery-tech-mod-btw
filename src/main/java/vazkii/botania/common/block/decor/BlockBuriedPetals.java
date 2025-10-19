/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jun 28, 2014, 6:43:33 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import java.util.Random;

import net.minecraft.src.IconRegister;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.Item;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockBuriedPetals extends BlockModFlower {

	public BlockBuriedPetals(int id) {
		super(id, LibBlockNames.BURIED_PETALS);
		initBlockBounds(0F, 0F, 0F, 1F, 0.1F, 1F);
		setLightValue(0.25F);
	}

//	@Override
//	@Optional.Method(modid = "easycoloredlights")
//	public int getLightValue(IBlockAccess world, int x, int y, int z) {
//		return ColoredLightHelper.getPackedColor(world.getBlockMetadata(x, y, z), originalLight);
//	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		float[] color = EntitySheep.fleeceColorTable[meta];

		Botania.getProxy().setSparkleFXNoClip(true);
		Botania.getProxy().sparkleFX(par1World, par2 + 0.3 + par5Random.nextFloat() * 0.5, par3 + 0.1 + par5Random.nextFloat() * 0.1, par4 + 0.3 + par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
		Botania.getProxy().setSparkleFXNoClip(false);
	}

	@Override
	public boolean registerInCreative() {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	}

	@Override
	public int idDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return ModItems.petal.itemID;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return ModItems.petal.itemID;
	}

	@Override
	public int getRenderType() {
		return 0;
	}
}
