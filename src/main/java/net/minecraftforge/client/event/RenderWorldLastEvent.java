package net.minecraftforge.client.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.RenderGlobal;

public class RenderWorldLastEvent extends Event
{
    public final RenderGlobal context;
    public final float partialTicks;
    public RenderWorldLastEvent(RenderGlobal context, float partialTicks)
    {
        this.context = context;
        this.partialTicks = partialTicks;
    }

    public static final net.legacyfabric.fabric.api.event.Event<RenderWorldLastEventCallback> EVENT = EventFactory.createArrayBacked(RenderWorldLastEventCallback.class, (listeners) -> (event) -> {
        for (RenderWorldLastEventCallback callback : listeners) {
            callback.renderWorldLast(event);
        }
    });

    @FunctionalInterface
    public interface RenderWorldLastEventCallback {
        public void renderWorldLast(RenderWorldLastEvent event);
    }
}