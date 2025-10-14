package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.ItemExtensions;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Icon;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
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
}
