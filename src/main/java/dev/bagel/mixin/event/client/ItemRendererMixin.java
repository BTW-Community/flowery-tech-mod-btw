package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Shadow private RenderBlocks renderBlocksInstance;

    @Shadow private Minecraft mc;

    //this targets all 3 calls
    @WrapOperation(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemRenderer;renderItem(Lnet/minecraft/src/EntityLivingBase;Lnet/minecraft/src/ItemStack;I)V"))
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
}
