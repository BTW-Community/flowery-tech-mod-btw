package dev.bagel.util;

import api.util.AddonSoundRegistryEntry;

public class BotaniaSounds {
    public static void registerSounds() {
        registerSound("agricarnation");
        registerSound("airRod");
        registerSound("altarCraft");
        registerSound("babylonAttack");
        registerSound("babylonSpawn");
        registerSound("bellows");
        registerSound("bifrostRod");
        registerSound("blackLotus");
        registerSound("dash");
        registerSound("ding");
        registerSound("divaCharm");
        registerSound("divinationRod");
        registerSound("doit");
        registerSound("enchanterBlock");
        registerSound("enchanterEnchant");
        registerSound("endoflame");
        registerSound("equipBauble");
        registerSound("gaiaTrap");
        registerSound("goldenLaurel");
        registerSound("laputaStart");
        registerSound("lexiconOpen");
        registerSound("lexiconPage");
        registerSound("lightRelay");
        registerSound("manaBlaster");
        registerSound("manaPoolCraft");
        registerSound("missile");
        registerSound("orechid");
        registerSound("potionCreate");
        registerSound("runeAltarCraft");
        registerSound("runeAltarStart");
        registerSound("spreaderFire");
        registerSound("starcaller");
        registerSound("terraBlade");
        registerSound("terraformRod");
        registerSound("terraPickMode");
        registerSound("terrasteelCraft");
        registerSound("thermalily");
        registerSound("unholyCloak");
        registerSound("way");
    }

    private static void registerSound(String id) {
        new AddonSoundRegistryEntry("botania:" + id, 1);
    }
}
