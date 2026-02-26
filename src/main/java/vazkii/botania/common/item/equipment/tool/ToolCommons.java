/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 13, 2014, 7:13:04 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.tool;

import api.item.items.ToolItem;
import btw.block.BTWBlocks;
import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.elementium.ItemElementiumPick;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public final class ToolCommons {

	public static Material[] materialsPick = new Material[]{ Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil, BTWBlocks.soulforgedSteelMaterial, BTWBlocks.netherRockMaterial, BTWBlocks.cementMaterial};
	public static Material[] materialsShovel = new Material[]{ Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay, BTWBlocks.naturalClayMaterial };
	public static Material[] materialsAxe = new Material[]{ Material.coral, Material.leaves, Material.plants, Material.wood, Material.pumpkin, BTWBlocks.plankMaterial , BTWBlocks.logMaterial, BTWBlocks.wickerMaterial };

	public static void damageItem(ItemStack stack, int dmg, EntityLivingBase entity, int manaPerDamage) {
		int manaToRequest = dmg * manaPerDamage;
		boolean manaRequested = entity instanceof EntityPlayer && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entity, manaToRequest, true);

		if(!manaRequested)
			stack.damageItem(dmg, entity);
	}

	public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean silk, int fortune, boolean dispose) {
		float blockHardness = block == null ? 1F : block.getBlockHardness(world, x, y, z);

		for(int x1 = xs; x1 < xe; x1++)
			for(int y1 = ys; y1 < ye; y1++)
				for(int z1 = zs; z1 < ze; z1++)
					removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, block, materialsListing, silk, fortune, blockHardness, dispose);
	}

	public static boolean isRightMaterial(Material material, Material[] materialsListing) {
		for(Material mat : materialsListing)
			if(material == mat)
				return true;

		return false;
	}

	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose) {
		removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, materialsListing, silk, fortune, blockHardness, dispose, true);
	}

	public static boolean canHarvestBlock(Block block, EntityPlayer player, int metadata, int x, int y, int z)
	{
		if (block == null) return false;
		if (block.blockMaterial.isToolNotRequired()) {
			return true;
		}

		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null) {
			return player.canHarvestBlock(block, x, y, z);
		}

		if (stack.getItem() instanceof ToolItem item) {
			int toolLevel = item.toolMaterial.getHarvestLevel();
			if (toolLevel < 0) {
				return player.canHarvestBlock(block, x, y, z);
			}
			return toolLevel >= block.getHarvestToolLevel(player.worldObj, x, y, z);
		}
		return false;
	}

	public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose, boolean particles) {
		if(!world.blockExists(x, y, z))
			return;

		Block blk = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if(block != null && blk != block)
			return;
		if (blk == null) return;
		Material mat = blk.blockMaterial;
		if(mat != null && !world.isRemote && !blk.isAir(world, x, y, z) && blk.getPlayerRelativeBlockHardness(player, world, x, y, z) > 0) {
			if(!canHarvestBlock(block, player, meta, x, y, z) || !isRightMaterial(mat, materialsListing))
				return;

			if(!player.capabilities.isCreativeMode) {
				int localMeta = world.getBlockMetadata(x, y, z);
				blk.onBlockHarvested(world, x, y, z, localMeta, player);

				if(world.setBlockToAir(x, y, z)/*blk.removedByPlayer(world, player, x, y, z, true)*/) {
					blk.onBlockDestroyedByPlayer(world, x, y, z, localMeta);

					if(!dispose || !ItemElementiumPick.isDisposable(blk))
						blk.harvestBlock(world, player, x, y, z, localMeta);
				}

				damageItem(stack, 1, player, 80);
			} else world.setBlockToAir(x, y, z);

			if(particles && !world.isRemote && ConfigHandler.blockBreakParticles && ConfigHandler.blockBreakParticlesTool)
				world.playAuxSFX(2001, x, y, z, BlockExtensions.getIdFromBlock(blk) + (meta << 12));
		}
	}

	public static int getToolPriority(ItemStack stack) {
		if(stack == null)
			return 0;

		Item item = stack.getItem();
		EnumToolMaterial material;
		if(item instanceof ItemTool tool)
			material = tool.getToolMaterial();
		else if (item instanceof ToolItem tool)
			material = tool.toolMaterial;
		else
			return 0;


		int materialLevel = 0;
		if(material == BotaniaAPI.manasteelToolMaterial)
			materialLevel = 10;
		if(material == BotaniaAPI.elementiumToolMaterial)
			materialLevel = 11;
		if(material == BotaniaAPI.terrasteelToolMaterial)
			materialLevel = 20;

		int modifier = 0;
		if(item == ModItems.terraPick)
			modifier = ItemTerraPick.getLevel(stack);

		int efficiency = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
		return materialLevel * 100 + modifier * 10 + efficiency;
	}

	/**
	 * @author mDiyo
	 */
	public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean hitFluidSources, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += player.getEyeHeight();
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * range, f6 * range, f8 * range);
		return world.rayTraceBlocks_do_do(vec3, vec31, hitFluidSources, false);
	}

}
