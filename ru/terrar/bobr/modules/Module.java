//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules;

import ru.terrar.bobr.settings.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;
import java.util.*;

public class Module
{
    private final String name;
    private final String id;
    private boolean enabled;
    private int keyBind;
    private final ModuleCategory moduleCategory;
    private final List<Setting> settings;
    public final BooleanSetting drawOnHud;
    
    public Module(final String name, final String id, final ModuleCategory category) {
        this(name, id, 0, category);
    }
    
    public Module(final String name, final String id, final int keyBind, final ModuleCategory category) {
        this.enabled = false;
        this.settings = new ArrayList<Setting>();
        this.drawOnHud = new BooleanSetting("Draw on Hud", "drawonhud", true);
        this.name = name;
        this.id = id;
        this.keyBind = keyBind;
        this.moduleCategory = category;
        this.addSettings(this.drawOnHud);
    }
    
    public void onEnable() {
        ChatUtil.clientMessage(this.name + TextFormatting.GREEN + " Enable");
    }
    
    public void onDisable() {
        ChatUtil.clientMessage(this.name + TextFormatting.RED + " Disable");
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean set) {
        if (set) {
            this.enabled = true;
            this.onEnable();
        }
        else {
            this.enabled = false;
            this.onDisable();
        }
    }
    
    public void toggle() {
        this.setEnabled(!this.enabled);
    }
    
    public void addSettings(final Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }
    
    public int getKeyBind() {
        return this.keyBind;
    }
    
    public void setKeyBind(final int key) {
        this.keyBind = key;
    }
    
    public ModuleCategory getModuleCategory() {
        return this.moduleCategory;
    }
    
    public List<Setting> getSettings() {
        return this.settings;
    }
    
    public enum ModuleCategory
    {
        COMBAT("Combat", 15024963), 
        HUD("HUD", 14431204), 
        MOVEMENT("Movement", 6527487), 
        PLAYER("Player", 16744497), 
        RENDER("Render", 16768809);
        
        private final String name;
        private final int color;
        
        private ModuleCategory(final String name, final int color) {
            this.name = name;
            this.color = color;
        }
        
        public String getName() {
            return this.name;
        }
        
        public int getColor() {
            return this.color;
        }
    }
}
