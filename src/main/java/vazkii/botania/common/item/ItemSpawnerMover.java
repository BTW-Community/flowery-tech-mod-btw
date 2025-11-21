/**
 * This class was created by <PowerCrystals>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [? (GMT)]
 */
package vazkii.botania.common.item;

import java.util.List;

import btw.achievement.event.AchievementEventDispatcher;
import btw.achievement.event.BTWAchievementEvents;
import net.minecraft.src.Block;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Icon;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.lib.LibItemNames;

public class ItemSpawnerMover extends ItemMod {

	public static final String TAG_SPAWNER = "spawner";
	private static final String TAG_PLACE_DELAY = "placeDelay";

	Icon iconNormal, iconSpawner;

	public ItemSpawnerMover(int id) {
        super(id);
        setUnlocalizedName(LibItemNames.SPAWNER_MOVER);
		setMaxStackSize(1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		iconNormal = IconHelper.forItem(par1IconRegister, this, 0);
		iconSpawner = IconHelper.forItem(par1IconRegister, this, 1);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		return getIconIndex(stack);
	}

	@Override
	public Icon getIconIndex(ItemStack par1ItemStack) {
		return hasData(par1ItemStack) ? iconSpawner : iconNormal;
	}

	public static NBTTagCompound getSpawnerTag(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null) {
			if(tag.hasKey(TAG_SPAWNER))
				return tag.getCompoundTag(TAG_SPAWNER);
			if(tag.hasKey("EntityId"))
				return tag;
		}

		return null;
	}

	private static String getEntityId(ItemStack stack) {
		NBTTagCompound tag = getSpawnerTag(stack);
		if(tag != null)
			return tag.getString("EntityId");

		return null;
	}

	public static boolean hasData(ItemStack stack) {
		return getEntityId(stack) != null;
	}

	private static int getDelay(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null)
			return tag.getInteger(TAG_PLACE_DELAY);

		return 0;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips) {
		String id = getEntityId(stack);
		if (id != null)
			infoList.add(StatCollector.translateToLocal("entity." + id + ".name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, EntityPlayer entity, int slot, boolean isHeld) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null && tag.hasKey(TAG_PLACE_DELAY) && tag.getInteger(TAG_PLACE_DELAY) > 0)
			tag.setInteger(TAG_PLACE_DELAY, tag.getInteger(TAG_PLACE_DELAY) - 1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
		if(getEntityId(itemstack) == null) {
			if(Block.mobSpawner.equals(world.getBlock(x, y, z))) {
				TileEntity te = world.getTileEntity(x, y, z);
				NBTTagCompound tag = new NBTTagCompound();
				tag.setTag(TAG_SPAWNER, new NBTTagCompound());
				te.writeToNBT(tag.getCompoundTag(TAG_SPAWNER));
				tag.setInteger(TAG_PLACE_DELAY, 20);
				itemstack.setTagCompound(tag);
				world.setBlockToAir(x, y, z);
				player.renderBrokenItemStack(itemstack);
				for(int i = 0; i < 50; i++) {
					float red = (float) Math.random();
					float green = (float) Math.random();
					float blue = (float) Math.random();
					Botania.getProxy().wispFX(world, x + 0.5, y + 0.5, z + 0.5, red, green, blue, (float) Math.random() * 0.1F + 0.05F, (float) (Math.random() - 0.5F) * 0.15F, (float) (Math.random() - 0.5F) * 0.15F, (float) (Math.random() - 0.5F) * 0.15F);
				}
				return true;
			} else return false;
		} else {
			if(getDelay(itemstack) <= 0 && placeBlock(itemstack, player, world, x, y, z, side, xOffset, yOffset, zOffset))
				return true;
			return false;
		}
	}

	private boolean placeBlock(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
		Block block = world.getBlock(x, y, z);

		if(block == Block.snow)
			side = 1;
		else if(block != Block.vine && block != Block.tallGrass && block != Block.deadBush && !block.isReplaceableVegetation(world, x, y, z)) {
			switch (side) {
			case 0:
				--y;
				break;
			case 1:
				++y;
				break;
			case 2:
				--z;
				break;
			case 3:
				++z;
				break;
			case 4:
				--x;
				break;
			case 5:
				++x;
				break;
			}
		}

		if(itemstack.stackSize == 0)
			return false;
		else if(!player.canPlayerEdit(x, y, z, side, itemstack))
			return false;
		else if(y == 255 && block != null && block.blockMaterial.isSolid())
			return false;
		else if(world.canPlaceEntityOnSide(Block.mobSpawner.blockID, x, y, z, false, side, player, itemstack)) {
			int meta = block != null ? block.onBlockPlaced(world, x, y, z, side, xOffset, yOffset, zOffset, 0) : 0;

			if(placeBlockAt(itemstack, player, world, x, y, z, side, xOffset, yOffset, zOffset, meta)) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block != null ? block.stepSound.getBreakSound() : Block.mobSpawner.stepSound.getBreakSound(), (block != null ? block.stepSound.getVolume() : Block.mobSpawner.stepSound.getVolume() + 1.0F) / 2.0F, block != null ? block.stepSound.getPitch() : Block.mobSpawner.stepSound.getPitch() * 0.8F);
				player.renderBrokenItemStack(itemstack);
				AchievementEventDispatcher.triggerEvent(ModAchievements.SpawnerMoverEvent.class, player, new BTWAchievementEvents.None());
				for(int i = 0; i < 100; i++)
					Botania.getProxy().sparkleFX(world, x + Math.random(), y + Math.random(), z + Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random(), 0.45F + 0.2F * (float) Math.random(), 6);

				--itemstack.stackSize;
			}

			return true;
		}
		else return false;
	}

	private boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, Block.mobSpawner, metadata, 3))
			return false;

		Block block = world.getBlock(x, y, z);
		if(Block.mobSpawner.equals(block)) {
			TileEntity te = world.getTileEntity(x, y, z);
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.hasKey(TAG_SPAWNER))
				tag = tag.getCompoundTag(TAG_SPAWNER);
			tag.setInteger("x", x);
			tag.setInteger("y", y);
			tag.setInteger("z", z);
			te.readFromNBT(tag);
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
		}

		return true;
	}
}
