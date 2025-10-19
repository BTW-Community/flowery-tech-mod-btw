/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 14, 2014, 5:31:15 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.*;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.block.ItemBlockElven;
import vazkii.botania.common.item.block.ItemBlockMod;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockMod extends Block {

	public int originalLight;

	public BlockMod(int id, Material par2Material) {
		super(id, par2Material);
		if(registerInCreative())
			setCreativeTab(CreativeTabs.tabMisc /*BotaniaCreativeTab.INSTANCE*/);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		if(shouldRegisterInNameSet()) {
			Item registered = this instanceof IElvenItem ? new ItemBlockElven(this) : new ItemBlockMod(this);
//			GameRegistry.registerBlock(this, this instanceof IElvenItem ? ItemBlockElven.class : ItemBlockMod.class, par1Str);
		}
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

	boolean registerInCreative() {
		return true;
	}


}
