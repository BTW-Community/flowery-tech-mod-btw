package dev.bagel.util;

import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;

public class GuiButtonSound extends GuiButton {
    public GuiButtonSound(int par1, int par2, int par3, String par4Str) {
        super(par1, par2, par3, par4Str);
    }

    public GuiButtonSound(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, par6Str);
    }
    //todofix redirect GuiScreen#mouseClicked and check button for GuiButtonSound
    /** Play sound*/
    public void func_146113_a() {
        Minecraft.getMinecraft().sndManager.playSoundFX("random.click", 1.0f, 1.0f);
    }
}
