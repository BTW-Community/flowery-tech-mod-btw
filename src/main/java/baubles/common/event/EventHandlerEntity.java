package baubles.common.event;

import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import btw.client.mojapi.ProfileUtils;
import btw.client.mojapi.UserProfile;
import com.google.common.io.Files;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class EventHandlerEntity {

	// player directory
	private static File playerDirectory;

	public static void init() {
		LivingEvent.LivingUpdateEvent.EVENT.register(EventHandlerEntity::playerTick);
	}

	@SubscribeEvent
	public static boolean playerTick(PlayerEvent.LivingUpdateEvent event) {

		// player events
		if (event.entity instanceof EntityPlayer player) {
			InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
			for (int a = 0; a < baubles.getSizeInventory(); a++) {
				if (baubles.getStackInSlot(a) != null
						&& baubles.getStackInSlot(a).getItem() instanceof IBauble) {
					((IBauble) baubles.getStackInSlot(a).getItem()).onWornTick(
							baubles.getStackInSlot(a), player);
				}
			}

		}
		return false;
	}

	@SubscribeEvent
	public static void playerDeath(PlayerDropsEvent event) {
		if (event.entity instanceof EntityPlayer
				&& !event.entity.worldObj.isRemote
				&& !event.entity.worldObj.getGameRules()
						.getGameRuleBooleanValue("keepInventory")) {
			PlayerHandler.getPlayerBaubles(event.entityPlayer).dropItemsAt(
					event.drops,event.entityPlayer);
		}

	}

	@SubscribeEvent
	public static void playerLoad(PlayerEvent.LoadFromFile event) {
		playerLoadDo(event.entityPlayer, event.playerDirectory, event.entityPlayer.capabilities.isCreativeMode);
		playerDirectory = event.playerDirectory;
	}

	private static void playerLoadDo(EntityPlayer player, File directory, Boolean gamemode) {
		PlayerHandler.clearPlayerBaubles(player);

		File mainFile, backupFile;
		final String fileExtension = "baub";
		final String fileExtensionBackup = "baubback";

		// look for normal files first
		mainFile = getPlayerFile(fileExtension, directory, player.getCommandSenderName());
		backupFile = getPlayerFile(fileExtensionBackup, directory, player.getCommandSenderName());

		// look for uuid files when normal file missing
		if (!mainFile.exists()) {
			UserProfile profile = ProfileUtils.getUserProfile(player.username, true).orElse(null);
			if (profile != null) {
				File filep = getPlayerFile(fileExtension, directory, profile.getUuid().toString());
				if (filep.exists()) {
					try {
						Files.copy(filep, mainFile);
						Baubles.log.info("Using and converting UUID Baubles savefile for " + player.getCommandSenderName());
						filep.delete();
						File fb = getPlayerFile(fileExtensionBackup, directory, profile.getUuid().toString());
						if (fb.exists()) fb.delete();
					} catch (IOException e) {}
				}
			}
		}

		PlayerHandler.loadPlayerBaubles(player, mainFile, backupFile);
	}

	public static File getPlayerFile(String extension, File playerDirectory, String playerName) {
        if("dat".equals(extension)) throw new IllegalArgumentException("The extension 'dat' is reserved");
        return new File(playerDirectory, playerName + "." + extension);
    }

	@SubscribeEvent
	public static void playerSave(PlayerEvent.SaveToFile event) {
		playerSaveDo(event.entityPlayer, event.playerDirectory, event.entityPlayer.capabilities.isCreativeMode);
	}

	private static void playerSaveDo(EntityPlayer player, File directory, Boolean gamemode) {
		PlayerHandler.savePlayerBaubles(player,
				getPlayerFile("baub", directory, player.getCommandSenderName()),
				getPlayerFile("baubback", directory, player.getCommandSenderName()));
	}

}
