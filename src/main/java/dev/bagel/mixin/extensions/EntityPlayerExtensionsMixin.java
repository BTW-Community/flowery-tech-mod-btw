package dev.bagel.mixin.extensions;

import cpw.mods.fml.common.network.IGuiHandler;
import dev.bagel.interfaces.EntityPlayerExtensions;
import dev.bagel.network.CustomGuiPacketHandler;
import net.fabricmc.api.EnvType;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public class EntityPlayerExtensionsMixin implements EntityPlayerExtensions {
    @Shadow public InventoryPlayer inventory;

    @Override
    public void openGui(String modId, int modGuiId, World world, int x, int y, int z) {
        IGuiHandler handler = CustomGuiPacketHandler.INSTANCE.modIdToHandler.get(modId);
        if (handler != null) {
            EnvType side = CustomGuiPacketHandler.getEffectiveSide();
            if (side == EnvType.CLIENT) {
                Object elem = handler.getClientGuiElement(modGuiId, (EntityPlayer) (Object) this, world, x, y, z);
                if (elem instanceof GuiScreen gs) {
                    Minecraft.getMinecraft().displayGuiScreen(gs);
                }
            }
            else { //server
                EntityPlayerMP player = (EntityPlayerMP) (Object) this;

                Object elem = handler.getServerGuiElement(modGuiId, player, world, x, y, z);
                CustomGuiPacketHandler.INSTANCE.writePacket(player, modId, modGuiId, x, y, z, elem);
            }
        }
        else {
            System.err.println("No handler found for modId " + modId);
        }

//        System.out.printf("trying to open gui, args: gui=%s id=%s world=%s x=%d y=%d z=%d%n", handler, modGuiId, world, x, y, z);
    }
}
