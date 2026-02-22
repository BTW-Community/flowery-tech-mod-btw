/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 14, 2014, 5:20:53 PM (GMT)]
 */
package vazkii.botania.common.core;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibMisc;
//todo creative tab, potentitally use fapi?
public final class BotaniaCreativeTab/* extends CreativeTabs*/ {

	public static BotaniaCreativeTab INSTANCE = new BotaniaCreativeTab();
	List list;

	public BotaniaCreativeTab() {
//		super(LibMisc.MOD_ID);
//		setNoTitle();
//		setBackgroundImageName(LibResources.GUI_CREATIVE);
	}

//	@Override
//	public ItemStack getIconItemStack() {
//		return new ItemStack(ModItems.lexicon);
//	}
//
//	@Override
//	public Item getTabIconItem() {
//		return getIconItemStack().getItem();
//	}
//
//	@Override
//	public boolean hasSearchBar() {
//		return true;
//	}
//
//	@Override
	public void displayAllReleventItems(List list, CreativeTabs tab) {
		this.list = list;

		addItem(ModItems.lexicon, tab);

		addBlock(ModBlocks.flower, tab);
		addBlock(ModBlocks.specialFlower, tab);
		addItem(ModItems.petal, tab);
		addItem(ModItems.pestleAndMortar, tab);
		addItem(ModItems.dye, tab);
		addItem(ModItems.fertilizer, tab);
		addItem(ModItems.flowerBag, tab);
		addItem(ModItems.blackLotus, tab);
		addItem(ModItems.twigWand, tab);
		addItem(ModItems.obedienceStick, tab);
		addItem(ModItems.manaResource, tab);
		addBlock(ModBlocks.storage, tab);
		addItem(ModItems.manaCookie, tab);
		addItem(ModItems.rune, tab);

		addBlock(ModBlocks.avatar, tab);
		addItem(ModItems.dirtRod, tab);
		addItem(ModItems.skyDirtRod, tab);
		addItem(ModItems.cobbleRod, tab);
		addItem(ModItems.terraformRod, tab);
		addItem(ModItems.laputaShard, tab);
		addItem(ModItems.grassHorn, tab);
		addItem(ModItems.waterRod, tab);
		addItem(ModItems.openBucket, tab);
		addItem(ModItems.rainbowRod, tab);
		addBlock(ModBlocks.bifrostPerm, tab);
		addBlock(ModFluffBlocks.bifrostPane, tab);
		addBlock(ModBlocks.shimmerrock, tab);
		addBlock(ModBlocks.shimmerwoodPlanks, tab);
		addItem(ModItems.tornadoRod, tab);
		addItem(ModItems.fireRod, tab);
		addItem(ModItems.smeltRod, tab);
		addItem(ModItems.exchangeRod, tab);
		addItem(ModItems.diviningRod, tab);
		addItem(ModItems.gravityRod, tab);
		addItem(ModItems.missileRod, tab);
		addItem(ModItems.virus, tab);
		addItem(ModItems.slingshot, tab);
		addItem(ModItems.vineBall, tab);
		addItem(ModItems.regenIvy, tab);
		addItem(ModItems.keepIvy, tab);
		addItem(ModItems.worldSeed, tab);
		addItem(ModItems.overgrowthSeed, tab);
		addBlock(ModBlocks.enchantedSoil, tab);
		addItem(ModItems.grassSeeds, tab);
		addBlock(ModBlocks.altGrass, tab);
		if(Botania.thaumcraftLoaded)
			addItem(ModItems.manaInkwell, tab);
		addBlock(ModBlocks.forestDrum, tab);
		addBlock(ModBlocks.forestEye, tab);
		addBlock(ModBlocks.enderEye, tab);
		addItem(ModItems.enderHand, tab);
		addItem(ModItems.spellCloth, tab);
		addItem(ModItems.craftingHalo, tab);
		addItem(ModItems.autocraftingHalo, tab);
		addItem(ModItems.spawnerMover, tab);
		addBlock(ModBlocks.spawnerClaw, tab);
		addBlock(ModBlocks.cocoon, tab);
		addBlock(ModBlocks.teruTeruBozu, tab);
		addItem(ModItems.slimeBottle, tab);
		addItem(ModItems.sextant, tab);
		addItem(ModItems.blackHoleTalisman, tab);

		if(Botania.gardenOfGlassLoaded) {
			addBlock(ModBlocks.root, tab);
			addItem(ModItems.waterBowl, tab);
		}

		addBlock(ModBlocks.livingrock, tab);
		addBlock(ModBlocks.livingwood, tab);
		addBlock(ModBlocks.openCrate, tab);
		addBlock(ModBlocks.craftyCrate, tab);
		addItem(ModItems.craftPattern, tab);
		addBlock(ModBlocks.platform, tab);
		addBlock(ModBlocks.alfPortal, tab);
		addBlock(ModBlocks.altar, tab);
		addBlock(ModBlocks.runeAltar, tab);
		addBlock(ModBlocks.terraPlate, tab);
		addBlock(ModBlocks.brewery, tab);
		addItem(ModItems.vial, tab);
		addItem(ModItems.brewVial, tab);
		addItem(ModItems.brewFlask, tab);
		addBlock(ModBlocks.incensePlate, tab);
		addItem(ModItems.incenseStick, tab);
		addItem(ModItems.bloodPendant, tab);
		addBlock(ModBlocks.felPumpkin, tab);
		addBlock(ModBlocks.pylon, tab);
		addBlock(ModBlocks.pistonRelay, tab);
		addBlock(ModBlocks.hourglass, tab);

		addBlock(ModBlocks.redStringContainer, tab);
		addBlock(ModBlocks.redStringDispenser, tab);
		addBlock(ModBlocks.redStringFertilizer, tab);
		addBlock(ModBlocks.redStringComparator, tab);
		addBlock(ModBlocks.redStringRelay, tab);
		addBlock(ModBlocks.redStringInterceptor, tab);

		addBlock(ModBlocks.tinyPotato, tab);
		addBlock(ModBlocks.starfield, tab);

		addBlock(ModBlocks.dreamwood, tab);
		addBlock(ModBlocks.manaGlass, tab);
		addBlock(ModFluffBlocks.managlassPane, tab);
		addBlock(ModBlocks.elfGlass, tab);
		addBlock(ModFluffBlocks.alfglassPane, tab);

		addItem(ModItems.glassPick, tab);
		addItem(ModItems.manasteelPick, tab);
		addItem(ModItems.manasteelShovel, tab);
		addItem(ModItems.manasteelAxe, tab);
		addItem(ModItems.manasteelShears, tab);
		addItem(ModItems.manasteelSword, tab);
		addItem(ModItems.enderDagger, tab);
		addItem(ModItems.livingwoodBow, tab);
		addItem(ModItems.manasteelHelm, tab);
		if(Botania.thaumcraftLoaded)
			addItem(ModItems.manasteelHelmRevealing, tab);
		addItem(ModItems.manasteelChest, tab);
		addItem(ModItems.manasteelLegs, tab);
		addItem(ModItems.manasteelBoots, tab);
		addItem(ModItems.manaweaveHelm, tab);
		addItem(ModItems.manaweaveChest, tab);
		addItem(ModItems.manaweaveLegs, tab);
		addItem(ModItems.manaweaveBoots, tab);
		addItem(ModItems.elementiumPick, tab);
		addItem(ModItems.elementiumShovel, tab);
		addItem(ModItems.elementiumAxe, tab);
		addItem(ModItems.elementiumShears, tab);
		addItem(ModItems.elementiumSword, tab);
		addItem(ModItems.starSword, tab);
		addItem(ModItems.thunderSword, tab);
		addItem(ModItems.crystalBow, tab);
		addItem(ModItems.elementiumHelm, tab);
		if(Botania.thaumcraftLoaded)
			addItem(ModItems.elementiumHelmRevealing, tab);
		addItem(ModItems.elementiumChest, tab);
		addItem(ModItems.elementiumLegs, tab);
		addItem(ModItems.elementiumBoots, tab);
		addItem(ModItems.terraSword, tab);
		addItem(ModItems.thornChakram, tab);
		addItem(ModItems.terraPick, tab);
		addItem(ModItems.terraAxe, tab);
		addItem(ModItems.temperanceStone, tab);
		addItem(ModItems.terrasteelHelm, tab);
		if(Botania.thaumcraftLoaded)
			addItem(ModItems.terrasteelHelmRevealing, tab);
		addItem(ModItems.terrasteelChest, tab);
		addItem(ModItems.terrasteelLegs, tab);
		addItem(ModItems.terrasteelBoots, tab);
		addItem(ModItems.phantomInk, tab);
		addItem(ModItems.cacophonium, tab);
		addItem(ModItems.recordGaia1, tab);
		addItem(ModItems.recordGaia2, tab);
		addItem(ModItems.ancientWill, tab);
		addItem(ModItems.pinkinator, tab);
		addItem(ModItems.gaiaHead, tab);
		if(ConfigHandler.relicsEnabled) {
			addItem(ModItems.dice, tab);
			addItem(ModItems.infiniteFruit, tab);
			addItem(ModItems.kingKey, tab);
			addItem(ModItems.flugelEye, tab);
			addItem(ModItems.thorRing, tab);
			addItem(ModItems.odinRing, tab);
			addItem(ModItems.lokiRing, tab);
			addItem(ModItems.aesirRing, tab);
		}

		addItem(ModItems.baubleBox, tab);
		addItem(ModItems.tinyPlanet, tab);
		addBlock(ModBlocks.tinyPlanet, tab);
		addItem(ModItems.manaRing, tab);
		addItem(ModItems.auraRing, tab);
		addItem(ModItems.manaRingGreater, tab);
		addItem(ModItems.auraRingGreater, tab);
		addItem(ModItems.waterRing, tab);
		addItem(ModItems.miningRing, tab);
		addItem(ModItems.magnetRing, tab);
		addItem(ModItems.magnetRingGreater, tab);
		addItem(ModItems.swapRing, tab);
		addItem(ModItems.reachRing, tab);
		addItem(ModItems.pixieRing, tab);
		addItem(ModItems.travelBelt, tab);
		addItem(ModItems.superTravelBelt, tab);
		addItem(ModItems.speedUpBelt, tab);
		addItem(ModItems.knockbackBelt, tab);
		addItem(ModItems.itemFinder, tab);
		addItem(ModItems.monocle, tab);
		addItem(ModItems.icePendant, tab);
		addItem(ModItems.lavaPendant, tab);
		addItem(ModItems.superLavaPendant, tab);
		addItem(ModItems.holyCloak, tab);
		addItem(ModItems.unholyCloak, tab);
		addItem(ModItems.goldLaurel, tab);
		addItem(ModItems.divaCharm, tab);
		addItem(ModItems.flightTiara, tab);

		addItem(ModItems.manaTablet, tab);
		addItem(ModItems.manaMirror, tab);
		addItem(ModItems.manaBottle, tab);
		addBlock(ModBlocks.pool, tab);
		addBlock(ModBlocks.alchemyCatalyst, tab);
		addBlock(ModBlocks.conjurationCatalyst, tab);
		addBlock(ModBlocks.distributor, tab);
		addBlock(ModBlocks.manaVoid, tab);
		addBlock(ModBlocks.bellows, tab);
		addBlock(ModBlocks.manaDetector, tab);
		addBlock(ModBlocks.manaBomb, tab);
		addBlock(ModBlocks.ghostRail, tab);
		addItem(ModItems.poolMinecart, tab);
		addBlock(ModBlocks.pump, tab);
//		addBlock(ModBlocks.rfGenerator, tab);
		addBlock(ModBlocks.spreader, tab);
		addBlock(ModBlocks.turntable, tab);
		addBlock(ModBlocks.prism, tab);
		addItem(ModItems.lens, tab);
		addItem(ModItems.manaGun, tab);
		addItem(ModItems.clip, tab);
		addItem(ModItems.spark, tab);
		addItem(ModItems.sparkUpgrade, tab);
		addBlock(ModBlocks.sparkChanger, tab);
		addItem(ModItems.corporeaSpark, tab);
		addBlock(ModBlocks.corporeaIndex, tab);
		addBlock(ModBlocks.corporeaFunnel, tab);
		addBlock(ModBlocks.corporeaInterceptor, tab);
		addBlock(ModBlocks.corporeaRetainer, tab);
		addBlock(ModBlocks.corporeaCrystalCube, tab);
		addBlock(ModBlocks.lightRelay, tab);
		addBlock(ModBlocks.lightLauncher, tab);
		addBlock(ModBlocks.cellBlock, tab);

		// FLUFF

		addBlock(ModBlocks.doubleFlower1, tab);
		addBlock(ModBlocks.doubleFlower2, tab);
		addBlock(ModBlocks.shinyFlower, tab);
		addBlock(ModBlocks.floatingFlower, tab);
		addBlock(ModBlocks.floatingSpecialFlower, tab);
		addBlock(ModBlocks.petalBlock, tab);
		addBlock(ModBlocks.mushroom, tab);
		addBlock(ModBlocks.unstableBlock, tab);
		addBlock(ModBlocks.manaBeacon, tab);
		addItem(ModItems.signalFlare, tab);

		addStack(new ItemStack(Block.dirt, 1, 1));
		addBlock(ModBlocks.dirtPath, tab);
		addBlock(ModFluffBlocks.dirtPathSlab, tab);

		addBlock(ModBlocks.prismarine, tab);
		addBlock(ModBlocks.seaLamp, tab);
		addBlock(ModFluffBlocks.prismarineStairs, tab);
		addBlock(ModFluffBlocks.prismarineSlab, tab);
		addBlock(ModFluffBlocks.prismarineWall, tab);
		addBlock(ModFluffBlocks.prismarineBrickStairs, tab);
		addBlock(ModFluffBlocks.prismarineBrickSlab, tab);
		addBlock(ModFluffBlocks.darkPrismarineStairs, tab);
		addBlock(ModFluffBlocks.darkPrismarineSlab, tab);

		addBlock(ModBlocks.blazeBlock, tab);

		addBlock(ModBlocks.reedBlock, tab);
		addBlock(ModFluffBlocks.reedStairs, tab);
		addBlock(ModFluffBlocks.reedSlab, tab);
		addBlock(ModFluffBlocks.reedWall, tab);
		addBlock(ModBlocks.thatch, tab);
		addBlock(ModFluffBlocks.thatchStairs, tab);
		addBlock(ModFluffBlocks.thatchSlab, tab);

		addBlock(ModBlocks.customBrick, tab);
		addBlock(ModFluffBlocks.netherBrickStairs, tab);
		addBlock(ModFluffBlocks.netherBrickSlab, tab);
		addBlock(ModFluffBlocks.soulBrickStairs, tab);
		addBlock(ModFluffBlocks.soulBrickSlab, tab);
		addBlock(ModFluffBlocks.snowBrickStairs, tab);
		addBlock(ModFluffBlocks.snowBrickSlab, tab);
		addBlock(ModFluffBlocks.tileStairs, tab);
		addBlock(ModFluffBlocks.tileSlab, tab);

		addBlock(ModFluffBlocks.livingwoodStairs, tab);
		addBlock(ModFluffBlocks.livingwoodSlab, tab);
		addBlock(ModFluffBlocks.livingwoodWall, tab);
		addBlock(ModFluffBlocks.livingwoodPlankStairs, tab);
		addBlock(ModFluffBlocks.livingwoodPlankSlab, tab);
		addBlock(ModFluffBlocks.livingrockStairs, tab);
		addBlock(ModFluffBlocks.livingrockSlab, tab);
		addBlock(ModFluffBlocks.livingrockWall, tab);
		addBlock(ModFluffBlocks.livingrockBrickStairs, tab);
		addBlock(ModFluffBlocks.livingrockBrickSlab, tab);
		addBlock(ModFluffBlocks.dreamwoodStairs, tab);
		addBlock(ModFluffBlocks.dreamwoodSlab, tab);
		addBlock(ModFluffBlocks.dreamwoodWall, tab);
		addBlock(ModFluffBlocks.dreamwoodPlankStairs, tab);
		addBlock(ModFluffBlocks.dreamwoodPlankSlab, tab);
		addBlock(ModFluffBlocks.shimmerwoodPlankStairs, tab);
		addBlock(ModFluffBlocks.shimmerwoodPlankSlab, tab);
		addBlock(ModFluffBlocks.shimmerrockStairs, tab);
		addBlock(ModFluffBlocks.shimmerrockSlab, tab);

		addItem(ModItems.quartz, tab);
		if(ConfigHandler.darkQuartzEnabled) {
			addBlock(ModFluffBlocks.darkQuartz, tab);
			addBlock(ModFluffBlocks.darkQuartzSlab, tab);
			addBlock(ModFluffBlocks.darkQuartzStairs, tab);
		}

		addBlock(ModFluffBlocks.manaQuartz, tab);
		addBlock(ModFluffBlocks.manaQuartzSlab, tab);
		addBlock(ModFluffBlocks.manaQuartzStairs, tab);
		addBlock(ModFluffBlocks.blazeQuartz, tab);
		addBlock(ModFluffBlocks.blazeQuartzSlab, tab);
		addBlock(ModFluffBlocks.blazeQuartzStairs, tab);
		addBlock(ModFluffBlocks.lavenderQuartz, tab);
		addBlock(ModFluffBlocks.lavenderQuartzSlab, tab);
		addBlock(ModFluffBlocks.lavenderQuartzStairs, tab);
		addBlock(ModFluffBlocks.redQuartz, tab);
		addBlock(ModFluffBlocks.redQuartzSlab, tab);
		addBlock(ModFluffBlocks.redQuartzStairs, tab);
		addBlock(ModFluffBlocks.elfQuartz, tab);
		addBlock(ModFluffBlocks.elfQuartzSlab, tab);
		addBlock(ModFluffBlocks.elfQuartzStairs, tab);
		addBlock(ModFluffBlocks.sunnyQuartz, tab);
		addBlock(ModFluffBlocks.sunnyQuartzSlab, tab);
		addBlock(ModFluffBlocks.sunnyQuartzStairs, tab);

		if(ConfigHandler.stones18Enabled) {
			addBlock(ModFluffBlocks.stone, tab);
			for(int i = 0; i < 8; i++)
				addBlock(ModFluffBlocks.stoneStairs[i], tab);
			for(int i = 0; i < 8; i++)
				addBlock(ModFluffBlocks.stoneSlabs[i], tab);
			addBlock(ModFluffBlocks.stoneWall, tab);
		}

		addBlock(ModFluffBlocks.biomeStoneA, tab);
		addBlock(ModFluffBlocks.biomeStoneB, tab);
		for(int i = 0; i < 24; i++)
			addBlock(ModFluffBlocks.biomeStoneStairs[i], tab);
		for(int i = 0; i < 24; i++)
			addBlock(ModFluffBlocks.biomeStoneSlabs[i], tab);
		addBlock(ModFluffBlocks.biomeStoneWall, tab);

		addBlock(ModFluffBlocks.pavement, tab);
		for (Block pavementStair : ModFluffBlocks.pavementStairs)
			addBlock(pavementStair, tab);
		for (Block pavementSlab : ModFluffBlocks.pavementSlabs)
			addBlock(pavementSlab, tab);

		if(ConfigHandler.enderStuff19Enabled) {
			addBlock(ModBlocks.endStoneBrick, tab);
			addBlock(ModFluffBlocks.endStoneSlab, tab);
			addBlock(ModFluffBlocks.endStoneStairs, tab);
			addBlock(ModFluffBlocks.enderBrickSlab, tab);
			addBlock(ModFluffBlocks.enderBrickStairs, tab);
		}

		addItem(ModItems.cosmetic, tab);
	}

	private void addItem(Item item, CreativeTabs tab) {
		item.getSubItems(item.itemID, tab, list);
	}

	private void addBlock(Block block, CreativeTabs tab) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.itemID, tab, list);
	}

	private void addStack(ItemStack stack) {
		list.add(stack);
	}

}