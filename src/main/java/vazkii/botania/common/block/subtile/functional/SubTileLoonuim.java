/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [May 31, 2014, 7:49:43 PM (GMT)]
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileLoonuim extends SubTileFunctional {

    private static final int COST = 35000;
    private static final int RANGE = 3;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (redstoneSignal == 0 && ticksExisted % 200 == 0 && mana >= COST) {
            Random rand = supertile.getWorldObj().rand;

            ItemStack stack;
            do {//todofix double check chest gen for loonium is working
                stack = getOneItem(rand);
            } while (stack == null || BotaniaAPI.looniumBlacklist.contains(stack.getItem()));

            int bound = RANGE * 2 + 1;
            EntityItem entity = new EntityItem(supertile.getWorldObj(), supertile.xCoord - RANGE + rand.nextInt(bound), supertile.yCoord + 1, supertile.zCoord - RANGE + rand.nextInt(bound), stack);
            entity.motionX = 0;
            entity.motionY = 0;
            entity.motionZ = 0;

            if (!supertile.getWorldObj().isRemote)
                supertile.getWorldObj().spawnEntityInWorld(entity);

            mana -= COST;
            sync();
        }
    }

    @Override
    public int getColor() {
        return 0x274A00;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.loonium;
    }

    @Override
    public int getMaxMana() {
        return COST;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
    }

    //Forge util methods

    public static WeightedRandomChestContent[] getItems(Random rnd) {
        ArrayList<WeightedRandomChestContent> ret = new ArrayList<WeightedRandomChestContent>();

        for (WeightedRandomChestContent orig : WorldGenDungeons.field_111189_a) {
            Item item = orig.theItemId.getItem();

            if (item != null) {
                WeightedRandomChestContent n = orig;
                if (item instanceof ItemEnchantedBook eb) {
                    n = eb.func_92112_a(rnd, orig.theMinimumChanceToGenerateItem, orig.theMaximumChanceToGenerateItem, orig.itemWeight);
                }

                if (n != null) {
                    ret.add(n);
                }
            }
        }

        return ret.toArray(new WeightedRandomChestContent[ret.size()]);
    }

    public ItemStack getOneItem(Random rand) {
        WeightedRandomChestContent[] items = getItems(rand);
        WeightedRandomChestContent item = (WeightedRandomChestContent) WeightedRandom.getRandomItem(rand, items);
        ItemStack[] stacks = generateStacks(rand, item.theItemId, item.theMinimumChanceToGenerateItem, item.theMaximumChanceToGenerateItem);
        return (stacks.length > 0 ? stacks[0] : null);
    }

    public static ItemStack[] generateStacks(Random rand, ItemStack source, int min, int max) {
        int count = min + (rand.nextInt(max - min + 1));

        ItemStack[] ret;
        if (source.getItem() == null) {
            ret = new ItemStack[0];
        } else if (count > source.getMaxStackSize()) {
            ret = new ItemStack[count];
            for (int x = 0; x < count; x++) {
                ret[x] = source.copy();
                ret[x].stackSize = 1;
            }
        } else {
            ret = new ItemStack[1];
            ret[0] = source.copy();
            ret[0].stackSize = count;
        }
        return ret;
    }
}
