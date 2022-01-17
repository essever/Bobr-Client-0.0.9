//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraft.client.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.commands.*;
import java.util.*;

public class ChatUtil
{
    public static void clientMessage(String message) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        message = TextFormatting.BLUE + "[" + "Bobr Client" + "] " + TextFormatting.RESET + message;
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(message));
    }
    
    public static boolean isCommand(final String name, final Command command) {
        if (name.equalsIgnoreCase(command.getName())) {
            return true;
        }
        for (final String alias : command.getAliases()) {
            if (name.equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }
}
