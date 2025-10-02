package baubles.common.event;

import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
//todobaubles network handling
public class EventHandlerNetwork {

	//import cpw.mods.fml.common.gameevent.PlayerEvent;
	@SubscribeEvent
	public void playerLoggedInEvent(/*PlayerEvent.PlayerLoggedInEvent event*/)    {
		/*Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)        {
			// Apply all baubles
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(event.player);
			for (int i = 0; i < baubles.getSizeInventory(); i++) {
				ItemStack stack = baubles.getStackInSlot(i);
				PacketHandler.INSTANCE.sendToAll(new PacketSyncBauble(event.player, i, true));
				if (stack != null && stack.getItem() instanceof IBauble itemBauble) {
					itemBauble.onPlayerLoad(stack, event.player);
				}
			}
		}*/
	}

	public static void syncBaubles(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		for (int i = 0; i < baubles.getSizeInventory(); i++) {
			baubles.syncSlotToClients(i);
		}
	}


}
