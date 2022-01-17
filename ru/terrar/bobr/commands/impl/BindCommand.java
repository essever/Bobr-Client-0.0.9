//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.util.*;
import net.minecraft.util.text.*;
import org.lwjgl.input.*;
import java.util.*;

public class BindCommand extends Command
{
    public BindCommand() {
        super("bind", "Binds a module to a key", new String[] { "bind <module> <key>", "bind <module>", "bind clear", "bind list" });
        this.addAliases(new String[] { "b" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("clear")) {
                for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
                    module.setKeyBind(0);
                }
                ChatUtil.clientMessage("Key Binds cleared");
                if (bobr.getGate().presetManager.isAutoSave()) {
                    bobr.getGate().presetManager.saveActivePreset();
                }
                return;
            }
            if (args[1].equalsIgnoreCase("list")) {
                ChatUtil.clientMessage(TextFormatting.BOLD + "Key Bind List:");
                for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
                    if (module.getKeyBind() != 0) {
                        ChatUtil.clientMessage(module.getName() + " is bound to " + Keyboard.getKeyName(module.getKeyBind()));
                    }
                }
                return;
            }
            for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
                if (module.getId().equalsIgnoreCase(args[1].toUpperCase())) {
                    ChatUtil.clientMessage(module.getName() + " is bound to " + Keyboard.getKeyName(module.getKeyBind()));
                    return;
                }
            }
        }
        if (args.length != 3 || args[2].length() != 1) {
            this.syntaxError();
            return;
        }
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            if (module.getId().equalsIgnoreCase(args[1])) {
                module.setKeyBind(Keyboard.getKeyIndex(args[2].toUpperCase()));
                ChatUtil.clientMessage(module.getName() + " bound to " + args[2].toUpperCase());
                if (bobr.getGate().presetManager.isAutoSave()) {
                    bobr.getGate().presetManager.saveActivePreset();
                }
                return;
            }
        }
        this.syntaxError();
    }
}
