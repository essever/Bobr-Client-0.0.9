//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;

public class PrefixCommand extends Command
{
    public PrefixCommand() {
        super("prefix", "Changes the Command Prefix", new String[] { "prefix <new prefix>" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 2) {
            bobr.getGate().commandManager.setPrefix(args[1]);
            bobr.getGate().configManager.save();
            ChatUtil.clientMessage("Prefix changed to: " + TextFormatting.GOLD + args[1]);
            return;
        }
        this.syntaxError();
    }
}
