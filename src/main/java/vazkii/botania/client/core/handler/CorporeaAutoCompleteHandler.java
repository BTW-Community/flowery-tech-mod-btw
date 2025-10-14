/**
 * This class was created by <SoundLogic>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [June 8, 2015, 12:55:20 AM (GMT)]
 */
package vazkii.botania.client.core.handler;

import java.util.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.src.Minecraft;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import org.lwjgl.input.Keyboard;

import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.common.lib.LibObfuscation;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class CorporeaAutoCompleteHandler {

	boolean isAutoCompleted = false;
	String originalString = "";
	List<CompletionData> completions = new ArrayList<CompletionData>();
	int position;

	static TreeSet<String> itemNames = new TreeSet<>(String::compareToIgnoreCase);

	private boolean tabLastTick = false;

	public static void updateItemList() {
		itemNames.clear();
		Iterator<Item> iterator = Arrays.asList(Item.itemsList).iterator();
		ArrayList<ItemStack> curList = new ArrayList<ItemStack>();

		while(iterator.hasNext()) {
			Item item = iterator.next();

			if(item != null && item.getCreativeTab() != null) {
				curList.clear();
				try {
					item.getSubItems(item.itemID, null, curList);
					for(ItemStack stack : curList)
						itemNames.add(CorporeaHelper.stripControlCodes(stack.getDisplayName().trim()));
				}
				catch (Exception e) {}
			}
		}
	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if(event.phase != Phase.END)
			return;
		GuiScreen screen = Minecraft.getMinecraft().currentScreen;
		if(!(screen instanceof GuiChat)) {
			isAutoCompleted = false;
			return;
		}
		GuiChat chat = (GuiChat) screen;
		if(isAutoCompleted) {
			boolean valid = chat.field_73905_m;
			if(!valid)
				isAutoCompleted = false;
		}
		if(Keyboard.isKeyDown(15)) {
			if(tabLastTick)
				return;
			tabLastTick = true;
		} else {
			tabLastTick = false;
			return;
		}

		if(!CorporeaHelper.shouldAutoComplete())
			return;

		GuiTextField inputField = chat.inputField;
		if(!isAutoCompleted)
			buildAutoCompletes(inputField, chat);
		if(isAutoCompleted && !completions.isEmpty())
			advanceAutoComplete(inputField, chat);
	}

	private void advanceAutoComplete(GuiTextField inputField, GuiChat chat) {
		position++;
		if(position >= completions.size())
			position -= completions.size();
		CompletionData data = completions.get(position);
		String str = originalString.substring(0, originalString.length() - data.prefixLength) + data.string;
		inputField.setText(str);
	}

	private void buildAutoCompletes(GuiTextField inputField, GuiChat chat) {
		String leftOfCursor;
		if(inputField.getCursorPosition() == 0)
			leftOfCursor = "";
		else
			leftOfCursor = inputField.getText().substring(0, inputField.getCursorPosition());
		if(leftOfCursor.length() == 0 || leftOfCursor.charAt(0) == '/')
			return;
		completions = getNames(leftOfCursor);
		if(completions.isEmpty())
			return;
		position = -1;
		chat.field_73905_m = true;
		StringBuilder stringbuilder = new StringBuilder();
		CompletionData data;
		for(Iterator<CompletionData> iterator = completions.iterator(); iterator.hasNext(); stringbuilder.append(data.string)) {
			data = iterator.next();
			if(stringbuilder.length() > 0)
				stringbuilder.append(", ");
		}

		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(stringbuilder.toString(), 1);
		isAutoCompleted = true;
		originalString = inputField.getText();
	}

	private ArrayList<CompletionData> getNames(String prefix) {
		String s = prefix.trim();
		if(s.isEmpty())
			return new ArrayList<>();
				
		TreeSet<CompletionData> result = new TreeSet<CompletionData>();
		String[] words = s.split(" ");
		int i = words.length - 1;
		String curPrefix = words[i];
		while(i >= 0) {
			result.addAll(getNamesStartingWith(curPrefix.toLowerCase()));
			i--;
			if(i >= 0)
				curPrefix = words[i] + " " + curPrefix;
		}
		return new ArrayList<CompletionData>(result);
	}

	private List<CompletionData> getNamesStartingWith(String prefix) {
		ArrayList<CompletionData> result = new ArrayList<CompletionData>();
		int length = prefix.length();
		SortedSet<String> after = itemNames.tailSet(prefix);
		for(String str : after) {
			if(str.toLowerCase().startsWith(prefix))
				result.add(new CompletionData(str, length));
			else return result;
		}
		return result;
	}

	private static class CompletionData implements Comparable<CompletionData> {

		private String string;
		private int prefixLength;

		public CompletionData(String string, int prefixLength) {
			this.string = string;
			this.prefixLength = prefixLength;
		}

		@Override
		public int compareTo(CompletionData arg0) {
			return string.compareTo(arg0.string);
		}
	}

}
