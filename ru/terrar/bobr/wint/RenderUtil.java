//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.wint;

import net.minecraft.client.renderer.culling.*;
import net.minecraft.item.*;
import java.awt.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;

public class RenderUtil implements MinecraftHelper
{
    private static final Frustum frustrum;
    private static final double DOUBLE_PI = 6.283185307179586;
    
    public static double interpolate(final double current, final double old, final double scale) {
        return old + (current - old) * scale;
    }
    
    public static void renderItem(final ItemStack itemStack, final int x, final int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        RenderUtil.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        RenderUtil.mc.getRenderItem().renderItemOverlays(RenderUtil.mc.fontRenderer, itemStack, x, y);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }
    
    public static void drawRectWithEdge(final double x, final double y, final double width, final double height, final Color color, final Color color2) {
        drawRect(x, y, x + width, y + height, color.getRGB());
        final int c = color2.getRGB();
        drawRect(x - 1.0, y, x, y + height, c);
        drawRect(x + width, y, x + width + 1.0, y + height, c);
        drawRect(x - 1.0, y - 1.0, x + width + 1.0, y, c);
        drawRect(x - 1.0, y + height, x + width + 1.0, y + height + 1.0, c);
    }
    
    public static void drawRoundedRect(final double x, final double y, final double x1, final double y1, final int borderC, final int insideC) {
        drawRect(x + 0.5, y, x1 - 0.5, y + 0.5, insideC);
        drawRect(x + 0.5, y1 - 0.5, x1 - 0.5, y1, insideC);
        drawRect(x, y + 0.5, x1, y1 - 0.5, insideC);
    }
    
    public static void drawRoundedRect(final int xCoord, final int yCoord, final int xSize, final int ySize, final int colour) {
        final int width = xCoord + xSize;
        final int height = yCoord + ySize;
        drawRect(xCoord + 1, yCoord, width - 1, height, colour);
        drawRect(xCoord, yCoord + 1, width, height - 1, colour);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisalignedbb) {
        GL11.glBegin(7);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        GL11.glVertex3d(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        GL11.glEnd();
    }
    
    public static void drawFilledCircle(final int xx, final int yy, final float radius, final Color color) {
        final int sections = 6;
        final double dAngle = 6.283185307179586 / sections;
        GL11.glPushAttrib(8192);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(6);
        for (int i = 0; i < sections; ++i) {
            final float x = (float)(radius * Math.sin(i * dAngle));
            final float y = (float)(radius * Math.cos(i * dAngle));
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glVertex2f(xx + x, yy + y);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }
    
    public static final void drawSmoothRect(final float left, final float top, final float right, final float bottom, final int color) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawRect(left, top, right, bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect(left * 2.0f - 1.0f, top * 2.0f, left * 2.0f, bottom * 2.0f - 1.0f, color);
        drawRect(left * 2.0f, top * 2.0f - 1.0f, right * 2.0f, top * 2.0f, color);
        drawRect(right * 2.0f, top * 2.0f, right * 2.0f + 1.0f, bottom * 2.0f - 1.0f, color);
        drawRect(left * 2.0f, bottom * 2.0f - 1.0f, right * 2.0f, bottom * 2.0f, color);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void drawImage(final ResourceLocation image, final int x, final int y, final int width, final int height) {
        GL11.glEnable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float)width, (float)height);
        disableGL2D();
        GL11.glPopMatrix();
    }
    
    public static void drawCircle(float cx, double cy, float r, final int num_segments, final int c) {
        GL11.glPushMatrix();
        cx *= 2.0f;
        cy *= 2.0;
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        final float theta = (float)(6.2831852 / num_segments);
        final float p = (float)Math.cos(theta);
        final float s = (float)Math.sin(theta);
        float x;
        r = (x = r * 2.0f);
        float y = 0.0f;
        GL11.glEnable(2848);
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(2);
        for (int ii = 0; ii < num_segments; ++ii) {
            GL11.glVertex2f(x + cx, (float)(y + cy));
            final float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void relativeRect(float left, float top, float right, float bottom, final int color) {
        if (left < right) {
            final float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final float j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f4, f5, f6, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)left, (double)bottom, 0.0).endVertex();
        bufferBuilder.pos((double)right, (double)bottom, 0.0).endVertex();
        bufferBuilder.pos((double)right, (double)top, 0.0).endVertex();
        bufferBuilder.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBorderedRect(final double left, final double top, final double right, final double bottom, final double borderWidth, final int insideColor, final int borderColor, final boolean borderIncludedInBounds) {
        drawRect(left - (borderIncludedInBounds ? 0.0 : borderWidth), top - (borderIncludedInBounds ? 0.0 : borderWidth), right + (borderIncludedInBounds ? 0.0 : borderWidth), bottom + (borderIncludedInBounds ? 0.0 : borderWidth), borderColor);
        drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0), top + (borderIncludedInBounds ? borderWidth : 0.0), right - (borderIncludedInBounds ? borderWidth : 0.0), bottom - (borderIncludedInBounds ? borderWidth : 0.0), insideColor);
    }
    
    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f4, f5, f6, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawRectOpacity(double left, double top, double right, double bottom, final float opacity) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(0.1f, 0.1f, 0.1f, opacity);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawNewRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f4, f5, f6, f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, top, 0.0).endVertex();
        vertexbuffer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static void drawLinesAroundPlayer(final Entity entity, final double radius, final float partialTicks, final int points, final float width, final int color) {
        GL11.glPushMatrix();
        enableGL2D3();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDisable(2929);
        GL11.glLineWidth(width);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glBegin(3);
        final RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - renderManager.viewerPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - renderManager.viewerPosY;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - renderManager.viewerPosZ;
        color228(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d(x + radius * Math.cos(i * 6.283185307179586 / points), y, z + radius * Math.sin(i * 6.283185307179586 / points));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        disableGL2D3();
        GL11.glPopMatrix();
    }
    
    public static void enableGL2D3() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void disableGL2D3() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void color228(final int color) {
        GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)(color >> 24 & 0xFF));
    }
    
    private static boolean isInViewFrustrum(final AxisAlignedBB bb) {
        final Entity current = RenderUtil.mc.getRenderViewEntity();
        RenderUtil.frustrum.setPosition(current.posX, current.posY, current.posZ);
        return RenderUtil.frustrum.isBoundingBoxInFrustum(bb);
    }
    
    public static void glColor(final int hex) {
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawColorBox(final AxisAlignedBB axisalignedbb, final float red, final float green, final float blue, final float alpha) {
        final Tessellator ts = Tessellator.getInstance();
        final BufferBuilder vb = ts.getBuffer();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
    }
    
    public static void rectangleBordered(final double x, final double y, final double x1, final double y1, final double width, final int internalColor, final int borderColor) {
        drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
        drawRect(x + width, y, x1 - width, y + width, borderColor);
        drawRect(x, y, x + width, y1, borderColor);
        drawRect(x1 - width, y, x1, y1, borderColor);
        drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
    }
    
    static {
        frustrum = new Frustum();
    }
}
