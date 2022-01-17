//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.*;
import net.minecraftforge.common.*;

public class DiscordRPC extends Module
{
    public static final DiscordRPC INSTANCE;
    
    public DiscordRPC() {
        super("Discord RPC", "discordRPC", ModuleCategory.HUD);
    }
    
    @Override
    public void onEnable() {
        RPC.startRPC();
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        RPC.stopRPC();
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    static {
        INSTANCE = new DiscordRPC();
    }
}
