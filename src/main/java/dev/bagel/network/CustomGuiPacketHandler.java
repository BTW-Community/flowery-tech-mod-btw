package dev.bagel.network;

import baubles.common.network.PacketHandler;
import btw.inventory.BTWContainers;
import btw.network.packet.handler.CustomPacketHandler;
import btw.world.util.WorldUtils;
import cpw.mods.fml.common.network.IGuiHandler;
import net.fabricmc.api.EnvType;
import net.minecraft.src.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGuiPacketHandler implements CustomPacketHandler {
    private CustomGuiPacketHandler() {}

    public static final CustomGuiPacketHandler INSTANCE = new CustomGuiPacketHandler();
    public final Map<String, IGuiHandler> modIdToHandler = new HashMap<>();

    @Override
    public void handleCustomPacket(Packet250CustomPayload packet, EntityPlayer player) throws IOException {
        try {
            var is = new ByteArrayInputStream(packet.data);
            DataInputStream dis = new DataInputStream(is);
            int windowId = dis.readInt();
            int containerId = dis.readInt();
            String modId = dis.readUTF();
            int x = dis.readInt();
            int y = dis.readInt();
            int z = dis.readInt();
            IGuiHandler handler = modIdToHandler.get(modId);
            if (handler != null) {
                var obj = handler.getClientGuiElement(containerId, player, player.worldObj, x, y, z);
                if (obj instanceof Container container) {
//                    var playerMp = (EntityClientPlayerMP) player;
//                    playerMp.incrementAndGetWindowID();
//                    playerMp.closeContainer();
                    player.openContainer = container;
//                    player.openContainer.windowId = windowId;
//                    player.openContainer.onCraftGuiOpened((EntityPlayerMP) player);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writePacket(EntityPlayerMP player, String modId, int containerID, int x, int y, int z, Object elem) {
        if (elem == null) {
            System.err.println("CustomGuiPacketHandler.writePacket: element is null");
            return;
        }
        if (!(elem instanceof Container container)) {
            throw new ClassCastException("Cannot cast " + elem.getClass() + " to Container");
        }
//        try {
//            int windowID = player.incrementAndGetWindowID();
//            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            DataOutputStream dataStream = new DataOutputStream(byteStream);
//            dataStream.writeInt(windowID);
//            dataStream.writeInt(containerID);
//            dataStream.writeUTF(modId);
//            dataStream.writeInt(x);
//            dataStream.writeInt(y);
//            dataStream.writeInt(z);
//            Packet250CustomPayload packet = new Packet250CustomPayload("botania|GUI", byteStream.toByteArray());
//            WorldUtils.sendPacketToPlayer(player.playerNetServerHandler, packet);
//            player.openContainer = container;
//            player.openContainer.windowId = windowID;
//            player.openContainer.onCraftGuiOpened(player);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        var os = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(os);
        int windowID = player.incrementAndGetWindowID();
        try {
            dataStream.writeInt(windowID);
            dataStream.writeInt(containerID);
            dataStream.writeUTF(modId);
            dataStream.writeInt(x);
            dataStream.writeInt(y);
            dataStream.writeInt(z);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        player.openContainer = container;
        player.openContainer.windowId = windowID;
        player.openContainer.onCraftGuiOpened(player);
        var packet = new Packet250CustomPayload("botania|GUI", os.toByteArray());
        WorldUtils.sendPacketToPlayer(player.playerNetServerHandler, packet);
    }

    public static EnvType getEffectiveSide() {
        Thread thr = Thread.currentThread();
        return !(thr instanceof ThreadMinecraftServer) && !(thr instanceof ServerListenThread) ? EnvType.CLIENT : EnvType.SERVER;
    }
}
