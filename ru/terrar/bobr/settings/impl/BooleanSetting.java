//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.settings.impl;

import ru.terrar.bobr.settings.*;

public class BooleanSetting extends Setting
{
    private boolean value;
    
    public BooleanSetting(final String name, final String id, final boolean value) {
        super(name, id);
        this.value = value;
    }
    
    public boolean getValue() {
        return this.value;
    }
    
    public void setValue(final boolean value) {
        this.value = value;
    }
    
    public void toggle() {
        this.setValue(!this.value);
    }
}
