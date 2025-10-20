package dev.bagel.mixin.extensions.reach;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Item.class)
public class ItemMixin {
    @ModifyConstant(method = "getMovingObjectPositionFromPlayer", constant = @Constant(doubleValue = 5.0))
    private double forge$modifyReach(double reach, @Local(argsOnly = true) EntityPlayer player) {
        if (player instanceof EntityPlayerMP mp) {
            reach = mp.theItemInWorldManager.getBlockReachDistance();
        }
        return reach;
    }
}
