package baubles.client.gui;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import baubles.common.network.PacketOpenNormalInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.I18n;
import net.minecraft.src.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.lang.reflect.Method;

import static baubles.common.BaublesConfig.useOldGuiButton;

public class GuiEvents {

	@Environment(EnvType.CLIENT)
	@SubscribeEvent
	public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!(event.gui instanceof GuiInventory) && !(event.gui instanceof GuiPlayerExpanded)) {
            return;
        }

        int xSize = 176;
        int ySize = 166;

        int guiLeft = (event.gui.width - xSize) / 2;
        int guiTop = (event.gui.height - ySize) / 2;

        if (!Minecraft.getMinecraft().thePlayer.getActivePotionEffects().isEmpty() && isNeiHidden()) {
            guiLeft = 160 + (event.gui.width - xSize - 200) / 2;
        }

        String tooltip = I18n.getString((event.gui instanceof GuiInventory) ? "button.baubles" : "button.normal");
        if (useOldGuiButton) {
            event.buttonList.add(new GuiBaublesButton(55, guiLeft + 66, guiTop + 9, 10, 10,
                tooltip));
        } else {
            event.buttonList.add(new GuiBaublesButton(55, guiLeft + 26, guiTop + 9, 10, 10,
                tooltip));
        }

    }

	@Environment(value = EnvType.CLIENT)
	@SubscribeEvent
	public void guiPostAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
		//todobaubles open gui packet
		if (event.gui instanceof GuiInventory) {
			if (event.button.id == 55) {
				PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory(event.gui.mc.thePlayer));
			}
		}

		if (event.gui instanceof GuiPlayerExpanded) {
			if (event.button.id == 55) {
				event.gui.mc.displayGuiScreen(new GuiInventory(event.gui.mc.thePlayer));
				PacketHandler.INSTANCE.sendToServer(new PacketOpenNormalInventory(event.gui.mc.thePlayer));
			}
		}
	}

	static Method isNEIHidden;
	boolean isNeiHidden() {
		boolean hidden = true;
		try {
			if (isNEIHidden == null) {
				Class<?> fake = Class.forName("codechicken.nei.NEIClientConfig");
			    isNEIHidden = fake.getMethod("isHidden");
			}
			hidden = (Boolean) isNEIHidden.invoke(null);
	    } catch (Exception ignored) {}
		return hidden;
	}
}
