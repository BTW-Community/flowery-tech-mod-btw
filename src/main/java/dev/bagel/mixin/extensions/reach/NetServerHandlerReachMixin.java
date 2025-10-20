package dev.bagel.mixin.extensions.reach;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NetServerHandler.class)
public class NetServerHandlerReachMixin {
    @Shadow public EntityPlayerMP playerEntity;

    @ModifyConstant(method = "handleBlockDig", constant = @Constant(doubleValue = 36.0F))
    private double forge$modifyReach(double reach) {
        double dist = playerEntity.theItemInWorldManager.getBlockReachDistance() + 1;
        dist *= dist;

        return dist;
    }

    @ModifyConstant(method = "handlePlace", constant = @Constant(doubleValue = 64.0F))
    private double forge$modifyReach2(double reach) {
        double dist = playerEntity.theItemInWorldManager.getBlockReachDistance() + 1;
        dist *= dist;

        return dist;
    }
}
