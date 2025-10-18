package dev.bagel.mixin.event;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.SlotCrafting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ItemCraftingHalo;
import vazkii.botania.common.item.ModItems;

@Mixin(SlotCrafting.class)
public class SlotCraftingMixin {
    @Shadow @Final private IInventory craftMatrix;

    //ItemCraftedEvent
    @Inject(method = "onPickupFromSlot", at = @At("HEAD"))
    private void forge$onPickupFromSlot(EntityPlayer player, ItemStack stack, CallbackInfo ci) {
        var event = new PlayerEvent.ItemCraftedEvent(player, stack, this.craftMatrix);
        ((ItemCraftingHalo) ModItems.craftingHalo).onItemCrafted(event);
        ((ItemCraftingHalo) ModItems.autocraftingHalo).onItemCrafted(event);
    }
}
