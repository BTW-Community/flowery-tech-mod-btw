/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Oct 10, 2015, 10:29:22 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.TextureAtlasSprite;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockShimmerrock extends BlockMod implements ILexiconable {

	public BlockShimmerrock(int id) {
		super(id, Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.SHIMMERROCK);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	@Environment(EnvType.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {
		if(event.map.getTextureType() == 0) {
			TextureAtlasSprite icon = new InterpolatedIcon("botania:shimmerrock");
			if(event.map.registerIcon("botania:shimmerrock", icon) != null)
				blockIcon = icon;
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.rainbowRod;
	}

}
