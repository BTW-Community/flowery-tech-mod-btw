package vazkii.botania.common.item.rod;

import api.util.MiscUtils;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.lib.LibItemNames;

public class ItemWaterRod extends ItemMod implements IManaUsingItem {

	public static final int COST = 75;

	public ItemWaterRod(int id) {
		super(id);
		setMaxStackSize(1);
		setUnlocalizedName(LibItemNames.WATER_ROD);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int facing, float clickX, float clickY, float clickZ) {
		if(ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, COST, false) && !world.provider.isHellWorld) {
			ForgeDirection dir = ForgeDirection.getOrientation(facing);

			ItemStack stackToPlace = new ItemStack(Block.waterMoving);
			stackToPlace.tryPlaceItemIntoWorld(par2EntityPlayer, world, x, y, z, facing, clickX, clickY, clickZ);

			if(stackToPlace.stackSize == 0) {
				//If not in the end, set it to be a flowing block
				if (world.provider.dimensionId != 1) {
					MiscUtils.placeNonPersistentWater(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				}
				ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, COST, true);
				for(int i = 0; i < 6; i++)
					Botania.getProxy().sparkleFX(world, i + dir.offsetX + Math.random(), y + dir.offsetY + Math.random(), z + dir.offsetZ + Math.random(), 0.2F, 0.2F, 1F, 1F, 5);
			}
		}
		return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}

}
