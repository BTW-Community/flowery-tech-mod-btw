package dev.bagel.mixin.extensions.enums;


import dev.bagel.interfaces.enums.EnumToolMaterialExtension;
import dev.bagel.interfaces.enums.EnumToolMaterialExtension;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.EnumToolMaterial;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(EnumToolMaterial.class)
public abstract class EnumToolMaterialMixin implements EnumToolMaterialExtension {
    @Shadow
    @Final
    @Mutable
    private static EnumToolMaterial[] $VALUES;
    private static final EnumToolMaterial MANASTEEL = noteblockExpansion$addVariant("MANASTEEL", 3, 300, 6.2F, 2F, 20, 25, 2);
    private static final EnumToolMaterial B_ELEMENTIUM = noteblockExpansion$addVariant( "B_ELEMENTIUM", 3, 720, 6.2F, 2F, 20, 30, 3);
    private static final EnumToolMaterial TERRASTEEL = noteblockExpansion$addVariant("TERRASTEEL", 4, 2300, 9F, 3F, 26, 30, 5);

    @Invoker("<init>")
    public static EnumToolMaterial botania$invokeInit(String name, int ordinal,  int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, int infernalMaxEnchantmentCost, int infernalMaxNumEnchants) {
        throw new AssertionError();
    }

    @Override
    public EnumToolMaterial MANASTEEL() {
        return MANASTEEL;
    }

    @Override
    public EnumToolMaterial B_ELEMENTIUM() {
        return B_ELEMENTIUM;
    }

    @Override
    public EnumToolMaterial TERRASTEEL() {
        return TERRASTEEL;
    }

    @Unique
    private static EnumToolMaterial noteblockExpansion$addVariant(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, int infernalMaxEnchantmentCost, int infernalMaxNumEnchants) {
        assert $VALUES != null;
        ArrayList<EnumToolMaterial> variants = new ArrayList<>(Arrays.asList($VALUES));
        EnumToolMaterial instrument = botania$invokeInit(name, variants.get(variants.size() - 1).ordinal() + 1, harvestLevel, maxUses, efficiency, damage, enchantability, infernalMaxEnchantmentCost, infernalMaxNumEnchants);
        variants.add(instrument);
        $VALUES = variants.toArray(new EnumToolMaterial[0]);
        return instrument;
    }
}