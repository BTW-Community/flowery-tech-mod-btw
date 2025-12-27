package dev.bagel.mixin.extensions.enums;


import dev.bagel.interfaces.enums.EnumArmorMaterialExtension;
import net.minecraft.src.EnumArmorMaterial;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(EnumArmorMaterial.class)
public abstract class EnumArmorMaterialMixin implements EnumArmorMaterialExtension {
    @Shadow
    @Final
    @Mutable
    private static EnumArmorMaterial[] $VALUES;
    private static final EnumArmorMaterial MANASTEEL = armorEnum$addVariant("MANASTEEL", 16, new int[] { 2, 6, 5, 2 }, 18);
    private static final EnumArmorMaterial B_ELEMENTIUM = armorEnum$addVariant("B_ELEMENTIUM", 18, new int[] { 2, 6, 5, 2 }, 18);
    private static final EnumArmorMaterial TERRASTEEL = armorEnum$addVariant("TERRASTEEL", 34, new int[] {3, 8, 6, 3}, 26);
    private static final EnumArmorMaterial MANAWEAVE = armorEnum$addVariant("MANAWEAVE", 5, new int[] { 1, 2, 2, 1 }, 18);

    @Invoker("<init>")
    public static EnumArmorMaterial armorEnum$invokeInit(String internalName, int ordinal, int durability, int[] reductionAmounts, int enchantability) {
        throw new AssertionError();
    }

    @Override
    public EnumArmorMaterial MANASTEEL() {
        return MANASTEEL;
    }

    @Override
    public EnumArmorMaterial B_ELEMENTIUM() {
        return B_ELEMENTIUM;
    }

    @Override
    public EnumArmorMaterial TERRASTEEL() {
        return TERRASTEEL;
    }

    @Override
    public EnumArmorMaterial MANAWEAVE() {
        return MANAWEAVE;
    }

    @Override
    public int forge$getWeight(int slot) {
        EnumArmorMaterial ths = (EnumArmorMaterial) (Object) this;
        if (ths == MANASTEEL) {
            return switch (slot) {
                case 0 -> 4;
                case 1 -> 8;
                case 2 -> 7;
                case 3 -> 3;
                default -> 0;
            };
        }
        if (ths == B_ELEMENTIUM) {
            return switch (slot) {
                case 0 -> 3;
                case 1 -> 6;
                case 2 -> 5;
                case 3 -> 2;
                default -> 0;
            };
        }
        if (ths == TERRASTEEL) {
            return switch (slot) {
                case 0 -> 6;
                case 1 -> 10;
                case 2 -> 8;
                case 3 -> 5;
                default -> 0;
            };
        }
        if (ths == MANAWEAVE) {
            return 0;
        }
        return 0;
    }

    @Override
    public double forge$getKnockbackResistance(int slot) {
        EnumArmorMaterial ths = (EnumArmorMaterial) (Object) this;
        if (ths == MANASTEEL) {
            return 0;
        }
        if (ths == B_ELEMENTIUM) {
            return 0.05;
        }
        if (ths == TERRASTEEL) {
            return switch (slot) {
                case 0 -> 0.15;
                case 1 -> 0.4;
                case 2 -> 0.3;
                case 3 -> 0.15;
                default -> 0;
            };
        }
        if (ths == MANAWEAVE) {
            return 0;
        }
        return 0;
    }

    @Unique
    private static EnumArmorMaterial armorEnum$addVariant(String internalName, int durability, int[] reductionAmounts, int enchantability) {
        assert $VALUES != null;
        ArrayList<EnumArmorMaterial> variants = new ArrayList<>(Arrays.asList($VALUES));
        EnumArmorMaterial instrument = armorEnum$invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1, durability, reductionAmounts, enchantability);
        variants.add(instrument);
        $VALUES = variants.toArray(new EnumArmorMaterial[0]);
        return instrument;
    }


}