package baubles.common.event;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    public static KeyHandler INSTANCE = new KeyHandler();

	public KeyBinding key = new KeyBinding(StatCollector.translateToLocal("keybind.baublesinventory"),
			Keyboard.KEY_B/*, "key.categories.inventory"*/);

	public KeyHandler() {
        KeyBindingHelper.registerKeyBinding(key);
	}

	public void onKeyEvent() {
        boolean pressed = true;
        while (key.isPressed()) {
            if (pressed) {
                PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
                pressed = false;
            }
        }
	}
}

