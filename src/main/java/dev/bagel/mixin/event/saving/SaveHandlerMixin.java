package dev.bagel.mixin.event.saving;

import baubles.common.event.EventHandlerEntity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.SaveHandler;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(SaveHandler.class)
public class SaveHandlerMixin {
    @Shadow protected File playersDirectory;

    @Inject(method = "writePlayerData", at = @At(value = "INVOKE", target = "Ljava/io/File;renameTo(Ljava/io/File;)Z", shift = At.Shift.AFTER))
    private void forge$playerData1(EntityPlayer player, CallbackInfo ci) {
        var event = new PlayerEvent.SaveToFile(player, this.playersDirectory, player.getUniqueID().toString());
        EventHandlerEntity.playerSave(event);
    }

    @Inject(method = "readPlayerData", at = @At(value = "RETURN"))
    private void forge$playerData2(EntityPlayer player, CallbackInfoReturnable<NBTTagCompound> cir) {
        var event = new PlayerEvent.LoadFromFile(player, this.playersDirectory, player.getUniqueID().toString());
        EventHandlerEntity.playerLoad(event);
    }
}
