package dev.bagel.mixin.extensions;

import dev.bagel.interfaces.NBTTagListExtensions;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(NBTTagList.class)
public abstract class NBTTagListExtensionMixin extends NBTBase implements NBTTagListExtensions {
    @Shadow private List tagList;

    protected NBTTagListExtensionMixin(String string) {
        super(string);
    }

    @Override
    public NBTTagCompound getCompoundTagAt(int i) {
        if (i >= 0 && i < this.tagList.size())
        {
            NBTBase nbtbase = (NBTBase)this.tagList.get(i);
            return nbtbase.getId() == 10 ? (NBTTagCompound)nbtbase : new NBTTagCompound();
        }
        else
        {
            return new NBTTagCompound();
        }
    }
}
