/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Sep 30, 2015, 10:01:17 PM (GMT)]
 */
package vazkii.botania.common.block.decor.panes;

import net.minecraft.src.*;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockMod;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockModPane extends BlockPane {

	Block source;
	public Icon iconTop;

	public BlockModPane(int id, Block source) {
		super(id, "", "", Material.glass, false);
		this.source = source;
		setUnlocalizedName(source.getUnlocalizedName().replaceAll("tile.", "") + "Pane");
		setCreativeTab(ModItems.botaniaTab);
		setHardness(0.3F);
		setStepSound(soundGlassFootstep);
		setLightValue(1.0F);
		useNeighborBrightness[0] = true;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconTop = IconHelper.forBlock(reg, this);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return false;
	}

	@Override
	public int getRenderType() {
		return 18;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getSideTextureIndex() {
		return source.getIcon(0, 0);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side >= 2 ? iconTop : source.getIcon(side, meta);
	}

	@Override
	public boolean canThisPaneConnectToThisBlockID(int id) {
		Block block = Block.blocksList[id];
		return block == ModBlocks.elfGlass || block == ModBlocks.manaGlass || block == ModBlocks.bifrostPerm || super.canThisPaneConnectToThisBlockID(id);
	}
//	@Override
//	public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
//		Block block = world.getBlock(x, y, z);
//		return block == ModBlocks.elfGlass || block == ModBlocks.manaGlass || block == ModBlocks.bifrostPerm || super.canPaneConnectTo(world, x, y, z, dir);
//	}

}
