package dev.bagel.mixin.event.client;

import baubles.client.gui.GuiBaublesButton;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import baubles.common.network.PacketOpenNormalInventory;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static baubles.common.BaublesConfig.useOldGuiButton;


@Mixin(GuiScreen.class)
public class GuiScreenMixin {
    @Shadow protected List buttonList;

    // ActionPerformedEvent.Post
    @Inject(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/GuiScreen;actionPerformed(Lnet/minecraft/src/GuiButton;)V", shift = At.Shift.AFTER))
    private void onGuiScreenMouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo ci, @Local GuiButton button) {
        GuiScreen gui = (GuiScreen) (Object) this;
        if (gui instanceof GuiInventory) {
            if (button.id == 55) {
                PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory(gui.mc.thePlayer));
            }
        }

        if (gui instanceof GuiPlayerExpanded) {
            if (button.id == 55) {
                gui.mc.displayGuiScreen(new GuiInventory(gui.mc.thePlayer));
                PacketHandler.INSTANCE.sendToServer(new PacketOpenNormalInventory(gui.mc.thePlayer));
            }
        }
    }
    
    @Inject(method = "setWorldAndResolution", at = @At("TAIL"))
    private void baubles$postSetup(Minecraft mc, int width, int height, CallbackInfo ci) {
        GuiScreen gui = (GuiScreen) (Object) this;
        if (!(gui instanceof GuiInventory) && !(gui instanceof GuiPlayerExpanded)) {
            return;
        }

        int xSize = 176;
        int ySize = 166;

        int guiLeft = (gui.width - xSize) / 2;
        int guiTop = (gui.height - ySize) / 2;

        if (!Minecraft.getMinecraft().thePlayer.getActivePotionEffects().isEmpty()/* && isNeiHidden()*/) {
            guiLeft = 160 + (gui.width - xSize - 200) / 2;
        }

        String tooltip = I18n.getString((gui instanceof GuiInventory) ? "button.baubles" : "button.normal");
        if (useOldGuiButton) {
            this.buttonList.add(new GuiBaublesButton(55, guiLeft + 66, guiTop + 9, 10, 10,
                    tooltip));
        } else {
            this.buttonList.add(new GuiBaublesButton(55, guiLeft + 26, guiTop + 9, 10, 10,
                    tooltip));
        }
    }
}
