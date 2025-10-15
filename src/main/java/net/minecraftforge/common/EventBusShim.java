package net.minecraftforge.common;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Method;

public class EventBusShim {
    public static EventBusShim INSTANCE = new EventBusShim();

    private EventBusShim() {
    }

    public boolean post(Event event) {
        return (event.isCancelable() ? event.isCanceled() : false);
    }

    public boolean register(Object event) {
        try {
            for (Method method : event.getClass().getMethods()) {
                var annot = method.getAnnotation(SubscribeEvent.class);
                if (annot != null) {
                    System.err.println("Class " + event.getClass().getName() + " is annotated with @SubscribeEvent for method " + method.getName());
                }
            }
        }
        catch (Throwable t) {

        }
        return true;
    }
}
