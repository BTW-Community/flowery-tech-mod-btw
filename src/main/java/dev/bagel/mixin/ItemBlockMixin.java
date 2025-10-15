package dev.bagel.mixin;

import btw.item.items.PlaceAsBlockItem;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlock.class)
public abstract class ItemBlockMixin extends PlaceAsBlockItem {
    public ItemBlockMixin(int iItemID, int iBlockID) {
        super(iItemID, iBlockID);
    }

    @Shadow public abstract int getBlockID();

    @Inject(method = "getSpriteNumber", at = @At("HEAD"), cancellable = true)
    private void getSpriteNumber(CallbackInfoReturnable<Integer> cir) {

        if (Block.blocksList[this.blockID] == null) {
            System.err.println("Block for block id " + this.blockID +" is: "+ Block.blocksList[this.blockID]);
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "registerIcons", at = @At("HEAD"), cancellable = true)
    private void regIcons(IconRegister par1IconRegister, CallbackInfo ci) {

        if (Block.blocksList[this.blockID] == null) {
            System.err.println("registerIcons for block " + this.blockID +" is: "+ Block.blocksList[this.blockID]);

            ci.cancel();
        }
    }
    @Inject(method = "getCreativeTab", at = @At("HEAD"), cancellable = true)
    private void regIcons(CallbackInfoReturnable<CreativeTabs> cir) {

        if (Block.blocksList[this.blockID] == null) {
            System.err.println("getCreativeTab for block " + this.blockID +" is: "+ Block.blocksList[this.blockID]);

            cir.setReturnValue(null);
        }
    }
}
