/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 13, 2014, 7:28:35 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import java.util.ArrayList;
import java.util.Random;

import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.*;
import net.minecraftforge.common.IShearable;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.lib.LibItemNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class ItemManasteelShears extends ItemShears implements IManaUsingItem {

	public static final int MANA_PER_DAMAGE = 30;

	public ItemManasteelShears(int id) {
		this(id, LibItemNames.MANASTEEL_SHEARS);
	}

	public ItemManasteelShears(int id, String name) {
        super(id);
        setCreativeTab(ModItems.botaniaTab);
		setUnlocalizedName(name);
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
//		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item.", "item." + LibResources.PREFIX_MOD);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = IconHelper.forItem(par1IconRegister, this);
	}

	//todofix manasteel shears
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
		if(entity.worldObj.isRemote)
			return false;

//		if(entity instanceof IShearable) {
//			IShearable target = (IShearable)entity;
//			if(target.isShearable(itemstack, entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ)) {
//				ArrayList<ItemStack> drops = target.onSheared(itemstack, entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
//
//				Random rand = new Random();
//				for(ItemStack stack : drops) {
//					EntityItem ent = entity.entityDropItem(stack, 1.0F);
//					ent.motionY += rand.nextFloat() * 0.05F;
//					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
//					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
//				}
//
//				ToolCommons.damageItem(itemstack, 1, player, MANA_PER_DAMAGE);
//			}
//
//			return true;
//		}

		return false;
	}


	@Override
	public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6) {
		if (super.isEfficientVsBlock(var1, var2, var3, var4, var5, var6)) {
			return super.getStrVsBlock(var1, var2, var3, var4, var5, var6) * 1.33f;
		}
		return super.getStrVsBlock(var1, var2, var3, var4, var5, var6);
	}

	@Override
	public boolean isConsumedInCrafting() {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return EnumToolMaterial.EMERALD.MANASTEEL().getEnchantability();
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
		if (player.worldObj.isRemote)
			return false;

		Block block = player.worldObj.getBlock(x, y, z);
		if(block instanceof IShearable) {
			IShearable target = (IShearable)block;
			if(target.isShearable(itemstack, player.worldObj, x, y, z)) {
				ArrayList<ItemStack> drops = target.onSheared(itemstack, player.worldObj, x, y, z, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
				Random rand = new Random();

				for(ItemStack stack : drops) {
					float f = 0.7F;
					double d  = rand.nextFloat() * f + (1D - f) * 0.5;
					double d1 = rand.nextFloat() * f + (1D - f) * 0.5;
					double d2 = rand.nextFloat() * f + (1D - f) * 0.5;

					EntityItem entityitem = new EntityItem(player.worldObj, x + d, y + d1, z + d2, stack);
					entityitem.delayBeforeCanPickup = 10;
					player.worldObj.spawnEntityInWorld(entityitem);
				}

				ToolCommons.damageItem(itemstack, 1, player, MANA_PER_DAMAGE);
				player.addStat(StatList.mineBlockStatArray[BlockExtensions.getIdFromBlock(block)], 1);
			}
		}

		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, EntityPlayer player, int par4, boolean par5) {
		if(!world.isRemote && stack.getItemDamage() > 0 && ManaItemHandler.requestManaExactForTool(stack, player, MANA_PER_DAMAGE * 2, true))
			stack.setItemDamage(stack.getItemDamage() - 1);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == ModItems.manaResource && par2ItemStack.getItemDamage() == 0 ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}

	@Override
	public String getModId() {
		return "botania";
	}
}
