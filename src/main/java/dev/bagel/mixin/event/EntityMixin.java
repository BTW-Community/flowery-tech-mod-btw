package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
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
    @Redirect(method = "entityDropItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;spawnEntityInWorld(Lnet/minecraft/src/Entity;)Z"))
    private boolean forge$onDropItem(World instance, Entity entityItem) {
        if (ths().getCaptureDrops()) {
            ths().getCapturedDrops().add((EntityItem) entityItem);
            return false;
        }
        else return instance.spawnEntityInWorld(entityItem);
    }
}
