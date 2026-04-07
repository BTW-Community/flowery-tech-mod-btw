package baubles.common;

import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import java.util.List;


/**
 * To be used to create standalone (i.e can't be used outside a bauble slot) bauble
 */
public abstract class BaubleItemBase extends Item implements IBaubleExpanded {

    public BaubleItemBase(int id){
        super(id);
        setCreativeTab(CreativeTabs.tabMisc);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        return BaubleItemHelper.onBaubleRightClick(itemStackIn, worldIn, player);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean debug) {
        BaubleItemHelper.addSlotInformation(tooltip, getBaubleTypes(stack));
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase player) {}

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase player) {}

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase player) {}
}
