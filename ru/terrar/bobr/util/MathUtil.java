//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;

public class MathUtil
{
    public static Vec3d interpolateEntity(final Entity entity) {
        final double partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks);
    }
    
    public static double[] getDirection(final float yaw) {
        return new double[] { -Math.sin(Math.toRadians(yaw)), Math.cos(Math.toRadians(yaw)) };
    }
}
