package net.minecraftforge.event.entity.living;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.DamageSource;

/**
 * LivingHurtEvent is fired when an Entity is set to be hurt. <br>
 * This event is fired whenever an Entity is hurt in 
 * EntityLivingBase#damageEntity(DamageSource, float) and 
 * EntityPlayer#damageEntity(DamageSource, float).<br>
 * <br>
 * This event is fired via the {@link ForgeHooks#onLivingHurt(EntityLivingBase, DamageSource, float)}.<br>
 * <br>
 * {@link #source} contains the DamageSource that caused this Entity to be hurt. <br>
 * {@link #amount} contains the amount of damage dealt to the Entity that was hurt. <br>
 * <br>
 * This event is {@link Cancelable}.<br>
 * If this event is canceled, the Entity is not hurt.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
@Cancelable
public class LivingHurtEvent extends LivingEvent
{
    public final DamageSource source;
    public float ammount;
    public LivingHurtEvent(EntityLivingBase entity, DamageSource source, float ammount)
    {
        super(entity);
        this.source = source;
        this.ammount = ammount;
    }

    public static final Event<LivingHurtEventCallback> EVENT = EventFactory.createArrayBacked(LivingHurtEventCallback.class, (callbacks) -> (event) -> {
        for (var callback : callbacks) {
            callback.onLivingHurt(event);
        }
    });

    @FunctionalInterface
    public interface LivingHurtEventCallback {
        public void onLivingHurt(LivingHurtEvent event);
    }
}