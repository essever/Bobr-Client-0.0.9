//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.events;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.state.*;

@Cancelable
public class RenderModelEvent extends Event
{
    private final IBlockState state;
    
    public RenderModelEvent(final IBlockState state) {
        this.state = state;
    }
    
    public IBlockState getState() {
        return this.state;
    }
}
