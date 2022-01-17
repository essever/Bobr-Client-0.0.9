//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import java.util.*;

public class HelpCommand extends Command
{
    public HelpCommand() {
        super("help", "Displays this list", new String[] { "help" });
        this.addAliases(new String[] { "?" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 1) {
            ChatUtil.clientMessage(TextFormatting.BOLD + "List of Commands in this Client:");
            for (final Command command : bobr.getGate().commandManager.COMMAND_LIST) {
                ChatUtil.clientMessage(TextFormatting.GOLD + command.getName() + ": " + TextFormatting.RESET + command.getDesc());
            }
        }
        else {
            this.syntaxError();
        }
    }
}
