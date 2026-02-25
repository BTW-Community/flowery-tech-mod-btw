package vazkii.botania.common.core.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;
import vazkii.botania.common.item.equipment.bauble.ItemMagnetRing;
import vazkii.botania.common.lib.LibMisc;

import com.google.common.collect.ImmutableList;

public final class IMCHandler {

	public static void setupIMC() {
		FabricLoader.getInstance().getObjectShare().put(LibMisc.BLACKLIST_ITEM, new ArrayList<String>());
	}

	public static void processMessages() {
		if (FabricLoader.getInstance().getObjectShare().get(LibMisc.BLACKLIST_ITEM) instanceof List<?> blackListedItems) {
			for (Object blackListedItemObj : blackListedItems){
				if (blackListedItemObj instanceof String blackListedItem) {
					ItemMagnetRing.addItemToBlackList(blackListedItem);
				}
			}

		}
	}
}
