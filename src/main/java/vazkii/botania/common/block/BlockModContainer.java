/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 14, 2014, 5:32:55 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.*;
import vazkii.botania.client.core.helper.IconHelper;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import vazkii.botania.common.item.block.ItemBlockMod;

public abstract class BlockModContainer<T extends TileEntity> extends BlockContainer {

	public int originalLight;

	protected BlockModContainer(int id, Material par2Material) {
		super(id, par2Material);
		if(registerInCreative())
			setCreativeTab(CreativeTabs.tabMisc/*.INSTANCE*/);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		if(shouldRegisterInNameSet()) {
			Item registered = new ItemBlockMod(this);
		}
//			GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	protected boolean shouldRegisterInNameSet() {
		return true;
	}

	@Override
	public Block setLightValue(float value) {
		originalLight = (int) (value * 15);
		return super.setLightValue(value);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	public boolean registerInCreative() {
		return true;
	}

	public abstract T createNewTileEntityT(World world, int meta);
	//todofix IMPORTANT no meta support for different tiles >:(
	@Override
	public TileEntity createNewTileEntity(World world) {
		return createNewTileEntityT(world, 0);
	}
}