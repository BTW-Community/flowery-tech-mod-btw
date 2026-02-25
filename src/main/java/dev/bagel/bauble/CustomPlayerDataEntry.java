package dev.bagel.bauble;

import api.world.data.DataEntry;
import api.world.data.component.DataComponents;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CustomPlayerDataEntry<T> extends DataEntry.PlayerDataEntry<T> {
    public final Function<EntityPlayer, T> defaultFunction;
    public final BiFunction<NBTTagCompound, EntityPlayer, T> reader;
    public CustomPlayerDataEntry(String name, Function<EntityPlayer, T> defaultFunction, boolean ignorePresence, BiFunction<NBTTagCompound, EntityPlayer, T> reader, BiConsumer<NBTTagCompound, T> writer) {
        super(name, () -> null, ignorePresence, (a) -> null, writer);
        this.addComponent(new DataComponents.SyncComponent(DataComponents.SyncBehavior.PlayerOwned));
        this.addComponent(new DataComponents.PlayerDataComponent());
        this.defaultFunction = defaultFunction;
        this.reader = reader;
    }
}
