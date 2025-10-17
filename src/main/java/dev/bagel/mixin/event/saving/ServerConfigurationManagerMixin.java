package dev.bagel.mixin.event.saving;

import baubles.common.event.EventHandlerEntity;
import net.minecraft.src.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerConfigurationManager.class)
public class ServerConfigurationManagerMixin {
    @Shadow private IPlayerFileData playerNBTManagerObj;

    @Inject(method = "readPlayerDataFromFile", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    private void forge$readPlayerData(EntityPlayerMP player, CallbackInfoReturnable<NBTTagCompound> cir) {
        SaveHandler sh = (SaveHandler) this.playerNBTManagerObj;
        var event = new PlayerEvent.LoadFromFile(player, sh.playersDirectory, player.getUniqueID().toString());
        EventHandlerEntity.playerLoad(event);
    }
}
