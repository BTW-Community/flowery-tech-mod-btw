package baubles.common.network;

import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import dev.bagel.network.IMessage;
import dev.bagel.network.IMessageHandler;
import dev.bagel.network.MessageContext;
import emi.shims.java.net.minecraft.network.PacketByteBuf;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import java.io.IOException;

public class PacketSyncBauble implements IMessage, IMessageHandler<PacketSyncBauble, IMessage> {

	int slot;
	int playerId;
	ItemStack bauble = null;
	boolean initial;

	public PacketSyncBauble() {}

	public PacketSyncBauble(EntityPlayer player, int slot) {
		this(player, slot, false);
	}

	public PacketSyncBauble(EntityPlayer player, int slot, boolean reset) {
		this.slot = slot;
		this.bauble = PlayerHandler.getPlayerBaubles(player).getStackInSlot(slot);
		this.playerId = player.entityId;
		this.initial = reset;
	}

	@Override
	public void toBytes(PacketByteBuf buffer) {
		buffer.writeByte(slot);
		buffer.writeInt(playerId);
		buffer.writeBoolean(initial);

//		PacketBuffer pb = new PacketBuffer(buffer);
        buffer.writeItemStack(bauble);
    }

	@Override
	public void fromBytes(PacketByteBuf buffer) {
		slot = buffer.readByte();
		playerId = buffer.readInt();
		initial = buffer.readBoolean();
//		PacketBuffer pb = new PacketBuffer(buffer);
        bauble = buffer.readItemStack();
    }

	@Override
	public IMessage onMessage(PacketSyncBauble message, MessageContext ctx) {
		World world = Baubles.getProxy().getClientWorld();
		if (world == null) return null;
		Entity e = world.getEntityByID(message.playerId);
		if (e instanceof EntityPlayer player) {
			InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
			if (message.initial) {
				if (message.slot == 0) {
					PlayerHandler.clearClientPlayerBaubles();
					baubles = PlayerHandler.getPlayerBaubles(player);
				}
				baubles.stackList[message.slot] = message.bauble;
				if (message.bauble != null && message.bauble.getItem() instanceof IBauble itemBauble) {
					itemBauble.onPlayerLoad(message.bauble, player);
				}
			}
			else {
				baubles.setInventorySlotContents(message.slot, message.bauble);
			}
		}
		return null;
	}
}
