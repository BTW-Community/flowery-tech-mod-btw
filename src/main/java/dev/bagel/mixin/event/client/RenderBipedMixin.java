package dev.bagel.mixin.event.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;

@Mixin( RenderBiped.class)
public abstract class RenderBipedMixin extends RenderLiving {
    @Shadow
    @Final
    private static Map field_110859_k;

    public RenderBipedMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @ModifyExpressionValue(method = "func_130006_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderBiped;func_110857_a(Lnet/minecraft/src/ItemArmor;I)Lnet/minecraft/src/ResourceLocation;"))
    private static ResourceLocation forge$addCustomRenderModel(ResourceLocation original, @Local(argsOnly = true) int slot, @Local ItemStack var4, @Local(argsOnly = true) EntityLiving living) {
        return getArmorResource(living, var4, slot, null);
    }

    private static String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};

    private static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
        ItemArmor item = (ItemArmor) stack.getItem();
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png",
                bipedArmorFilenamePrefix[item.renderIndex], (slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", type));

        s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
        ResourceLocation resourcelocation = (ResourceLocation) field_110859_k.get(s1);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }

        return resourcelocation;
    }

    @ModifyArgs(method = "func_130006_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderBiped;setRenderPassModel(Lnet/minecraft/src/ModelBase;)V"))
    private void getForgeArmorModel(Args args, @Local(argsOnly = true) EntityLiving living, @Local ItemStack stack, @Local(argsOnly = true) int slot) {
        args.set(0, ForgeHooksClient.getArmorModel(living, stack, slot, args.get(0)));
    }

    @Override
    protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_)
    {
        this.func_82408_c((EntityLiving)p_82408_1_, p_82408_2_, p_82408_3_);
    }

    protected void func_82408_c(EntityLiving p_82408_1_, int p_82408_2_, float p_82408_3_)
    {
        ItemStack itemstack = p_82408_1_.func_130225_q(3 - p_82408_2_);

        if (itemstack != null)
        {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor)
            {
                this.bindTexture(getArmorResource(p_82408_1_, itemstack, p_82408_2_, "overlay"));
                float f1 = 1.0F;
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
        }
    }
}
