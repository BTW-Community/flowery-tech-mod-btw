package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.bagel.client.OpenGlHelper2;
import net.minecraft.src.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.client.core.helper.ShaderHelper;

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

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/OpenGlHelper;initializeTextures()V"))
    private void forge$invokeImprovedOGLHelper(CallbackInfo ci) {
        OpenGlHelper2.initializeTextures();
        System.err.println("ogl: "+OpenGlHelper2.func_153172_c());
    }

    @Inject(method = "loadWorld(Lnet/minecraft/src/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void forge$onWorldUnloadClient(WorldClient world, String par2Str, CallbackInfo ci) {
        if (theWorld != null) {
            WorldEvent.Unload.EVENT.invoker().onWorldUnload(new WorldEvent.Unload(theWorld));
        }
    }
}
