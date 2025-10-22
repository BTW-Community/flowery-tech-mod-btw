package dev.bagel.emi;

import emi.dev.emi.emi.api.widget.Widget;
import emi.dev.emi.emi.screen.Bounds;
import emi.shims.java.net.minecraft.client.gui.DrawContext;
import vazkii.botania.client.core.handler.HUDHandler;

public class ManaWidget extends Widget {
    private final int x, y;
    private final int mana, maxMana;

    public ManaWidget(int x, int y, int mana, int maxMana) {
        this.x = x;
        this.y = y;
        this.mana = mana;
        this.maxMana = maxMana;
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(x, y, 102, 5);
    }

    @Override
    public void render(DrawContext var1, int var2, int var3, float var4) {
        HUDHandler.renderManaBar(x, y, 0x0000FF, 0.75F, mana, maxMana);
    }
}