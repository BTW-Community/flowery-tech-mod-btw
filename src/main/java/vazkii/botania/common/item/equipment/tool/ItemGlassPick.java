/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Aug 6, 2014, 9:55:23 PM (GMT)]
 */
package vazkii.botania.common.item.equipment.tool;

import dev.bagel.util.Items;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemGlassPick extends ItemManasteelPick {

	private static final int MANA_PER_DAMAGE = 160;
	//todofix Another enum tool material thing
	private static final EnumToolMaterial MATERIAL = EnumToolMaterial.EMERALD /*EnumHelper.addToolMaterial("MANASTEEL_GLASS", 0, 125, 4.8F, 1F, 10)*/;

	public ItemGlassPick(int id) {
		super(id, MATERIAL, LibItemNames.GLASS_PICK);
		MinecraftForge.EVENT_BUS.register(this);
		BlockEvent.HarvestDropsEvent.EVENT.register(this::onBlockDrops);
	}

//	@SubscribeEvent
	public void onBlockDrops(HarvestDropsEvent event) {
		if(event.harvester != null && event.block != null && event.drops.isEmpty() && event.harvester.getCurrentEquippedItem() != null && event.harvester.getCurrentEquippedItem().getItem() == this && event.block.blockMaterial == Material.glass /*&& event.block.canSilkHarvest(event.world, event.harvester, event.x, event.y, event.z, event.blockMetadata)*/)
			event.drops.add(new ItemStack(event.block, 1, event.blockMetadata));
	}

	@Override
	public int getManaPerDmg() {
		return MANA_PER_DAMAGE;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Items.getItemFromBlock(Block.glass) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public int getSortingPriority(ItemStack stack) {
		return 0;
	}

}
