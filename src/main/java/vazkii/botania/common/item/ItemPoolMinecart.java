/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 17, 2015, 6:48:29 PM (GMT)]
 */
package vazkii.botania.common.item;

import net.minecraft.src.BlockRailBase;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import net.minecraft.src.World;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityPoolMinecart;
import vazkii.botania.common.lib.LibItemNames;


public class ItemPoolMinecart extends ItemMod implements ICraftAchievement {

	public ItemPoolMinecart(int id) {
        super(id);
        setMaxStackSize(1);
		setUnlocalizedName(LibItemNames.POOL_MINECART);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float pX, float pY, float pZ) {
		if(BlockRailBase.isRailBlock(world.getBlockId(x, y, z))) {
			if(!world.isRemote) {
				EntityMinecart entityminecart = new EntityPoolMinecart(world, x + 0.5, y + 0.5, z + 0.5);

				if(stack.hasDisplayName())
					entityminecart.setMinecartName(stack.getDisplayName());

				world.spawnEntityInWorld(entityminecart);
			}

			--stack.stackSize;
			return true;
		}

		return false;
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return ModAchievements.MANA_CART_CRAFT;
	}
//
//	@Override
//	public boolean canBePlacedByNonPlayer(ItemStack cart) {
//		return true;
//	}
//
//	@Override
//	public EntityMinecart placeCart(GameProfile owner, ItemStack cart, World world, int i, int j, int k) {
//		if(BlockRailBase.func_150051_a(world.getBlock(i, j, k))) {
//			if(!world.isRemote) {
//				EntityMinecart entityminecart = new EntityPoolMinecart(world, i + 0.5,j + 0.5, k + 0.5);
//
//				if(cart.hasDisplayName())
//					entityminecart.setMinecartName(cart.getDisplayName());
//
//				if(world.spawnEntityInWorld(entityminecart))
//					return entityminecart;
//			}
//		}
//		return null;
//	}

}
