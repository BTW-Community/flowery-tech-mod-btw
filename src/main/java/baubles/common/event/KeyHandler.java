package baubles.common.event;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;
import org.lwjgl.input.Keyboard;
//todobaubles keybinds
public class KeyHandler {

	public KeyBinding key = new KeyBinding(StatCollector.translateToLocal("keybind.baublesinventory"),
			Keyboard.KEY_NONE/*, "key.categories.inventory"*/);

	public KeyHandler() {
//		 ClientRegistry.registerKeyBinding(key);
	}

	@SubscribeEvent
	public void onKeyEvent(/*InputEvent.KeyInputEvent event*/) {
/*		if (key.getIsKeyPressed()) {
			PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
		}*/
	}
}

