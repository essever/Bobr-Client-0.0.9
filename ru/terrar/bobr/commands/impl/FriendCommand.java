//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.managers.*;

public class FriendCommand extends Command
{
    public FriendCommand() {
        super("friend", "add or remove friend", new String[] { "friend <nick>" });
        this.addAliases(new String[] { "f" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 2) {
            FriendManager.toggleFriend(args[1].toUpperCase());
        }
        else {
            this.syntaxError();
        }
    }
}
