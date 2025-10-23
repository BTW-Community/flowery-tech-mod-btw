/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 24, 2015, 2:35:08 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.Block;
import vazkii.botania.common.block.decor.Block18Stone;
import vazkii.botania.common.block.decor.BlockPavement;
import vazkii.botania.common.block.decor.biomestone.BlockBiomeStoneA;
import vazkii.botania.common.block.decor.biomestone.BlockBiomeStoneB;
import vazkii.botania.common.block.decor.panes.BlockAlfglassPane;
import vazkii.botania.common.block.decor.panes.BlockBifrostPane;
import vazkii.botania.common.block.decor.panes.BlockManaglassPane;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartz;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartzSlab;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartzStairs;
import vazkii.botania.common.block.decor.slabs.Block18StoneSlab;
import vazkii.botania.common.block.decor.slabs.BlockBiomeStoneSlab;
import vazkii.botania.common.block.decor.slabs.BlockDirtPathSlab;
import vazkii.botania.common.block.decor.slabs.BlockEndStoneSlab;
import vazkii.botania.common.block.decor.slabs.BlockEnderBrickSlab;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import vazkii.botania.common.block.decor.slabs.BlockPavementSlab;
import vazkii.botania.common.block.decor.slabs.BlockReedSlab;
import vazkii.botania.common.block.decor.slabs.BlockThatchSlab;
import vazkii.botania.common.block.decor.slabs.bricks.BlockCustomBrickSlab;
import vazkii.botania.common.block.decor.slabs.bricks.BlockSnowBrickSlab;
import vazkii.botania.common.block.decor.slabs.bricks.BlockSoulBrickSlab;
import vazkii.botania.common.block.decor.slabs.bricks.BlockTileSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockDreamwoodPlankSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockDreamwoodSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockLivingrockBrickSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockLivingrockSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockLivingwoodPlankSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockLivingwoodSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockShimmerrockSlab;
import vazkii.botania.common.block.decor.slabs.living.BlockShimmerwoodPlankSlab;
import vazkii.botania.common.block.decor.slabs.prismarine.BlockDarkPrismarineSlab;
import vazkii.botania.common.block.decor.slabs.prismarine.BlockPrismarineBrickSlab;
import vazkii.botania.common.block.decor.slabs.prismarine.BlockPrismarineSlab;
import vazkii.botania.common.block.decor.stairs.Block18StoneStairs;
import vazkii.botania.common.block.decor.stairs.BlockBiomeStoneStairs;
import vazkii.botania.common.block.decor.stairs.BlockEndStoneStairs;
import vazkii.botania.common.block.decor.stairs.BlockEnderBrickStairs;
import vazkii.botania.common.block.decor.stairs.BlockPavementStairs;
import vazkii.botania.common.block.decor.stairs.BlockReedStairs;
import vazkii.botania.common.block.decor.stairs.BlockThatchStairs;
import vazkii.botania.common.block.decor.stairs.bricks.BlockCustomBrickStairs;
import vazkii.botania.common.block.decor.stairs.bricks.BlockSnowBrickStairs;
import vazkii.botania.common.block.decor.stairs.bricks.BlockSoulBrickStairs;
import vazkii.botania.common.block.decor.stairs.bricks.BlockTileStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockDreamwoodPlankStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockDreamwoodStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockLivingrockBrickStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockLivingrockStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockLivingwoodPlankStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockLivingwoodStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockShimmerrockStairs;
import vazkii.botania.common.block.decor.stairs.living.BlockShimmerwoodPlankStairs;
import vazkii.botania.common.block.decor.stairs.prismarine.BlockDarkPrismarineStairs;
import vazkii.botania.common.block.decor.stairs.prismarine.BlockPrismarineBrickStairs;
import vazkii.botania.common.block.decor.stairs.prismarine.BlockPrismarineStairs;
import vazkii.botania.common.block.decor.walls.Block18StoneWall;
import vazkii.botania.common.block.decor.walls.BlockBiomeStoneWall;
import vazkii.botania.common.block.decor.walls.BlockPrismarineWall;
import vazkii.botania.common.block.decor.walls.BlockReedWall;
import vazkii.botania.common.block.decor.walls.living.BlockDreamwoodWall;
import vazkii.botania.common.block.decor.walls.living.BlockLivingrockWall;
import vazkii.botania.common.block.decor.walls.living.BlockLivingwoodWall;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibBlockNames;

