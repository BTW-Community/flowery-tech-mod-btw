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
    private static final EnumArmorMaterial MANASTEEL = noteblockExpansion$addVariant("MANASTEEL", 16, new int[] { 2, 6, 5, 2 }, 18);
    private static final EnumArmorMaterial B_ELEMENTIUM = noteblockExpansion$addVariant("B_ELEMENTIUM", 18, new int[] { 2, 6, 5, 2 }, 18);
    private static final EnumArmorMaterial TERRASTEEL = noteblockExpansion$addVariant("TERRASTEEL", 34, new int[] {3, 8, 6, 3}, 26);
    private static final EnumArmorMaterial MANAWEAVE = noteblockExpansion$addVariant("MANAWEAVE", 5, new int[] { 1, 2, 2, 1 }, 18);

    @Invoker("<init>")
    public static EnumArmorMaterial noteblockExpansion$invokeInit(String internalName, int ordinal, int durability, int[] reductionAmounts, int enchantability) {
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

    @Unique
    private static EnumArmorMaterial noteblockExpansion$addVariant(String internalName, int durability, int[] reductionAmounts, int enchantability) {
        assert $VALUES != null;
        ArrayList<EnumArmorMaterial> variants = new ArrayList<>(Arrays.asList($VALUES));
        EnumArmorMaterial instrument = noteblockExpansion$invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1, durability, reductionAmounts, enchantability);
        variants.add(instrument);
        $VALUES = variants.toArray(new EnumArmorMaterial[0]);
        return instrument;
    }
}