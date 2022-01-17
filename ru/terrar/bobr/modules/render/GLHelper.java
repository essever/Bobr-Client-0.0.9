//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import org.lwjgl.opengl.*;
import java.awt.*;

public class GLHelper
{
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawBorderedRect(final float f, final float f2, final float f3, final float f4, final float f5, final int n, final int n2) {
        enableGL2D();
        glColor(n);
        drawRect(f + f5, f2 + f5, f3 - f5, f4 - f5);
        glColor(n2);
        drawRect(f + f5, f2, f3 - f5, f2 + f5);
        drawRect(f, f2, f + f5, f4);
        drawRect(f3 - f5, f2, f3, f4);
        drawRect(f + f5, f4 - f5, f3 - f5, f4);
        disableGL2D();
    }
    
    public static void drawBorderedRect(float f, float f2, float f3, float f4, final int n, final int n2) {
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(f *= 2.0f, f2 *= 2.0f, f4 *= 2.0f, n2);
        drawVLine((f3 *= 2.0f) - 1.0f, f2, f4, n2);
        drawHLine(f, f3 - 1.0f, f2, n2);
        drawHLine(f, f3 - 2.0f, f4 - 1.0f, n2);
        drawRect(f + 1.0f, f2 + 1.0f, f3 - 1.0f, f4 - 1.0f, n);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }
    
    public static void drawGradientRect(final float f, final float f2, final float f3, final float f4, final int n, final int n2) {
        enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        glColor(n);
        GL11.glVertex2f(f, f4);
        GL11.glVertex2f(f3, f4);
        glColor(n2);
        GL11.glVertex2f(f3, f2);
        GL11.glVertex2f(f, f2);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        disableGL2D();
    }
    
    public static void drawHLine(float f, float f2, final float f3, final int n) {
        if (f2 < f) {
            final float f4 = f;
            f = f2;
            f2 = f4;
        }
        drawRect(f, f3, f2 + 1.0f, f3 + 1.0f, n);
    }
    
    public static void drawHLine(float f, float f2, final float f3, final int n, final int n2) {
        if (f2 < f) {
            final float f4 = f;
            f = f2;
            f2 = f4;
        }
        drawGradientRect(f, f3, f2 + 1.0f, f3 + 1.0f, n, n2);
    }
    
    public static void drawRect(final float f, final float f2, final float f3, final float f4, final int n) {
        enableGL2D();
        glColor(n);
        drawRect(f, f2, f3, f4);
        disableGL2D();
    }
    
    public static void drawRect(final Rectangle rectangle, final int n) {
        drawRect((float)rectangle.x, (float)rectangle.y, (float)(rectangle.x + rectangle.width), (float)(rectangle.y + rectangle.height), n);
    }
    
    public static void drawRect(final float f, final float f2, final float f3, final float f4) {
        GL11.glBegin(7);
        GL11.glVertex2f(f, f4);
        GL11.glVertex2f(f3, f4);
        GL11.glVertex2f(f3, f2);
        GL11.glVertex2f(f, f2);
        GL11.glEnd();
    }
    
    public static void drawRect(final float f, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8) {
        enableGL2D();
        GL11.glColor4f(f5, f6, f7, f8);
        drawRect(f, f2, f3, f4);
        disableGL2D();
    }
    
    public static void drawVLine(final float f, float f2, float f3, final int n) {
        if (f3 < f2) {
            final float f4 = f2;
            f2 = f3;
            f3 = f4;
        }
        drawRect(f, f2 + 1.0f, f + 1.0f, f3, n);
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
    
    public static void glColor(final float f, final int n, final int n2, final int n3) {
        final float f2 = 0.003921569f * n;
        final float f3 = 0.003921569f * n2;
        final float f4 = 0.003921569f * n3;
        GL11.glColor4f(f2, f3, f4, f);
    }
    
    public static void glColor(final int n) {
        final float f = (n >> 24 & 0xFF) / 255.0f;
        final float f2 = (n >> 16 & 0xFF) / 255.0f;
        final float f3 = (n >> 8 & 0xFF) / 255.0f;
        final float f4 = (n & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
}
