//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.events;

import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class PlayerMoveEvent extends Event
{
    public double x;
    public double y;
    public double z;
    
    public PlayerMoveEvent(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
