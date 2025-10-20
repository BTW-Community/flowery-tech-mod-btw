package dev.bagel.mixin.event;

import net.minecraft.src.*;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInWorldManager.class)
public class ItemInWorldManagerMixin {
    @Shadow
    public EntityPlayerMP thisPlayerMP;

    @Shadow
    public World theWorld;

    @Inject(method = "activateBlockOrUseItem", at = @At("HEAD"), cancellable = true)
    private void forge$onActivateBlockOrUseItem(EntityPlayer player, World world, ItemStack par3ItemStack, int x, int y, int z, int par7, float par8, float par9, float par10, CallbackInfoReturnable<Boolean> cir) {
        PlayerInteractEvent event = ForgeEventFactory.onPlayerInteract(player, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, x, y, z, par7, world);
        if (event.isCanceled()) {
            thisPlayerMP.playerNetServerHandler.sendPacket(new Packet53BlockChange(x, y, z, theWorld));
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onBlockClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayerMP;isCurrentToolAdventureModeExempt(III)Z", shift = At.Shift.AFTER), cancellable = true)
    private void forge$onActivateBlockOrUseItem(int x, int y, int z, int par7, CallbackInfo ci) {
        PlayerInteractEvent event = ForgeEventFactory.onPlayerInteract(thisPlayerMP, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, x, y, z, par7, theWorld);
        if (event.isCanceled()) {
            thisPlayerMP.playerNetServerHandler.sendPacket(new Packet53BlockChange(x, y, z, theWorld));
            ci.cancel();
        }
    }

    @Inject(method = "tryHarvestBlock(IIII)Z", at = @At("HEAD"), cancellable = true)
    private void forge$onBlockStartBreak(int i, int j, int k, int iFromSide, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = thisPlayerMP.getCurrentEquippedItem();
        if (stack != null && stack.getItem().onBlockStartBreak(stack, i, j, k, thisPlayerMP)) {
            cir.setReturnValue(false);
        }
    }
}
