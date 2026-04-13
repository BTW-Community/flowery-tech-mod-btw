/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 21, 2014, 7:48:54 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.List;
import java.util.Random;

import api.block.util.RayTraceUtils;
import btw.block.model.BlockModel;
import btw.item.BTWTags;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dev.bagel.interfaces.CustomBoundingBoxBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.model.block.PetalApothecaryModel;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.item.rod.ItemWaterRod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

/**
 * Petal Apothecary Block
 */
public class BlockAltar extends BlockModContainer<TileAltar> implements ILexiconable, CustomBoundingBoxBlock {
	Icon[] icons;
	Random random;
	PetalApothecaryModel model = new PetalApothecaryModel();

	protected BlockAltar(int id) {
		super(id, Material.rock);
		setHardness(3.5F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(LibBlockNames.ALTAR);

		float f = 1F / 16F * 2F;
		initBlockBounds(f, f, f, 1F - f, 1F / 16F * 20F, 1F - f);

		random = new Random();
	}

	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public ItemStack getStackRetrievedByBlockDispenser(World world, int i, int j, int k) {
		if (world.getTileEntity(i, j, k) instanceof TileAltar altar && altar.isMortared()) {
			ItemStack stack = new ItemStack(this, 1, altar.getBlockMetadata());
			ItemNBTHelper.setBoolean(stack, "mortared", true);
			return stack;
		}
		return super.getStackRetrievedByBlockDispenser(world, i, j, k);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockWithMetadataAndName(this) {
			@Override
			public String getUnlocalizedName(ItemStack stack) {
				boolean mortared = ItemNBTHelper.getBoolean(stack, "mortared", false);
				return super.getUnlocalizedName(stack) + (mortared ? "Mortared" : "");
			}
		};
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public void getSubBlocks(int item, CreativeTabs tab, List list) {
		for(int i = 0; i < 9; i++)
			list.add(new ItemStack(item, 1, i));
	}

//	@Override
//	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
//		return false;
//	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
	}

//	@Override
//	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
//		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
//	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity par5Entity) {
		if(par5Entity instanceof EntityItem entityItem) {
			TileAltar tile = (TileAltar) world.getTileEntity(x, y, z);
			if(tile.collideEntityItem(entityItem))
				VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
		}
	}

