package vazkii.botania.common.core.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.src.Item;
import vazkii.botania.common.item.equipment.bauble.ItemMagnetRing;
import vazkii.botania.common.lib.LibMisc;

public final class IMCHandler {

	/** An example implementation for adding new items to the magnet blacklist */
	public static void addItemsToBlackList(Item... itemsToAdd) {
		FabricLoader.getInstance().getObjectShare().whenAvailable("botania:itemMagnetBlacklist", (s, itemsObj) -> {
			if (itemsObj instanceof List<?> listObj) {
				//Need to cast twice due to type erasure
				List<Item> items = (List<Item>) listObj;
				items.addAll(Arrays.asList(itemsToAdd));
			}
		});
	}

	public static void setupIMC() {
		FabricLoader.getInstance().getObjectShare().put(LibMisc.ITEM_MAGNET_BLACKLIST, new ArrayList<Item>());
	}

	public static void processMessages() {
		if (FabricLoader.getInstance().getObjectShare().get(LibMisc.ITEM_MAGNET_BLACKLIST) instanceof List<?> blackListedItems) {
			for (Object blackListedItemObj : blackListedItems){
				if (blackListedItemObj instanceof Item blackListedItem) {
					ItemMagnetRing.addItemToBlackList(blackListedItem);
				}
			}

		}
	}
}
