package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
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

@Debug(export = true)
@Mixin(RenderItem.class)
public abstract class RenderItemMixin extends Render {
    @Shadow private Random random;
    @Shadow public boolean renderWithColor;

    @Shadow public float zLevel;

    @Inject(method = "renderItemAndEffectIntoGUI", at = @At(value = "HEAD"), cancellable = true)
    private void forge$renderCustomInvItem(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (stack != null && ForgeHooksClient.renderInventoryItem(this.renderBlocks, textureManager, stack, this.renderWithColor, this.zLevel, (float) x, (float) y)) {
            ci.cancel();
        }
    }

    @Inject(method = "doRenderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemStack;getItemSpriteNumber()I", ordinal = 0),
            cancellable = true)
    private void forge$renderCustomItemEntity(EntityItem par1EntityItem, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci, @Local ItemStack stack, @Local(ordinal = 2) float bobbing, @Local(ordinal = 3) float rotation) {
        if (ForgeHooksClient.renderEntityItem(par1EntityItem, stack, bobbing, rotation, this.random, this.renderManager.renderEngine, this.renderBlocks)) {
            //these are called normally afterwards
            GL11.glDisable(32826);
            GL11.glPopMatrix();
            ci.cancel();
        }
    }


    @ModifyExpressionValue(method = "doRenderItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z", shift = At.Shift.AFTER)),
            at = @At(value = "CONSTANT", args = "intValue=1"))
    private int forge$multipleRenderPasses(int constant, @Local ItemStack stack) {
        return stack.getItem().getRenderPasses(stack.getItemDamage()) - 1;
    }

    @ModifyArgs(method = "doRenderItem",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;")),
            at = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;"))
    private void forge$stackSensetiveItem(Args args) {
        ItemStack stack = args.get(1);
        int pass = args.get(2);
        args.set(0, stack.getItem().getIcon(stack, pass));
    }

    @ModifyArgs(method = "renderItemIntoGUI",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z")),
            at = @At(value = "INVOKE", target = "Lcom/prupe/mcpatcher/cit/CITUtils;getIcon(Lnet/minecraft/src/Icon;Lnet/minecraft/src/ItemStack;I)Lnet/minecraft/src/Icon;"))
    private void forge$stackSensetiveItem2(Args args, @Local(argsOnly = true) ItemStack stack, @Local(ordinal = 5) int pass) {
        args.set(0, stack.getItem().getIcon(stack, pass));
    }

    @ModifyExpressionValue(method = "renderItemIntoGUI",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z", shift = At.Shift.AFTER)),
            at = @At(value = "CONSTANT", args = "intValue=1"))
    private int forge$multipleRenderPasses2(int constant, @Local(argsOnly = true) ItemStack stack) {
        return stack.getItem().getRenderPasses(stack.getItemDamage()) - 1;
    }

    @Redirect(method = "renderItemOverlayIntoGUI(Lnet/minecraft/src/FontRenderer;Lnet/minecraft/src/TextureManager;Lnet/minecraft/src/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemStack;isItemDamaged()Z"))
    private boolean forge$renderItemOverlayIntoGUI(ItemStack stack) {
        return stack.getItem().showDurabilityBar(stack);
    }

    @Inject(method = "renderItemOverlayIntoGUI(Lnet/minecraft/src/FontRenderer;Lnet/minecraft/src/TextureManager;Lnet/minecraft/src/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 7))
    private void forge$renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, String par6Str, CallbackInfo ci, @Local(name = "var12") LocalIntRef var12, @Local(name = "var8") LocalIntRef var8) {
        double health = par3ItemStack.getItem().getDurabilityForDisplay(par3ItemStack);
        var12.set((int) Math.round(13.0D - health * 13.0D));
        var8.set((int) Math.round(255.0D - health * 255.0D));
    }
}
