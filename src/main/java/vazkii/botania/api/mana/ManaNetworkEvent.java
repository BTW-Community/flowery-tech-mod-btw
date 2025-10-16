/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 22, 2014, 5:04:30 PM (GMT)]
 */
package vazkii.botania.api.mana;

import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;

public class ManaNetworkEvent extends Event {

	public static final net.legacyfabric.fabric.api.event.Event<ManaNetworkEventCallback> EVENT = EventFactory.createArrayBacked(ManaNetworkEventCallback.class, (listeners) -> (event) -> {
		for (ManaNetworkEventCallback callback : listeners) {
			callback.onManaNetworkEvent(event);
		}
	});

	public final TileEntity tile;
	public final ManaBlockType type;
	public final Action action;

	public ManaNetworkEvent(TileEntity tile, ManaBlockType type, Action action) {
		this.tile = tile;
		this.type = type;
		this.action = action;
	}

	public static void addCollector(TileEntity tile) {
		ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.COLLECTOR, Action.ADD);
		EVENT.invoker().onManaNetworkEvent(event);
	}

	public static void removeCollector(TileEntity tile) {
		ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.COLLECTOR, Action.REMOVE);
		EVENT.invoker().onManaNetworkEvent(event);
	}

	public static void addPool(TileEntity tile) {
		ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.POOL, Action.ADD);
		EVENT.invoker().onManaNetworkEvent(event);
	}

	public static void removePool(TileEntity tile) {
		ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.POOL, Action.REMOVE);
		EVENT.invoker().onManaNetworkEvent(event);
	}

	public enum ManaBlockType {
		POOL, COLLECTOR
	}

	public enum Action {
		REMOVE, ADD
	}

	@FunctionalInterface
	public interface ManaNetworkEventCallback {
		public void onManaNetworkEvent(ManaNetworkEvent event);
	}
}
