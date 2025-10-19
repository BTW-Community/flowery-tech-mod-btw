package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.src.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow public MovingObjectPosition objectMouseOver;

    @Shadow public WorldClient theWorld;

    @Shadow public EntityClientPlayerMP thePlayer;

    @WrapOperation(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/PlayerControllerMP;onPlayerRightClick(Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;Lnet/minecraft/src/ItemStack;IIIILnet/minecraft/src/Vec3;)Z"))
    private boolean forge$onClick(PlayerControllerMP instance, EntityPlayer var15, World world, ItemStack par1EntityPlayer, int par2World, int par3ItemStack, int par4, int par5, Vec3 par6, Operation<Boolean> original) {
        boolean result = !net.minecraftforge.event.ForgeEventFactory.onPlayerInteract(thePlayer, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ, this.objectMouseOver.sideHit, this.theWorld).isCanceled();
        return result && original.call(instance, var15, world, par1EntityPlayer, par2World, par3ItemStack, par4, par5, par6);
    }

    @WrapOperation(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/PlayerControllerMP;sendUseItem(Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;Lnet/minecraft/src/ItemStack;)Z"))
    private boolean forge$onClick2(PlayerControllerMP instance, EntityPlayer player, World world, ItemStack stack, Operation<Boolean> original) {
        boolean result = !net.minecraftforge.event.ForgeEventFactory.onPlayerInteract(thePlayer, PlayerInteractEvent.Action.RIGHT_CLICK_AIR, 0, 0, 0, -1, this.theWorld).isCanceled();
        return result && original.call(instance, player, world, stack);
    }
}