public final class ModFluffBlocks {

	public static Block livingwoodStairs;
	public static Block livingwoodSlab;
	public static Block livingwoodSlabFull;
	public static Block livingwoodWall;
	public static Block livingwoodPlankStairs;
	public static Block livingwoodPlankSlab;
	public static Block livingwoodPlankSlabFull;
	public static Block livingrockStairs;
	public static Block livingrockSlab;
	public static Block livingrockSlabFull;
	public static Block livingrockWall;
	public static Block livingrockBrickStairs;
	public static Block livingrockBrickSlab;
	public static Block livingrockBrickSlabFull;
	public static Block dreamwoodStairs;
	public static Block dreamwoodSlab;
	public static Block dreamwoodSlabFull;
	public static Block dreamwoodWall;
	public static Block dreamwoodPlankStairs;
	public static Block dreamwoodPlankSlab;
	public static Block dreamwoodPlankSlabFull;

	public static Block prismarineStairs;
	public static Block prismarineSlab;
	public static Block prismarineSlabFull;
	public static Block prismarineWall;
	public static Block prismarineBrickStairs;
	public static Block prismarineBrickSlab;
	public static Block prismarineBrickSlabFull;
	public static Block darkPrismarineStairs;
	public static Block darkPrismarineSlab;
	public static Block darkPrismarineSlabFull;

	public static Block reedStairs;
	public static Block reedSlab;
	public static Block reedSlabFull;
	public static Block reedWall;
	public static Block thatchStairs;
	public static Block thatchSlab;
	public static Block thatchSlabFull;

	public static Block netherBrickStairs;
	public static Block netherBrickSlab;
	public static Block netherBrickSlabFull;
	public static Block soulBrickStairs;
	public static Block soulBrickSlab;
	public static Block soulBrickSlabFull;
	public static Block snowBrickStairs;
	public static Block snowBrickSlab;
	public static Block snowBrickSlabFull;
	public static Block tileStairs;
	public static Block tileSlab;
	public static Block tileSlabFull;

	public static Block darkQuartz;
	public static Block darkQuartzSlab;
	public static Block darkQuartzSlabFull;
	public static Block darkQuartzStairs;
	public static Block manaQuartz;
	public static Block manaQuartzSlab;
	public static Block manaQuartzSlabFull;
	public static Block manaQuartzStairs;
	public static Block blazeQuartz;
	public static Block blazeQuartzSlab;
	public static Block blazeQuartzSlabFull;
	public static Block blazeQuartzStairs;
	public static Block lavenderQuartz;
	public static Block lavenderQuartzSlab;
	public static Block lavenderQuartzSlabFull;
	public static Block lavenderQuartzStairs;
	public static Block redQuartz;
	public static Block redQuartzSlab;
	public static Block redQuartzSlabFull;
	public static Block redQuartzStairs;
	public static Block elfQuartz;
	public static Block elfQuartzSlab;
	public static Block elfQuartzSlabFull;
	public static Block elfQuartzStairs;
	public static Block sunnyQuartz;
	public static Block sunnyQuartzSlab;
	public static Block sunnyQuartzSlabFull;
	public static Block sunnyQuartzStairs;

	public static Block dirtPathSlab;
	public static Block dirtPathSlabFull;

	public static Block biomeStoneA;
	public static Block biomeStoneB;
	public static Block stone;
	public static Block pavement;

	public static Block[] biomeStoneStairs = new Block[24];
	public static Block[] biomeStoneSlabs = new Block[24];
	public static Block[] biomeStoneFullSlabs = new Block[24];
	public static Block biomeStoneWall;

	public static Block[] stoneStairs = new Block[8];
	public static Block[] stoneSlabs = new Block[8];
	public static Block[] stoneFullSlabs = new Block[8];
	public static Block stoneWall;

	public static Block[] pavementStairs = new Block[BlockPavement.TYPES];
	public static Block[] pavementSlabs = new Block[BlockPavement.TYPES];
	public static Block[] pavementFullSlabs = new Block[BlockPavement.TYPES];

	public static Block endStoneSlab;
	public static Block endStoneSlabFull;
	public static Block endStoneStairs;
	public static Block enderBrickSlab;
	public static Block enderBrickSlabFull;
	public static Block enderBrickStairs;

