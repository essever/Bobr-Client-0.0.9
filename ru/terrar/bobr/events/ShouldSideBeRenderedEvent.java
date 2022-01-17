//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.events;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.state.*;

public class ShouldSideBeRenderedEvent extends Event
{
    private final IBlockState blockState;
    private boolean shouldBeRendered;
    
    public ShouldSideBeRenderedEvent(final IBlockState blockState, final boolean shouldBeRendered) {
        this.blockState = blockState;
        this.shouldBeRendered = shouldBeRendered;
    }
    
    public IBlockState getBlockState() {
        return this.blockState;
    }
    
    public boolean getShouldBeRendered() {
        return this.shouldBeRendered;
    }
    
    public void setShouldBeRendered(final boolean shouldBeRendered) {
        this.shouldBeRendered = shouldBeRendered;
    }
}
