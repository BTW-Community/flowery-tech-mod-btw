package dev.bagel.network;

import emi.shims.java.net.minecraft.network.PacketByteBuf;

public interface IMessage {
    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    public void fromBytes(PacketByteBuf buf);

    /**
     * Deconstruct your message into the supplied byte buffer
     * @param buf
     */
    public void toBytes(PacketByteBuf buf);
}