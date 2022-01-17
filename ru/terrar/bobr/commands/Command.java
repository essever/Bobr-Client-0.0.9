//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands;

import ru.terrar.bobr.util.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.*;
import java.util.*;

public abstract class Command
{
    private final String name;
    private final String desc;
    private final String[] syntax;
    private final List<String> aliases;
    
    public Command(final String name, final String desc, final String... syntax) {
        this.aliases = new ArrayList<String>();
        this.name = name;
        this.desc = desc;
        this.syntax = syntax;
    }
    
    public void syntaxError() {
        ChatUtil.clientMessage("Incorrect Syntax: ");
        for (final String syntax : this.getSyntax()) {
            ChatUtil.clientMessage(TextFormatting.YELLOW + bobr.getGate().commandManager.getPrefix() + syntax);
        }
    }
    
    public void addAliases(final String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }
    
    public abstract void onCommand(final String[] p0);
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String[] getSyntax() {
        return this.syntax;
    }
    
    public List<String> getAliases() {
        return this.aliases;
    }
}
