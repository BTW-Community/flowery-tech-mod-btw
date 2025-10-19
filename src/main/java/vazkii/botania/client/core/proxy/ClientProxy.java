/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 13, 2014, 7:46:05 PM (GMT)]
 */
package vazkii.botania.client.core.proxy;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;

import dev.bagel.util.BotaniaSounds;
import dev.bagel.util.Items;
import net.fabricmc.api.EnvType;
import net.minecraft.src.*;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.item.IExtendedPlayerController;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.multiblock.IMultiblockRenderHook;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.AnyComponent;
import vazkii.botania.api.wiki.IWikiProvider;
import vazkii.botania.api.wiki.WikiHooks;
import vazkii.botania.client.challenge.ModChallenges;
import vazkii.botania.client.core.handler.BaubleRenderHandler;
import vazkii.botania.client.core.handler.BotaniaPlayerController;
import vazkii.botania.client.core.handler.BoundTileRenderer;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.CorporeaAutoCompleteHandler;
import vazkii.botania.client.core.handler.DebugHandler;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.core.handler.SubTileRadiusRenderHandler;
import vazkii.botania.client.core.handler.TooltipHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.fx.FXSparkle;
import vazkii.botania.client.fx.FXWisp;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexiconIndex;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.render.block.RenderAltar;
import vazkii.botania.client.render.block.RenderAvatar;
import vazkii.botania.client.render.block.RenderBellows;
import vazkii.botania.client.render.block.RenderBrewery;
import vazkii.botania.client.render.block.RenderCocoon;
import vazkii.botania.client.render.block.RenderCorporeaCrystalCube;
import vazkii.botania.client.render.block.RenderCorporeaIndex;
import vazkii.botania.client.render.block.RenderDoubleFlower;
import vazkii.botania.client.render.block.RenderFloatingFlower;
import vazkii.botania.client.render.block.RenderHourglass;
import vazkii.botania.client.render.block.RenderIncensePlate;
import vazkii.botania.client.render.block.RenderPool;
import vazkii.botania.client.render.block.RenderPump;
import vazkii.botania.client.render.block.RenderPylon;
import vazkii.botania.client.render.block.RenderSpawnerClaw;
import vazkii.botania.client.render.block.RenderSpecialFlower;
import vazkii.botania.client.render.block.RenderSpreader;
import vazkii.botania.client.render.block.RenderTeruTeruBozu;
import vazkii.botania.client.render.block.RenderTinyPotato;
import vazkii.botania.client.render.entity.RenderBabylonWeapon;
import vazkii.botania.client.render.entity.RenderCorporeaSpark;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.client.render.entity.RenderManaStorm;
import vazkii.botania.client.render.entity.RenderPinkWither;
import vazkii.botania.client.render.entity.RenderPixie;
import vazkii.botania.client.render.entity.RenderPoolMinecart;
import vazkii.botania.client.render.entity.RenderSpark;
import vazkii.botania.client.render.entity.RenderThornChakram;
import vazkii.botania.client.render.item.RenderBow;
import vazkii.botania.client.render.item.RenderFloatingFlowerItem;
import vazkii.botania.client.render.item.RenderLens;
import vazkii.botania.client.render.item.RenderLexicon;
import vazkii.botania.client.render.item.RenderTransparentItem;
import vazkii.botania.client.render.tile.RenderTileAlfPortal;
import vazkii.botania.client.render.tile.RenderTileAltar;
import vazkii.botania.client.render.tile.RenderTileAvatar;
import vazkii.botania.client.render.tile.RenderTileBellows;
import vazkii.botania.client.render.tile.RenderTileBrewery;
import vazkii.botania.client.render.tile.RenderTileCocoon;
import vazkii.botania.client.render.tile.RenderTileCorporeaCrystalCube;
import vazkii.botania.client.render.tile.RenderTileCorporeaIndex;
import vazkii.botania.client.render.tile.RenderTileEnchanter;
import vazkii.botania.client.render.tile.RenderTileFloatingFlower;
import vazkii.botania.client.render.tile.RenderTileHourglass;
import vazkii.botania.client.render.tile.RenderTileIncensePlate;
import vazkii.botania.client.render.tile.RenderTileLightRelay;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.client.render.tile.RenderTilePrism;
import vazkii.botania.client.render.tile.RenderTilePump;
import vazkii.botania.client.render.tile.RenderTilePylon;
import vazkii.botania.client.render.tile.RenderTileRedString;
import vazkii.botania.client.render.tile.RenderTileRuneAltar;
import vazkii.botania.client.render.tile.RenderTileSkullOverride;
import vazkii.botania.client.render.tile.RenderTileSparkChanger;
import vazkii.botania.client.render.tile.RenderTileSpawnerClaw;
import vazkii.botania.client.render.tile.RenderTileSpreader;
import vazkii.botania.client.render.tile.RenderTileStarfield;
import vazkii.botania.client.render.tile.RenderTileTerraPlate;
import vazkii.botania.client.render.tile.RenderTileTeruTeruBozu;
import vazkii.botania.client.render.tile.RenderTileTinyPotato;
import vazkii.botania.client.render.world.SkyblockRenderEvents;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileAvatar;
import vazkii.botania.common.block.tile.TileBrewery;
import vazkii.botania.common.block.tile.TileCocoon;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.block.tile.TileHourglass;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSparkChanger;
import vazkii.botania.common.block.tile.TileSpawnerClaw;
import vazkii.botania.common.block.tile.TileStarfield;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.block.tile.TileTeruTeruBozu;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.block.tile.mana.TilePrism;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.block.tile.mana.TileSpreader;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.entity.EntityManaStorm;
import vazkii.botania.common.entity.EntityPinkWither;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.entity.EntityPoolMinecart;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.entity.EntityVineBall;
import vazkii.botania.common.item.ItemSextant.MultiblockSextant;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemMonocle;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;

