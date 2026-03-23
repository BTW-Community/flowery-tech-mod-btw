package dev.bagel.interfaces;

import net.minecraftforge.client.IRenderHandler;

public interface WorldProviderExtensions {

    IRenderHandler getSkyRenderer();

    void setSkyRenderer(IRenderHandler skyRenderer);
}
