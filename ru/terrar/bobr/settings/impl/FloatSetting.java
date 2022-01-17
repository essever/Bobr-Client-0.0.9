//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.settings.impl;

import ru.terrar.bobr.settings.*;

public class FloatSetting extends Setting
{
    private float value;
    private final float min;
    private final float max;
    private final float step;
    
    public FloatSetting(final String name, final String id, final float value, final float min, final float max, final float step) {
        super(name, id);
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
    }
    
    public FloatSetting(final String name, final String id, final float value, final float min, final float max) {
        this(name, id, value, min, max, 0.1f);
    }
    
    public FloatSetting(final String name, final String id, final float value) {
        this(name, id, value, 0.0f, 10.0f, 0.1f);
    }
    
    public float getValue() {
        return this.value;
    }
    
    public boolean setValue(final float value) {
        if (value >= this.min && value <= this.max) {
            this.value = value;
            return true;
        }
        return false;
    }
    
    public boolean setValueWithStep(final float value) {
        return this.setValue(this.step * Math.round(value / this.step));
    }
    
    public float getMin() {
        return this.min;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public float getStep() {
        return this.step;
    }
}
