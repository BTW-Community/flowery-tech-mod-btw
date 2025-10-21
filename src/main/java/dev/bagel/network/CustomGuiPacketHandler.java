package dev.bagel.network;

import baubles.common.network.PacketHandler;
import btw.inventory.BTWContainers;
import btw.network.packet.handler.CustomPacketHandler;
import btw.world.util.WorldUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
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
        ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
        int windowId = dat.readInt();
        String modId = dat.readUTF();
        int modGuiId = dat.readInt();
        int x = dat.readInt();
        int y = dat.readInt();
        int z = dat.readInt();

        Minecraft.getMinecraft().thePlayer.openGui(modId, modGuiId, Minecraft.getMinecraft().thePlayer.worldObj, x, y, z);
        Minecraft.getMinecraft().thePlayer.openContainer.windowId = windowId;
    }
}
