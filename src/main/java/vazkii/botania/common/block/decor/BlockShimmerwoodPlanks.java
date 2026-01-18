/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Oct 11, 2015, 3:08:09 PM (GMT)]
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
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockShimmerwoodPlanks extends BlockMod implements ILexiconable {

	public BlockShimmerwoodPlanks(int id) {
		super(id, Material.wood);
		setHardness(2.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(LibBlockNames.SHIMMERWOOD_PLANKS);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Environment(EnvType.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {
		if(event.map.getTextureType() == 0) {
			TextureAtlasSprite icon = new InterpolatedIcon("botania:shimmerwoodPlanks");
			if(event.map.registerIcon("botania:shimmerwoodPlanks", icon) != null)
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
