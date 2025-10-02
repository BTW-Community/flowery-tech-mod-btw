package baubles.common.network;

import dev.bagel.network.IMessage;
import dev.bagel.network.IMessageHandler;
import dev.bagel.network.MessageContext;
import emi.shims.java.net.minecraft.network.PacketByteBuf;
import net.minecraft.src.EntityPlayer;

public class PacketOpenNormalInventory implements IMessage, IMessageHandler<PacketOpenNormalInventory, IMessage> {

	public PacketOpenNormalInventory() {}

	public PacketOpenNormalInventory(EntityPlayer player) {}

	@Override
	public void toBytes(PacketByteBuf buffer) {}

	@Override
	public void fromBytes(PacketByteBuf buffer) {}

	@Override
	public IMessage onMessage(PacketOpenNormalInventory message, MessageContext ctx) {
		ctx.getServerHandler().playerEntity.openContainer.onContainerClosed(ctx.getServerHandler().playerEntity);
		ctx.getServerHandler().playerEntity.openContainer = ctx.getServerHandler().playerEntity.inventoryContainer;
		return null;
	}
}
