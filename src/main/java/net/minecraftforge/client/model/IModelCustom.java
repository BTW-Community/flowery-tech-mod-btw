package net.minecraftforge.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


public interface IModelCustom
{
    String getType();
    @Environment(EnvType.CLIENT)
    void renderAll();
    @Environment(EnvType.CLIENT)
    void renderOnly(String... groupNames);
    @Environment(EnvType.CLIENT)
    void renderPart(String partName);
    @Environment(EnvType.CLIENT)
    void renderAllExcept(String... excludedGroupNames);
}