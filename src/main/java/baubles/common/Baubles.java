package baubles.common;

import baubles.common.event.EventHandlerEntity;
import baubles.common.event.EventHandlerNetwork;
import baubles.common.network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.src.Item;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
//todobaubles verify entrypoint
public class Baubles {

    public static final String MODID = "Baubles";
    public static final String MODNAME = "Baubles";

//    @SidedProxy(clientSide = "baubles.client.ClientProxy", serverSide = "baubles.common.CommonProxy")
    public static CommonProxy proxy = new CommonProxy();

    public static Baubles instance = new Baubles();

    public EventHandlerEntity entityEventHandler;
    public EventHandlerNetwork entityEventNetwork;

    public static final Logger log = LogManager.getLogger("Baubles");
    public static final int GUI = 0;

    public static final Item itemDebugger = new ItemDebugger(1000).setUnlocalizedName("baubleSlotDebugTool");

//    FMLPreInitializationEvent event
//    @SubscribeEvent
    public void preInit() {

        PacketHandler.init();

        entityEventHandler = new EventHandlerEntity();
        entityEventNetwork = new EventHandlerNetwork();

        MinecraftForge.EVENT_BUS.register(entityEventHandler);
//        FMLCommonHandler.instance().bus().register(entityEventNetwork);
        proxy.registerHandlers();
    }

    public void init() {
        //This config is intentionally loaded later than normal.
//        BaublesConfig.loadConfig(new Configuration(new File(Launch.minecraftHome, "config" + File.separator + "Baubles.cfg")));

//        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
          proxy.registerKeyBindings();
//          GameRegistry.registerItem(itemDebugger, "bauble_slot_debug_tool", Baubles.MODID);
    }

}
