/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 30, 2014, 4:49:16 PM (GMT)]
 */
package vazkii.botania.common.item.material;

import java.awt.Color;
import java.util.List;

import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import net.minecraft.src.Icon;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.IPickupAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class ItemManaResource extends ItemMod implements IFlowerComponent, IElvenItem, IPickupAchievement {

	final int types = 24;
	Icon[] icons;

	// begin dank_memes
	public Icon tailIcon = null;
	public Icon phiFlowerIcon = null;
	public Icon goldfishIcon = null;
	public Icon nerfBatIcon = null;
	// end dank_memes

	public ItemManaResource(int id) {
		super(id);
		setUnlocalizedName(LibItemNames.MANA_RESOURCE);
		setHasSubtypes(true);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		boolean rightEvent = event.action == Action.RIGHT_CLICK_AIR;
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		boolean correctStack = stack != null && stack.getItem() == Item.glassBottle;
		boolean ender = event.world.provider.dimensionId == 1;

		if(rightEvent && correctStack && ender) {
			MovingObjectPosition pos = ToolCommons.raytraceFromEntity(event.world, event.entityPlayer, false, 5F);

			if(pos == null) {
				ItemStack stack1 = new ItemStack(this, 1, 15);
				event.entityPlayer.addStat(ModAchievements.enderAirMake, 1);

				if(!event.entityPlayer.inventory.addItemStackToInventory(stack1))
					event.entityPlayer.dropPlayerItemWithRandomChoice(stack1, true);

				stack.stackSize--;
				if(stack.stackSize == 0)
					event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, null);

				if(event.world.isRemote)
					event.entityPlayer.swingItem();
				else event.world.playSoundAtEntity(event.entityPlayer, "random.pop", 0.5F, 1F);
			}
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par1ItemStack.getItemDamage() == 4 || par1ItemStack.getItemDamage() == 14)
			return EntityDoppleganger.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, par1ItemStack.getItemDamage() == 14);
//todofix bonemealing
//		else if(par1ItemStack.getItemDamage() == 20 && ((net.minecraft.src.ItemDye) Item.dyePowder).applyBonemeal(par3World, par4, par5, par6)) {
//			if(!par3World.isRemote)
//				par3World.playAuxSFX(2005, par4, par5, par6, 0);
//
//			return true;
//		}

		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par3World, EntityPlayer par2EntityPlayer) {
		if(par1ItemStack.getItemDamage() == 15) {
			if(!par2EntityPlayer.capabilities.isCreativeMode)
				--par1ItemStack.stackSize;

			par3World.playSoundAtEntity(par2EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if(!par3World.isRemote)
				par3World.spawnEntityInWorld(new EntityEnderAirBottle(par3World, par2EntityPlayer));
			else par2EntityPlayer.swingItem();
		}

		return par1ItemStack;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void getSubItems(int par1,  CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < types; i++)
			if(Botania.gardenOfGlassLoaded || i != 20 && i != 21)
				par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[types];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forName(par1IconRegister, LibItemNames.MANA_RESOURCE_NAMES[i]);

		tailIcon = IconHelper.forName(par1IconRegister, "tail");
		phiFlowerIcon = IconHelper.forName(par1IconRegister, "phiFlower");
		goldfishIcon = IconHelper.forName(par1IconRegister, "goldfish");
		nerfBatIcon = IconHelper.forName(par1IconRegister, "nerfBat");
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par1ItemStack.getItemDamage() == 5 || par1ItemStack.getItemDamage() == 14)
			return Color.HSBtoRGB(Botania.proxy.getWorldElapsedTicks() * 2 % 360 / 360F, 0.25F, 1F);

		return 0xFFFFFF;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "item." + LibItemNames.MANA_RESOURCE_NAMES[Math.min(types - 1, par1ItemStack.getItemDamage())];
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}

	@Override
	public boolean canFit(ItemStack stack, IInventory apothecary) {
		int meta = stack.getItemDamage();
		return meta == 6 || meta == 8 || meta == 5 || meta == 23;
	}

	@Override
	public int getParticleColor(ItemStack stack) {
		return 0x9b0000;
	}

	@Override
	public boolean isElvenItem(ItemStack stack) {
		int meta = stack.getItemDamage();
		return meta == 7 || meta == 8 || meta == 9;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return itemStack.getItemDamage() == 11 ? itemStack.copy() : null;
	}

	@Override
	public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
		return stack.getItemDamage() == 4 ? ModAchievements.terrasteelPickup : null;
	}

}
