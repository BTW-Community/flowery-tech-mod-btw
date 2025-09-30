package dev.bagel.util;

public interface ICommonProxy {

    void preInit();
    void init();
    void postInit();
    void serverAboutToStart();
    void serverStarting();
    default void serverStopping() {

    }
}
