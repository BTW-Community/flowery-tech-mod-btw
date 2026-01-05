package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.IntegratedServer;
import net.minecraft.src.WorldType;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin extends MinecraftServer {
    public IntegratedServerMixin(File par1File) {
        super(par1File);
    }

    @Inject(method = "loadAllWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ServerConfigurationManager;setPlayerManager([Lnet/minecraft/src/WorldServer;)V"))
    private void forge$onWorldLoad(String par1Str, String par2Str, long par3, WorldType par5WorldType, String par6Str, CallbackInfo ci, @Local int worldId) {
        WorldEvent.Load.EVENT.invoker().onWorldLoad(new WorldEvent.Load(this.worldServers[worldId]));
    }
}
