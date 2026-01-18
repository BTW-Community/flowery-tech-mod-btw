package net.minecraftforge.event.entity.item;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;

import java.util.function.Consumer;

/**
 * Event that is fired whenever a player tosses (Q) an item or drag-n-drops a
 * stack of items outside the inventory GUI screens. Canceling the event will
 * stop the items from entering the world, but will not prevent them being
 * removed from the inventory - and thus removed from the system.
 */
@Cancelable
public class ItemTossEvent extends ItemEvent
{
    public static final Event<Consumer<ItemTossEvent>> EVENT = EventFactory.createArrayBacked(Consumer.class, (listeners) -> (event) -> {
        for (var consumer : listeners) {
            consumer.accept(event);
        }
    });

    /**
     * The player tossing the item.
     */
    public final EntityPlayer player;

    /**
     * Creates a new event for EntityItems tossed by a player.
     * 
     * @param entityItem The EntityItem being tossed.
     * @param player The player tossing the item.
     */
    public ItemTossEvent(EntityItem entityItem, EntityPlayer player)
    {
        super(entityItem);
        this.player = player;
    }
}