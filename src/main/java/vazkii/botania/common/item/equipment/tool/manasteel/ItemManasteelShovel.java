/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Apr 13, 2014, 7:14:54 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import btw.block.BTWBlocks;
import api.item.items.ShovelItem;
import net.minecraft.src.*;
import net.minecraft.src.Block;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.ISortableTool;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.lib.LibItemNames;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class ItemManasteelShovel extends ShovelItem implements IManaUsingItem, ISortableTool {

	private static final int MANA_PER_DAMAGE = 60;

	public ItemManasteelShovel(int id) {
		this(id, BotaniaAPI.manasteelToolMaterial, LibItemNames.MANASTEEL_SHOVEL);
	}

	public ItemManasteelShovel(int id, EnumToolMaterial mat, String name) {
		super(id, mat);
		setCreativeTab(CreativeTabs.tabMisc);
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

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		ToolCommons.damageItem(par1ItemStack, 1, par3EntityLivingBase, MANA_PER_DAMAGE);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int block, int x, int y, int z, EntityLivingBase entity) {
		if (Block.blocksList[block].getBlockHardness(world, x, y, z) != 0F)
			ToolCommons.damageItem(stack, 1, entity, MANA_PER_DAMAGE);

		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if(!player.canPlayerEdit(x, y, z, p_77648_7_, stack))
			return false;
		else {
//			UseHoeEvent event = new UseHoeEvent(p_77648_2_, p_77648_1_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_);
//			if(MinecraftForge.EVENT_BUS.post(event))
//				return false;
//
//			if(event.getResult() == Result.ALLOW) {
//				ToolCommons.damageItem(p_77648_1_, 1, p_77648_2_, MANA_PER_DAMAGE);
//				return true;
//			}
			ToolCommons.damageItem(stack, 1, player, MANA_PER_DAMAGE);

			Block block = world.getBlock(x, y, z);

			if(p_77648_7_ != 0 && world.isAirBlock(x, y + 1, z) && (block == Block.grass || block == Block.dirt || block == BTWBlocks.looseDirt || block == BTWBlocks.looseSparseGrass)) {
				Block block1 = Block.tilledField;
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block1.stepSound.getStepSound(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

				if (world.isRemote)
					return true;
				else {
					world.setBlock(x, y, z, block1);
					ToolCommons.damageItem(stack, 1, player, MANA_PER_DAMAGE);
					return true;
				}
			}

			return false;
		}
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
	public ToolType getSortingType(ItemStack stack) {
		return ToolType.SHOVEL;
	}

	@Override
	public int getSortingPriority(ItemStack stack) {
		return ToolCommons.getToolPriority(stack);
	}

	@Override
	public String getModId() {
		return "botania";
	}
}