public class ClientProxy extends CommonProxy {
	public static final ClientProxy instance = new ClientProxy();
	public static boolean jingleTheBells = false;
	public static boolean dootDoot = false;

	@Override
	public void preInit() {
		PersistentVariableHelper.setCacheFile(new File(Minecraft.getMinecraft().mcDataDir, "BotaniaVars.dat"));
		try {
			PersistentVariableHelper.load();
			PersistentVariableHelper.save();
		} catch (IOException e) {
			FMLLog.severe("Botania's persistent Variables couldn't load!");
			e.printStackTrace();
		}

		super.preInit();
	}

	@Override
	public void init() {
		super.init();

		ModChallenges.init();

		//todofix client tick handler
//		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new HUDHandler());
		MinecraftForge.EVENT_BUS.register(new LightningHandler());
		if(ConfigHandler.boundBlockWireframe)
			MinecraftForge.EVENT_BUS.register(new BoundTileRenderer());
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
		MinecraftForge.EVENT_BUS.register(new BaubleRenderHandler());
		MinecraftForge.EVENT_BUS.register(new DebugHandler());
		MinecraftForge.EVENT_BUS.register(new SubTileRadiusRenderHandler());
		MinecraftForge.EVENT_BUS.register(new MultiblockRenderHandler());
		MinecraftForge.EVENT_BUS.register(new SkyblockRenderEvents());
		//todofix CorporeaAutoCompleteHandler
//		FMLCommonHandler.instance().bus().register(new CorporeaAutoCompleteHandler());

		if(ConfigHandler.enableSeasonalFeatures) {
			Calendar calendar = Calendar.getInstance();
			if((calendar.get(2) == 11 && calendar.get(5) >= 16) || (calendar.get(2) == 0 && calendar.get(5) <= 2))
				jingleTheBells = true;
			if(calendar.get(2) == 9)
				dootDoot = true;
		}

		initRenderers();
		BotaniaSounds.registerSounds();
	}

	@Override
	public void postInit() {
		super.postInit();
		CorporeaAutoCompleteHandler.updateItemList();
	}

