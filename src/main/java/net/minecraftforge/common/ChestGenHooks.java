package net.minecraftforge.common;

import com.google.common.collect.Maps;
import net.minecraft.src.*;

import java.util.Map;

public class ChestGenHooks {

    public static final String MINESHAFT_CORRIDOR       = "mineshaftCorridor";
    public static final String PYRAMID_DESERT_CHEST     = "pyramidDesertyChest";
    public static final String PYRAMID_JUNGLE_CHEST     = "pyramidJungleChest";
    public static final String PYRAMID_JUNGLE_DISPENSER = "pyramidJungleDispenser";
    public static final String STRONGHOLD_CORRIDOR      = "strongholdCorridor";
    public static final String STRONGHOLD_LIBRARY       = "strongholdLibrary";
    public static final String STRONGHOLD_CROSSING      = "strongholdCrossing";
    public static final String VILLAGE_BLACKSMITH       = "villageBlacksmith";
//    public static final String BONUS_CHEST              = "bonusChest";
    public static final String DUNGEON_CHEST            = "dungeonChest";

    static {
        addInfo(MINESHAFT_CORRIDOR,       StructureMineshaftPieces.mineshaftChestContents,   3,  7);
        addInfo(PYRAMID_DESERT_CHEST,     ComponentScatteredFeatureDesertPyramid.lootListArray,             2,  7);
        addInfo(PYRAMID_JUNGLE_CHEST,     ComponentScatteredFeatureJunglePyramid.lootListArray,         2,  7);
        addInfo(PYRAMID_JUNGLE_DISPENSER, ComponentScatteredFeatureJunglePyramid.junglePyramidsDispenserContents,     2,  2);
        addInfo(STRONGHOLD_CORRIDOR,      ComponentStrongholdChestCorridor.strongholdChestContents,             2,  4);
        addInfo(STRONGHOLD_LIBRARY,       ComponentStrongholdLibrary.strongholdLibraryChestContents,            1,  5);
        addInfo(STRONGHOLD_CROSSING,      ComponentStrongholdRoomCrossing.strongholdRoomCrossingChestContents,  1,  5);
        addInfo(VILLAGE_BLACKSMITH,       ComponentVillageHouse2.villageBlacksmithChestContents,             3,  9);
//        addInfo(BONUS_CHEST,              WorldServer.bonusChestContent,                    10, 10);
        addInfo(DUNGEON_CHEST,            WorldGenDungeons.field_111189_a,                   8,  8);
    }

    static void addDungeonLoot(ChestGenHooks dungeon, ItemStack item, int weight, int min, int max) {

    }

    private static void addInfo(String category, WeightedRandomChestContent[] items, int min, int max) {
//        chestInfo.putIfAbsent(category, items);
    }

    public static void addItem(String category, WeightedRandomChestContent item) {
        switch (category) {
            case MINESHAFT_CORRIDOR -> StructureMineshaftPieces.mineshaftChestContents = getInfo(StructureMineshaftPieces.mineshaftChestContents, item);
            case PYRAMID_DESERT_CHEST -> ComponentScatteredFeatureDesertPyramid.lootListArray = getInfo(ComponentScatteredFeatureDesertPyramid.lootListArray, item);
            case PYRAMID_JUNGLE_CHEST -> ComponentScatteredFeatureJunglePyramid.lootListArray = getInfo(ComponentScatteredFeatureJunglePyramid.lootListArray, item);
            case PYRAMID_JUNGLE_DISPENSER -> ComponentScatteredFeatureJunglePyramid.junglePyramidsDispenserContents = getInfo(ComponentScatteredFeatureJunglePyramid.junglePyramidsDispenserContents, item);
            case STRONGHOLD_CORRIDOR -> ComponentStrongholdChestCorridor.strongholdChestContents = getInfo(ComponentStrongholdChestCorridor.strongholdChestContents, item);
            case STRONGHOLD_LIBRARY -> ComponentStrongholdLibrary.strongholdLibraryChestContents = getInfo(ComponentStrongholdLibrary.strongholdLibraryChestContents, item);
            case STRONGHOLD_CROSSING -> ComponentStrongholdRoomCrossing.strongholdRoomCrossingChestContents = getInfo(ComponentStrongholdRoomCrossing.strongholdRoomCrossingChestContents, item);
            case VILLAGE_BLACKSMITH -> ComponentVillageHouse2.villageBlacksmithChestContents = getInfo(ComponentVillageHouse2.villageBlacksmithChestContents, item);
            case DUNGEON_CHEST -> WorldGenDungeons.field_111189_a = getInfo(WorldGenDungeons.field_111189_a, item);
            default -> throw new IllegalArgumentException("Invalid ChestGenHooks category: " + category);
        }
    }

    private static WeightedRandomChestContent[] getInfo(WeightedRandomChestContent[] v, WeightedRandomChestContent item) {
        WeightedRandomChestContent[] newArray = new WeightedRandomChestContent[v.length + 1];
        System.arraycopy(v, 0, newArray, 0, v.length);
        newArray[v.length] = item;
        return newArray;
    }
}
