package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.EntityExtensions;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Entity.class)
public abstract class EntityExtensionMixin implements EntityExtensions {
    private ArrayList<EntityItem> capturedItems = new ArrayList<>();
    private boolean captureDrops = false;
    private NBTTagCompound forge$customEntityData;

    @Override
    public NBTTagCompound getEntityData() {
        if (forge$customEntityData == null) {
            forge$customEntityData = new NBTTagCompound();
        }
        return forge$customEntityData;
    }

    @Inject(method = "readFromNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Entity;setRotation(FF)V"))
    private void forge$readExtraData(NBTTagCompound tag, CallbackInfo ci) {
        if (tag.hasKey("extraData")) {
            forge$customEntityData = tag.getCompoundTag("extraData");
        }
    }

    @Inject(method = "writeToNBT", at = @At(value = "INVOKE", target = "Ljava/util/UUID;getLeastSignificantBits()J", shift = At.Shift.AFTER))
    private void forge$writeExtraData(NBTTagCompound tag, CallbackInfo ci) {
        if (forge$customEntityData != null) {
            tag.setTag("extraData", forge$customEntityData);
        }
    }

    @Override
    public ArrayList<EntityItem> getCapturedDrops() {
        return capturedItems;
    }

    @Override
    public void setCaptureDrops(boolean flag) {
        this.captureDrops = flag;
    }

    @Override
    public boolean getCaptureDrops() {
        return captureDrops;
    }
}
