package net.minecraftforge.client.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.TextureMap;

import java.util.function.Consumer;


public class TextureStitchEvent extends Event
{
    public final TextureMap map;

    public TextureStitchEvent(TextureMap map)
    {
        this.map = map;
    }

    /**
     * Fired when the TextureMap is told to refresh it's stitched texture. 
     * Called after the Stitched list is cleared, but before any blocks or items
     * add themselves to the list.
     */
    public static class Pre extends TextureStitchEvent
    {
        public static final net.legacyfabric.fabric.api.event.Event<Consumer<Pre>> EVENT = EventFactory.createArrayBacked(Consumer.class, (listeners) -> (event) -> {
            for (Consumer<Pre> listener : listeners) {
                listener.accept(event);
            }
        });
        public Pre(TextureMap map){ super(map); }
    }

    /**
     * This event is fired once the texture map has loaded all textures and 
     * stitched them together. All Icons should have there locations defined
     * by the time this is fired.
     */
    public static class Post extends TextureStitchEvent
    {
        public Post(TextureMap map){ super(map); }
    }
}