package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin extends Render {
    @Shadow private Random random;
    @Shadow public boolean renderWithColor;

    @Shadow public float zLevel;

    @Inject(method = "renderItemAndEffectIntoGUI", at = @At(value = "HEAD"), cancellable = true)
    private void btb$renderCustomInvItem(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (stack != null && ForgeHooksClient.renderInventoryItem(this.renderBlocks, textureManager, stack, this.renderWithColor, this.zLevel, (float) x, (float) y)) {
            ci.cancel();
        }
    }

    //this is fine mcdev just is dumb
    @Inject(method = "doRenderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemStack;getItemSpriteNumber()I"),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void btb$renderCustomItemEntity(EntityItem par1EntityItem, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci, ItemStack stack, float bobbing, float rotation) {
        if (ForgeHooksClient.renderEntityItem(par1EntityItem, stack, bobbing, rotation, this.random, this.renderManager.renderEngine, this.renderBlocks)) {
            //these are called normally afterwards
            GL11.glDisable(32826);
            GL11.glPopMatrix();
            ci.cancel();
        }
    }

    @ModifyArgs(method = "doRenderItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;")),
            at = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;", remap = false))
    private void forge$stackSensetiveItem(Args args, @Local ItemStack stack, @Local int pass) {
        args.set(0, stack.getItem().getIcon(stack, pass));
    }

    @ModifyExpressionValue(method = "doRenderItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z", shift = At.Shift.AFTER)),
            at = @At(value = "CONSTANT", args = "intValue=1"))
    private int forge$multipleRenderPasses(int constant, @Local ItemStack stack) {
        return stack.getItem().getRenderPasses(stack.getItemDamage());
    }

    @ModifyArgs(method = "renderItemIntoGUI",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z")),
            at = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;", remap = false))
    private void forge$stackSensetiveItem2(Args args, @Local ItemStack stack, @Local(ordinal = 5) int pass) {
        args.set(0, stack.getItem().getIcon(stack, pass));
    }

    @ModifyExpressionValue(method = "renderItemIntoGUI",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z", shift = At.Shift.AFTER)),
            at = @At(value = "CONSTANT", args = "intValue=1"))
    private int forge$multipleRenderPasses2(int constant, @Local(argsOnly = true) ItemStack stack) {
        return stack.getItem().getRenderPasses(stack.getItemDamage());
    }
}
