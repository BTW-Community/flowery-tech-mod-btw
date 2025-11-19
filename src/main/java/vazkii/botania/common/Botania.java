/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 13, 2014, 6:32:39 PM (GMT)]
 */
package vazkii.botania.common;

import baubles.common.Baubles;
import baubles.common.network.PacketHandler;
import btw.AddonHandler;
import btw.BTWAddon;
import btw.world.biome.BiomeDecoratorBase;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import dev.bagel.network.CustomGuiPacketHandler;
import dev.bagel.util.GuiHandlerHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.common.core.command.CommandOpen;
import vazkii.botania.common.core.command.CommandShare;
import vazkii.botania.common.core.command.CommandSkyblockSpread;
import vazkii.botania.common.core.handler.BiomeDecorationHandler;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.integration.coloredlights.ILightHelper;
import vazkii.botania.common.integration.coloredlights.LightHelperVanilla;

import java.util.Random;

//@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES, guiFactory = LibMisc.GUI_FACTORY)
public class Botania extends BTWAddon implements GuiHandlerHolder {

	public static boolean gardenOfGlassLoaded = false;

	public static boolean thaumcraftLoaded = false;
	public static boolean bcTriggersLoaded = false;
	public static boolean bloodMagicLoaded = false;
	public static boolean coloredLightsLoaded = false;
	public static boolean etFuturumLoaded = false;
	public static boolean storageDrawersLoaded = false;

	public static ILightHelper lightHelper;

	public static Botania instance = new Botania();

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
	public String getModID() {
		return "botania";
	}

	@Override
	public void preInitialize() {
		if (!MinecraftServer.getIsServer()) {
			addResourcePackDomain("baubles");
		}
		Baubles.instance.preInit();
		registerPacketHandler("botania|BAUB", PacketHandler.INSTANCE);
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

	@Override
	public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
		Baubles.instance.entityEventNetwork.playerLoggedInEvent(new PlayerEvent.PlayerLoggedInEvent(playerMP));
	}

	//FMLInitializationEvent
	@Override
	public void initialize() {
		registerPacketHandler("botania|GUI", CustomGuiPacketHandler.INSTANCE);
		Baubles.instance.init();
		getProxy().init();
		registerAddonCommandServerOnly(new CommandShare());
		registerAddonCommandServerOnly(new CommandOpen());

		if(Botania.gardenOfGlassLoaded)
			registerAddonCommandServerOnly(new CommandSkyblockSpread());
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


	@Override
	public IGuiHandler getGuiHandler() {
		return CommonProxy.guiHandler;
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

	public static ResourceLocation loc(String id) {
		return new ResourceLocation("botania", id);
	}
}
