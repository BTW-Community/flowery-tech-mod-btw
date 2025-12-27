package dev.bagel.interfaces.enums;

import net.minecraft.src.EnumArmorMaterial;

public interface EnumArmorMaterialExtension {
    default public EnumArmorMaterial MANASTEEL() {
        throw new AssertionError();
    }

    default public EnumArmorMaterial B_ELEMENTIUM() {
        throw new AssertionError();
    }

    default public EnumArmorMaterial TERRASTEEL() {
        throw new AssertionError();
    }

    default public EnumArmorMaterial MANAWEAVE() {
        throw new AssertionError();
    }

    default public int forge$getWeight(int slot) {
        throw new AssertionError();
    }

    default public double forge$getKnockbackResistance(int slot) {
        throw new AssertionError();
    }
}
