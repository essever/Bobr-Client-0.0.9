//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.settings;

public abstract class Setting
{
    private final String name;
    private final String id;
    private String parent;
    
    public Setting(final String name, final String id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setParent(final String parent) {
        this.parent = parent;
    }
    
    public String getParent() {
        return this.parent;
    }
}
