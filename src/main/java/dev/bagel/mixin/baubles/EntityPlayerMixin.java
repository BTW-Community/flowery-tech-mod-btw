package dev.bagel.mixin.baubles;

import com.llamalad7.mixinextras.sugar.Local;
import dev.bagel.bauble.CustomPlayerDataEntry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {

    @ModifyArgs(method = "readModDataFromNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayer;setData(Lapi/world/data/DataEntry$PlayerDataEntry;Ljava/lang/Object;)V"))
    private void baubles$readCustomData(Args args, @Local(name = "dataTag") NBTTagCompound tag) {
        if (args.get(0) instanceof CustomPlayerDataEntry custom) {
            args.set(1, custom.reader.apply(tag, (EntityPlayer) (Object) this));
        }
    }
}
