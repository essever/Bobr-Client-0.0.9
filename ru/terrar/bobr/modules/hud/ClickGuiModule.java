//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import ru.terrar.bobr.*;
import net.minecraft.client.gui.*;

public class ClickGuiModule extends Module
{
    public static final ClickGuiModule INSTANCE;
    
    public ClickGuiModule() {
        super("Click GUI", "clickgui", 54, ModuleCategory.HUD);
    }
    
    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)bobr.getGate().guiManager.CLICK_GUI);
        super.onEnable();
    }
    
    static {
        INSTANCE = new ClickGuiModule();
    }
}
