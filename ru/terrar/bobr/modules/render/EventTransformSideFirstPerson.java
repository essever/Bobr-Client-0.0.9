//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import net.minecraft.util.*;

public class EventTransformSideFirstPerson implements Event
{
    private final EnumHandSide enumHandSide;
    
    public EventTransformSideFirstPerson(final EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }
    
    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}
