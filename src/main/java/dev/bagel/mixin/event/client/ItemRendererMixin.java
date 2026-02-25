package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

@Debug(export = true)
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Shadow private RenderBlocks renderBlocksInstance;

    @Shadow private Minecraft mc;

    //this targets all 3 calls
    @WrapOperation(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemRenderer;renderItem(Lnet/minecraft/src/EntityLivingBase;Lnet/minecraft/src/ItemStack;I)V", ordinal = 2))
    private void forge$renderCustomFirstPersonModel(ItemRenderer instance, EntityLivingBase entity, ItemStack stack, int pass, Operation<Void> original) {
        IItemRenderer.ItemRenderType type = EQUIPPED_FIRST_PERSON;
        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(stack, type);
        if (customRenderer != null)
        {
            GL11.glPushMatrix();
            TextureManager var4 = this.mc.getTextureManager();
            var4.bindTexture(var4.getResourceLocation(stack.getItemSpriteNumber()));
            ForgeHooksClient.renderEquippedItem(type, customRenderer, renderBlocksInstance, entity, stack);
            GL11.glPopMatrix();
        }
        else {
            original.call(instance, entity, stack, pass);
        }
    }

    @Redirect(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;requiresMultipleRenderPasses()Z", ordinal = 0))
    private boolean forge$cancel(Item instance) {
        return false;
    }

    @WrapOperation(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemRenderer;renderItem(Lnet/minecraft/src/EntityLivingBase;Lnet/minecraft/src/ItemStack;I)V", ordinal = 2))
    private void forge$wrapRenderItem(ItemRenderer instance, EntityLivingBase entity, ItemStack itemStack, int pass, Operation<Void> original) {
        original.call(instance, entity, itemStack, 0);
        if (itemStack.getItem().requiresMultipleRenderPasses()) {
            for (int x = 1; x < itemStack.getItem().getRenderPasses(itemStack.getItemDamage()); x++) {
                int k1 = itemStack.getItem().getColorFromItemStack(itemStack, x);
                float f10 = (float) (k1 >> 16 & 255) / 255.0F;
                float f11 = (float) (k1 >> 8 & 255) / 255.0F;
                float f12 = (float) (k1 & 255) / 255.0F;
                GL11.glColor4f(1.0F * f10, 1.0F * f11, 1.0F * f12, 1.0F);
                original.call(instance, entity, itemStack, x);
            }
        }
    }

    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void forge$renderItem(EntityLivingBase entity, ItemStack stack, int par3, CallbackInfo ci) {
        if (stack == null || stack.getItem() == null) return;
        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
        if (customRenderer != null) {
            GL11.glPushMatrix();
            TextureManager texturemanager = this.mc.getTextureManager();
            texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
            ForgeHooksClient.renderEquippedItem(IItemRenderer.ItemRenderType.EQUIPPED, customRenderer, renderBlocksInstance, entity, stack);
            GL11.glPopMatrix();
            ci.cancel();
        }
    }
}
