package dev.bagel.emi;

import emi.dev.emi.emi.api.widget.TextureWidget;
import emi.shims.java.net.minecraft.client.gui.DrawContext;
import net.minecraft.src.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlendTextureWidget extends TextureWidget {
    public BlendTextureWidget(ResourceLocation texture, int x, int y, int width, int height, int u, int v) {
        super(texture, x, y, width, height, u, v);
    }

    @Override
    public void render(DrawContext draw, int mouseX, int mouseY, float delta) {
        GL11.glEnable(GL11.GL_BLEND);
        super.render(draw, mouseX, mouseY, delta);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
