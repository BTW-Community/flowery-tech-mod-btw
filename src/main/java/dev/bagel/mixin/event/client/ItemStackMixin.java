package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.client.core.handler.TooltipHandler;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    //ItemTooltipEvent
    @ModifyReturnValue(method = "getTooltip", at = @At("RETURN"))
    private List onGetTooltip(List original, @Local(argsOnly = true) EntityPlayer player, @Local(argsOnly = true) boolean advanced) {
        var event = new ItemTooltipEvent((ItemStack) (Object) this, player, (List<String>) original, advanced);
        TooltipHandler.instance.onTooltipEvent(event);
        return event.toolTip;
    }
}
