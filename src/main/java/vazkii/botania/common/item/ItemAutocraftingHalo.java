/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 1, 2015, 2:23:26 AM (GMT)]
 */
package vazkii.botania.common.item;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.World;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.lib.LibItemNames;

public class ItemAutocraftingHalo extends ItemCraftingHalo {

	private static final ResourceLocation glowTexture = new ResourceLocation(LibResources.MISC_GLOW_CYAN);

	public ItemAutocraftingHalo(int id) {
		super(id, LibItemNames.AUTOCRAFTING_HALO);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, EntityPlayer entity, int pos, boolean equipped) {
		super.onUpdate(stack, world, entity, pos, equipped);

		if(entity instanceof EntityPlayer && !equipped) {
			EntityPlayer player = (EntityPlayer) entity;
			IInventory inv = getFakeInv(player);

			for(int i = 1; i < SEGMENTS; i++)
				tryCraft(player, stack, i, false, inv, false);
		}
	}

	@Override
	public ResourceLocation getGlowResource() {
		return glowTexture;
	}

}
