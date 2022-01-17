//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class RenderUtils
{
    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float partialTicks) {
        final Vec3d from = new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
        return entity.getPositionVector().subtract(from).scale((double)partialTicks).add(from);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB bb) {
        GL11.glBegin(1);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glEnd();
    }
    
    public static void drawSolidBox(final AxisAlignedBB bb) {
        GL11.glBegin(7);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }
    
    public static void drawESPBoxes(final List<Entity> entities, final int box, final float partialTicks) {
        GL11.glLineWidth(2.0f);
        for (final Entity entity : entities) {
            GL11.glPushMatrix();
            final Vec3d interpolated = getInterpolatedPos(entity, partialTicks);
            GL11.glTranslated(interpolated.x, interpolated.y, interpolated.z);
            GL11.glScaled(entity.width + 0.1, entity.height + 0.1, entity.width + 0.1);
            if (entity instanceof EntityItem) {
                GL11.glColor4f(0.5f, 0.5f, 1.0f, 0.5f);
            }
            else {
                final float intensity = Minecraft.getMinecraft().player.getDistance(entity) / 20.0f;
                GL11.glColor4f(2.0f - intensity, intensity, 0.0f, 0.5f);
            }
            GL11.glCallList(box);
            GL11.glPopMatrix();
        }
    }
    
    public static void drawESPTracers(final List<Entity> entities) {
        final Vec3d start = new Vec3d(Minecraft.getMinecraft().getRenderManager().viewerPosX, Minecraft.getMinecraft().getRenderManager().viewerPosY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().getRenderManager().viewerPosZ).add(Minecraft.getMinecraft().player.getLookVec());
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        for (final Entity entity : entities) {
            if (entity instanceof EntityPlayer) {
                final Vec3d target = entity.getEntityBoundingBox().getCenter();
                if (entity instanceof EntityItem) {
                    GL11.glColor4f(0.5f, 0.5f, 1.0f, 0.5f);
                }
                else {
                    final float intensity = Minecraft.getMinecraft().player.getDistance(entity) / 20.0f;
                    GL11.glColor4f(2.0f - intensity, intensity, 0.0f, 0.5f);
                }
                GL11.glVertex3d(start.x, start.y, start.z);
                GL11.glVertex3d(target.x, target.y, target.z);
            }
        }
        GL11.glEnd();
    }
}
