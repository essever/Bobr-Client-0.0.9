//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.math.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class RenderUtil
{
    public static void renderEntityBoundingBox(final Entity entity, final float red, final float green, final float blue, final float alpha) {
        final RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        final Vec3d entityPos = MathUtil.interpolateEntity(entity);
        final AxisAlignedBB bb = new AxisAlignedBB(entityPos.x - entity.width / 2.0f, entityPos.y, entityPos.z - entity.width / 2.0f, entityPos.x + entity.width / 2.0f, entityPos.y + entity.height, entityPos.z + entity.width / 2.0f).offset(-rm.viewerPosX, -rm.viewerPosY, -rm.viewerPosZ);
        RenderGlobal.drawSelectionBoundingBox(bb, red, green, blue, alpha);
    }
    
    public static void renderEntityBoundingBox(final Entity entity, final RGBSetting color, final float alpha) {
        renderEntityBoundingBox(entity, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, alpha);
    }
    
    public static void renderEntityFilledBoundingBox(final Entity entity, final float red, final float green, final float blue, final float alpha) {
        final RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        final Vec3d entityPos = MathUtil.interpolateEntity(entity);
        final AxisAlignedBB bb = new AxisAlignedBB(entityPos.x - entity.width / 2.0f, entityPos.y, entityPos.z - entity.width / 2.0f, entityPos.x + entity.width / 2.0f, entityPos.y + entity.height, entityPos.z + entity.width / 2.0f).offset(-rm.viewerPosX, -rm.viewerPosY, -rm.viewerPosZ);
        RenderGlobal.renderFilledBox(bb, red, green, blue, alpha);
    }
    
    public static void renderEntityFilledBoundingBox(final Entity entity, final RGBSetting color, final float alpha) {
        renderEntityFilledBoundingBox(entity, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, alpha);
    }
    
    public static void draw2DTriangleDown(final double x, final double y, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.color(red, green, blue, alpha);
        buffer.begin(4, DefaultVertexFormats.POSITION);
        buffer.pos(x, y, 0.0).endVertex();
        buffer.pos(x + width / 2.0, y + height, 0.0).endVertex();
        buffer.pos(x + width, y, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void draw2DTriangleRight(final double x, final double y, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.color(red, green, blue, alpha);
        buffer.begin(4, DefaultVertexFormats.POSITION);
        buffer.pos(x, y, 0.0).endVertex();
        buffer.pos(x, y + height, 0.0).endVertex();
        buffer.pos(x + width, y + height / 2.0, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void draw2DRect(final double x, final double y, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.color(red, green, blue, alpha);
        buffer.begin(5, DefaultVertexFormats.POSITION);
        buffer.pos(x, y, 0.0).endVertex();
        buffer.pos(x, y + height, 0.0).endVertex();
        buffer.pos(x + width, y, 0.0).endVertex();
        buffer.pos(x + width, y + height, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void draw2DRectLines(final double x, final double y, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.color(red, green, blue, alpha);
        buffer.begin(2, DefaultVertexFormats.POSITION);
        buffer.pos(x, y, 0.0).endVertex();
        buffer.pos(x, y + height, 0.0).endVertex();
        buffer.pos(x + width, y + height, 0.0).endVertex();
        buffer.pos(x + width, y, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
