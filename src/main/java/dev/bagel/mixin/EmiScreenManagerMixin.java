package dev.bagel.mixin;

import emi.dev.emi.emi.screen.EmiScreenManager;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.Map;

@Mixin(value = EmiScreenManager.class, remap = false)
public class EmiScreenManagerMixin {
    //        String s = "{";
    //        String s1;
    //
    //        for (Iterator iterator = this.tagMap.keySet().iterator(); iterator.hasNext(); s = s + s1 + ':' + this.tagMap.get(s1) + ',')
    //        {
    //            s1 = (String)iterator.next();
    //        }
    //
    //        return s + "}";
    @Redirect(method = "give", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/NBTTagCompound;toString()Ljava/lang/String;"))
    private static String getStr(NBTTagCompound instance) {
        final StringBuilder s = new StringBuilder(" {");
        String s1;
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
//        for (Iterator<String> iterator = instance.tagMap.keySet().iterator(); iterator.hasNext(); s = s + "\"" + s1 + "\"" + ':' + (instance.tagMap.get(s1) instanceof NBTTagString str ? "\"" + str + "\"" : instance.tagMap.get(s1) ) + ',')
//        {
//            s1 = iterator.next();
//        }
        s.append("}");
        return s.toString();
    }
}
