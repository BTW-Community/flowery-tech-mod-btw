package dev.bagel.mixin.extensions.enums;

import dev.bagel.interfaces.enums.EnumRarityExtension;
import net.minecraft.src.EnumRarity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(EnumRarity.class)
public class EnumRarityMixin implements EnumRarityExtension {
    @Shadow
    @Final
    @Mutable
    private static EnumRarity[] $VALUES;
    private static final EnumRarity rarityRelic = noteblockExpansion$addVariant("RELIC", 6, "Relic");

    @Invoker("<init>")
    public static EnumRarity botania$invokeInit(String name, int ordinal, int rarityColor, String rarityName) {
        throw new AssertionError();
    }

    @Override
    public EnumRarity rarityRelic() {
        return rarityRelic;
    }

    @Unique
    private static EnumRarity noteblockExpansion$addVariant(String name, int rarityColor, String rarityName) {
        assert $VALUES != null;
        ArrayList<EnumRarity> variants = new ArrayList<>(Arrays.asList($VALUES));
        EnumRarity instrument = botania$invokeInit(name, variants.get(variants.size() - 1).ordinal() + 1, rarityColor, rarityName);
        variants.add(instrument);
        $VALUES = variants.toArray(new EnumRarity[0]);
        return instrument;
    }


}
