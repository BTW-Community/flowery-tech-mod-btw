package baubles.common.network;

import baubles.common.Baubles;
import dev.bagel.network.IMessage;
import dev.bagel.network.IMessageHandler;
import net.fabricmc.api.EnvType;
import net.minecraft.src.EntityPlayerMP;

//todobaubles IMPORTANT: PACKETS
public class PacketHandler {
//    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Baubles.MODID.toLowerCase());
    public static final PacketHandler INSTANCE = new PacketHandler();
    public static void init() {
        INSTANCE.registerMessage(PacketOpenBaublesInventory.class, PacketOpenBaublesInventory.class, 0, EnvType.SERVER);
        INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 1, EnvType.SERVER);
        INSTANCE.registerMessage(PacketSyncBauble.class, PacketSyncBauble.class, 2, EnvType.CLIENT);
    }
    //todobaubles HIGH PRIO PACKET HANDLING
    public void sendToAll(IMessage message) {

    }

    public void sendTo(IMessage message, EntityPlayerMP player) {

    }

    public void sendToServer(IMessage message) {

    }

    public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, EnvType side) {

    }
}
