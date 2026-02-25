package dev.bagel.mixin.baubles;

import api.world.data.DataEntry;
import btw.client.network.packet.handler.PlayerDataSyncPacketHandler;
import dev.bagel.bauble.CustomPlayerDataEntry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Function;

@Mixin(PlayerDataSyncPacketHandler.class)
public class PlayerDataSyncPacketHandlerMixin {
    @Unique
    private EntityPlayer player;
    @Unique
    DataEntry.PlayerDataEntry<?> entry;
    @Inject(method = "handleCustomPacket", at = @At(value = "INVOKE", target = "Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void baubles$capturePlayer(Packet250CustomPayload packet, EntityPlayer player, CallbackInfo ci, NBTTagCompound tag, String name, DataEntry.PlayerDataEntry<?> entry){
        this.player = player;
        this.entry = entry;
    }

    @Redirect(method = "handleCustomPacket", at = @At(value = "INVOKE", target = "Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;"))
    private <T, R> R baubles$redirectFunction(Function instance, T t) {
        if (entry instanceof CustomPlayerDataEntry custom) {
            return (R) custom.reader.apply(t, player);
        }
        return (R) instance.apply(t);
    }
}
