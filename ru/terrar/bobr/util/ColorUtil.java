//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

public class ColorUtil
{
    public static int RGBtoHex(final int red, final int green, final int blue) {
        return red << 16 | green << 8 | blue;
    }
    
    public static int[] getRainbow(final int cycle, final float offset) {
        int r = 0;
        int g = 0;
        int b = 0;
        final long timeInCycle = (System.currentTimeMillis() - Math.round(offset * 1000.0f)) % (cycle * 1000);
        final float portionOfCycle = 6.0f * timeInCycle / (cycle * 1000);
        final float timeInPortion = portionOfCycle - (float)Math.floor(portionOfCycle);
        final int timeInPortionRGB = Math.round(255.0f * timeInPortion);
        if (portionOfCycle < 1.0f) {
            r = 255;
            g = timeInPortionRGB;
        }
        else if (portionOfCycle < 2.0f) {
            r = 255 - timeInPortionRGB;
            g = 255;
        }
        else if (portionOfCycle < 3.0f) {
            g = 255;
            b = timeInPortionRGB;
        }
        else if (portionOfCycle < 4.0f) {
            g = 255 - timeInPortionRGB;
            b = 255;
        }
        else if (portionOfCycle < 5.0f) {
            r = timeInPortionRGB;
            b = 255;
        }
        else if (portionOfCycle < 6.0f) {
            r = 255;
            b = 255 - timeInPortionRGB;
        }
        return new int[] { r, g, b };
    }
}
