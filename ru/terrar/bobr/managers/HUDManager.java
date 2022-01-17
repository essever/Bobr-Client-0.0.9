//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import net.minecraftforge.client.event.*;
import ru.terrar.bobr.modules.hud.*;
import ru.terrar.bobr.modules.render.*;
import java.io.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HUDManager
{
    public void init() {
        ModuleList.INSTANCE.sortList();
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent event) throws IOException {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (ModuleList.INSTANCE.isEnabled()) {
                ModuleList.INSTANCE.drawList(event.getResolution());
            }
            if (Coords.INSTANCE.isEnabled()) {
                Coords.INSTANCE.drawCoords(event.getResolution());
            }
            if (Watermark.INSTANCE.isEnabled()) {
                Watermark.INSTANCE.drawWatermark();
            }
            if (TargetHud.INSTANCE.isEnabled()) {
                TargetHud.INSTANCE.drawtartet();
            }
            if (PlayerRadar.INSTANCE.isEnabled()) {
                PlayerRadar.INSTANCE.drawradar();
            }
        }
        if (NoOverlay.INSTANCE.isEnabled() && event instanceof RenderGameOverlayEvent.Pre && (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO || event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS)) {
            event.setCanceled(false);
            event.setCanceled(false);
        }
    }
}
