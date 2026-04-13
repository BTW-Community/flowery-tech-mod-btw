package vazkii.botania.client.model;

import net.minecraft.src.Entity;
import net.minecraft.src.Icon;
import net.minecraft.src.ItemRenderer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelBox;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.render.item.RenderGun;
import vazkii.botania.common.item.lens.ItemLens;

public class ModelGun extends ModelBase {
    public final ModelRenderer bone;
    private final ModelRenderer Magazine_r1;

    public final CustomModelRenderer lens;

    public ModelGun(boolean hasClip) {
        textureWidth = 64;
        textureHeight = 64;

        bone = new CustomModelRenderer(this);
        bone.setRotationPoint(8.0F, 24.0F, -8.0F);
        bone.cubeList.add(new ModelBox(bone, 30, 12, -17.5F, -12.0F, 6.0F, 8, 1, 4, 0.0F)/*, false*/ {
            @Override
            public void render(Tessellator tessellator, float f) {
                super.render(tessellator, f);
                GL11.glColor4f(1F, 1F, 1F, 1f);
            }
        });
        bone.cubeList.add(new ModelBox(bone, 0, 21, 3.0F, -14.0F, 5.5F, 7, 9, 5, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 0, 12, -7.0F, -9.0F, 5.5F, 10, 4, 5, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 16, 36, -10.0F, -14.0F, 5.5F, 3, 9, 5, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 24, 29, -18.0F, -14.0F, 5.5F, 8, 2, 5, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 24, 29, -18.0F, -11.0F, 5.5F, 8, 2, 5, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 32, 36, -16.0F, -7.0F, 7.0F, 6, 1, 2, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 38, 0, -16.0F, -9.0F, 7.5F, 3, 2, 1, 0.0F/*, false*/));
        bone.cubeList.add(new ModelBox(bone, 38, 3, -11.0F, -9.0F, 7.0F, 1, 2, 2, 0.0F/*, false*/));

        bone.cubeList.add(new ModelBox(bone, 24, 21, -7.5F, -13.5F, 6.0F, 11, 4, 4, 0.0F/*, false*/) {
            //C&Pd from water rendering :)
            @Override
            public void render(Tessellator tess, float par1) {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glColor4f(1F, 1F, 1F, 0.15f);
                super.render(tess, par1);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_BLEND);
            }
        });
        if (hasClip)
            bone.cubeList.add(new ModelBox(bone, 0, 0, -8.5F, -8.0F, 5.0F, 13, 6, 6, 0.0F/*, false*/));


        lens = new CustomModelRenderer(this);
        lens.setRotationPoint(-8.0F, 0.0F, 8.0F);
        bone.addChild(lens);
//        lens.cubeList.add(new ModelBox(lens, 32, 19, 5.5F, -14.5F, -3.0F, 1, 6, 6, 0.0F) {
//            @Override
//            public void render(Tessellator tess, float f) {
//                var tm = Minecraft.getMinecraft().getTextureManager();
//                int lensMeta = lens.lensType;
//                if (lensMeta >= 0) {
//                    Icon icon = ItemLens.ringIcons[lensMeta];
//                    float minU = icon.getMinU();
//                    float maxU = icon.getMaxU();
//                    float minV = icon.getMinV();
//                    float maxV = icon.getMaxV();
//                    tm.bindTexture(tm.getResourceLocation(1));
//                    ItemRenderer.renderItemIn2D(tess, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
//                    tm.bindTexture(RenderGun.texture);
//                }
//            }
//        });

        Magazine_r1 = new ModelRenderer(this);
        Magazine_r1.setRotationPoint(-8.0F, -8.0F, 8.0F);
        bone.addChild(Magazine_r1);
        setRotationAngle(Magazine_r1, 0.0F, 0.0F, 0.3927F);
        Magazine_r1.cubeList.add(new ModelBox(Magazine_r1, 30, 17, -10.5F, 13.0F, -1.0F, 6, 1, 2, 0.0F/*, false*/));
        Magazine_r1.cubeList.add(new ModelBox(Magazine_r1, 0, 35, -11.0F, -1.0F, -1.5F, 5, 14, 3, 0.0F/*, false*/));
    }

    @Override
    public void render(Entity entity, float transX, float transY, float transZ, float f3, float f4, float f5) {

        bone.render(f5);
        var tm = Minecraft.getMinecraft().getTextureManager();
        int lensMeta = lens.lensType;
        if (lensMeta >= 0) {
            GL11.glPushMatrix();
            Icon icon = ItemLens.iconGlass;
            float minU = icon.getMinU();
            float maxU = icon.getMaxU();
            float minV = icon.getMinV();
            float maxV = icon.getMaxV();
            tm.bindTexture(tm.getResourceLocation(1));
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef(-.5f, .3f, .4f);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1F, 1F, 1F, 1f);

            ItemRenderer.renderItemIn2D(Tessellator.instance, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);

            icon = ItemLens.ringIcons[lensMeta];
            minU = icon.getMinU();
            maxU = icon.getMaxU();
            minV = icon.getMinV();
            maxV = icon.getMaxV();
            ItemRenderer.renderItemIn2D(Tessellator.instance, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
//            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
            tm.bindTexture(RenderGun.texture);
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setLens(ItemStack lens) {
        this.lens.lensType = lens == null ? -1 : lens.getItemDamage();
    }
}
