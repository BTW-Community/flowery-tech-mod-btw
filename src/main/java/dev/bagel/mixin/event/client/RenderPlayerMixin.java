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
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;

@Debug(export = true)
@Mixin( RenderPlayer.class)
public abstract class RenderPlayerMixin extends RendererLivingEntity {

    public RenderPlayerMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @ModifyExpressionValue(method = "func_130220_b", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderBiped;func_110858_a(Lnet/minecraft/src/ItemArmor;ILjava/lang/String;)Lnet/minecraft/src/ResourceLocation;"))
    private static ResourceLocation forge$addCustomRenderModel1(ResourceLocation original, @Local(argsOnly = true) int slot, @Local ItemStack var4, @Local(argsOnly = true) AbstractClientPlayer living) {
        return getArmorResource(living, var4, slot, "overlay");
    }
    @ModifyExpressionValue(method = "setArmorModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderBiped;func_110857_a(Lnet/minecraft/src/ItemArmor;I)Lnet/minecraft/src/ResourceLocation;"))
    private static ResourceLocation forge$addCustomRenderModel2(ResourceLocation original, @Local(argsOnly = true) int slot, @Local ItemStack var4, @Local(argsOnly = true) AbstractClientPlayer living) {
        return getArmorResource(living, var4, slot, null);
    }

    private static String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};

    private static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
        ItemArmor item = (ItemArmor) stack.getItem();
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png",
                bipedArmorFilenamePrefix[item.renderIndex], (slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", type));

        s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
        ResourceLocation resourcelocation = (ResourceLocation) RenderBiped.field_110859_k.get(s1);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            RenderBiped.field_110859_k.put(s1, resourcelocation);
        }

        return resourcelocation;
    }

    @ModifyArgs(method = "setArmorModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderPlayer;setRenderPassModel(Lnet/minecraft/src/ModelBase;)V"))
    private void getForgeArmorModel(Args args, @Local(argsOnly = true) AbstractClientPlayer living, @Local ItemStack stack, @Local(argsOnly = true) int slot) {
        var model = ForgeHooksClient.getArmorModel(living, stack, slot, args.get(0));
        System.out.println("rendering armor, old " + args.get(0) + "new " + model);
        args.set(0, model);
    }

}
