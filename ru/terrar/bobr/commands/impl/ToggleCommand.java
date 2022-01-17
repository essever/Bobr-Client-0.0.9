//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import ru.terrar.bobr.modules.*;
import java.util.*;

public class ToggleCommand extends Command
{
    public ToggleCommand() {
        super("toggle", "Toggles a Module", new String[] { "toggle <module>" });
        this.addAliases(new String[] { "t" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length != 2) {
            this.syntaxError();
            return;
        }
        if (!this.isModule(args[1])) {
            ChatUtil.clientMessage("Module not found");
        }
    }
    
    public boolean isModule(final String name) {
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            if (module.getId().equalsIgnoreCase(name)) {
                module.toggle();
                ChatUtil.clientMessage(module.getName() + " module toggled");
                if (bobr.getGate().presetManager.isAutoSave()) {
                    bobr.getGate().presetManager.saveActivePreset();
                }
                return true;
            }
        }
        return false;
    }
}
