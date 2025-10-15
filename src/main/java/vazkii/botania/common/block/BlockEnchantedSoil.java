/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 26, 2015, 6:08:29 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.TextureAtlasSprite;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockEnchantedSoil extends BlockMod implements ILexiconable {

	Icon iconSide;

	public BlockEnchantedSoil(int id) {
		super(id, Material.grass);
		setHardness(0.6F);
		setStepSound(soundGrassFootstep);
		setUnlocalizedName(LibBlockNames.ENCHANTED_SOIL);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	boolean registerInCreative() {
		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Environment(EnvType.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {
		if(event.map.getTextureType() == 0) {
			TextureAtlasSprite icon = new InterpolatedIcon("botania:enchantedSoil0");
			if(event.map.registerIcon("botania:enchantedSoil0", icon) != null)
				blockIcon = icon;

			icon = new InterpolatedIcon("botania:enchantedSoil1");
			if(event.map.registerIcon("botania:enchantedSoil1", icon) != null)
				iconSide = icon;
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? Block.dirt.getIcon(0, 0) : side == 1 ? blockIcon : iconSide;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.dirt.idDropped(par1, par2Random, par3);
	}

//	@Override
//	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
//		return Block.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
//	}

	//todofix canSustain plant
/*	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return plantable.getPlantType(world, x, y - 1, z) == EnumPlantType.Plains;
	}*/

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.overgrowthSeed;
	}

}
