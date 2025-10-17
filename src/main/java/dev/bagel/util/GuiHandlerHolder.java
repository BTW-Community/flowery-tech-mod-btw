package dev.bagel.util;

import cpw.mods.fml.common.network.IGuiHandler;
import net.fabricmc.api.EnvType;
import net.minecraft.src.ServerListenThread;
import net.minecraft.src.ThreadMinecraftServer;

public interface GuiHandlerHolder {
    IGuiHandler getGuiHandler();

    default EnvType getEffectiveSide() {
        Thread thr = Thread.currentThread();
        return !(thr instanceof ThreadMinecraftServer) && !(thr instanceof ServerListenThread) ? EnvType.CLIENT : EnvType.SERVER;
    }
}
