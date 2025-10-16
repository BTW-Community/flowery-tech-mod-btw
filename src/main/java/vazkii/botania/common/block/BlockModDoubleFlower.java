/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Mar 22, 2015, 7:46:55 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.bagel.interfaces.BlockExtensions;
import dev.bagel.shim.BlockDoublePlant;
import dev.bagel.util.Items;
import net.minecraft.src.Block;
import net.minecraft.src.IconRegister;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatList;
import net.minecraft.src.Icon;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeEventFactory;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

//todo double flower block backport, this is pain
public class BlockModDoubleFlower extends BlockDoublePlant implements ILexiconable {

    private static final int COUNT = 8;

    Icon[] doublePlantTopIcons, doublePlantBottomIcons;
    Icon[] doublePlantTopIconsAlt, doublePlantBottomIconsAlt;

    final int offset;

    public BlockModDoubleFlower(int id, boolean second) {
        super(id);
        offset = second ? 8 : 0;
        setUnlocalizedName(LibBlockNames.DOUBLE_FLOWER + (second ? 2 : 1));
        setHardness(0F);
        setStepSound(soundGrassFootstep);
        setTickRandomly(false);
//		setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public Block setUnlocalizedName(String par1Str) {
        if (!par1Str.equals("doublePlant")) {
            var item = new ItemBlockWithMetadataAndName(this);
        }
//GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
        return super.setUnlocalizedName(par1Str);
    }

//    @Override
//    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
//        return null;
//    }

    @Override
    public int damageDropped(int meta) {
        return meta & 7;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Icon func_149888_a(boolean top, int index) {
        return (ConfigHandler.altFlowerTextures ? top ? doublePlantTopIconsAlt : doublePlantBottomIconsAlt : top ? doublePlantTopIcons : doublePlantBottomIcons)[index & 7];
    }

    @Override
    public void func_149889_c(World world, int x, int y, int z, int meta, int flags) {
        world.setBlock(x, y, z, this, meta, flags);
        world.setBlock(x, y + 1, z, this, meta | 8, flags);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        world.setBlock(x, y + 1, z, this, stack.getItemDamage() | 8, 2);
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean fuckifiknow) {
        return false;
    }

    @Override
    protected boolean canSilkHarvest(int iMetadata) {
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (world.isRemote || player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != Item.shears || func_149887_c(meta)) {
            harvestBlockCopy(world, player, x, y, z, meta);
        }
    }

    // This is how I get around encapsulation
	public void harvestBlockCopy(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int meta) {
		if (func_149887_c(meta)) {
			par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
			par2EntityPlayer.addHarvestBlockExhaustion(this.blockID, par3, par4, par5, meta);
			if (this.canSilkHarvest(meta) && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer)) {
				ItemStack var8 = this.createStackedBlock(meta);
				if (var8 != null) {
					this.dropBlockAsItem_do(par1World, par3, par4, par5, var8);
				}
			} else {
				int var7 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
				this.dropBlockAsItem(par1World, par3, par4, par5, meta, var7);
			}
		}
	}

    //    public void harvestBlockCopy(World worldIn, int x, int y, int z, int meta, EntityPlayer player) {
//        if (func_149887_c(meta)) {
//            if (worldIn.getBlock(x, y - 1, z) == this) {
//                if (!player.capabilities.isCreativeMode) {
//                    int i1 = worldIn.getBlockMetadata(x, y - 1, z);
//                    int j1 = func_149890_d(i1);
//
//                    if (j1 != 3 && j1 != 2) {
//                        worldIn.func_147480_a(x, y - 1, z, true);
//                    } else {
//                        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
//                            this.func_149886_b(worldIn, x, y, z, i1, player);
//                        }
//
//                        worldIn.setBlockToAir(x, y - 1, z);
//                    }
//                } else {
//                    worldIn.setBlockToAir(x, y - 1, z);
//                }
//            }
//        } else if (player.capabilities.isCreativeMode && worldIn.getBlock(x, y + 1, z) == this) {
//            worldIn.setBlock(x, y + 1, z, 0, 0, 2);
//        }
//
//        super.onBlockHarvested(worldIn, x, y, z, meta, player);
//    }



