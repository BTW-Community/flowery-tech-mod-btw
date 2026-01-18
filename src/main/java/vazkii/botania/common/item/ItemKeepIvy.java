/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 31, 2015, 9:11:23 PM (GMT)]
 */
package vazkii.botania.common.item;

import java.util.ArrayList;
import java.util.List;

import dev.bagel.util.Persisted;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.KeepIvyRecipe;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;


public class ItemKeepIvy extends ItemMod {

	public static final String TAG_KEEP = "Botania_keepIvy";

	private static final String TAG_PLAYER_KEPT_DROPS = "Botania_playerKeptDrops";
	private static final String TAG_DROP_COUNT = "dropCount";
	private static final String TAG_DROP_PREFIX = "dropPrefix";

	public ItemKeepIvy(int id) {
        super(id);
        setUnlocalizedName(LibItemNames.KEEP_IVY);
		CraftingManager.getInstance().getRecipeList().add(new KeepIvyRecipe(Botania.loc("resolute_ivy")));
//		RecipeSorter.register("botania:keepIvy", KeepIvyRecipe.class, Category.SHAPELESS, "");
		MinecraftForge.EVENT_BUS.register(this);
//		FMLCommonHandler.instance().bus().register(this);
		PlayerDropsEvent.PLAYER_DROPS.register(this::onPlayerDrops);
		PlayerRespawnEvent.EVENT.register(this::onPlayerRespawn);
	}

//	@SubscribeEvent
	public void onPlayerDrops(PlayerDropsEvent event) {
		List<EntityItem> keeps = new ArrayList<>();
		for(EntityItem item : event.drops) {
			ItemStack stack = item.getEntityItem();
			if(stack != null && ItemNBTHelper.detectNBT(stack) && ItemNBTHelper.getBoolean(stack, TAG_KEEP, false))
				keeps.add(item);
		}

		if(!keeps.isEmpty()) {
			event.drops.removeAll(keeps);


			NBTTagCompound cmp = new NBTTagCompound();
			cmp.setInteger(TAG_DROP_COUNT, keeps.size());

			int i = 0;
			for(EntityItem keep : keeps) {
				ItemStack stack = keep.getEntityItem();
				NBTTagCompound cmp1 = new NBTTagCompound();
				stack.writeToNBT(cmp1);
				cmp.setTag(TAG_DROP_PREFIX + i, cmp1);
				i++;
			}

			NBTTagCompound data = event.entityPlayer.getEntityData();
			if(!data.hasKey(Persisted.PERSISTED_NBT_TAG))
				data.setTag(Persisted.PERSISTED_NBT_TAG, new NBTTagCompound());

			NBTTagCompound persist = data.getCompoundTag(Persisted.PERSISTED_NBT_TAG);
			persist.setTag(TAG_PLAYER_KEPT_DROPS, cmp);
		}
	}

//	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		NBTTagCompound data = event.player.getEntityData();
		if(data.hasKey(Persisted.PERSISTED_NBT_TAG)) {
			NBTTagCompound cmp = data.getCompoundTag(Persisted.PERSISTED_NBT_TAG);
			NBTTagCompound cmp1 = cmp.getCompoundTag(TAG_PLAYER_KEPT_DROPS);

			int count = cmp1.getInteger(TAG_DROP_COUNT);
			for(int i = 0; i < count; i++) {
				NBTTagCompound cmp2 = cmp1.getCompoundTag(TAG_DROP_PREFIX + i);
				ItemStack stack = ItemStack.loadItemStackFromNBT(cmp2);
				if(stack != null) {
					ItemStack copy = stack.copy();
					ItemNBTHelper.setBoolean(copy, TAG_KEEP, false);
					event.player.inventory.addItemStackToInventory(copy);
				}
			}

			cmp.setTag(TAG_PLAYER_KEPT_DROPS, new NBTTagCompound());
		}
	}

}