	@Override
	public boolean getCanBlockLightItemOnFire(IBlockAccess blockAccess, int i, int j, int k) {
		TileAltar tile = (TileAltar) blockAccess.getBlockTileEntity(i, j, k);
		return tile.hasLava;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileAltar tile = (TileAltar) world.getBlockTileEntity(x, y, z);
		return tile.hasLava ? 15 : 0;
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack stack = player.getCurrentEquippedItem();
		TileAltar tile = (TileAltar) par1World.getTileEntity(x, y, z);

		if(player.isSneaking()) {
			for(int i = tile.getSizeInventory() - 1; i >= 0; i--) {
				ItemStack stackAt = tile.getStackInSlot(i);
				if(stackAt != null) {
					ItemStack copy = stackAt.copy();
					if(!player.inventory.addItemStackToInventory(copy))
						player.dropPlayerItemWithRandomChoice(copy, false);
					tile.setInventorySlotContents(i, null);
					par1World.func_96440_m(x, y, z, this.blockID);
					break;
				}
			}
		} else if(tile.isEmpty() && tile.hasWater && stack == null)
			tile.trySetLastRecipe(player);
		else {
			if (BTWTags.mortars.test(stack) && !tile.isMortared()) {
				if(!player.capabilities.isCreativeMode)
					stack.stackSize--;
//					player.inventory.setInventorySlotContents(player.inventory.currentItem, getContainer(stack));

				tile.setMortared(true);
			}
			else if(!tile.isMortared()) {
				return false;
			}
			else if(stack != null && (isValidWaterContainer(stack) || stack.getItem() == ModItems.waterRod && ManaItemHandler.requestManaExact(stack, player, ItemWaterRod.COST, false))) {
				if(!tile.hasWater) {
					if(stack.getItem() == ModItems.waterRod)
						ManaItemHandler.requestManaExact(stack, player, ItemWaterRod.COST, true);
					else if(!player.capabilities.isCreativeMode)
						player.inventory.setInventorySlotContents(player.inventory.currentItem, getContainer(stack));

					tile.setWater(true);
					par1World.func_96440_m(x, y, z, this.blockID);
				}

				return true;
			} else if(stack != null && stack.getItem() == Item.bucketLava) {
				if(!player.capabilities.isCreativeMode)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, getContainer(stack));

				tile.setLava(true);
				tile.setWater(false);
				par1World.func_96440_m(x, y, z, this.blockID);

				return true;
			} else if(stack != null && stack.getItem() == Item.bucketEmpty && (tile.hasWater || tile.hasLava) && !Botania.gardenOfGlassLoaded) {
				ItemStack bucket = tile.hasLava ? new ItemStack(Item.bucketLava) : new ItemStack(Item.bucketWater);
				if(stack.stackSize == 1)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, bucket);
				else {
					if(!player.inventory.addItemStackToInventory(bucket))
						player.dropPlayerItemWithRandomChoice(bucket, false);
					stack.stackSize--;
				}

				if(tile.hasLava)
					tile.setLava(false);
				else tile.setWater(false);
				par1World.func_96440_m(x, y, z, this.blockID);

				return true;
			}
		}

		return false;
	}

	@Override
	public void fillWithRain(World world, int x, int y, int z) {
		if(world.rand.nextInt(20) == 1) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileAltar) {
				TileAltar altar = (TileAltar) tile;
				if(!altar.hasLava && !altar.hasWater)
					altar.setWater(true);
				world.func_96440_m(x, y, z, this.blockID);
			}
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	private boolean isValidWaterContainer(ItemStack stack) {
		if(stack == null || stack.stackSize != 1)
			return false;
		if(stack.getItem() == ModItems.waterBowl)
			return true;
		if(stack.getItem() == Item.bucketWater)
			return true;

		return false;
		//todofix maybe????? implement fluid support
//		if(stack.getItem() instanceof IFluidContainerItem) {
//			FluidStack fluidStack = ((IFluidContainerItem) stack.getItem()).getFluid(stack);
//			return fluidStack != null && fluidStack.getFluid() == FluidRegistry.WATER && fluidStack.amount >= FluidContainerRegistry.BUCKET_VOLUME;
//		}
//		FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(stack);
//		return fluidStack != null && fluidStack.getFluid() == FluidRegistry.WATER && fluidStack.amount >= FluidContainerRegistry.BUCKET_VOLUME;
	}

	private ItemStack getContainer(ItemStack stack) {
		if(stack.getItem() == ModItems.waterBowl)
			return new ItemStack(Item.bowlEmpty);
		if(stack.getItem() == Item.bucketWater)
			return new ItemStack(Item.bucketEmpty);
		return null;

		//fluid registry stuff!
//		if (stack.getItem().hasContainerItem(stack))
//			return stack.getItem().getContainerItem(stack);
//		else if (stack.getItem() instanceof IFluidContainerItem) {
//			((IFluidContainerItem) stack.getItem()).drain(stack, FluidContainerRegistry.BUCKET_VOLUME, true);
//			return stack;
//		}
//		return FluidContainerRegistry.drainFluidContainer(stack);
	}

	private String[] names = {
			"Default",
			"Forest",
			"Plains",
			"Mountain",
			"Fungal",
			"Swamp",
			"Desert",
			"Taiga",
			"Mesa",
			"Livingrock",
			"Mossy",
			"Deepslate"
	};
	private String[] sides = {
			"Bottom",
			"Top",
			"Side",
			"Side",
			"Side",
			"Side",
	};
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[36];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, names[i / 3] + sides[i % 3]);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		int finalVal = meta * 3 + Math.min(2, side);
		if (finalVal >= icons.length) {
			finalVal = 0;
		}
		return icons[finalVal];
//		return meta == 0 ? Block.cobblestone.getIcon(side, meta) : ModFluffBlocks.biomeStoneA.getIcon(side, meta + 7);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

//	@Override
//	public int getRenderType() {
//		return LibRenderIDs.idAltar;
//	}

	@Override
	public TileAltar createNewTileEntityT(World world) {
		return new TileAltar();
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int block, int par6) {
		TileSimpleInventory.breakBlock(par1World, par2, par3, par4, block, this.random);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileAltar altar = (TileAltar) par1World.getTileEntity(par2, par3, par4);
		return altar.hasWater ? 15 : 0;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.apothecary;
	}

	private boolean secondPass = false;
	@Override
	@Environment(value= EnvType.CLIENT)
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		BlockModel transformedModel = this.model.makeTemporaryCopy();
		return transformedModel.renderAsBlock(renderer, this, i, j, k);
	}

	@Override
	public void renderBlockSecondPass(RenderBlocks renderBlocks, int i, int j, int k, boolean bFirstPassResult) {
		secondPass = true;
		BlockModel transformedModel = this.model.base.makeTemporaryCopy();
		transformedModel.renderAsBlock(renderBlocks, this, i, j, k);
		secondPass = false;
	}

	@Override
	@Environment(value=EnvType.CLIENT)
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		this.model.renderAsItemBlock(renderBlocks, this, iItemDamage);
		secondPass = true;
		this.model.base.renderAsItemBlock(renderBlocks, this, iItemDamage);
		secondPass = false;
	}


	@Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB boundingBox, List list, Entity entity) {
		this.model.makeTemporaryCopy().addIntersectingBoxesToCollisionList(world, i, j, k, boundingBox, list);
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3 startRay, Vec3 endRay) {
		RayTraceUtils rayTrace = new RayTraceUtils(world, i, j, k, startRay, endRay);
		BlockModel transformedModel = this.model;
		transformedModel.addToRayTrace(rayTrace);
		this.model.base.addToRayTrace(rayTrace);
		return rayTrace.getFirstIntersection();
	}

	@Override
	public List<AxisAlignedBB> getCustomSelectionBoxes(World world, int x, int y, int z) {
		return model.bounds;
	}

	@Override
	public boolean rotatable() {
		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
}
