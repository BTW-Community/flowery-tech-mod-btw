package dev.bagel.mixin;

import emi.dev.emi.emi.screen.EmiScreenManager;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(value = EmiScreenManager.class)
public class EmiScreenManagerMixin {
    @Redirect(method = "give", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/NBTTagCompound;toString()Ljava/lang/String;"))
    private static String getStr(NBTTagCompound instance) {
        final StringBuilder s = new StringBuilder(" {");
        Map<String, NBTBase> map = instance.tagMap;
        final int[] i = {0};
        map.forEach((k, v) -> {
            s.append(k).append(':');
            if (v instanceof NBTTagString str) {
                var data = str.data;
                s.append('"').append(data).append('"');
            }
            else if (v instanceof NBTTagByte) {
                s.append(v).append('B');
            }
            else if (v instanceof NBTTagShort) {
                s.append(v).append('S');
            }
            else if (v instanceof NBTTagLong) {
                s.append(v).append('L');
            }
            else if (v instanceof NBTTagFloat) {
                s.append(v).append('F');
            }
            else {
                s.append(v);
            }
            i[0]++;
            if (i[0] != map.size()) {
                s.append(',');
            }
        });
        s.append("}");
        return s.toString();
    }
}
