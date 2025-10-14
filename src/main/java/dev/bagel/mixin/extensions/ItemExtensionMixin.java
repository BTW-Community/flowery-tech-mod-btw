package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.ItemExtensions;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemExtensionMixin implements ItemExtensions {

    @Shadow
    public abstract boolean hasContainerItem();

    @Shadow
    public abstract Item getContainerItem();

    @Shadow
    public abstract Icon getIconFromDamageForRenderPass(int par1, int par2);

    @Shadow public abstract boolean requiresMultipleRenderPasses();

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (!hasContainerItem()) {
            return null;
        }
        return new ItemStack(getContainerItem());
    }

    public boolean hasContainerItem(ItemStack stack) {
        return hasContainerItem();
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
        return false;
    }

    @Override
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return getIcon(stack, renderPass);
    }

    public Icon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public int getRenderPasses(int metadata) {
        return requiresMultipleRenderPasses() ? 2 : 1;
    }

    //Armor stuff

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return null;
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return null;
    }

    //damage
    @Override
    public int getDamage(ItemStack stack) {
        return stack.getItemDamage();
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {
        return getDamage(stack);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return 6000;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.isItemDamaged();
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return false;
    }
}
