package dev.bagel.network;

public interface IMessageHandler<REQ extends IMessage, REPLY extends IMessage> {
    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @return an optional return message
     */
    public REPLY onMessage(REQ message, MessageContext ctx);
}
