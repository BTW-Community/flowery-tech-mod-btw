/**
 * This class was created by <SoundLogic>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [June 8, 2015, 12:55:20 AM (GMT)]
 */
package vazkii.botania.client.core.handler;

import java.util.*;

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.src.Minecraft;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import org.lwjgl.input.Keyboard;

import vazkii.botania.api.corporea.CorporeaHelper;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public abstract class CorporeaAutoCompleteHandler {

	public static final CorporeaAutoCompleteHandler INSTANCE = new CorporeaAutoCompleteHandler() {};

	public void init() {}

	CorporeaAutoCompleteHandler() {
		TickEvent.ClientTickEvent.EVENT.register(this::onTick);
	}

	boolean isAutoCompleted = false;
	String originalString = "";
	List<CompletionData> completions = new ArrayList<>();
	int position;

	static TreeSet<String> itemNames = new TreeSet<>(String::compareToIgnoreCase);

	private boolean tabLastTick = false;

	public static void updateItemList() {
		itemNames.clear();
		Iterator<Item> iterator = Arrays.asList(Item.itemsList).iterator();
		ArrayList<ItemStack> curList = new ArrayList<>();

		while(iterator.hasNext()) {
			Item item = iterator.next();

			if(item != null && item.getCreativeTab() != null) {
				curList.clear();
				try {
					item.getSubItems(item.itemID, null, curList);
					for(ItemStack stack : curList)
						itemNames.add(CorporeaHelper.stripControlCodes(stack.getDisplayName().trim()));
				}
				catch (Exception ignored) {}
			}
		}
	}

//	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if(event.phase != Phase.END)
			return;
		GuiScreen screen = Minecraft.getMinecraft().currentScreen;
		if(!(screen instanceof GuiChat chat)) {
			isAutoCompleted = false;
			return;
		}
        if(isAutoCompleted) {
			boolean valid = chat.field_73905_m;
			if(!valid)
				isAutoCompleted = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
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
			advanceAutoComplete(inputField);
	}

	private void advanceAutoComplete(GuiTextField inputField) {
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
		if(leftOfCursor.isEmpty() || leftOfCursor.charAt(0) == '/')
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
			if(!stringbuilder.isEmpty())
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
				
		TreeSet<CompletionData> result = new TreeSet<>();
		String[] words = s.split(" ");
		int i = words.length - 1;
		StringBuilder curPrefix = new StringBuilder(words[i]);
		while(i >= 0) {
			result.addAll(getNamesStartingWith(curPrefix.toString().toLowerCase()));
			i--;
			if(i >= 0)
				curPrefix.insert(0, words[i] + " ");
		}
		return new ArrayList<>(result);
	}

	private List<CompletionData> getNamesStartingWith(String prefix) {
		ArrayList<CompletionData> result = new ArrayList<>();
		int length = prefix.length();
		SortedSet<String> after = itemNames.tailSet(prefix);
		for(String str : after) {
			if(str.toLowerCase().startsWith(prefix))
				result.add(new CompletionData(str, length));
			else return result;
		}
		return result;
	}

	private record CompletionData(String string, int prefixLength) implements Comparable<CompletionData> {

		@Override
			public int compareTo(CompletionData arg0) {
				return string.compareTo(arg0.string);
			}
		}

}
