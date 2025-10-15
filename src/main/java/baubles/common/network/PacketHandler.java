package baubles.common.network;

import baubles.common.Baubles;
import dev.bagel.network.IMessage;
import net.minecraft.src.EntityPlayerMP;

//todobaubles IMPORTANT: PACKETS
public class PacketHandler {
//    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Baubles.MODID.toLowerCase());
    public static final PacketHandler INSTANCE = new PacketHandler();
    public static void init() {
        PacketOpenBaublesInventory.class.getName();
        PacketOpenNormalInventory.class.getName();
        PacketSyncBauble.class.getName();
//        INSTANCE.registerMessage(PacketOpenBaublesInventory.class, PacketOpenBaublesInventory.class, 0, Side.SERVER);
//        INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 1, Side.SERVER);
//        INSTANCE.registerMessage(PacketSyncBauble.class, PacketSyncBauble.class, 2, Side.CLIENT);
    }
    //todofix HIGH PRIO PACKET HANDLING
    public void sendToAll(IMessage message) {

    }

    public void sendTo(IMessage message, EntityPlayerMP player) {

    }

    public void sendToServer(IMessage message) {

    }
}
