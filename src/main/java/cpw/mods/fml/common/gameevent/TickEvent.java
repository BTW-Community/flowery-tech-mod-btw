package cpw.mods.fml.common.gameevent;

import cpw.mods.fml.common.eventhandler.Event;
import net.fabricmc.api.EnvType;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.World;

public class TickEvent extends Event {
    public enum Type {
        WORLD, PLAYER, CLIENT, SERVER, RENDER;
    }

    public enum Phase {
        START, END;
    }

    public final Type type;
    public final EnvType envType;
    public final Phase phase;

    public TickEvent(Type type, EnvType EnvType, Phase phase) {
        this.type = type;
        this.envType = EnvType;
        this.phase = phase;
    }

    public static class ServerTickEvent extends TickEvent {
        public ServerTickEvent(Phase phase) {
            super(Type.SERVER, EnvType.SERVER, phase);
        }
    }

    public static class ClientTickEvent extends TickEvent {
        public ClientTickEvent(Phase phase) {
            super(Type.CLIENT, EnvType.CLIENT, phase);
        }

        public static net.legacyfabric.fabric.api.event.Event<ClientTickEventCallback> EVENT = EventFactory.createArrayBacked(ClientTickEventCallback.class, callbacks -> (event) -> {
            for (ClientTickEventCallback callback : callbacks) {
                callback.onTick(event);
            }
        });

        @FunctionalInterface
        public static interface ClientTickEventCallback {
            void onTick(ClientTickEvent event);
        }
    }

    public static class WorldTickEvent extends TickEvent {
        public final World world;

        public WorldTickEvent(EnvType EnvType, Phase phase, World world) {
            super(Type.WORLD, EnvType, phase);
            this.world = world;
        }
    }

    public static class PlayerTickEvent extends TickEvent {
        public final EntityPlayer player;

        public PlayerTickEvent(Phase phase, EntityPlayer player) {
            super(Type.PLAYER, player instanceof EntityPlayerMP ? EnvType.SERVER : EnvType.CLIENT, phase);
            this.player = player;
        }
    }

    public static class RenderTickEvent extends TickEvent {
        public final float renderTickTime;

        public RenderTickEvent(Phase phase, float renderTickTime) {
            super(Type.RENDER, EnvType.CLIENT, phase);
            this.renderTickTime = renderTickTime;
        }
    }
}