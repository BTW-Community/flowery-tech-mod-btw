package vazkii.botania.client.render.item;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.model.ModelGun;
import vazkii.botania.common.item.ItemManaGun;

public class RenderGun implements IItemRenderer {

    ModelGun modelClip = new ModelGun(true);
    ModelGun modelNoClip = new ModelGun(false);
    ResourceLocation texture = new ResourceLocation(LibResources.MODEL_GUN);

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.ENTITY && !item.isOnItemFrame() || type == ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(texture);
        if (item == null || !(item.getItem() instanceof ItemManaGun gunItem)) return;

        boolean hasClip = ItemManaGun.hasClip(item);
        ItemStack lensStack = ItemManaGun.getLens(item);

        float translateX = 0f, translateY = 0f, translateZ = 0f;
        float rotateX = 0f, rotateY = 0f, rotateZ = 0f;
        switch(type) {
            case EQUIPPED, EQUIPPED_FIRST_PERSON -> {
                GL11.glScalef(0.45F, 0.45F, 0.45F);
                GL11.glRotatef(45, 0F, 0F, 1F);
                GL11.glRotatef(180, 1F, 0F, 0F);
                translateX = 1.8f;
                translateY = -1f;
                GL11.glRotatef(90, rotateX, rotateY, rotateZ);
                GL11.glTranslatef(translateX, translateY, translateZ);
                render(hasClip, lensStack);
            }
            case ENTITY -> {
                if(item.isOnItemFrame()) {
                    GL11.glScalef(0.45F, 0.45F, 0.45F);
                    GL11.glTranslatef(0.05f, -.8f, 0.1f);
                    GL11.glRotatef(90, 0f, 1f, 0f);
                }
                else {
                    GL11.glScalef(0.5F, 0.5F, 0.5F);
                    GL11.glRotatef(180, 1f, 0f, 0f);
                    GL11.glTranslatef(0.0f, -1.3f, 0f);
                }
                render(hasClip, lensStack);

            }
            case INVENTORY -> {
                GL11.glRotatef(180, 1f, 0f, 0f);
//                GL11.glRotatef(90, 0f, 1f, 0f);
                GL11.glTranslatef(0.0f, -1.05f, .1f);
                GL11.glRotatef(180, 0f, 1f, 0f);
                render(hasClip, lensStack);
            }
            case FIRST_PERSON_MAP -> {

            }
        }

        GL11.glPopMatrix();
    }

    private void render(boolean hasClip, ItemStack lensStack) {
        if (hasClip)
            modelClip.render(null, 0f, 0f, 0f, 0f, 0f, 1/16f);
        else modelNoClip.render(null, 0f, 0f, 0f, 0f, 0f, 1/16f);
    }
}