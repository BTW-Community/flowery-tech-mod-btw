package net.minecraftforge.common;

import cpw.mods.fml.common.eventhandler.Event;

public class EventBusShim {
    public static EventBusShim INSTANCE = new EventBusShim();

    private EventBusShim() {
    }

    public boolean post(Event event) {
        return (event.isCancelable() ? event.isCanceled() : false);
    }
}
