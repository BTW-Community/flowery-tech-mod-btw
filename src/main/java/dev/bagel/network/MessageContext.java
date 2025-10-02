package dev.bagel.network;

import net.fabricmc.api.EnvType;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetServerHandler;

/**
 * Context for the {@link IMessageHandler}
 *
 * @author cpw
 *
 */
public class MessageContext {
    /**
     * The {@link INetHandler} for this message. It could be a client or server handler, depending
     * on the {@link #side} recieved.
     */
    public final NetHandler netHandler;

    /**
     * The Side this message has been received on
     */
    public final EnvType side;
    /**
     * @param netHandler
     */
    MessageContext(NetHandler netHandler, EnvType side)
    {
        this.netHandler = netHandler;
        this.side = side;
    }

    public NetServerHandler getServerHandler()
    {
        return (NetServerHandler) netHandler;
    }

    public NetClientHandler getClientHandler()
    {
        return (NetClientHandler) netHandler;
    }
}
