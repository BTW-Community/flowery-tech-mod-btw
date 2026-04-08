package baubles.common.lib;

import api.world.data.DataEntry;
import api.world.data.DataProvider;
import baubles.common.container.InventoryBaubles;
import dev.bagel.bauble.CustomPlayerDataEntry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

import java.lang.ref.WeakReference;

public class BaubleLoader {
    private InventoryBaubles inventoryBaubles;

    public static final String BAUBLES_NAME = "Baubles";
//    public static final DataEntry.PlayerDataEntry<BaubleLoader> BAUBLES_DATA = DataProvider.getBuilder(BaubleLoader.class)
//            .name(BAUBLES_NAME)
//            .defaultSupplier(BaubleLoader::new)
//            .readNBT(tag -> new BaubleLoader(tag.getCompoundTag(BAUBLES_NAME)))
//            .writeNBT((tag, baubles) -> tag.setTag(BAUBLES_NAME, baubles.saveToNBT()))
//            .player()
//            //Doesn't need syncing, already have own system for that
//            .buildPlayer();

    public static final CustomPlayerDataEntry<BaubleLoader> BAUBLES_DATA = new CustomPlayerDataEntry<>(
            BAUBLES_NAME,
            BaubleLoader::new,
            true,
            (tag, player) -> new BaubleLoader(tag.getCompoundTag(BAUBLES_NAME), player),
            (tag, baubles) -> tag.setTag(BAUBLES_NAME, saveToNBT(baubles)));

    public BaubleLoader(NBTTagCompound list, EntityPlayer player) {
        this(player);
        this.loadFromNBT(list);
    }

    public BaubleLoader(EntityPlayer player) {
        this.inventoryBaubles = new InventoryBaubles(player);
    }

    public void loadFromNBT(NBTTagCompound tagList) {
        inventoryBaubles.readNBT(tagList);
    }

    public static NBTTagCompound saveToNBT(BaubleLoader baubles) {
        NBTTagCompound tag = new NBTTagCompound(BAUBLES_NAME);
        if (baubles != null && baubles.inventoryBaubles != null)
            baubles.inventoryBaubles.saveNBT(tag);
        return tag;
    }

    public NBTTagCompound saveToNBT() {
        NBTTagCompound tag = new NBTTagCompound(BAUBLES_NAME);
        if (inventoryBaubles != null)
            inventoryBaubles.saveNBT(tag);
        return tag;
    }

    public InventoryBaubles getInventoryBaubles(EntityPlayer player) {
        if (inventoryBaubles == null) {
            inventoryBaubles = new InventoryBaubles(player);
        }
        else if (inventoryBaubles.player == null) {
            inventoryBaubles.player = player;
        }
        return inventoryBaubles;
    }

    public void setInventoryBaubles(EntityPlayer player, InventoryBaubles inventoryBaubles) {
        if (inventoryBaubles.player == null) {
            inventoryBaubles.player = player;
        }
        this.inventoryBaubles = inventoryBaubles;
    }
}
