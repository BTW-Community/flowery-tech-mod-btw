package vazkii.botania.common.block;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import vazkii.botania.common.block.tile.TileCraftCrate;

import java.util.List;

public class BlockCraftyCrate extends BlockOpenCrate{
    public BlockCraftyCrate(int id) {
        super(id);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCraftCrate();
    }

    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 1));
    }
}
