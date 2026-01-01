/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Nov 17, 2014, 5:31:53 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.decor.BlockFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.crafting.recipe.SpecialFloatingFlowerRecipe;
import vazkii.botania.common.item.block.ItemBlockFloatingSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;

import static vazkii.botania.common.item.block.ItemBlockSpecialFlower.getType;


public class BlockFloatingSpecialFlower extends BlockFloatingFlower implements ISpecialFlower, IWandable, ILexiconable, IWandHUD {

	public BlockFloatingSpecialFlower(int id) {
		super(id, LibBlockNames.FLOATING_SPECIAL_FLOWER);

		CraftingManager.getInstance().getRecipeList().add(new SpecialFloatingFlowerRecipe(Botania.loc("special_floating_flower")));
//		RecipeSorter.register("botania:floatingSpecialFlower", SpecialFloatingFlowerRecipe.class, Category.SHAPELESS, "");
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int currentLight = ((TileSpecialFlower) world.getBlockTileEntity(x, y, z)).getLightValue();
		if(currentLight == -1)
			currentLight = originalLight;
		return Botania.lightHelper.getPackedColor(world.getBlockMetadata(x, y, z), currentLight);
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
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		// NO-OP
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(String s : BotaniaAPI.subtilesForCreativeMenu) {
			par3List.add(ItemBlockSpecialFlower.ofType(new ItemStack(par1, 1, 0), s));
			if(BotaniaAPI.miniFlowers.containsKey(s))
				par3List.add(ItemBlockSpecialFlower.ofType(new ItemStack(par1, 1, 0), BotaniaAPI.miniFlowers.get(s)));
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		String name = ((TileSpecialFlower) world.getTileEntity(x, y, z)).subTileName;
		return ItemBlockSpecialFlower.ofType(new ItemStack(world.getBlock(x, y, z)), name);
	}

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
			list.add(ItemBlockSpecialFlower.ofType(new ItemStack(world.getBlock(x, y, z)), name));
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ) || super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		((TileSpecialFlower) world.getTileEntity(x, y, z)).onBlockAdded(world, x, y, z);
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileSpecialFlower) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	protected void register(String name) {
		var registered = new ItemBlockFloatingSpecialFlower(this);
//		GameRegistry.registerBlock(this, ItemBlockFloatingSpecialFlower.class, name);
	}

	@Override
	public TileEntity createNewTileEntityT(World world) {
		return new TileFloatingSpecialFlower();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return ((TileSpecialFlower) world.getTileEntity(x, y, z)).getEntry();
	}
}
