//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import ru.terrar.bobr.commands.*;
import java.util.*;
import ru.terrar.bobr.commands.impl.*;

public class CommandManager
{
    private String prefix;
    public final List<Command> COMMAND_LIST;
    
    public CommandManager() {
        this.prefix = ".";
        this.COMMAND_LIST = new ArrayList<Command>();
    }
    
    public void close() {
        this.COMMAND_LIST.clear();
    }
    
    public void init() {
        this.COMMAND_LIST.add((Command)new BindCommand());
        this.COMMAND_LIST.add((Command)new ClipCommand());
        this.COMMAND_LIST.add((Command)new HelpCommand());
        this.COMMAND_LIST.add((Command)new ModuleListCommand());
        this.COMMAND_LIST.add((Command)new PrefixCommand());
        this.COMMAND_LIST.add((Command)new PresetCommand());
        this.COMMAND_LIST.add((Command)new SayCommand());
        this.COMMAND_LIST.add((Command)new SetCommand());
        this.COMMAND_LIST.add((Command)new ToggleCommand());
        this.COMMAND_LIST.add((Command)new FriendCommand());
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}
