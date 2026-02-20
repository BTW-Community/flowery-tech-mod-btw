/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p> 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p> 
 * File Created @ [Jan 22, 2014, 7:06:38 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.ITileEntityProvider;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.integration.coloredlights.LightHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import static vazkii.botania.common.item.block.ItemBlockSpecialFlower.getType;


public class BlockSpecialFlower extends BlockFlower implements ITileEntityProvider, ISpecialFlower, IWandable, ILexiconable, IWandHUD {

	public static Map<String, Icon> icons = new HashMap<>();
	public static Map<String, Icon> iconsAlt = new HashMap<>();

	static {
		BotaniaAPI.subtilesForCreativeMenu.addAll(Arrays.asList(new String[] {
				// Misc
				LibBlockNames.SUBTILE_PUREDAISY,
				LibBlockNames.SUBTILE_MANASTAR,

				// Generating
				LibBlockNames.SUBTILE_DAYBLOOM,
				LibBlockNames.SUBTILE_NIGHTSHADE,
				LibBlockNames.SUBTILE_ENDOFLAME,
				LibBlockNames.SUBTILE_HYDROANGEAS,
				LibBlockNames.SUBTILE_THERMALILY,
				LibBlockNames.SUBTILE_ARCANE_ROSE,
				LibBlockNames.SUBTILE_MUNCHDEW,
				LibBlockNames.SUBTILE_ENTROPINNYUM,
				LibBlockNames.SUBTILE_KEKIMURUS,
				LibBlockNames.SUBTILE_GOURMARYLLIS,
				LibBlockNames.SUBTILE_NARSLIMMUS,
				LibBlockNames.SUBTILE_SPECTROLUS,
				LibBlockNames.SUBTILE_RAFFLOWSIA,
				LibBlockNames.SUBTILE_DANDELIFEON,

				// Functional
				LibBlockNames.SUBTILE_JADED_AMARANTHUS,
				LibBlockNames.SUBTILE_BELLETHORN,
				LibBlockNames.SUBTILE_DREADTHORN,
				LibBlockNames.SUBTILE_HEISEI_DREAM,
				LibBlockNames.SUBTILE_TIGERSEYE,
				LibBlockNames.SUBTILE_MARIMORPHOSIS,
				LibBlockNames.SUBTILE_ORECHID,
				LibBlockNames.SUBTILE_ORECHID_IGNEM,
				LibBlockNames.SUBTILE_FALLEN_KANADE,
				LibBlockNames.SUBTILE_EXOFLAME,
				LibBlockNames.SUBTILE_AGRICARNATION,
				LibBlockNames.SUBTILE_HOPPERHOCK,
				LibBlockNames.SUBTILE_RANNUNCARPUS,
				LibBlockNames.SUBTILE_TANGLEBERRIE,
				LibBlockNames.SUBTILE_JIYUULIA,
				LibBlockNames.SUBTILE_HYACIDUS,
				LibBlockNames.SUBTILE_MEDUMONE,
				LibBlockNames.SUBTILE_POLLIDISIAC,
				LibBlockNames.SUBTILE_CLAYCONIA,
				LibBlockNames.SUBTILE_LOONIUM,
				LibBlockNames.SUBTILE_DAFFOMILL,
				LibBlockNames.SUBTILE_VINCULOTUS,
				LibBlockNames.SUBTILE_SPECTRANTHEMUM,
				LibBlockNames.SUBTILE_BUBBELL,
				LibBlockNames.SUBTILE_SOLEGNOLIA
		}));
	}

	protected BlockSpecialFlower(int id) {
		super(id);
		setUnlocalizedName(LibBlockNames.SPECIAL_FLOWER);
		this.isBlockContainer = true;
		setHardness(0.1F);
		setStepSound(soundGrassFootstep);
		setTickRandomly(false);
		setCreativeTab(ModItems.botaniaTab);
		initBlockBounds(0.3F, 0.0F, 0.3F, 0.8F, 1, 0.8F);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int currentLight = ((TileSpecialFlower) world.getBlockTileEntity(x, y, z)).getLightValue();
		if(currentLight == -1)
			currentLight = 0;
		return LightHelper.getPackedColor(world.getBlockMetadata(x, y, z), currentLight);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).getComparatorInputOverride(side);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		return ((TileSpecialFlower) world.getBlockTileEntity(x, y, z)).getPowerLevel(side);
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		return isProvidingWeakPower(world, x, y, z, side);
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idSpecialFlower;
	}

//	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockSpecialFlower(this);
//		GameRegistry.registerBlock(this, ItemBlockSpecialFlower.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(String s : BotaniaAPI.subtilesForCreativeMenu) {
			par3List.add(ItemBlockSpecialFlower.ofType(s));
			if(BotaniaAPI.miniFlowers.containsKey(s))
				par3List.add(ItemBlockSpecialFlower.ofType(BotaniaAPI.miniFlowers.get(s)));
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(String s : BotaniaAPI.getAllSubTiles())
			if(!s.isEmpty())
				BotaniaAPI.getSignatureForName(s).registerIcons(par1IconRegister);
	}

	@Override
	public Icon getIcon(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
		return ((TileSpecialFlower) par1iBlockAccess.getBlockTileEntity(par2, par3, par4)).getIcon();
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return BlockModFlower.icons[16];
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		String name = ((TileSpecialFlower) world.getTileEntity(x, y, z)).subTileName;
		return ItemBlockSpecialFlower.ofType(name);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		Block below = world.getBlock(i, j - 1, k);
		return super.canPlaceBlockAt(world, i, j, k) || below == ModBlocks.redStringRelay || below == Block.mycelium;
//		return super.canPlaceBlockAt(world, i, j, k);
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int l, int m) {
		super.breakBlock(world, i, j, k, l, m);
		world.removeBlockTileEntity(i, j, k);
	}

