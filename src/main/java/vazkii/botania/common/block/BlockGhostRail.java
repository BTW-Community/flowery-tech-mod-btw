/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jun 9, 2015, 12:48:18 AM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class BlockGhostRail extends BlockRailBase implements ILexiconable {

	private static final String TAG_FLOAT_TICKS = "Botania_FloatTicks";

	public BlockGhostRail(int id) {
		super(id, true);
		setCreativeTab(CreativeTabs.tabMisc);
		MinecraftForge.EVENT_BUS.register(this);
		setUnlocalizedName(LibBlockNames.GHOST_RAIL);
        MinecartUpdateEvent.EVENT.register(this::onMinecartUpdate);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		var item = new ItemBlockMod(this);
//		GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	@SubscribeEvent
	public void onMinecartUpdate(MinecartUpdateEvent event) {
		int x = MathHelper.floor_double(event.entity.posX);
		int y = MathHelper.floor_double(event.entity.posY);
		int z = MathHelper.floor_double(event.entity.posZ);
		Block block = event.entity.worldObj.getBlock(x, y, z);
		boolean air = event.entity.worldObj.isAirBlock(x, y, z);
		int floatTicks = event.entity.getEntityData().getInteger(TAG_FLOAT_TICKS);

		if(block == this)
			event.entity.getEntityData().setInteger(TAG_FLOAT_TICKS, 20);
		else if(block instanceof BlockRailBase || block == ModBlocks.dreamwood) {
			event.entity.getEntityData().setInteger(TAG_FLOAT_TICKS, 0);
			if(floatTicks > 0)
				event.entity.worldObj.playAuxSFX(2003, x, y, z, 0);
		}
		floatTicks = event.entity.getEntityData().getInteger(TAG_FLOAT_TICKS);

		if(floatTicks > 0) {
			boolean airBelow = event.entity.worldObj.isAirBlock(x, y - 1, z);
			if(air && airBelow || !air && !airBelow)
				event.entity.noClip = true;
			event.entity.motionY = 0.2;
			event.entity.motionX *= 1.4;
			event.entity.motionZ *= 1.4;
			event.entity.getEntityData().setInteger(TAG_FLOAT_TICKS, floatTicks - 1);
			event.entity.worldObj.playAuxSFX(2000, x, y, z, 0);
		} else event.entity.noClip = false;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.ghostRail;
	}

}
