package dev.bagel.mixin.botania;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.world.WorldTypeSkyblock;

@Mixin(WorldProvider.class)
public class GOGWorldProviderMixin {
    @Shadow public WorldType terrainType;

    @Shadow public World worldObj;

    @Inject(method = "createChunkGenerator", at = @At("HEAD"), cancellable = true)
    private void botania$addGog(CallbackInfoReturnable<IChunkProvider> cir) {
        if (this.terrainType instanceof WorldTypeSkyblock) {
            cir.setReturnValue(new ChunkProviderFlat(worldObj, worldObj.getSeed(), false, "2;1x0;"));
        }
    }

    @Inject(method = "getCloudHeight", at = @At("HEAD"), cancellable = true)
    private void botania$getCloudHeightGog(CallbackInfoReturnable<Float> cir) {
        if (this.terrainType instanceof WorldTypeSkyblock) {
            cir.setReturnValue(260F);
        }
    }
}
