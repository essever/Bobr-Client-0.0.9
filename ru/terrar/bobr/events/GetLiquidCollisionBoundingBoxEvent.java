//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.events;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class GetLiquidCollisionBoundingBoxEvent extends Event
{
    private AxisAlignedBB collisionBoundingBox;
    
    public GetLiquidCollisionBoundingBoxEvent() {
        this.collisionBoundingBox = Block.NULL_AABB;
    }
    
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.collisionBoundingBox;
    }
    
    public void setCollisionBoundingBox(final AxisAlignedBB collisionBoundingBox) {
        this.collisionBoundingBox = collisionBoundingBox;
    }
}
