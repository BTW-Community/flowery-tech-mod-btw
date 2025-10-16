/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 8, 2014, 10:16:53 PM (GMT)]
 */
package vazkii.botania.common.block.decor;


import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.decor.IFloatingFlower.IslandType;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.item.IFloatingFlowerVariant;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

import java.util.List;
import java.util.Random;

public class BlockFloatingFlower extends BlockModContainer implements ILexiconable {

	public BlockFloatingFlower(int id) {
		this(id, LibBlockNames.MINI_ISLAND);
	}

	public BlockFloatingFlower(int id, String name) {
		super(id, Material.ground);
		setUnlocalizedName(name);
		setHardness(0.5F);
		setStepSound(soundGravelFootstep);
		setLightValue(1F);

		float f = 0.1F;
		setBlockBounds(f, f, f, 1F - f, 1F - f, 1F - f);
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		register(par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	protected void register(String name) {
		var item = new ItemBlockWithMetadataAndName(this);
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, name);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		float[] color = EntitySheep.fleeceColorTable[meta];

		if(par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency)
			Botania.getProxy().sparkleFX(par1World, par2 + 0.3 + par5Random.nextFloat() * 0.5, par3 + 0.5 + par5Random.nextFloat() * 0.5, par4 + 0.3 + par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2, List par3) {
		for(int i = 0; i < 16; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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
	public Icon getIcon(int par1, int par2) {
		return Block.dirt.getIcon(par1, par2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null) {
			IFloatingFlower flower = (IFloatingFlower) world.getTileEntity(x, y, z);
			IslandType type = null;
			if(stack.getItem() == Item.snowball)
				type = IslandType.SNOW;
			else if(stack.getItem() instanceof IFloatingFlowerVariant) {
				IslandType newType = ((IFloatingFlowerVariant) stack.getItem()).getIslandType(stack);
				if(newType != null)
					type = newType;
			}

			if(type != null && type != flower.getIslandType()) {
				if(!world.isRemote) {
					flower.setIslandType(type);
					VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
				}

				if(!player.capabilities.isCreativeMode)
					stack.stackSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idMiniIsland;
	}

	@Override
	public TileEntity createNewTileEntityT(World world, int meta) {
		return new TileFloatingFlower();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.shinyFlowers;
	}
}
