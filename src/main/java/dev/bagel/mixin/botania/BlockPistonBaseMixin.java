package dev.bagel.mixin.botania;

import api.world.BlockPos;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.entity.EntitySpark;

//Sometimes, you must ask why not instead of why. Is it blursed? Absolutely. Do I love it? Absolutely.
@Mixin(BlockPistonBase.class)
public class BlockPistonBaseMixin {
    @Inject(method = "onBlockEventReceived", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/BlockPistonBase;getBlockTileEntityData(Lnet/minecraft/src/World;III)Lnet/minecraft/src/NBTTagCompound;"))
    private void botania$onBlockEventReceived(World world, int x, int y, int z, int idfk, int facing, CallbackInfoReturnable<Boolean> cir) {
        BlockPos oldPos = new BlockPos(x, y, z, facing);
        TileEntity tile = world.getTileEntity(oldPos.x, oldPos.y, oldPos.z);
        if (tile instanceof ISparkAttachable attachable && attachable.getAttachedSpark() != null) {
            if (attachable.getAttachedSpark() instanceof EntitySpark spark) {
                spark.ticksToStopProcessingForPistonUpdate = 5;
                spark.setPosition(x + 0.5, y + 1.5, z + 0.5);
            }
        } else if (tile instanceof IInventory inv && CorporeaHelper.getSparkForInventory(inv) instanceof EntityCorporeaSpark spark) {
            spark.ticksToStopProcessingForPistonUpdate = 5;
            spark.setPosition(x + 0.5, y + 1.5, z + 0.5);
        }
    }

    @Inject(method = "tryExtend", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/BlockPistonBase;getBlockTileEntityData(Lnet/minecraft/src/World;III)Lnet/minecraft/src/NBTTagCompound;"))
    private void botania$tryExtend(World world, int x, int y, int z, int facingTo, CallbackInfoReturnable<Boolean> cir, @Local(name = "movingX") int movingX, @Local(name = "movingY") int movingY, @Local(name = "movingZ") int movingZ) {
        BlockPos oldPos = new BlockPos(movingX, movingY, movingZ, facingTo);
        TileEntity tile = world.getTileEntity(oldPos.x, oldPos.y, oldPos.z);
        BlockPos newPos = new BlockPos(oldPos.x, oldPos.y, oldPos.z, facingTo);
        if (tile instanceof ISparkAttachable attachable && attachable.getAttachedSpark() instanceof EntitySpark spark) {
            spark.ticksToStopProcessingForPistonUpdate = 5;
            spark.setPosition(newPos.x + 0.5, newPos.y + 1.5, newPos.z + 0.5);
        } else if (tile instanceof IInventory inv && CorporeaHelper.getSparkForInventory(inv) instanceof EntityCorporeaSpark spark) {
            spark.ticksToStopProcessingForPistonUpdate = 5;
            spark.setPosition(newPos.x + 0.5, newPos.y + 1.5, newPos.z + 0.5);
        }

    }
}
