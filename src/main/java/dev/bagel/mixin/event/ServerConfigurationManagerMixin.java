package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.ItemKeepIvy;
import vazkii.botania.common.item.ModItems;

@Mixin(ServerConfigurationManager.class)
public class ServerConfigurationManagerMixin {
    @Inject(method = "respawnPlayer", at = @At("TAIL"))
    private void forge$respawnPlayer(EntityPlayerMP oldPlayer, int iDefaultDimension, boolean playerLeavingTheEnd, CallbackInfoReturnable<EntityPlayerMP> cir, @Local(ordinal = 1) EntityPlayerMP newPlayer) {
        var event = new PlayerEvent.PlayerRespawnEvent(newPlayer);
        PlayerEvent.PlayerRespawnEvent.EVENT.invoker().onPlayerRespawn(event);
    }
}
