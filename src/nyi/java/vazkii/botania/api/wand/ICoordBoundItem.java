package vazkii.botania.api.wand;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ChunkCoordinates;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

/**
 * The item equivalent of ITileBound, renders when the
 * item is in hand.
 * @see ITileBound
 */
public interface ICoordBoundItem {

	@Environment(EnvType.CLIENT)
	public ChunkCoordinates getBinding(ItemStack stack);

}
