package net.minecraftforge.event.entity.minecart;

import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.EntityMinecart;

/**
 * MinecartUpdateEvent is fired when a minecart is updated.<br>
 * This event is fired whenever a minecart is updated in
 * EntityMinecart#onUpdate().<br>
 * <br>
 * {@link #x} contains the x-coordinate of the minecart Entity.<br>
 * {@link #y} contains the y-coordinate of the minecart Entity.<br>
 * {@link #z} contains the z-coordinate of the minecart Entity.<br>
 * <br>
 * This event is not {@link Cancelable}.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
public class MinecartUpdateEvent extends MinecartEvent
{
    public final float x;
    public final float y;
    public final float z;

    public MinecartUpdateEvent(EntityMinecart minecart, float x, float y, float z)
    {
        super(minecart);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static final Event<MinecartUpdateCallback> EVENT = EventFactory.createArrayBacked(MinecartUpdateCallback.class, (listeners) -> (event) -> {
        for (MinecartUpdateCallback callback : listeners) {
            callback.onMinecartUpdate(event);
        }
    });

    public interface MinecartUpdateCallback {
        void onMinecartUpdate(MinecartUpdateEvent event);
    }
}