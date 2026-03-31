package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {
    private Entity ths() {
        return (Entity) (Object) this;
    }
    @WrapOperation(method = "entityDropItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;spawnEntityInWorld(Lnet/minecraft/src/Entity;)Z"))
    private boolean forge$onDropItemEntity(World instance, Entity item, Operation<Boolean> original, @Local EntityItem entityItem) {
        if (ths().getCaptureDrops()) {
            ths().getCapturedDrops().add(entityItem);
            return false;
        }
        else return original.call(instance, item);
    }
}
