package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.ItemExtensions;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Item.class)
public abstract class ItemExtensionMixin implements ItemExtensions {

    @Shadow
    public abstract boolean hasContainerItem();

    @Shadow
    public abstract Item getContainerItem();

    @Shadow
    public abstract Icon getIconFromDamageForRenderPass(int par1, int par2);

    @Shadow public abstract boolean requiresMultipleRenderPasses();

    @Shadow public abstract boolean isDamageable();

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
    public double getDurabilityForDisplay(ItemStack stack) {
        return (double) stack.getItemDamageForDisplay() / (double) stack.getMaxDamage();
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

    protected boolean canRepair = true;
    /**
     * Called by CraftingManager to determine if an item is reparable.
     * @return True if reparable
     */
    public boolean isRepairable()
    {
        return canRepair && isDamageable();
    }

    /**
     * Call to disable repair recipes.
     * @return The current Item instance
     */
    public Item setNoRepair()
    {
        canRepair = false;
        return (Item) (Object) this;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;getUnlocalizedName()Ljava/lang/String;"))
    private String testit(Item instance) {
        //dont do nothin
        if (instance == null)
            return "unknown";
        try {
            return instance.getUnlocalizedName();
        }
        catch (Throwable e) {
            return "instance is null";
        }

    }

//    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;getUnlocalizedName()Ljava/lang/String;"))
//    private String testbl(Item instance) {
//        //dont do nothin
//        if (instance == null)
//            return "unknown";
//        else return instance.getUnlocalizedName();
//    }
}