//	@Override
//	protected boolean canPlaceBlockOn(Block block) {
//
//		return super.canPlaceBlockOn(block) || block == ModBlocks.redStringRelay || block == Block.mycelium;
//	}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		if(!par6EntityPlayer.capabilities.isCreativeMode) {
			dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
			((TileSpecialFlower) par1World.getTileEntity(par2, par3, par4)).onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<>();
		TileEntity tile = world.getTileEntity(x, y, z);

		if(tile != null) {
			String name = ((TileSpecialFlower) tile).subTileName;
			list.add(ItemBlockSpecialFlower.ofType(name));
			((TileSpecialFlower) tile).getDrops(list);
		}

		return list;
	}

	@Override
	public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
		super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileSpecialFlower();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).getEntry();
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).onWanded(stack, player);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(entity instanceof EntityPlayer player) {
			String type = getType(stack);
			TileEntity te = world.getTileEntity(x, y, z);
			if(te instanceof TileSpecialFlower tile) {
				tile.setSubTile(type);
				tile.onBlockAdded(world, x, y, z);
				tile.onBlockPlacedBy(world, x, y, z, player, stack);
				if(!world.isRemote)
					world.markBlockForUpdate(x, y, z);
			}
		}
		((TileSpecialFlower) world.getTileEntity(x, y, z)).onBlockPlacedBy(world, x, y, z, entity, stack);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) instanceof TileSpecialFlower tsf) {
			tsf.onBlockAdded(world, x, y, z);
		}
//		((TileSpecialFlower) world.getTileEntity(x, y, z)).onBlockAdded(world, x, y, z);
	}

	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		float[] rgb = EntitySheep.fleeceColorTable[world.getBlockMetadata(x, y, z)];
		return ((int) (rgb[0] * 255) << 16) + ((int) (rgb[1] * 255) << 8) + (int) (rgb[2] * 255);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == ModItems.dye) {
			int newMeta = stack.getItemDamage();
			int oldMeta = world.getBlockMetadata(x, y, z);
			if(newMeta != oldMeta)
				world.setBlockMetadataWithNotify(x, y, z, newMeta, 1 | 2);
		}

		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileSpecialFlower) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

}
