package cpw.mods.fml.common.eventhandler;

public class Event {
    private boolean isCanceled = false;

    public boolean isCancelable() {
        return false;
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}
