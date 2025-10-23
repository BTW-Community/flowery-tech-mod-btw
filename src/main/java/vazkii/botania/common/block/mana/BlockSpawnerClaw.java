/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jul 23, 2014, 5:28:55 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import java.util.List;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.src.*;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileSpawnerClaw;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockSpawnerClaw extends BlockModContainer<TileSpawnerClaw> implements ILexiconable {

	public BlockSpawnerClaw(int id) {
		super(id, Material.iron);
		setHardness(3.0F);
		setUnlocalizedName(LibBlockNames.SPAWNER_CLAW);

		float f = 1F / 8F;
		float f1 = 1F / 16F;
		initBlockBounds(f, 0F, f, 1F - f, f1, 1F - f);
	}

	@Override
	public void getSubBlocks(int item, CreativeTabs tab, List list) {
		list.add(new ItemStack(Item.itemsList[item]));
//		list.add(new ItemStack(Block.mobSpawner));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		//NO-OP
	}

	@Override
	public boolean renderBlock(RenderBlocks renderer, int i, int j, int k) {
		return false;
	}

	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
		RenderingRegistry.instance().renderInventoryBlock(renderBlocks, this, iItemDamage, getRenderType());
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idSpawnerClaw;
	}

	@Override
	public TileSpawnerClaw createNewTileEntityT(World world, int meta) {
		return new TileSpawnerClaw();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.spawnerClaw;
	}

}
