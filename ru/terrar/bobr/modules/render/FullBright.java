//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FullBright extends Module
{
    public static final FullBright INSTANCE;
    private float lastGamma;
    
    public FullBright() {
        super("Full Bright", "fullbright", Module.ModuleCategory.RENDER);
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        Minecraft.getMinecraft().gameSettings.gammaSetting = Math.min(this.lastGamma, 1.0f);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 10000.0f;
    }
    
    static {
        INSTANCE = new FullBright();
    }
}
