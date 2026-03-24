package dev.bagel.interfaces.enums;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.src.EnumArmorMaterial;

public interface EnumArmorMaterialExtension {
    EnumArmorMaterial MANASTEEL = ClassTinkerers.getEnum(EnumArmorMaterial.class, "MANASTEEL");
    EnumArmorMaterial B_ELEMENTIUM = ClassTinkerers.getEnum(EnumArmorMaterial.class, "ELEMENTIUM");
    EnumArmorMaterial TERRASTEEL = ClassTinkerers.getEnum(EnumArmorMaterial.class, "TERRASTEEL");
    EnumArmorMaterial MANAWEAVE = ClassTinkerers.getEnum(EnumArmorMaterial.class, "MANAWEAVE");

    static int getWeight(EnumArmorMaterial ths, int slot) {
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

    static double getKnockbackResistance(EnumArmorMaterial ths, int slot) {
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
}
