package dev.bagel.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.bagel.interfaces.CustomBoundingBoxBlock;
import dev.bagel.interfaces.WorldProviderExtensions;
import net.minecraft.src.*;
import net.minecraftforge.client.IRenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin {
    @Shadow
    private Minecraft mc;

    @Shadow
    private WorldClient theWorld;

    @Shadow
    protected abstract void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB);

    @Redirect(method = "drawSelectionBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/src/AxisAlignedBB;)V"))
    private void drawCustomSelectionBox(RenderGlobal instance, AxisAlignedBB aabb, EntityPlayer player, MovingObjectPosition pos, int par3, float par4) {
        int blockId = this.theWorld.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
        Block block = Block.blocksList[blockId];
        if (block instanceof CustomBoundingBoxBlock cbbb) {

            double posX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)par4;
            double posY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)par4;
            double posZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)par4;
            for (AxisAlignedBB bb : cbbb.getCustomSelectionBoxes(this.theWorld, pos.blockX, pos.blockY, pos.blockZ)) {
                bb = bb.makeTemporaryCopy();
                float expand = cbbb.boxExpansion();
                if (cbbb.rotatable()) {
                    int facing = block.getFacing(theWorld, pos.blockX, pos.blockY, pos.blockZ);
                    bb.rotateAroundYToFacing(facing);
                    bb.tiltToFacingAlongY(facing);
                }
                bb = bb.offset(pos.blockX, pos.blockY, pos.blockZ).expand(expand, expand, expand).getOffsetBoundingBox(-posX, -posY, -posZ);
                drawOutlinedBoundingBox(bb);
            }
        }
        else {
            drawOutlinedBoundingBox(aabb);
        }
    }

    @WrapOperation(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/TileEntityRenderer;renderTileEntity(Lnet/minecraft/src/TileEntity;F)V"))
    private void test(TileEntityRenderer instance, TileEntity tile, float floatVal, Operation<Void> original, @Local(argsOnly = true) ICamera camera) {
        if (camera.isBoundingBoxInFrustum(tile.getRenderBoundingBox())) {
            original.call(instance, tile, floatVal);
        }
    }

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void forge$onRenderSky(float partial, CallbackInfo ci) {
        IRenderHandler skyProvider;
        if ((skyProvider = ((WorldProviderExtensions) this.mc.theWorld.provider).getSkyRenderer()) != null)
        {
            skyProvider.render(partial, this.theWorld, mc);
            ci.cancel();
        }
    }

    private void drawOutlinedBoundingBoxTest(AxisAlignedBB par1AxisAlignedBB) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(3);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.draw();
        tessellator.startDrawing(3);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.draw();
        tessellator.startDrawing(1);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        tessellator.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        tessellator.draw();
    }
}
