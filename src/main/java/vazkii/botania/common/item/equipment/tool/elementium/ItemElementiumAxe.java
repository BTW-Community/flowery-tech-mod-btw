package vazkii.botania.common.item.equipment.tool.elementium;

import java.util.Random;

import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelAxe;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemElementiumAxe extends ItemManasteelAxe {

	public ItemElementiumAxe(int id) {
		super(id, BotaniaAPI.elementiumToolMaterial, LibItemNames.ELEMENTIUM_AXE);
		MinecraftForge.EVENT_BUS.register(this);
	}

	// Thanks to SpitefulFox for the drop rates
	// https://github.com/SpitefulFox/ForbiddenMagic/blob/master/src/com/spiteful/forbidden/FMEventHandler.java

	@SubscribeEvent
	public void onEntityDrops(LivingDropsEvent event) {
		if(event.recentlyHit && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
			ItemStack weapon = ((EntityPlayer) event.source.getEntity()).getCurrentEquippedItem();
			if(weapon != null && weapon.getItem() == this) {
				Random rand = event.entity.worldObj.rand;
				int looting = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, weapon);

				if(event.entityLiving instanceof EntitySkeleton && rand.nextInt(26) <= 3 + looting)
					addDrop(event, new ItemStack(Item.skull, 1, ((EntitySkeleton)event.entityLiving).getSkeletonType().id()));
				else if(event.entityLiving instanceof EntityZombie && !(event.entityLiving instanceof EntityPigZombie) && rand.nextInt(26) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(Item.skull, 1, 2));
				else if(event.entityLiving instanceof EntityCreeper && rand.nextInt(26) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(Item.skull, 1, 4));
				else if(event.entityLiving instanceof EntityPlayer && rand.nextInt(11) <= 1 + looting) {
					ItemStack stack = new ItemStack(Item.skull, 1, 3);
					ItemNBTHelper.setString(stack, "SkullOwner", ((EntityPlayer)event.entityLiving).getCommandSenderName());
					addDrop(event, stack);
				} else if(event.entityLiving instanceof EntityDoppleganger && rand.nextInt(13) < 1 + looting)
					addDrop(event, new ItemStack(ModItems.gaiaHead));
			}
		}
	}

	private void addDrop(LivingDropsEvent event, ItemStack drop) {
		EntityItem entityitem = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, drop);
		entityitem.delayBeforeCanPickup = 10;
		event.drops.add(entityitem);
	}

}