	public static Block shimmerrockSlab;
	public static Block shimmerrockSlabFull;
	public static Block shimmerrockStairs;
	public static Block shimmerwoodPlankSlab;
	public static Block shimmerwoodPlankSlabFull;
	public static Block shimmerwoodPlankStairs;

	public static Block managlassPane;
	public static Block alfglassPane;
	public static BlockBifrostPane bifrostPane;

	public static void init() {
		int id = 421+256;
		livingwoodStairs = new BlockLivingwoodStairs(id++);
		livingwoodSlab = new BlockLivingwoodSlab(id++, false);
		livingwoodSlabFull = new BlockLivingwoodSlab(id++, true);
		livingwoodWall = new BlockLivingwoodWall(id++);
		livingwoodPlankStairs = new BlockLivingwoodPlankStairs(id++);
		livingwoodPlankSlab = new BlockLivingwoodPlankSlab(id++, false);
		livingwoodPlankSlabFull = new BlockLivingwoodPlankSlab(id++, true);
		livingrockStairs = new BlockLivingrockStairs(id++);
		livingrockSlab = new BlockLivingrockSlab(id++, false);
		livingrockSlabFull = new BlockLivingrockSlab(id++, true);
		livingrockWall = new BlockLivingrockWall(id++);
		livingrockBrickStairs = new BlockLivingrockBrickStairs(id++);
		livingrockBrickSlab = new BlockLivingrockBrickSlab(id++, false);
		livingrockBrickSlabFull = new BlockLivingrockBrickSlab(id++, true);
		dreamwoodStairs = new BlockDreamwoodStairs(id++);
		dreamwoodSlab = new BlockDreamwoodSlab(id++, false);
		dreamwoodSlabFull = new BlockDreamwoodSlab(id++, true);
		dreamwoodWall = new BlockDreamwoodWall(id++);
		dreamwoodPlankStairs = new BlockDreamwoodPlankStairs(id++);
		dreamwoodPlankSlab = new BlockDreamwoodPlankSlab(id++, false);
		dreamwoodPlankSlabFull = new BlockDreamwoodPlankSlab(id++, true);

		prismarineStairs = new BlockPrismarineStairs(id++);
		prismarineSlab = new BlockPrismarineSlab(id++, false);
		prismarineSlabFull = new BlockPrismarineSlab(id++, true);
		prismarineWall = new BlockPrismarineWall(id++);
		prismarineBrickStairs = new BlockPrismarineBrickStairs(id++);
		prismarineBrickSlab = new BlockPrismarineBrickSlab(id++, false);
		prismarineBrickSlabFull = new BlockPrismarineBrickSlab(id++, true);
		darkPrismarineStairs = new BlockDarkPrismarineStairs(id++);
		darkPrismarineSlab = new BlockDarkPrismarineSlab(id++, false);
		darkPrismarineSlabFull = new BlockDarkPrismarineSlab(id++, true);

		reedStairs = new BlockReedStairs(id++);
		reedSlab = new BlockReedSlab(id++, false);
		reedSlabFull = new BlockReedSlab(id++, true);
		reedWall = new BlockReedWall(id++);
		thatchStairs = new BlockThatchStairs(id++);
		thatchSlab = new BlockThatchSlab(id++, false);
		thatchSlabFull = new BlockThatchSlab(id++, true);

		netherBrickStairs = new BlockCustomBrickStairs(id++);
		netherBrickSlab = new BlockCustomBrickSlab(id++, false);
		netherBrickSlabFull = new BlockCustomBrickSlab(id++, true);
		soulBrickStairs = new BlockSoulBrickStairs(id++);
		soulBrickSlab = new BlockSoulBrickSlab(id++, false);
		soulBrickSlabFull = new BlockSoulBrickSlab(id++, true);
		snowBrickStairs = new BlockSnowBrickStairs(id++);
		snowBrickSlab = new BlockSnowBrickSlab(id++, false);
		snowBrickSlabFull = new BlockSnowBrickSlab(id++, true);
		tileStairs = new BlockTileStairs(id++);
		tileSlab = new BlockTileSlab(id++, false);
		tileSlabFull = new BlockTileSlab(id++, true);

		biomeStoneA = new BlockBiomeStoneA(id++);
		biomeStoneB = new BlockBiomeStoneB(id++);
		stone = new Block18Stone(id++);
		pavement = new BlockPavement(id++);

		if(ConfigHandler.darkQuartzEnabled) {
			darkQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_DARK);
			darkQuartzSlab = new BlockSpecialQuartzSlab(id++, darkQuartz, false);
			darkQuartzSlabFull = new BlockSpecialQuartzSlab(id++, darkQuartz, true);
			darkQuartzStairs = new BlockSpecialQuartzStairs(id++, darkQuartz);
		}

		manaQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_MANA);
		manaQuartzSlab = new BlockSpecialQuartzSlab(id++, manaQuartz, false);
		manaQuartzSlabFull = new BlockSpecialQuartzSlab(id++, manaQuartz, true);
		manaQuartzStairs = new BlockSpecialQuartzStairs(id++, manaQuartz);
		blazeQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_BLAZE);
		blazeQuartzSlab = new BlockSpecialQuartzSlab(id++, blazeQuartz, false);
		blazeQuartzSlabFull = new BlockSpecialQuartzSlab(id++, blazeQuartz, true);
		blazeQuartzStairs = new BlockSpecialQuartzStairs(id++, blazeQuartz);
		lavenderQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_LAVENDER);
		lavenderQuartzSlab = new BlockSpecialQuartzSlab(id++, lavenderQuartz, false);
		lavenderQuartzSlabFull = new BlockSpecialQuartzSlab(id++, lavenderQuartz, true);
		lavenderQuartzStairs = new BlockSpecialQuartzStairs(id++, lavenderQuartz);
		redQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_RED);
		redQuartzSlab = new BlockSpecialQuartzSlab(id++, redQuartz, false);
		redQuartzSlabFull = new BlockSpecialQuartzSlab(id++, redQuartz, true);
		redQuartzStairs = new BlockSpecialQuartzStairs(id++, redQuartz);
		elfQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_ELF);
		elfQuartzSlab = new BlockSpecialQuartzSlab(id++, elfQuartz, false);
		elfQuartzSlabFull = new BlockSpecialQuartzSlab(id++, elfQuartz, true);
		elfQuartzStairs = new BlockSpecialQuartzStairs(id++, elfQuartz);
		sunnyQuartz = new BlockSpecialQuartz(id++, LibBlockNames.QUARTZ_SUNNY);
		sunnyQuartzSlab = new BlockSpecialQuartzSlab(id++, sunnyQuartz, false);
		sunnyQuartzSlabFull = new BlockSpecialQuartzSlab(id++, sunnyQuartz, true);
		sunnyQuartzStairs = new BlockSpecialQuartzStairs(id++, sunnyQuartz);

		dirtPathSlab = new BlockDirtPathSlab(id++, false);
		dirtPathSlabFull = new BlockDirtPathSlab(id++, true);

		for(int i = 0; i < 24; i++) {
			int meta = i % 16;
			Block block = i < 16 ? biomeStoneA : biomeStoneB;
			biomeStoneStairs[i] = new BlockBiomeStoneStairs(id++, block, meta);
			biomeStoneSlabs[i] = new BlockBiomeStoneSlab(id++, false, block, meta, i);
			biomeStoneFullSlabs[i] = new BlockBiomeStoneSlab(id++, true, block, meta, i);
		}
		biomeStoneWall = new BlockBiomeStoneWall(id++);

		for(int i = 0; i < 8; i++) {
			int meta = i > 3 ? i + 4 : i;
			stoneStairs[i] = new Block18StoneStairs(id++, meta);
			stoneSlabs[i] = new Block18StoneSlab(id++, false, meta, i);
			stoneFullSlabs[i] = new Block18StoneSlab(id++, true, meta, i);
		}
		stoneWall = new Block18StoneWall(id++);

		for(int i = 0; i < pavementStairs.length; i++) {
			pavementStairs[i] = new BlockPavementStairs(id++, i);
			pavementSlabs[i] = new BlockPavementSlab(id++, false, i, i);
			pavementFullSlabs[i] = new BlockPavementSlab(id++, true, i, i);
		}

		endStoneSlab = new BlockEndStoneSlab(id++, false);
		endStoneSlabFull = new BlockEndStoneSlab(id++, true);
		endStoneStairs = new BlockEndStoneStairs(id++);
		enderBrickSlab = new BlockEnderBrickSlab(id++, false);
		enderBrickSlabFull = new BlockEnderBrickSlab(id++, true);
		enderBrickStairs = new BlockEnderBrickStairs(id++);

		shimmerrockSlab = new BlockShimmerrockSlab(id++, false);
		shimmerrockSlabFull = new BlockShimmerrockSlab(id++, true);
		shimmerrockStairs = new BlockShimmerrockStairs(id++);
		shimmerwoodPlankSlab = new BlockShimmerwoodPlankSlab(id++, false);
		shimmerwoodPlankSlabFull = new BlockShimmerwoodPlankSlab(id++, true);
		shimmerwoodPlankStairs = new BlockShimmerwoodPlankStairs(id++);

		managlassPane = new BlockManaglassPane(id++);
		alfglassPane = new BlockAlfglassPane(id++);
		bifrostPane = new BlockBifrostPane(id++);

		if(ConfigHandler.darkQuartzEnabled) {
			((BlockModSlab) darkQuartzSlab).register();
			((BlockModSlab) darkQuartzSlabFull).register();
		}
		((BlockModSlab) manaQuartzSlab).register();
		((BlockModSlab) manaQuartzSlabFull).register();
		((BlockModSlab) blazeQuartzSlab).register();
		((BlockModSlab) blazeQuartzSlabFull).register();
		((BlockModSlab) lavenderQuartzSlab).register();
		((BlockModSlab) lavenderQuartzSlabFull).register();
		((BlockModSlab) redQuartzSlab).register();
		((BlockModSlab) redQuartzSlabFull).register();
		((BlockModSlab) elfQuartzSlab).register();
		((BlockModSlab) elfQuartzSlabFull).register();
		((BlockModSlab) sunnyQuartzSlab).register();
		((BlockModSlab) sunnyQuartzSlabFull).register();

		((BlockModSlab) livingwoodSlab).register();
		((BlockModSlab) livingwoodSlabFull).register();
		((BlockModSlab) livingwoodPlankSlab).register();
		((BlockModSlab) livingwoodPlankSlabFull).register();
		((BlockModSlab) livingrockSlab).register();
		((BlockModSlab) livingrockSlabFull).register();
		((BlockModSlab) livingrockBrickSlab).register();
		((BlockModSlab) livingrockBrickSlabFull).register();
		((BlockModSlab) dreamwoodSlab).register();
		((BlockModSlab) dreamwoodSlabFull).register();
		((BlockModSlab) dreamwoodPlankSlab).register();
		((BlockModSlab) dreamwoodPlankSlabFull).register();

		((BlockModSlab) reedSlab).register();
		((BlockModSlab) reedSlabFull).register();
		((BlockModSlab) thatchSlab).register();
		((BlockModSlab) thatchSlabFull).register();

		((BlockModSlab) prismarineSlab).register();
		((BlockModSlab) prismarineSlabFull).register();
		((BlockModSlab) prismarineBrickSlab).register();
		((BlockModSlab) prismarineBrickSlabFull).register();
		((BlockModSlab) darkPrismarineSlab).register();
		((BlockModSlab) darkPrismarineSlabFull).register();

		((BlockModSlab) netherBrickSlab).register();
		((BlockModSlab) netherBrickSlabFull).register();
		((BlockModSlab) soulBrickSlab).register();
		((BlockModSlab) soulBrickSlabFull).register();
		((BlockModSlab) snowBrickSlab).register();
		((BlockModSlab) snowBrickSlabFull).register();
		((BlockModSlab) tileSlab).register();
		((BlockModSlab) tileSlabFull).register();

		((BlockModSlab) dirtPathSlab).register();
		((BlockModSlab) dirtPathSlabFull).register();

		((BlockModSlab) endStoneSlab).register();
		((BlockModSlab) endStoneSlabFull).register();
		((BlockModSlab) enderBrickSlab).register();
		((BlockModSlab) enderBrickSlabFull).register();

		((BlockModSlab) shimmerrockSlab).register();
		((BlockModSlab) shimmerrockSlabFull).register();
		((BlockModSlab) shimmerwoodPlankSlab).register();
		((BlockModSlab) shimmerwoodPlankSlabFull).register();

		for(int i = 0; i < 24; i++) {
			((BlockModSlab) biomeStoneSlabs[i]).register();
			((BlockModSlab) biomeStoneFullSlabs[i]).register();
		}

		for(int i = 0; i < 8; i++) {
			((BlockModSlab) stoneSlabs[i]).register();
			((BlockModSlab) stoneFullSlabs[i]).register();
		}

		for(int i = 0; i < pavementSlabs.length; i++) {
			((BlockModSlab) pavementSlabs[i]).register();
			((BlockModSlab) pavementFullSlabs[i]).register();
		}
	}

}
