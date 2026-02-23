package baubles.common;

import api.config.AddonConfig;
import api.config.ConfigUtils;
import baubles.client.ClientProxy;
import baubles.common.event.EventHandlerEntity;
import baubles.common.event.EventHandlerNetwork;
import baubles.common.network.PacketHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import dev.bagel.network.CustomGuiPacketHandler;
import dev.bagel.util.GuiHandlerHolder;
import net.fabricmc.api.EnvType;
import net.minecraft.src.Item;
import net.minecraft.src.ServerListenThread;
import net.minecraft.src.ThreadMinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Baubles implements GuiHandlerHolder {

    public static final String MODID = "Baubles";
    public static final String MODNAME = "Baubles";

//    @SidedProxy(clientSide = "baubles.client.ClientProxy", serverSide = "baubles.common.CommonProxy")
    public static CommonProxy proxy = new CommonProxy();

    public static Baubles instance = new Baubles();

    public String modId = "baubles";

    public static CommonProxy getProxy() {
        if (instance.getEffectiveSide() == EnvType.SERVER) {
            return proxy;
        }
        else {
            return ClientProxy.instance;
        }
    }

    public EventHandlerEntity entityEventHandler;
    public EventHandlerNetwork entityEventNetwork;

    public static final Logger log = LogManager.getLogger("Baubles");
    public static final int GUI = 0;

    public static final Item itemDebugger = new ItemDebugger(2700).setUnlocalizedName("baubleSlotDebugTool");

//    FMLPreInitializationEvent event
//    @SubscribeEvent
    public void preInit() {

        PacketHandler.init();

        entityEventHandler = new EventHandlerEntity();
        entityEventNetwork = new EventHandlerNetwork();
        EventHandlerEntity.init();

        MinecraftForge.EVENT_BUS.register(entityEventHandler);
//        FMLCommonHandler.instance().bus().register(entityEventNetwork);
        getProxy().registerHandlers();
    }

    public void init() {
        CustomGuiPacketHandler.INSTANCE.modIdToHandler.put("baubles", getGuiHandler());
        //This config is intentionally loaded later than normal.
        var config = new AddonConfig("baubles");
        try {
            var field = ConfigUtils.class.getDeclaredField("hasFinishedLoading");
            field.setAccessible(true);
            field.set(null, Boolean.FALSE);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BaublesConfig.loadConfig(config);
        config.readAndWriteConfig();
        BaublesConfig.handleConfig(config);

        ConfigUtils.finishedLoading();
//        BaublesConfig.loadConfig(new Configuration(new File(Launch.minecraftHome, "config" + File.separator + "Baubles.cfg")));

//        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
          getProxy().registerKeyBindings();
//          GameRegistry.registerItem(itemDebugger, "bauble_slot_debug_tool", Baubles.MODID);
    }

    @Override
    public IGuiHandler getGuiHandler() {
        return getProxy();
    }
}
