/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 29, 2015, 8:17:08 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TileHourglass;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockHourglass extends BlockModContainer<TileHourglass> implements IManaTrigger, IWandable, IWandHUD, ILexiconable {

	Random random;

	protected BlockHourglass(int id) {
		super(id, Material.iron);
		setUnlocalizedName(LibBlockNames.HOURGLASS);
		setHardness(2.0F);
		setStepSound(soundMetalFootstep);

		float f = 1F / 16F;
		float w = 8F * f;
		float d = (1F - w) / 2;
		initBlockBounds(d, 0F, d, 1F - d, 1.15F, 1F - d);

		random = new Random();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xs, float ys, float zs) {
		TileHourglass hourglass = (TileHourglass) world.getTileEntity(x, y, z);
		ItemStack hgStack = hourglass.getStackInSlot(0);
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == ModItems.twigWand)
			return false;

		if(hourglass.lock) {
			if(!player.worldObj.isRemote)
				player.addChatMessage(StatCollector.translateToLocal("botaniamisc.hourglassLock"));
			return true;
		}

		if(hgStack == null && stack != null && TileHourglass.getStackItemTime(stack) > 0) {
			hourglass.setInventorySlotContents(0, stack.copy());
			hourglass.onInventoryChanged();
			stack.stackSize = 0;
			return true;
		} else if(hgStack != null) {
			ItemStack copy = hgStack.copy();
			if(!player.inventory.addItemStackToInventory(copy))
				player.dropPlayerItemWithRandomChoice(copy, false);
			hourglass.setInventorySlotContents(0, null);
			hourglass.onInventoryChanged();
			return true;
		}

		return false;
	}

	@Override
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		return false;
	}

	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlockMetadata(x, y, z) == 0 ? 0 : 15;
	}

	@Override
	public int tickRate(World world) {
		return 4;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 1 | 2);
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public Icon getIcon(int p_149691_1_, int p_149691_2_) {
		return ModBlocks.manaGlass.getIcon(0, 0);
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
		return LibRenderIDs.idHourglass;
	}

	@Override
	public TileHourglass createNewTileEntityT(World world, int meta) {
		return new TileHourglass();
	}

	@Override
	public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
		if(!world.isRemote && !burst.isFake()) {
			TileHourglass tile = (TileHourglass) world.getTileEntity(x, y, z);
			tile.move = !tile.move;
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
		}
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		TileHourglass tile = (TileHourglass) world.getTileEntity(x, y, z);
		tile.lock = !tile.lock;
		return false;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		TileHourglass tile = (TileHourglass) world.getTileEntity(x, y, z);
		tile.renderHUD(res);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.hourglass;
	}

}
