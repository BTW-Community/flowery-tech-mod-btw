package baubles.common;

import api.config.AddonConfig;
import baubles.api.expanded.BaubleExpandedSlots;
import com.typesafe.config.ConfigUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaublesConfig {

	public static boolean hideDebugItem = true;

    public static List<Integer> soulBoundEnchantments = new ArrayList<>();

    public static boolean useOldGuiButton = false;
    public static boolean useOldGuiRendering = false;
    public static boolean showUnusedSlots = false;
    public static boolean manualSlotSelection = false;

    public static String[] overrideSlotTypes = new String[] {
        BaubleExpandedSlots.amuletType,
        BaubleExpandedSlots.ringType,
        BaubleExpandedSlots.ringType,
        BaubleExpandedSlots.beltType
    };

    static final String categoryDebug = "debug";
    static final String categoryGeneral = "general";
    static final String categoryMenu = "menu";
    static final String categoryClient = "client";
    static final String categoryOverride = "override";

    //todobaubles move to new config system
    public static void loadConfig(AddonConfig config) {

        ArrayList<String> currentlyRegisteredTypes = BaubleExpandedSlots.getCurrentlyRegisteredTypes();
        String[] currentSlotAssignments = BaubleExpandedSlots.getCurrentSlotAssignments();
        //categoryDebug
        config.registerBoolean(ConfigUtil.joinPath(categoryDebug, "hideDebugItem"), hideDebugItem, "Hides the Bauble debug item from the creative menu.");

        //categoryGeneral
        config.registerIntList(ConfigUtil.joinPath(categoryGeneral, "soulBoundEnchantments"), soulBoundEnchantments,
            "IDs of enchantments that should be treated as soul bound when on items in a bauble slot."
        );
        //categoryClient
        config.registerBoolean(ConfigUtil.joinPath(categoryClient, "useOldGuiButton"), useOldGuiButton, "Use the old Baubles Button texture and location instead.");
        config.registerBoolean(ConfigUtil.joinPath(categoryClient, "useOldRendering"), useOldGuiRendering, "Display the old Bauble GUI instead of the new sidebar.");

        //categoryMenu
        config.registerBoolean(ConfigUtil.joinPath(categoryMenu, "showUnusedSlots"), showUnusedSlots, "Display unused Bauble slots.");
        config.registerBoolean(ConfigUtil.joinPath(categoryMenu, "manualSlotSelection"), manualSlotSelection,
            "Manually override slot assignments.", "!Bauble slot types must be configured manually with this option enabled!"
        );

        //categoryOverride
        config.registerStringList(ConfigUtil.joinPath(categoryOverride, "defualtSlotTypes"), Arrays.asList(currentSlotAssignments),
            "Baubles and its addons assigned the following types to the bauble slots.", "!This config option automatically changes to reflect what Baubles and its addons assigned each time the game is launched!"
        );
//        config.getCategory(categoryOverride).get("defualtSlotTypes").set(Arrays.asList(currentSlotAssignments));

        config.registerStringList(ConfigUtil.joinPath(categoryOverride, "slotTypeOverrides"), Arrays.asList(overrideSlotTypes),
            "Slot assignments to use if manualSlotSelection is enabled.", "Any assignments after the first " +
            BaubleExpandedSlots.slotLimit + " will be ignored.", "!Adding, moving, or removing slots of the "
            + BaubleExpandedSlots.amuletType + ", " + BaubleExpandedSlots.ringType + ", or " + BaubleExpandedSlots.beltType +
            " types will reduce compatibility with mods made for original Baubles versions!",
                currentlyRegisteredTypes.toString()
        );

        if(manualSlotSelection) {
            BaubleExpandedSlots.overrideSlots(overrideSlotTypes);
        }
    }

    public static void handleConfig(AddonConfig config) {
        hideDebugItem = config.getBoolean(ConfigUtil.joinPath(categoryDebug, "hideDebugItem"));
        soulBoundEnchantments = config.getIntList(ConfigUtil.joinPath(categoryGeneral, "soulBoundEnchantments"));

        useOldGuiButton = config.getBoolean(ConfigUtil.joinPath(categoryClient, "useOldGuiButton"));
        useOldGuiRendering =  config.getBoolean(ConfigUtil.joinPath(categoryClient, "useOldRendering"));

        showUnusedSlots = config.getBoolean(ConfigUtil.joinPath(categoryMenu, "showUnusedSlots"));
        manualSlotSelection = config.getBoolean(ConfigUtil.joinPath(categoryMenu, "manualSlotSelection"));

        if(manualSlotSelection) {
            List<String> overrides = config.getStringList(ConfigUtil.joinPath(categoryOverride, "slotTypeOverrides"));
            BaubleExpandedSlots.overrideSlots(overrides.toArray(new String[0]));
        }
    }

}
