/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 3, 2014, 6:31:10 PM (GMT)]
 */
package vazkii.botania.common.item;

import net.minecraft.src.CraftingManager;
import net.minecraft.src.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.RegenIvyRecipe;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;


public class ItemRegenIvy extends ItemMod {

	public static final String TAG_REGEN = "Botania_regenIvy";
	private static final int MANA_PER_DAMAGE = 200;

	public ItemRegenIvy(int id) {
        super(id);
        setUnlocalizedName(LibItemNames.REGEN_IVY);
		CraftingManager.getInstance().getRecipeList().add(new RegenIvyRecipe());
//		RecipeSorter.register("botania:regenIvy", RegenIvyRecipe.class, Category.SHAPELESS, "");
//		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.END && !event.player.worldObj.isRemote)
			for(int i = 0; i < event.player.inventory.getSizeInventory(); i++) {
				ItemStack stack = event.player.inventory.getStackInSlot(i);
				if(stack != null && ItemNBTHelper.detectNBT(stack) && ItemNBTHelper.getBoolean(stack, TAG_REGEN, false) && stack.getItemDamage() > 0 && ManaItemHandler.requestManaExact(stack, event.player, MANA_PER_DAMAGE, true))
					stack.setItemDamage(stack.getItemDamage() - 1);
			}
	}
}
