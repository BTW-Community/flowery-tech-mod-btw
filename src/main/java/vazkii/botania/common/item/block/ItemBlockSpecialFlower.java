/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 25, 2014, 2:04:15 PM (GMT)]
 */
package vazkii.botania.common.item.block;

import java.util.List;

import api.world.BlockPos;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import net.minecraft.src.TileEntity;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Icon;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IRecipeKeyProvider;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibMisc;

public class ItemBlockSpecialFlower extends ItemBlockMod implements IRecipeKeyProvider {

	public ItemBlockSpecialFlower(Block block1) {
		super(block1);
	}

	@Override
	public Icon getIconIndex(ItemStack stack) {
		return BotaniaAPI.getSignatureForName(getType(stack)).getIconForStack(stack);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		return getIconIndex(stack);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int iFacing, float fClickX, float fClickY, float fClickZ) {
		boolean placed = super.onItemUse(stack, player, world, x, y, z, iFacing, fClickX, fClickY, fClickZ);
		if(placed) {
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

		return placed;
	}

	@Override
	public boolean onItemUsedByBlockDispenser(ItemStack stack, World world, int i, int j, int k, int iFacing) {
		BlockPos targetPos = new BlockPos(i, j, k, iFacing);
		int iTargetDirection = this.getTargetFacingPlacedByBlockDispenser(iFacing);
		int iBlockID = this.getBlockIDToPlace(world, stack.getItemDamage(), iTargetDirection, 0.5f, 0.25f, 0.5f);
		Block newBlock = Block.blocksList[iBlockID];
		if (newBlock != null && world.canPlaceEntityOnSide(iBlockID, targetPos.x, targetPos.y, targetPos.z, true, iTargetDirection, null, stack)) {
			int iBlockMetadata = this.getMetadata(stack.getItemDamage());
			iBlockMetadata = newBlock.onBlockPlaced(world, targetPos.x, targetPos.y, targetPos.z, iTargetDirection, 0.5f, 0.25f, 0.5f, iBlockMetadata);
			world.setBlockAndMetadataWithNotify(targetPos.x, targetPos.y, targetPos.z, iBlockID, iBlockMetadata);
			newBlock.onPostBlockPlaced(world, targetPos.x, targetPos.y, targetPos.z, iBlockMetadata);
			String type = getType(stack);
			TileEntity te = world.getTileEntity(targetPos.x, targetPos.y, targetPos.z);
			if(te instanceof TileSpecialFlower tile) {
				tile.setSubTile(type);
				tile.onBlockAdded(world, i, j, k);
				tile.onBlockPlacedBy(world, i, j, k, null, stack);
				if(!world.isRemote)
					world.markBlockForUpdate(i, j, k);
			}
			world.playAuxSFX(2236, i, j, k, iBlockID);
			return true;
		}
		return false;
	}

//	@Override
//	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
//		boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
//		if(placed) {
//			String type = getType(stack);
//			TileEntity te = world.getTileEntity(x, y, z);
//			if(te instanceof TileSpecialFlower tile) {
//                tile.setSubTile(type);
//				tile.onBlockAdded(world, x, y, z);
//				tile.onBlockPlacedBy(world, x, y, z, player, stack);
//				if(!world.isRemote)
//					world.markBlockForUpdate(x, y, z);
//			}
//		}
//
//		return placed;
//	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return BotaniaAPI.getSignatureForName(getType(stack)).getUnlocalizedNameForStack(stack);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return getUnlocalizedNameInefficiently_(par1ItemStack);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String type = getType(par1ItemStack);
		SubTileSignature sig = BotaniaAPI.getSignatureForName(type);

		sig.addTooltip(par1ItemStack, par2EntityPlayer, par3List);

		if(ConfigHandler.referencesEnabled) {
			String refUnlocalized = sig.getUnlocalizedLoreTextForStack(par1ItemStack);
			String refLocalized = StatCollector.translateToLocal(refUnlocalized);
			if(!refLocalized.equals(refUnlocalized))
				par3List.add(EnumChatFormatting.ITALIC + refLocalized);
		}

		String mod = BotaniaAPI.subTileMods.get(type);
		if(mod != null && !mod.equals(LibMisc.MOD_ID))
			par3List.add(EnumChatFormatting.ITALIC + "[" + mod + "]");
	}

	public static String getType(ItemStack stack) {
		return ItemNBTHelper.detectNBT(stack) ? ItemNBTHelper.getString(stack, SubTileEntity.TAG_TYPE, "") : "";
	}

	public static ItemStack ofType(String type) {
		return ofType(new ItemStack(ModBlocks.specialFlower), type);
	}

	public static ItemStack ofType(ItemStack stack, String type) {
		ItemNBTHelper.setString(stack, SubTileEntity.TAG_TYPE, type);
		return stack;
	}

	@Override
	public String getKey(ItemStack stack) {
		return "flower." + getType(stack);
	}

}

