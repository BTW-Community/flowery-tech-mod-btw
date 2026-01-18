/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Oct 21, 2014, 4:58:55 PM (GMT)]
 */
package vazkii.botania.client.core.handler;

import api.client.debug.DebugInfoSection;
import api.client.debug.DebugRegistry;
import api.client.debug.DebugRegistryUtils;
import net.minecraft.src.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

import vazkii.botania.client.fx.ParticleRenderDispatcher;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.lib.LibMisc;

import java.util.Optional;

public final class DebugHandler {
    public static final ResourceLocation SECTION_ID = new ResourceLocation(LibMisc.MOD_ID, "debug");
    public DebugHandler() {
        DebugInfoSection section = DebugRegistryUtils.registerSection(SECTION_ID, DebugRegistryUtils.Side.RIGHT);
        section.orderSection(DebugRegistry.specsSectionID, 100);
        onDrawDebugText(section);
    }

    private static final String PREFIX = EnumChatFormatting.GREEN + "[Botania] " + EnumChatFormatting.RESET;

    private Optional<String> toOption(boolean isExtendedDebug, String expected) {
        if (isExtendedDebug) {
            return Optional.of(expected);
        }
        return Optional.empty();
    }

    private Optional<String> toOptionIfCtrlShiftDown(boolean isExtendedDebug, String expected) {
        if (isExtendedDebug && GuiScreen.isCtrlKeyDown() && GuiScreen.isShiftKeyDown()) {
            return Optional.of(expected);
        }
        return Optional.empty();
    }

//	@SubscribeEvent
	public void onDrawDebugText(DebugInfoSection section) {
		World world = Minecraft.getMinecraft().theWorld;
        final String version;
        if(LibMisc.VERSION.contains("GRADLE"))
            version = "N/A";
        else version = LibMisc.VERSION;
        section.addEntry((mc, isExtendedDebug) -> toOption(isExtendedDebug, PREFIX + "pS: " + ParticleRenderDispatcher.sparkleFxCount + ", pFS: " + ParticleRenderDispatcher.fakeSparkleFxCount + ", pW: " + ParticleRenderDispatcher.wispFxCount + ", pDIW: " + ParticleRenderDispatcher.depthIgnoringWispFxCount + ", pLB: " + ParticleRenderDispatcher.lightningCount));
        section.addEntry((mc, isExtendedDebug) -> toOption(isExtendedDebug, PREFIX + "netColl: " + ManaNetworkHandler.instance.getAllCollectorsInWorld(world).size() + ", netPool: " + ManaNetworkHandler.instance.getAllPoolsInWorld(world).size() + ", rv: " + version));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, PREFIX + "Config Context"));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  shaders.enabled: " + ConfigHandler.useShaders));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  shaders.secondaryUnit: " + ConfigHandler.glSecondaryTextureUnit));


        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, PREFIX + "OpenGL Context"));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_VERSION: " + GL11.glGetString(GL11.GL_VERSION)));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_RENDERER: " + GL11.glGetString(GL11.GL_RENDERER)));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_SHADING_LANGUAGE_VERSION: " + GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION)));
        section.addEntry((mc, isExtendedDebug) -> toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_MAX_TEXTURE_IMAGE_UNITS_ARB: " + GL11.glGetInteger(ARBFragmentShader.GL_MAX_TEXTURE_IMAGE_UNITS_ARB)));
        section.addEntry((mc, isExtendedDebug) -> {
            ContextCapabilities caps = GLContext.getCapabilities();
            return toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_ARB_multitexture: " + caps.GL_ARB_multitexture);
        });
        section.addEntry((mc, isExtendedDebug) -> {
            ContextCapabilities caps = GLContext.getCapabilities();
            return toOptionIfCtrlShiftDown(isExtendedDebug, "  GL_ARB_texture_non_power_of_two: " + caps.GL_ARB_texture_non_power_of_two);
        });
        section.addEntry((mc, isExtendedDebug) -> {
            ContextCapabilities caps = GLContext.getCapabilities();
            return toOptionIfCtrlShiftDown(isExtendedDebug, "  OpenGL13: " + caps.OpenGL13);
        });
        section.addEntry((mc, isExtendedDebug) -> {
            if (Minecraft.isRunningOnMac)
                return toOption(isExtendedDebug, PREFIX + "SHIFT+CMD for context");
            return Optional.empty();
        });
        section.addEntry((mc, isExtendedDebug) -> {
            if (!Minecraft.isRunningOnMac)
                toOption(isExtendedDebug, PREFIX + "SHIFT+CTRL for context");
            return Optional.empty();
        });
	}

}