	private void initRenderers() {
		LibRenderIDs.idAltar = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idSpecialFlower = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idSpreader = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idPool = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idPylon = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idMiniIsland = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idTinyPotato = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idSpawnerClaw = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idBrewery = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idCorporeaIndex = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idPump = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idDoubleFlower = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idCorporeaCrystalCybe = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idIncensePlate = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idHourglass = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idCocoon = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idLightRelay = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idBellows = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idTeruTeruBozu = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idAvatar = RenderingRegistry.getNextAvailableRenderId();

		RenderSpecialFlower specialFlowerRender = new RenderSpecialFlower(LibRenderIDs.idSpecialFlower);
		RenderingRegistry.registerBlockHandler(new RenderAltar());
		RenderingRegistry.registerBlockHandler(specialFlowerRender);
		RenderingRegistry.registerBlockHandler(new RenderSpreader());
		RenderingRegistry.registerBlockHandler(new RenderPool());
		RenderingRegistry.registerBlockHandler(new RenderPylon());
		RenderingRegistry.registerBlockHandler(new RenderFloatingFlower());
		RenderingRegistry.registerBlockHandler(new RenderTinyPotato());
		RenderingRegistry.registerBlockHandler(new RenderSpawnerClaw());
		RenderingRegistry.registerBlockHandler(new RenderBrewery());
		RenderingRegistry.registerBlockHandler(new RenderCorporeaIndex());
		RenderingRegistry.registerBlockHandler(new RenderPump());
		RenderingRegistry.registerBlockHandler(new RenderDoubleFlower());
		RenderingRegistry.registerBlockHandler(new RenderCorporeaCrystalCube());
		RenderingRegistry.registerBlockHandler(new RenderIncensePlate());
		RenderingRegistry.registerBlockHandler(new RenderHourglass());
		RenderingRegistry.registerBlockHandler(new RenderCocoon());
		RenderingRegistry.registerBlockHandler(new RenderBellows());
		RenderingRegistry.registerBlockHandler(new RenderTeruTeruBozu());
		RenderingRegistry.registerBlockHandler(new RenderAvatar());

		IMultiblockRenderHook.renderHooks.put(ModBlocks.flower, specialFlowerRender);
		IMultiblockRenderHook.renderHooks.put(ModBlocks.shinyFlower, specialFlowerRender);

		RenderTransparentItem renderTransparentItem = new RenderTransparentItem();
		RenderFloatingFlowerItem renderFloatingFlower = new RenderFloatingFlowerItem();
		RenderBow renderBow = new RenderBow();

		MinecraftForgeClient.registerItemRenderer(ModItems.lens, new RenderLens());
		if(ConfigHandler.lexicon3dModel)
			MinecraftForgeClient.registerItemRenderer(ModItems.lexicon, new RenderLexicon());
		MinecraftForgeClient.registerItemRenderer(ModItems.glassPick, renderTransparentItem);
		MinecraftForgeClient.registerItemRenderer(ModItems.spark, renderTransparentItem);
		MinecraftForgeClient.registerItemRenderer(Items.getItemFromBlock(ModBlocks.floatingFlower), renderFloatingFlower);
		MinecraftForgeClient.registerItemRenderer(Items.getItemFromBlock(ModBlocks.floatingSpecialFlower), renderFloatingFlower);
		MinecraftForgeClient.registerItemRenderer(ModItems.livingwoodBow, renderBow);
		MinecraftForgeClient.registerItemRenderer(ModItems.crystalBow, renderBow);

		RenderTileFloatingFlower renderTileFloatingFlower = new RenderTileFloatingFlower();
		TileEntityRenderer.instance.addSpecialRendererForClass(TileAltar.class, new RenderTileAltar());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileSpreader.class, new RenderTileSpreader());
		TileEntityRenderer.instance.addSpecialRendererForClass(TilePool.class, new RenderTilePool());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileRuneAltar.class, new RenderTileRuneAltar());
		TileEntityRenderer.instance.addSpecialRendererForClass(TilePylon.class, new RenderTilePylon());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileEnchanter.class, new RenderTileEnchanter());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileAlfPortal.class, new RenderTileAlfPortal());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileFloatingFlower.class, renderTileFloatingFlower);
		TileEntityRenderer.instance.addSpecialRendererForClass(TileFloatingSpecialFlower.class, renderTileFloatingFlower);
		TileEntityRenderer.instance.addSpecialRendererForClass(TileTinyPotato.class, new RenderTileTinyPotato());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileSpawnerClaw.class, new RenderTileSpawnerClaw());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileStarfield.class, new RenderTileStarfield());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileBrewery.class, new RenderTileBrewery());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileTerraPlate.class, new RenderTileTerraPlate());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileRedString.class, new RenderTileRedString());
		TileEntityRenderer.instance.addSpecialRendererForClass(TilePrism.class, new RenderTilePrism());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileCorporeaIndex.class, new RenderTileCorporeaIndex());
		TileEntityRenderer.instance.addSpecialRendererForClass(TilePump.class, new RenderTilePump());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileCorporeaCrystalCube.class, new RenderTileCorporeaCrystalCube());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileIncensePlate.class, new RenderTileIncensePlate());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileHourglass.class, new RenderTileHourglass());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileSparkChanger.class, new RenderTileSparkChanger());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileCocoon.class, new RenderTileCocoon());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileLightRelay.class, new RenderTileLightRelay());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileBellows.class, new RenderTileBellows());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileGaiaHead.class, new RenderTileSkullOverride());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileTeruTeruBozu.class, new RenderTileTeruTeruBozu());
		TileEntityRenderer.instance.addSpecialRendererForClass(TileAvatar.class, new RenderTileAvatar());

		TileEntityRenderer.instance.addSpecialRendererForClass(TileEntitySkull.class, new RenderTileSkullOverride());

		RenderingRegistry.registerEntityRenderingHandler(EntityPixie.class, new RenderPixie());
		RenderingRegistry.registerEntityRenderingHandler(EntityVineBall.class, new RenderSnowball(ModItems.vineBall));
		RenderingRegistry.registerEntityRenderingHandler(EntityDoppleganger.class, new RenderDoppleganger());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpark.class, new RenderSpark());
		RenderingRegistry.registerEntityRenderingHandler(EntityThornChakram.class, new RenderThornChakram());
		RenderingRegistry.registerEntityRenderingHandler(EntityCorporeaSpark.class, new RenderCorporeaSpark());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderAirBottle.class, new RenderSnowball(ModItems.manaResource, 15));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoolMinecart.class, new RenderPoolMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntityPinkWither.class, new RenderPinkWither());
		RenderingRegistry.registerEntityRenderingHandler(EntityManaStorm.class, new RenderManaStorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityBabylonWeapon.class, new RenderBabylonWeapon());

		//todofix shaders??
