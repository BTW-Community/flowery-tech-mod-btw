/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 17, 2015, 7:55:33 PM (GMT)]
 */
package vazkii.botania.common.block.decor;

import dev.bagel.util.Items;
import net.minecraft.src.Material;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityFurnace;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockBlaze extends BlockMod implements ILexiconable/*, IFuelHandler*/ {

	public BlockBlaze(int id) {
		super(id, Material.iron);
		setHardness(3F);
		setResistance(10F);
		setStepSound(soundMetalFootstep);
		setLightValue(1F);
		setUnlocalizedName(LibBlockNames.BLAZE_BLOCK);
//		GameRegistry.registerFuelHandler(this);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.blazeBlock;
	}
	private static final TileEntityFurnace furnace = new TileEntityFurnace();

	@Override
	public int getFurnaceBurnTime(int iItemDamage) {
		return furnace.getItemBurnTime(new ItemStack(Item.blazeRod)) * (Botania.gardenOfGlassLoaded ? 5 : 10);
	}

}
