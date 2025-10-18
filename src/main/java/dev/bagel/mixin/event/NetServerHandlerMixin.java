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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(NetServerHandler.class)
public class NetServerHandlerMixin {
    @Shadow public EntityPlayerMP playerEntity;

    private final Pattern pattern = Pattern.compile("<[^>]*>\\s(.*)");
    //ServerChatEvent
    @WrapOperation(method = "handleChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ServerConfigurationManager;func_110459_a(Lnet/minecraft/src/ChatMessageComponent;Z)V"))
    private void handleChat(ServerConfigurationManager instance, ChatMessageComponent component, boolean alwaysFalse, Operation<Void> original) {
        String found = component.toString();
        Matcher m = pattern.matcher(found);
        if (m.find()) {
            found = m.group(1);
        }
        var event = new ServerChatEvent(this.playerEntity, found, component);
        ServerChatEvent.EVENT.invoker().onServerChat(event);
        if (!event.isCanceled()) {
            original.call(instance, component, alwaysFalse);
        }
    }
}
