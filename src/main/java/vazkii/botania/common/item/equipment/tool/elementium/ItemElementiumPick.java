package vazkii.botania.common.item.equipment.tool.elementium;

import java.util.Arrays;
import java.util.List;

import dev.bagel.util.Blocks;
import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;
import vazkii.botania.common.lib.LibItemNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemElementiumPick extends ItemManasteelPick {

	public ItemElementiumPick(int id) {
		super(id, EnumToolMaterial.EMERALD/*BotaniaAPI.elementiumToolMaterial*/, LibItemNames.ELEMENTIUM_PICK);
		BlockEvent.HarvestDropsEvent.EVENT.register(this::onHarvestDrops);
	}

//	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event) {
		if(event.harvester != null) {
			ItemStack stack = event.harvester.getCurrentEquippedItem();
			if(stack != null && (stack.getItem() == this || stack.getItem() == ModItems.terraPick && ItemTerraPick.isTipped(stack))) {
				for(int i = 0; i < event.drops.size(); i++) {
					ItemStack drop = event.drops.get(i);
					if(drop != null) {
						Block block = Blocks.getBlockFromItem(drop.getItem());
						if(block != null){
							if(isDisposable(block) || (isSemiDisposable(block) && !event.harvester.isSneaking()))
								event.drops.remove(i);
						}
					}
				}
			}
		}
	}
	//todo easy, disposable item tag
	public static boolean isDisposable(Block block) {
//		for(int id : OreDictionary.getOreIDs(new ItemStack(block))) {
//			String name = OreDictionary.getOreName(id);
//			if(BotaniaAPI.disposableBlocks.contains(name))
//				return true;
//		}
		return false;
	}
	
	public static boolean isSemiDisposable(Block block) {
//		for(int id : OreDictionary.getOreIDs(new ItemStack(block))) {
//			String name = OreDictionary.getOreName(id);
//			if(BotaniaAPI.semiDisposableBlocks.contains(name))
//				return true;
//		}
		return false;
	}
}
