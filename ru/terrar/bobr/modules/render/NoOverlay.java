//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.client.*;

public class NoOverlay extends Module
{
    public static final NoOverlay INSTANCE;
    
    public NoOverlay() {
        super("No Overlay", "nooverlay", Module.ModuleCategory.RENDER);
    }
    
    public void onEnable() {
        super.onEnable();
        GuiIngameForge.renderObjective = false;
    }
    
    public void onDisable() {
        super.onDisable();
        GuiIngameForge.renderObjective = true;
    }
    
    static {
        INSTANCE = new NoOverlay();
    }
}
