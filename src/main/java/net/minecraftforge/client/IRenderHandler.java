package net.minecraftforge.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Minecraft;
import net.minecraft.src.WorldClient;

public abstract class IRenderHandler
{
    @Environment(EnvType.CLIENT)
    public abstract void render(float partialTicks, WorldClient world, Minecraft mc);
}