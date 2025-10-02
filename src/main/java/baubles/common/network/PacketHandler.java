package baubles.common.network;

import baubles.common.Baubles;

//todobaubles IMPORTANT: PACKETS
public class PacketHandler {
//    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Baubles.MODID.toLowerCase());

    public static void init() {
        PacketOpenBaublesInventory.class.getName();
        PacketOpenNormalInventory.class.getName();
        PacketSyncBauble.class.getName();
//        INSTANCE.registerMessage(PacketOpenBaublesInventory.class, PacketOpenBaublesInventory.class, 0, Side.SERVER);
//        INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 1, Side.SERVER);
//        INSTANCE.registerMessage(PacketSyncBauble.class, PacketSyncBauble.class, 2, Side.CLIENT);
    }
}
