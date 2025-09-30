package vazkii.botania.common.block.decor.slabs;

import net.minecraft.src.Block;
import net.minecraft.util.IIcon;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public abstract class BlockLivingSlab extends BlockModSlab {

	Block source;
	int meta;

	public BlockLivingSlab(boolean full, Block source, int meta) {
		super(full, source.getMaterial(), source.getUnlocalizedName().replaceAll("tile.", "") + meta + "Slab" + (full ? "Full" : ""));
		setStepSound(source.stepSound);
		this.source = source;
		this.meta = meta;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return source.getIcon(par1, meta);
	}

}
