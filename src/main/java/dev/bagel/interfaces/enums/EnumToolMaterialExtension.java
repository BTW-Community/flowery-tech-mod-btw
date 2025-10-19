package dev.bagel.interfaces.enums;

import net.minecraft.src.EnumToolMaterial;

public interface EnumToolMaterialExtension {
    default public EnumToolMaterial MANASTEEL() {
        throw new AssertionError();
    }

    default public EnumToolMaterial B_ELEMENTIUM() {
        throw new AssertionError();
    }

    default public EnumToolMaterial TERRASTEEL() {
        throw new AssertionError();
    }
}
