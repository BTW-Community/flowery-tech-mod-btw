package dev.bagel.mixin.extensions;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IGuiHandler;
import dev.bagel.interfaces.EntityPlayerExtensions;
import dev.bagel.network.CustomGuiPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerExtensionsMixin extends EntityLivingBase implements EntityPlayerExtensions {
    @Shadow public InventoryPlayer inventory;

    public EntityPlayerExtensionsMixin(World par1World) {
        super(par1World);
    }

    @Override
    public void openGui(String modId, int modGuiId, World world, int x, int y, int z) {
        EntityPlayer player = ((EntityPlayer) (Object) this);
        if (!player.worldObj.isRemote) {
            forge$openRemoteGui(modId, ((EntityPlayerMP) player), modGuiId, world, x, y, z);
        } else {
            forge$openLocalGui(modId, player, modGuiId, world, x, y, z);
        }
    }

    @Environment(EnvType.CLIENT)
    private void forge$openLocalGui(String modId, EntityPlayer player, int modGuiId, World world, int x, int y, int z) {
        IGuiHandler handler = CustomGuiPacketHandler.INSTANCE.modIdToHandler.get(modId);
        if (handler != null) {
            var guiElement = handler.getClientGuiElement(modGuiId, player, world, x, y, z);
            if (guiElement instanceof GuiContainer container) {
                Minecraft.getMinecraft().displayGuiScreen(container);
            }

        } else {
            System.err.println("Packet failed to send, mod id is " + modId + " with packet id of " + modGuiId);
        }
    }

    private void forge$openRemoteGui(String modId, EntityPlayerMP player, int modGuiId, World world, int x, int y, int z) {
        IGuiHandler handler = CustomGuiPacketHandler.INSTANCE.modIdToHandler.get(modId);
        if (handler != null) {
            Container container = (Container) handler.getServerGuiElement(modGuiId, player, world, x, y, z);
            if (container != null) {
                player.incrementAndGetWindowID();
                player.closeContainer();
                int windowId = player.currentWindowId;
                Packet250CustomPayload pkt = new Packet250CustomPayload("botania|GUI", forge$generateGuiPacket(modId, windowId, modGuiId, x, y, z));
                player.playerNetServerHandler.sendPacketToPlayer(pkt);
                player.openContainer = container;
                player.openContainer.windowId = windowId;
                player.openContainer.onCraftGuiOpened(player);

            }
        }
    }

    private byte[] forge$generateGuiPacket(String modId, int windowId, int modGuiId, int x, int y, int z) {
        ByteArrayDataOutput dat = ByteStreams.newDataOutput();
        dat.writeInt(windowId);
        dat.writeUTF(modId);
        dat.writeInt(modGuiId);
        dat.writeInt(x);
        dat.writeInt(y);
        dat.writeInt(z);
        return dat.toByteArray();
    }
}
