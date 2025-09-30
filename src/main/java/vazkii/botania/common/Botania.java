/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 13, 2014, 6:32:39 PM (GMT)]
 */
package vazkii.botania.common;

import btw.BTWAddon;
import dev.bagel.util.ICommonProxy;
import net.fabricmc.loader.api.FabricLoader;
import vazkii.botania.common.integration.coloredlights.ILightHelper;
import vazkii.botania.common.integration.coloredlights.LightHelperVanilla;
import vazkii.botania.common.lib.LibMisc;

//@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES, guiFactory = LibMisc.GUI_FACTORY)
public class Botania extends BTWAddon {

	public static boolean gardenOfGlassLoaded = false;

	public static boolean thaumcraftLoaded = false;
	public static boolean bcTriggersLoaded = false;
	public static boolean bloodMagicLoaded = false;
	public static boolean coloredLightsLoaded = false;
	public static boolean etFuturumLoaded = false;
	public static boolean storageDrawersLoaded = false;

	public static ILightHelper lightHelper;

	public static Botania instance = new Botania();

//	@SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
	//ADDED interface
	public static ICommonProxy proxy = new ICommonProxy() {
	@Override
	public void preInit() {
		System.out.println("pre init");
	}

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void postInit() {
		System.out.println("post init");
	}

	@Override
	public void serverAboutToStart() {
		System.out.println("server about to start");
	}

	@Override
	public void serverStarting() {
		System.out.println("server starting");
	}

	@Override
	public void serverStopping() {
		System.out.println("server stopping");
	}
};

	@Override
	public void preInitialize() {
		gardenOfGlassLoaded = FabricLoader.getInstance().isModLoaded("GardenOfGlass");

		thaumcraftLoaded = FabricLoader.getInstance().isModLoaded("Thaumcraft");
		/*bcTriggersLoaded = ModAPIManager.INSTANCE.hasAPI("BuildCraftAPI|statements");*/
		bcTriggersLoaded = false;
		bloodMagicLoaded = FabricLoader.getInstance().isModLoaded("AWWayofTime"); // Psh, noob
		coloredLightsLoaded = FabricLoader.getInstance().isModLoaded("easycoloredlights");
		etFuturumLoaded = FabricLoader.getInstance().isModLoaded("etfuturum");
		storageDrawersLoaded = FabricLoader.getInstance().isModLoaded("StorageDrawers");
		
		lightHelper = /*coloredLightsLoaded ? new LightHelperColored() :*/ new LightHelperVanilla();

		proxy.preInit();
	}
	//FMLInitializationEvent
	@Override
	public void initialize() {
		proxy.init();
	}
	//FMLPostInitializationEvent
	@Override
	public void postInitialize() {
		proxy.postInit();
	}

	//FMLServerAboutToStartEvent
	public void serverAboutToStart() {
		proxy.serverAboutToStart();
	}

	//FMLServerStartingEvent
	public void serverStarting() {
		proxy.serverStarting();
	}

	//FMLServerStoppingEvent
	public void serverStopping() {
//		ManaNetworkHandler.instance.clear();
	}

	//FMLInterModComms.IMCEvent event
	public void handleIMC() {
//		IMCHandler.processMessages(event.getMessages());
	}
}
