package baubles.common.event;

import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.fabricmc.api.EnvType;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.Botania;

public class EventHandlerNetwork {

	public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event)    {
		EnvType side = Botania.instance.getEffectiveSide();
		if (side == EnvType.SERVER) {
			// Apply all baubles
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(event.player);
			for (int i = 0; i < baubles.getSizeInventory(); i++) {
				ItemStack stack = baubles.getStackInSlot(i);
				PacketHandler.INSTANCE.sendToAll(new PacketSyncBauble(event.player, i, true));
				if (stack != null && stack.getItem() instanceof IBauble itemBauble) {
					itemBauble.onPlayerLoad(stack, event.player);
				}
			}
		}
	}

	public static void syncBaubles(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		for (int i = 0; i < baubles.getSizeInventory(); i++) {
			baubles.syncSlotToClients(i);
		}
	}
}