    @Override
    public void onBlockHarvested(World world, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
        if(func_149887_c(p_149681_5_)) {
            if(world.getBlock(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this) {
                if(!p_149681_6_.capabilities.isCreativeMode) {
                    int i1 = world.getBlockMetadata(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
                    int j1 = func_149890_d(i1);

                    if(j1 != 3 && j1 != 2);
                        //p_149681_1_.func_147480_a(p_149681_2_, p_149681_3_ - 1, p_149681_4_, true);
                    else {
						/*if (!p_149681_1_.isRemote && p_149681_6_.getCurrentEquippedItem() != null && p_149681_6_.getCurrentEquippedItem().getItem() == Items.shears)
                        {
                            this.func_149886_b(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, i1, p_149681_6_);
                        }*/

                        world.setBlockToAir(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
                    }
                } else world.setBlockToAir(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
            }
        } else if(p_149681_6_.capabilities.isCreativeMode && world.getBlock(p_149681_2_, p_149681_3_ + 1, p_149681_4_) == this)
            world.setBlock(p_149681_2_, p_149681_3_ + 1, p_149681_4_, 0, 0, 2);

        //super.onBlockHarvested(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, p_149681_6_);
    }


    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 7));
        return ret;
    }


	/*	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		return new ArrayList<>();
	}*/

    @Override
    public Icon getIcon(int p_149691_1_, int p_149691_2_) {
        boolean top = func_149887_c(p_149691_2_);
        return (ConfigHandler.altFlowerTextures ? top ? doublePlantTopIconsAlt : doublePlantBottomIconsAlt : top ? doublePlantTopIcons : doublePlantBottomIcons)[p_149691_2_ & 7];
    }

    @Override
    public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        boolean top = func_149887_c(meta);
        if (top)
            meta = world.getBlockMetadata(x, y - 1, z);

        return (ConfigHandler.altFlowerTextures ? top ? doublePlantBottomIconsAlt : doublePlantTopIconsAlt : top ? doublePlantBottomIcons : doublePlantTopIcons)[meta & 7];
    }

    @Override
    public void registerIcons(IconRegister register) {
        doublePlantTopIcons = new Icon[COUNT];
        doublePlantBottomIcons = new Icon[COUNT];
        doublePlantTopIconsAlt = new Icon[COUNT];
        doublePlantBottomIconsAlt = new Icon[COUNT];
        for (int i = 0; i < COUNT; i++) {
            int off = offset(i);
            doublePlantTopIcons[i] = IconHelper.forName(register, "flower" + off + "Tall0");
            doublePlantBottomIcons[i] = IconHelper.forName(register, "flower" + off + "Tall1");
            doublePlantTopIconsAlt[i] = IconHelper.forName(register, "flower" + off + "Tall0", BlockModFlower.ALT_DIR);
            doublePlantBottomIconsAlt[i] = IconHelper.forName(register, "flower" + off + "Tall1", BlockModFlower.ALT_DIR);
        }
    }

    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return 16777215;
    }

    @Override
    public void getSubBlocks(int id, CreativeTabs tab, List items) {
        for (int i = 0; i < COUNT; ++i)
            items.add(new ItemStack(id, 1, i));
    }

    @Override
    public int getRenderType() {
        return LibRenderIDs.idDoubleFlower;
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        float[] color = EntitySheep.fleeceColorTable[offset(meta & 7)];

        if (par5Random.nextDouble() < ConfigHandler.flowerParticleFrequency)
            Botania.getProxy().sparkleFX(par1World, par2 + 0.3 + par5Random.nextFloat() * 0.5, par3 + 0.5 + par5Random.nextFloat() * 0.5, par4 + 0.3 + par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.flowers;
    }

    int offset(int meta) {
        return meta + offset;
    }

}
