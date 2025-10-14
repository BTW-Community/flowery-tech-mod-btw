
package baubles.client;

import baubles.client.gui.GuiEvents;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.Baubles;
import baubles.common.CommonProxy;
import baubles.common.event.KeyHandler;
import net.minecraft.src.Minecraft;
import net.minecraft.src.WorldClient;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerHandlers() {}
	
	@Override
	public void registerKeyBindings() {
		keyHandler = new KeyHandler();
//		FMLCommonHandler.instance().bus().register(keyHandler);
		MinecraftForge.EVENT_BUS.register(new GuiEvents());
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world instanceof WorldClient) {
			switch (ID) {
				case Baubles.GUI: return new GuiPlayerExpanded(player);
			}
		}
		return null;
	}
				
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
		
}
