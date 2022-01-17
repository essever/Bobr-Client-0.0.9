//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.settings.impl;

import ru.terrar.bobr.settings.*;

public class EnumSetting extends Setting
{
    private final Enum<?>[] values;
    private Enum<?> currentValue;
    
    public EnumSetting(final String name, final String id, final Enum<?>[] values, final Enum<?> currentValue) {
        super(name, id);
        this.values = values;
        this.currentValue = currentValue;
    }
    
    public Enum<?>[] getValues() {
        return this.values;
    }
    
    public Enum<?> getCurrentValue() {
        return this.currentValue;
    }
    
    public void setCurrentValue(final Enum<?> currentValue) {
        this.currentValue = currentValue;
    }
    
    public boolean setValueFromName(final String name) {
        for (final Enum<?> value : this.values) {
            if (name.equalsIgnoreCase(value.toString())) {
                this.setCurrentValue(value);
                return true;
            }
        }
        return false;
    }
    
    public String getCurrentValueName() {
        return this.currentValue.toString();
    }
}
