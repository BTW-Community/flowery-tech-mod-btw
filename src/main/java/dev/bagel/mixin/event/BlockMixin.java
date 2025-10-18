package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.bagel.interfaces.BlockExtensions;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public abstract class BlockMixin implements BlockExtensions {
    @Shadow
    protected abstract void dropBlockAsItem_do(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack);

    private EntityPlayer forge$harvester;

    /**
     * @author Bagel
     * @reason This completely replaces the drops method
     */
    @Overwrite
    public void dropBlockAsItemWithChance(World worldIn, int x, int y, int z, int meta, float chance, int fortune) {
        if (!worldIn.isRemote) {
            ArrayList<ItemStack> items = getDrops(worldIn, x, y, z, meta, fortune);
            chance = ForgeEventFactory.fireBlockHarvesting(items, worldIn, (Block) (Object) this, x, y, z, meta, fortune, chance, false, forge$harvester);

            for (ItemStack item : items) {
                if (worldIn.rand.nextFloat() <= chance) {
                    this.dropBlockAsItem_do(worldIn, x, y, z, item);
                }
            }
        }
    }

    @Inject(method = "harvestBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EnchantmentHelper;getFortuneModifier(Lnet/minecraft/src/EntityLivingBase;)I"))
    private void forge$harvestBlockPre(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6, CallbackInfo ci) {
        forge$harvester = par2EntityPlayer;
    }

    @Inject(method = "harvestBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Block;dropBlockAsItem(Lnet/minecraft/src/World;IIIII)V", shift = At.Shift.AFTER))
    private void forge$harvestBlockPost(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6, CallbackInfo ci) {
        forge$harvester = null;
    }

    @Inject(method = "harvestBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Block;createStackedBlock(I)Lnet/minecraft/src/ItemStack;"))
    private void forge$harvestBlockList(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6, CallbackInfo ci, @Share("items") LocalRef<ArrayList<ItemStack>> items) {
        items.set(new ArrayList<>());
    }

    @Redirect(method = "harvestBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Block;dropBlockAsItem_do(Lnet/minecraft/src/World;IIILnet/minecraft/src/ItemStack;)V"))
    private void forge$harvestBlock(Block instance, World worldIn, int x, int y, int z, ItemStack stack, @Local ItemStack droppedStack, @Local(argsOnly = true) EntityPlayer player, @Local(argsOnly = true, ordinal = 3) int meta, @Share("items") LocalRef<ArrayList<ItemStack>> items) {
        ArrayList<ItemStack> itemsDropped = items.get();
        itemsDropped.add(droppedStack);
        ForgeEventFactory.fireBlockHarvesting(itemsDropped, worldIn, (Block) (Object) this, x, y, z, meta, 0, 1.0f, true, player);
        for (ItemStack is : itemsDropped)
        {
            this.dropBlockAsItem_do(worldIn, x, y, z, is);
        }
    }
}
