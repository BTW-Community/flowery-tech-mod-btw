package baubles.common.network;

import baubles.common.Baubles;
import dev.bagel.network.IMessage;
import dev.bagel.network.IMessageHandler;
import dev.bagel.network.MessageContext;
import emi.shims.java.net.minecraft.network.PacketByteBuf;
import net.minecraft.src.EntityPlayer;

public class PacketOpenBaublesInventory implements IMessage, IMessageHandler<PacketOpenBaublesInventory, IMessage> {

	public PacketOpenBaublesInventory() {}

	public PacketOpenBaublesInventory(EntityPlayer player) {}

	@Override
	public void toBytes(PacketByteBuf buffer) {}

	@Override
	public void fromBytes(PacketByteBuf buffer) {}

	@Override
	public IMessage onMessage(PacketOpenBaublesInventory message, MessageContext ctx) {
		ctx.getServerHandler().playerEntity.openGui(Baubles.instance, Baubles.GUI, ctx.getServerHandler().playerEntity.worldObj, (int)ctx.getServerHandler().playerEntity.posX, (int)ctx.getServerHandler().playerEntity.posY, (int)ctx.getServerHandler().playerEntity.posZ);
		return null;
	}
}
