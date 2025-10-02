/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Feb 18, 2014, 10:13:02 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;


public class BlockPylon extends BlockModContainer implements ILexiconable {

	public BlockPylon(int id) {
		super(id, Material.iron);
		setHardness(5.5F);
		setStepSound(soundMetalFootstep);
		setBlockName(LibBlockNames.PYLON);
		setLightValue(0.5F);

		float f = 1F / 16F * 2F;
		setBlockBounds(f, 0F, f, 1F - f, 1F / 16F * 21F, 1F - f);
	}
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public Block setBlockName(String par1Str) {
		var item = new ItemBlockWithMetadataAndName(this.blockID, this);
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setBlockName(par1Str);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2, List par3) {
		for(int i = 0; i < 3; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return par2 == 0 ? Block.blockDiamond.getIcon(0, 0) : ModBlocks.storage.getIcon(0, par2);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idPylon;
	}

	//todofix enchant power
//	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 0 ? 8 : 15;
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TilePylon();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? LexiconData.pylon : meta == 1 ? LexiconData.alfhomancyIntro : LexiconData.gaiaRitual;
	}
}
