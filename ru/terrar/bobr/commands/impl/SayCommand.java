//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import net.minecraft.client.*;

public class SayCommand extends Command
{
    public SayCommand() {
        super("say", "Sends a message (that can include the prefix)", new String[] { "say <message>" });
        this.addAliases(new String[] { "s" });
    }
    
    public void onCommand(final String[] args) {
        final StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            message.append(args[i]);
            if (i != args.length - 1) {
                message.append(" ");
            }
        }
        Minecraft.getMinecraft().player.sendChatMessage(message.toString());
    }
}
