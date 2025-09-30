package vazkii.botania.api.lexicon;

import net.minecraft.src.EnumChatFormatting;

public class KnowledgeType {

	public final String id;
	public final EnumChatFormatting color;
	public final boolean autoUnlock;

	public KnowledgeType(String id, EnumChatFormatting color, boolean autoUnlock) {
		this.id = id;
		this.color = color;
		this.autoUnlock = autoUnlock;
	}

	public String getUnlocalizedName() {
		return "botania.knowledge." + id;
	}
}
