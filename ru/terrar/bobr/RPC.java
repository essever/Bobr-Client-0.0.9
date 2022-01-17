//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr;

import ru.terrar.bobr.discord.*;
import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import java.util.*;

public class RPC
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void startRPC() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "909559441907675136";
        RPC.discordRPC.Discord_Initialize(discordID, eventHandlers, true, (String)null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "Null";
        RPC.discordRichPresence.largeImageKey = "bobr";
        RPC.discordRichPresence.largeImageText = "Bobr Client  0.0.9";
        RPC.discordRPC.Discord_UpdatePresence(RPC.discordRichPresence);
        int use;
        int max;
        final Iterator<Module> iterator;
        Module module;
        new Thread(() -> {
            while (true) {
                use = 0;
                max = bobr.getGate().moduleManager.MODULE_LIST.size();
                bobr.getGate().moduleManager.MODULE_LIST.iterator();
                while (iterator.hasNext()) {
                    module = iterator.next();
                    if (module.isEnabled() && module.drawOnHud.getValue()) {
                        ++use;
                    }
                }
                RPC.discordRichPresence.state = "Hacks [" + use + "/" + max + ']';
                RPC.discordRPC.Discord_UpdatePresence(RPC.discordRichPresence);
                if (Minecraft.getMinecraft().world != null) {
                    if (Minecraft.getMinecraft().isSingleplayer()) {
                        RPC.discordRichPresence.details = "Singleplayer";
                    }
                    else {
                        RPC.discordRichPresence.details = "server: " + Minecraft.getMinecraft().getCurrentServerData().serverIP;
                    }
                }
                else {
                    RPC.discordRichPresence.details = "MainMenu";
                }
                RPC.discordRPC.Discord_RunCallbacks();
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public static void stopRPC() {
        RPC.discordRPC.Discord_Shutdown();
        RPC.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
}
