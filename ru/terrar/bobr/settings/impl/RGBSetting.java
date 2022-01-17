//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.settings.impl;

import ru.terrar.bobr.settings.*;

public class RGBSetting extends Setting
{
    private int red;
    private int green;
    private int blue;
    
    public RGBSetting(final String name, final String id, final int red, final int green, final int blue) {
        super(name, id);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public void setRed(final int red) {
        this.red = red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
}
