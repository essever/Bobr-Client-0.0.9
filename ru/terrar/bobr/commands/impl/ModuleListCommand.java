//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import ru.terrar.bobr.modules.*;
import org.lwjgl.input.*;
import java.util.*;

public class ModuleListCommand extends Command
{
    public ModuleListCommand() {
        super("modulelist", "Shows the name, id and keybind of every Module in the Client", new String[] { "modulelist" });
        this.addAliases(new String[] { "list", "modules" });
    }
    
    public void onCommand(final String[] args) {
        ChatUtil.clientMessage(TextFormatting.BOLD + "Module List:");
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            ChatUtil.clientMessage(TextFormatting.GOLD + module.getName() + ": " + TextFormatting.RESET + module.getId() + " [" + Keyboard.getKeyName(module.getKeyBind()) + "]");
        }
    }
}
