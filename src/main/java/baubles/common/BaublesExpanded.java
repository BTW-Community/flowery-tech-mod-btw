package baubles.common;

public class BaublesExpanded {
    public static final String MODID = "Baubles|Expanded";
    public static final String MODNAME = "Baubles Expanded";
    static boolean isPreInit = true;

    public static void noLongerPreInit() {
        isPreInit = false;
    }
}
