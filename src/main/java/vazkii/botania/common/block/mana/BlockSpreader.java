/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 25, 2014, 9:38:23 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.api.wand.IWireframeAABBProvider;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileSpreader;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockSpreader extends BlockModContainer<TileSpreader> implements IWandable, IWandHUD, ILexiconable, IWireframeAABBProvider {

	Random random;

	public BlockSpreader(int id) {
		super(id, Material.wood);
		setHardness(2.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(LibBlockNames.SPREADER);

		random = new Random();
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockWithMetadataAndName(this);
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setUnlocalizedName(par1Str);
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
	public void registerIcons(IconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2, List par3) {
		for(int i = 0; i < 4; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int orientation = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
		TileSpreader spreader = (TileSpreader) par1World.getTileEntity(par2, par3, par4);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, par6ItemStack.getItemDamage(), 1 | 2);

		switch(orientation) {
		case 0:
			spreader.rotationY = -90F;
			break;
		case 1:
			spreader.rotationY = 90F;
			break;
		case 2:
			spreader.rotationX = 270F;
			break;
		case 3:
			spreader.rotationX = 90F;
			break;
		case 4:
			break;
		default:
			spreader.rotationX = 180F;
			break;
		}
	}

	@Override
	public boolean canRotateOnTurntable(IBlockAccess blockAccess, int i, int j, int k) {
		return true;
	}

	@Override
	protected void onRotatedOnTurntable(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) instanceof TileSpreader spreader) {
			spreader.setRotationX(spreader.getRotationX() + 90F);
			if (spreader.getRotationX() >= 360F) {
				spreader.setRotationX(spreader.getRotationX() - 360F);
			}
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
		}
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
		return par2 >= 2 ? ModBlocks.dreamwood.getIcon(par1, 0) : ModBlocks.livingwood.getIcon(par1, 0);
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idSpreader;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if(!(tile instanceof TileSpreader spreader))
			return false;

        ItemStack lens = spreader.getStackInSlot(0);
		ItemStack heldItem = par5EntityPlayer.getCurrentEquippedItem();
		boolean isHeldItemLens = heldItem != null && heldItem.getItem() instanceof ILens;
		boolean wool = heldItem != null && heldItem.getItem() == new ItemStack(Block.cloth).getItem();

		if(heldItem != null)
			if(heldItem.getItem() == ModItems.twigWand)
				return false;

		if(lens == null && isHeldItemLens) {
			if (!par5EntityPlayer.capabilities.isCreativeMode)
				par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);

			spreader.setInventorySlotContents(0, heldItem.copy());
			spreader.onInventoryChanged();
		} else if(lens != null && !wool) {
			ItemStack add = lens.copy();
			if(!par5EntityPlayer.inventory.addItemStackToInventory(add))
				par5EntityPlayer.dropPlayerItemWithRandomChoice(add, false);
			spreader.setInventorySlotContents(0, null);
			spreader.onInventoryChanged();
		}

		if(wool && spreader.paddingColor == -1) {
			spreader.paddingColor = heldItem.getItemDamage();
			heldItem.stackSize--;
			if(heldItem.stackSize == 0)
				par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
		} else if(heldItem == null && spreader.paddingColor != -1 && lens == null) {
			ItemStack pad = new ItemStack(Block.cloth, 1, spreader.paddingColor);
			if(!par5EntityPlayer.inventory.addItemStackToInventory(pad))
				par5EntityPlayer.dropPlayerItemWithRandomChoice(pad, false);
			spreader.paddingColor = -1;
			spreader.onInventoryChanged();
		}

		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if(!(tile instanceof TileSpreader inv))
			return;

        for (int j1 = 0; j1 < inv.getSizeInventory() + 1; ++j1) {
            ItemStack itemstack = j1 >= inv.getSizeInventory() ? inv.paddingColor == -1 ? null : new ItemStack(Block.cloth, 1, inv.paddingColor) : inv.getStackInSlot(j1);

            if (itemstack != null) {
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                EntityItem entityitem;

                for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem)) {
                    int k1 = random.nextInt(21) + 10;

                    if (k1 > itemstack.stackSize)
                        k1 = itemstack.stackSize;

                    itemstack.stackSize -= k1;
                    entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float) random.nextGaussian() * f3;
                    entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float) random.nextGaussian() * f3;

                    if (itemstack.hasTagCompound())
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }
            }
        }

        par1World.func_96440_m(par2, par3, par4, block);

        super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		((TileSpreader) world.getTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

	@Override
	public TileSpreader createNewTileEntityT(World world) {
		return new TileSpreader();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileSpreader) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? LexiconData.spreader : meta == 1 ? LexiconData.redstoneSpreader : LexiconData.dreamwoodSpreader;
	}

	@Override
	public AxisAlignedBB getWireframeAABB(World world, int x, int y, int z) {
		float f = 1F / 16F;
		return AxisAlignedBB.getBoundingBox(x + f, y + f, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
	}

}
