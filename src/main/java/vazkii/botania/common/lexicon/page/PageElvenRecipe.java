package vazkii.botania.common.lexicon.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import dev.bagel.client.RenderInstances;
import net.minecraft.src.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.TextureMap;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Icon;
import net.minecraft.src.ResourceLocation;

import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.BlockAlfPortal;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class PageElvenRecipe extends PageRecipe {

	private static final ResourceLocation elvenTradeOverlay = new ResourceLocation(LibResources.GUI_ELVEN_TRADE_OVERLAY);

	List<RecipeElvenTrade> recipes;
	int ticksElapsed = 0;
	int recipeAt = 0;

	public PageElvenRecipe(String unlocalizedName, List<RecipeElvenTrade> recipes) {
		super(unlocalizedName);
		this.recipes = filterRecipes(recipes);
	}

	public PageElvenRecipe(String unlocalizedName, RecipeElvenTrade recipe) {
		this(unlocalizedName, Collections.singletonList(recipe));
	}

	@Override
	public void onPageAdded(LexiconEntry entry, int index) {
		for(RecipeElvenTrade recipe : recipes)
			if (recipe != null)
				LexiconRecipeMappings.map(recipe.getOutput(), entry, index);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
		if (recipes.isEmpty()) return;
		RecipeElvenTrade recipe = recipes.get(recipeAt);

		renderLexiconTexture(gui, elvenTradeOverlay);

		renderItemAtGridPos(gui, 3, 1, recipe.getOutput(), false);

		List<TagOrStack> inputs = recipe.getInputs();
		int i = 0;
		for(TagOrStack obj : inputs) {
			Object input = obj;
			if(input instanceof TagInstance ti) {
				//todofix elven recipe use tags
				input = ti.tag().getItems().get(0);
			}

			renderItemAtInputPos(gui, i, (ItemStack) input);
			i++;
		}

		Icon portalIcon = BlockAlfPortal.portalTex;
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		RenderInstances.getItemInstance().renderIcon(gui.getLeft() + 22, gui.getTop() + 36, portalIcon, 48, 48);
	}

	@Environment(EnvType.CLIENT)
	public void renderItemAtInputPos(IGuiLexiconEntry gui, int x, ItemStack stack) {
		if(stack == null || stack.getItem() == null)
			return;
		stack = stack.copy();

		if(stack.getItemDamage() == Short.MAX_VALUE)
			stack.setItemDamage(0);

		int xPos = gui.getLeft() + x * 20 + 45;
		int yPos = gui.getTop() + 14;
		ItemStack stack1 = stack.copy();
		if(stack1.getItemDamage() == -1)
			stack1.setItemDamage(0);

		renderItem(gui, xPos, yPos, stack1, false);
	}


	@Override
	@Environment(EnvType.CLIENT)
	public void updateScreen(IGuiLexiconEntry gui) {
		if(GuiScreen.isShiftKeyDown())
			return;

		if(ticksElapsed % 20 == 0) {
			recipeAt++;

			if(recipeAt == recipes.size())
				recipeAt = 0;
		}
		++ticksElapsed;
	}

	@Override
	public List<ItemStack> getDisplayedRecipes() {
		ArrayList<ItemStack> list = new ArrayList<>();
		for(RecipeElvenTrade r : recipes)
			list.add(r.getOutput());

		return list;
	}

}