//		ShaderHelper.initShaders();
	}

	@Override
	public void registerNEIStuff() {
//		NEIGuiHooks.init();
	}

	@Override
	public void setEntryToOpen(LexiconEntry entry) {
		GuiLexicon.currentOpenLexicon = new GuiLexiconEntry(entry, new GuiLexiconIndex(entry.category));
	}

	@Override
	public void setToTutorialIfFirstLaunch() {
		if(PersistentVariableHelper.firstLoad)
			GuiLexicon.currentOpenLexicon = new GuiLexiconEntry(LexiconData.welcome, new GuiLexiconEntry(LexiconData.tutorial, new GuiLexicon())).setFirstEntry();
	}

	@Override
	public void setLexiconStack(ItemStack stack) {
		GuiLexicon.stackUsed = stack;
	}

	@Override
	public boolean isTheClientPlayer(EntityLivingBase entity) {
		return entity == Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public boolean isClientPlayerWearingMonocle() {
		return ItemMonocle.hasMonocle(Minecraft.getMinecraft().thePlayer);
	}

	@Override
	public void setExtraReach(EntityLivingBase entity, float reach) {
		super.setExtraReach(entity, reach);
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(entity == player) {
			if(!(mc.playerController instanceof IExtendedPlayerController)) {
				EnumGameType type = mc.playerController.currentGameType;
				NetClientHandler net = mc.playerController.getNetClientHandler();
				BotaniaPlayerController controller = new BotaniaPlayerController(mc, net);
				boolean isFlying = player.capabilities.isFlying;
				boolean allowFlying = player.capabilities.allowFlying;
				controller.setGameType(type);
				player.capabilities.isFlying = isFlying;
				player.capabilities.allowFlying = allowFlying;
				mc.playerController = controller;
			}

			((IExtendedPlayerController) mc.playerController).setReachDistanceExtension(Math.max(0, ((IExtendedPlayerController) mc.playerController).getReachDistanceExtension() + reach));
		}
	}

	@Override
	public boolean openWikiPage(World world, Block block, MovingObjectPosition pos) {
		IWikiProvider wiki = WikiHooks.getWikiFor(block);
		String url = wiki.getWikiURL(world, pos);
		if(url != null && !url.isEmpty()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void playRecordClientSided(World world, int x, int y, int z, ItemRecord record) {
		Minecraft mc = Minecraft.getMinecraft();
		if(record == null)
			world.playAuxSFXAtEntity(null, 1005, x, y, z, 0);
		else {
			world.playAuxSFXAtEntity(null, 1005, x, y, z, record.itemID);
			mc.ingameGUI.setRecordPlayingMessage(record.recordName);
		}
	}

	@Override
	public long getWorldElapsedTicks() {
		return ClientTickHandler.ticksInGame;
	}

	@Override
	public void setMultiblock(World world, int x, int y, int z, double radius, Block block) {
		MultiblockSextant mb = new MultiblockSextant();

		int iradius = (int) radius + 1;
		for(int i = 0; i < iradius * 2 + 1; i++)
			for(int j = 0; j < iradius * 2 + 1; j++) {
				int xp = x + i - iradius;
				int zp = z + j - iradius;
				if((int) Math.floor(MathHelper.pointDistancePlane(xp, zp, x, z)) == iradius - 1)
					mb.addComponent(new AnyComponent(new ChunkCoordinates(xp - x, 1, zp - z), block, 0));
			}

		MultiblockRenderHandler.setMultiblock(mb.makeSet());
		MultiblockRenderHandler.anchor = new ChunkCoordinates(x, y, z);
	}

	@Override
	public void removeSextantMultiblock() {
		MultiblockSet set = MultiblockRenderHandler.currentMultiblock;
		if(set != null) {
			Multiblock mb = set.getForIndex(0);
			if(mb instanceof MultiblockSextant)
				MultiblockRenderHandler.setMultiblock(null);
		}
	}

	private static boolean noclipEnabled = false;
	private static boolean corruptSparkle = false;

	@Override
	public void setSparkleFXNoClip(boolean noclip) {
		noclipEnabled = noclip;
	}

	@Override
	public void setSparkleFXCorrupt(boolean corrupt) {
		corruptSparkle = corrupt;
	}

	@Override
	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
		if(!doParticle(world) && !fake)
			return;

		FXSparkle sparkle = new FXSparkle(world, x, y, z, size, r, g, b, m);
		sparkle.fake = sparkle.noClip = fake;
		if(noclipEnabled)
			sparkle.noClip = true;
		if(corruptSparkle)
			sparkle.corrupt = true;
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}

	private static boolean distanceLimit = true;
	private static boolean depthTest = true;

	@Override
	public void setWispFXDistanceLimit(boolean limit) {
		distanceLimit = limit;
	}

	@Override
	public void setWispFXDepthTest(boolean test) {
		depthTest = test;
	}

	@Override
	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul) {
		if(!doParticle(world))
			return;

		FXWisp wisp = new FXWisp(world, x, y, z, size, r, g, b, distanceLimit, depthTest, maxAgeMul);
		wisp.motionX = motionx;
		wisp.motionY = motiony;
		wisp.motionZ = motionz;

		Minecraft.getMinecraft().effectRenderer.addEffect(wisp);
	}

	private boolean doParticle(World world) {
		if(!world.isRemote)
			return false;

		if(!ConfigHandler.useVanillaParticleLimiter)
			return true;

		float chance = 1F;
		if(Minecraft.getMinecraft().gameSettings.particleSetting == 1)
			chance = 0.6F;
		else if(Minecraft.getMinecraft().gameSettings.particleSetting == 2)
			chance = 0.2F;

		return chance == 1F || Math.random() < chance;
	}

	@Override
	public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
		LightningHandler.spawnLightningBolt(world, vectorStart, vectorEnd, ticksPerMeter, seed, colorOuter, colorInner);
	}
}

