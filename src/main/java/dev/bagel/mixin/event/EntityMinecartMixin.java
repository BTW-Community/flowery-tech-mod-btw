package dev.bagel.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import dev.bagel.interfaces.MinecartExtensions;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityMinecart.class)
public abstract class EntityMinecartMixin extends Entity {
    public EntityMinecartMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;getEntitiesWithinAABBExcludingEntity(Lnet/minecraft/src/Entity;Lnet/minecraft/src/AxisAlignedBB;)Ljava/util/List;", shift = At.Shift.AFTER))
    private void forge$onMinecartUpdate(CallbackInfo ci) {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.posY);
        int z = MathHelper.floor_double(this.posZ);
        MinecartUpdateEvent.EVENT.invoker().onMinecartUpdate(new MinecartUpdateEvent((EntityMinecart) (Object) this, x, y, z));
    }

    @Inject(method = "updateOnTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityMinecart;moveEntity(DDD)V", shift = At.Shift.AFTER))
    private void forge$jankyNotSameAsForgeMoveMinecartOnRail(int par1, int par2, int par3, double par4, double par6, int par8, int par9, CallbackInfo ci){
        if (((EntityMinecart) (Object) this) instanceof MinecartExtensions ex) {
            ex.moveMinecartOnRail(par1, par2, par3, par4);
        }
    }
}
