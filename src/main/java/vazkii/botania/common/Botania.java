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

import api.config.AddonConfig;
import baubles.common.Baubles;
import baubles.common.network.PacketHandler;
import api.AddonHandler;
import api.BTWAddon;
import api.world.BiomeDecoratorBase;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import dev.bagel.emi.BotaniaEmiPlugin;
import dev.bagel.network.CustomGuiPacketHandler;
import dev.bagel.util.GuiHandlerHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.common.core.command.CommandOpen;
import vazkii.botania.common.core.command.CommandShare;
import vazkii.botania.common.core.command.CommandSkyblockSpread;
import vazkii.botania.common.core.handler.BiomeDecorationHandler;
import vazkii.botania.common.core.handler.IMCHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.integration.coloredlights.ILightHelper;
import vazkii.botania.common.integration.coloredlights.LightHelperVanilla;
import vazkii.botania.common.lib.LibMisc;

import java.util.Random;

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

	public static CommonProxy proxy = new CommonProxy();

	public static final Logger LOGGER = LogManager.getLogger("Botania");

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
		return LibMisc.MOD_ID;
	}

	@Override
	public void postSetup() {
		IMCHandler.setupIMC();
	}

	@Override
	public void preInitialize() {
		if (!MinecraftServer.getIsServer()) {
			addResourcePackDomain("baubles");
		}

		Baubles.instance.preInit();
		registerPacketHandler("botania|BAUB", PacketHandler.INSTANCE);
		gardenOfGlassLoaded = FabricLoader.getInstance().isModLoaded("denovo");

		thaumcraftLoaded = FabricLoader.getInstance().isModLoaded("Thaumcraft");
		/*bcTriggersLoaded = ModAPIManager.INSTANCE.hasAPI("BuildCraftAPI|statements");*/
		bcTriggersLoaded = false;
		bloodMagicLoaded = FabricLoader.getInstance().isModLoaded("AWWayofTime"); // Psh, noob
		coloredLightsLoaded = FabricLoader.getInstance().isModLoaded("easycoloredlights");
		etFuturumLoaded = FabricLoader.getInstance().isModLoaded("etfuturum");
		storageDrawersLoaded = FabricLoader.getInstance().isModLoaded("StorageDrawers");
		
		lightHelper = /*coloredLightsLoaded ? new LightHelperColored() :*/ new LightHelperVanilla();

		KeyBindingHelper.registerKeyBinding(BotaniaEmiPlugin.KEY);
		getProxy().preInit();
	}

	@Override
	public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
		Baubles.instance.entityEventNetwork.playerLoggedInEvent(new PlayerEvent.PlayerLoggedInEvent(playerMP));
	}

	@Override
	public void initialize() {
		BotaniaAPI.init();
		registerPacketHandler("botania|GUI", CustomGuiPacketHandler.INSTANCE);
		Baubles.instance.init();
		getProxy().init();
		registerAddonCommand(new CommandShare());
		registerAddonCommand(new CommandOpen());

		if(Botania.gardenOfGlassLoaded)
			registerAddonCommand(new CommandSkyblockSpread());
	}

	@Override
	public void postInitialize() {
		getProxy().postInit();
		ServerLifecycleEvents.SERVER_STARTED.register(server -> IMCHandler.processMessages());
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> ManaNetworkHandler.instance.clear());
	}

	@Override
	public void decorateWorld(BiomeDecoratorBase decorator, World world, Random rand, int x, int y, BiomeGenBase biome) {
		new BiomeDecorationHandler().decorate(world, rand, x, y);
		BiomeDecorationHandler.onWorldDecoration(new DecorateBiomeEvent.Decorate(world, rand, x, y, DecorateBiomeEvent.Decorate.EventType.FLOWERS));
	}

	@Override
	public void registerConfigProperties(AddonConfig config) {
		getProxy().registerConfigProperties(config);
	}

	@Override
	public void handleConfigProperties(AddonConfig config) {
		getProxy().handleConfigProperties(config);
	}

	@Override
	public IGuiHandler getGuiHandler() {
		return CommonProxy.guiHandler;
	}

	public static ResourceLocation loc(String id) {
		return new ResourceLocation("botania", id);
	}
}
