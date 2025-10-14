package dev.bagel.interfaces;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public interface ItemExtensions {
    public default ItemStack getContainerItem(ItemStack itemStack) {
        return null;
    }

    public default boolean hasContainerItem(ItemStack stack) {
        return false;
    }

    /**
     * Called each tick while using an item.
     *
     * @param stack  The Item being used
     * @param player The Player using the item
     * @param count  The amount of time in tick the item has been used for continuously
     */
    public default void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
    }

    /**
     * Called before a block is broken.  Return true to prevent default block harvesting.
     * <p>
     * Note: In SMP, this is called on both client and server sides!
     *
     * @param itemstack The current ItemStack
     * @param X         The X Position
     * @param Y         The X Position
     * @param Z         The X Position
     * @param player    The Player that is wielding the item
     * @return True to prevent harvesting, false to continue as normal
     */
    public default boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
        return false;
    }

    /**
     * Player, Render pass, and item usage sensitive version of getIconIndex.
     *
     * @param stack        The item stack to get the icon for. (Usually this, and usingItem will be the same if usingItem is not null)
     * @param renderPass   The pass to get the icon for, 0 is default.
     * @param player       The player holding the item
     * @param usingItem    The item the player is actively using. Can be null if not using anything.
     * @param useRemaining The ticks remaining for the active item.
     * @return The icon index
     */
    public default Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return null;
    }

    /**
     * Return the correct icon for rendering based on the supplied ItemStack and render pass.
     *
     * Defers to {@link net.minecraft.src.Item#getIconFromDamageForRenderPass(int, int)}
     * @param stack to render for
     * @param pass the multi-render pass
     * @return the icon
     */
    public default Icon getIcon(ItemStack stack, int pass)
    {
        return null;
    }

    default public int getRenderPasses(int metadata)
    {
        return 1;
    }

    //Armor stuff

    /**
     * Called to tick armor in the armor slot. Override to do something
     *
     * @param world
     * @param player
     * @param itemStack
     */
    public default void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {

    }

    /**
     * Called by RenderBiped and RenderPlayer to determine the armor texture that
     * should be use for the currently equiped item.
     * This will only be called on instances of ItemArmor.
     *
     * Returning null from this function will use the default value.
     *
     * @param stack ItemStack for the equpt armor
     * @param entity The entity wearing the armor
     * @param slot The slot the armor is in
     * @param type The subtype, can be null or "overlay"
     * @return Path of texture to bind, or null to use default
     */
    public default String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return null;
    }

    /**
     * Override this method to have an item handle its own armor rendering.
     *
     * @param  entityLiving  The entity wearing the armor
     * @param  itemStack  The itemStack to render the model of
     * @param  armorSlot  0=head, 1=torso, 2=legs, 3=feet
     *
     * @return  A ModelBiped to render instead of the default
     */
    @Environment(EnvType.CLIENT)
    public default ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        return null;
    }
}
