package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.src.*;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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

    @WrapOperation(method = "handlePlace", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemInWorldManager;tryUseItem(Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;Lnet/minecraft/src/ItemStack;)Z"))
    private boolean forge$playerInteract(ItemInWorldManager instance, EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack, Operation<Boolean> original, @Local WorldServer worldServer) {
        PlayerInteractEvent event = ForgeEventFactory.onPlayerInteract(playerEntity, PlayerInteractEvent.Action.RIGHT_CLICK_AIR, 0, 0, 0, -1, worldServer);
        if (event.useItem != Event.Result.DENY) {
            return original.call(instance, par1EntityPlayer, par2World, par3ItemStack);
        }
        return false;
    }
}
