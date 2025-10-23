package net.minecraftforge.event.entity.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;

/**
 * Child class of LivingDropEvent that is fired specifically when a
 * player dies.  Canceling the event will prevent ALL drops from entering the
 * world.
 */
@Cancelable
public class PlayerDropsEvent extends LivingDropsEvent {
    public final EntityPlayer entityPlayer;

    /**
     * Creates a new event containing all the items that will drop into the
     * world when a player dies.
     *
     * @param entity The dying player.
     * @param source The source of the damage which is killing the player.
     * @param drops  List of all drops entering the world.
     */
    public PlayerDropsEvent(EntityPlayer entity, DamageSource source, ArrayList<EntityItem> drops, boolean recentlyHit) {
        super(entity, source, drops,
                (source.getEntity() instanceof EntityPlayer player) ? EnchantmentHelper.getLootingModifier(player) : 0, recentlyHit, 0);

        this.entityPlayer = entity;
    }

    public static Event<PlayerDropsEventCallback> PLAYER_DROPS = EventFactory.createArrayBacked(PlayerDropsEventCallback.class, (callbacks) -> (event) -> {
        for (PlayerDropsEventCallback callback : callbacks) {
            callback.onLivingDropsEvent(event);
            if (event.isCanceled()) return;
        }
    });

    @Override
    public boolean isCancelable() {
        return true;
    }

    @Override
    public void setCanceled(boolean cancel) {
        super.setCanceled(cancel);
    }

    @FunctionalInterface
    public interface PlayerDropsEventCallback {
        public void onLivingDropsEvent(PlayerDropsEvent event);
    }
}