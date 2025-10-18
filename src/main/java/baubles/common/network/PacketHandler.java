package baubles.common.network;

import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import btw.network.packet.handler.CustomPacketHandler;
import dev.bagel.network.IMessage;
import dev.bagel.network.IMessageHandler;
import net.fabricmc.api.EnvType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.io.*;

//todobaubles IMPORTANT: PACKETS
public class PacketHandler implements CustomPacketHandler {
//    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Baubles.MODID.toLowerCase());
    public static final PacketHandler INSTANCE = new PacketHandler();
    public static void init() {
        INSTANCE.registerMessage(PacketOpenBaublesInventory.class, PacketOpenBaublesInventory.class, 0, EnvType.SERVER);
        INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 1, EnvType.SERVER);
        INSTANCE.registerMessage(PacketSyncBauble.class, PacketSyncBauble.class, 2, EnvType.CLIENT);
    }

    private Packet250CustomPayload makePackets(IMessage message) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(out);
        if (message instanceof PacketOpenBaublesInventory) {
            data.writeInt(0);
        }
        else if (message instanceof PacketOpenNormalInventory) {
            data.writeInt(1);
        }
        else if (message instanceof PacketSyncBauble psb) {
            data.writeInt(2);
            psb.write(data);
        }
        return new Packet250CustomPayload("botania|BAUB", out.toByteArray());
    }

    //todobaubles HIGH PRIO PACKET HANDLING
    public void sendToAll(IMessage message) {
        try {
            Packet250CustomPayload packet = makePackets(message);
//            if (MinecraftServer.getIsServer()) {
                MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
                System.out.println("sent");
//            }
            System.out.println("sending all baubles to player");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTo(IMessage message, EntityPlayerMP player) {
        try {
            player.playerNetServerHandler.sendPacketToPlayer(makePackets(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sending baubles to specific player");
    }

    public void sendToServer(IMessage message) {
        System.out.println("sending message to server");
        try {
            Minecraft.getMinecraft().playerController.getNetClientHandler().addToSendQueue(makePackets(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
    }

    public <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, EnvType side) {

    }

    @Override
    public void handleCustomPacket(Packet250CustomPayload packet, EntityPlayer player) throws IOException {
        try {
            var is = new ByteArrayInputStream(packet.data);
            DataInputStream data = new DataInputStream(is);
            int id = data.readInt();
            switch (id) {
                case 0:
                    player.openGui(Baubles.instance.modId, Baubles.GUI, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
                    break;
                case 1:
                    player.openContainer.onContainerClosed(player);
                    player.openContainer = player.inventoryContainer;
                    break;
                case 2:
                    int slot = data.readByte();
                    int playerId = data.readInt();
                    boolean initial = data.readBoolean();
                    ItemStack bauble = Packet.readItemStack(data);
                    World world = Baubles.getProxy().getClientWorld();
                    if (world == null)
                        break;
                    Entity e = world.getEntityByID(playerId);
                    if (e instanceof EntityPlayer playerE) {
                        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(playerE);
                        if (initial) {
                            if (slot == 0) {
                                PlayerHandler.clearClientPlayerBaubles();
                                baubles = PlayerHandler.getPlayerBaubles(playerE);
                            }
                            baubles.stackList[slot] = bauble;
                            if (bauble != null && bauble.getItem() instanceof IBauble itemBauble) {
                                itemBauble.onPlayerLoad(bauble, playerE);
                            }
                        }
                        else {
                            baubles.setInventorySlotContents(slot, bauble);
                        }
                    }
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
