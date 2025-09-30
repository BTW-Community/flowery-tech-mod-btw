/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 25, 2015, 6:42:47 PM (GMT)]
 */
package vazkii.botania.common.item;
import java.util.List;

import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Achievement;
import net.minecraft.src.Icon;
import net.minecraft.src.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.lib.LibItemNames;

public class ItemThornChakram extends ItemMod implements ICraftAchievement {

	Icon iconFire;

	public ItemThornChakram() {
		setUnlocalizedName(LibItemNames.THORN_CHAKRAM);
		setMaxStackSize(6);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 2; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = IconHelper.forItem(par1IconRegister, this, 0);
		iconFire = IconHelper.forItem(par1IconRegister, this, 1);
	}

	@Override
	public Icon getIconFromDamage(int dmg) {
		return dmg == 0 ? itemIcon : iconFire;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + stack.getItemDamage();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)  {
		--p_77659_1_.stackSize;

		p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if(!p_77659_2_.isRemote) {
			EntityThornChakram c = new EntityThornChakram(p_77659_2_, p_77659_3_);
			c.setFire(p_77659_1_.getItemDamage() != 0);
			p_77659_2_.spawnEntityInWorld(c);
		}


		return p_77659_1_;
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return ModAchievements.terrasteelWeaponCraft;
	}

}
