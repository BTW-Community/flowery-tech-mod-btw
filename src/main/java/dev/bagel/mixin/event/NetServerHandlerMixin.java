package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ChatMessageComponent;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraftforge.event.ServerChatEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetServerHandler.class)
public class NetServerHandlerMixin {
    @Shadow public EntityPlayerMP playerEntity;

    @Shadow @Final public MinecraftServer mcServer;

    //ServerChatEvent
    @WrapOperation(method = "handleChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ServerConfigurationManager;func_110459_a(Lnet/minecraft/src/ChatMessageComponent;Z)V"))
    private void handleChat(ServerConfigurationManager instance, ChatMessageComponent component, boolean alwaysFalse, Operation<Void> original) {
        var event = new ServerChatEvent(this.playerEntity, component.toString(), component);
        ServerChatEvent.EVENT.invoker().onServerChat(event);
        if (!event.isCanceled()) {
            original.call(instance, component, alwaysFalse);
        }
    }
}
