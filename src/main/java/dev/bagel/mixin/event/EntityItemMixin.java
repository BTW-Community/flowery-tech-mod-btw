package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ItemFlowerBag;
import vazkii.botania.common.item.ModItems;

@Mixin(EntityItem.class)
public class EntityItemMixin {
    @Inject(method = "onCollideWithPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/InventoryPlayer;addItemStackToInventory(Lnet/minecraft/src/ItemStack;)Z"), cancellable = true)
    private void onCollideWithPlayer(EntityPlayer player, CallbackInfo ci) {
        var event = new EntityItemPickupEvent(player, (EntityItem) (Object) this);
        ((ItemFlowerBag) ModItems.flowerBag).onPickupItem(event);
        if (event.isCanceled() || event.getResult() == Event.Result.DENY) {
            ci.cancel();
        }
    }
}
