package dev.bagel.mixin;

import emi.dev.emi.emi.screen.EmiScreenManager;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagString;
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
        map.forEach((k, v) -> {
            s.append(k).append(':');
            if (v instanceof NBTTagString str) {
                var data = str.data;
                s.append('"').append(str.data).append('"');
            }
            else {
                s.append(v);
            }
        });
        s.append("}");
        return s.toString();
    }
}
