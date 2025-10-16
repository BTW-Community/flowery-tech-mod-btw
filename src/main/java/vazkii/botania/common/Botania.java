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
import btw.world.biome.BiomeDecoratorBase;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ServerListenThread;
import net.minecraft.src.ThreadMinecraftServer;
import net.minecraft.src.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.common.core.handler.BiomeDecorationHandler;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.integration.coloredlights.ILightHelper;
import vazkii.botania.common.integration.coloredlights.LightHelperVanilla;

import java.util.Random;

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

	public EnvType getEffectiveSide() {
		Thread thr = Thread.currentThread();
		return !(thr instanceof ThreadMinecraftServer) && !(thr instanceof ServerListenThread) ? EnvType.CLIENT : EnvType.SERVER;
	}

	@Override
	public void postSetup() {
		super.postSetup();
	}

	//	@SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
	//ADDED interface
	public static CommonProxy proxy = new CommonProxy();

	public static CommonProxy getProxy() {
		if (instance.getEffectiveSide() == EnvType.SERVER) {
			return proxy;
		}
		else {
			return ClientProxy.instance;
		}
	}

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

		getProxy().preInit();
	}
	//FMLInitializationEvent
	@Override
	public void initialize() {
		getProxy().init();
	}
	//FMLPostInitializationEvent
	@Override
	public void postInitialize() {
		getProxy().postInit();
	}

	@Override
	public void decorateWorld(BiomeDecoratorBase decorator, World world, Random rand, int x, int y, BiomeGenBase biome) {
		new BiomeDecorationHandler().decorate(world, rand, x, y);
		BiomeDecorationHandler.onWorldDecoration(new DecorateBiomeEvent.Decorate(world, rand, x, y, DecorateBiomeEvent.Decorate.EventType.FLOWERS));
	}

	//FMLServerAboutToStartEvent
	public void serverAboutToStart() {
//		getProxy().serverAboutToStart();
	}

	//FMLServerStartingEvent
	public void serverStarting() {
		getProxy().serverStarting();
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
