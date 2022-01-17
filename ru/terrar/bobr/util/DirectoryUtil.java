//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import java.io.*;
import net.minecraft.client.*;

public class DirectoryUtil
{
    public static final File GATE_FOLDER;
    public static final File PRESET_FOLDER;
    
    public static boolean isJson(final File file) {
        final String name = file.getName();
        final int index = name.lastIndexOf(".");
        return name.substring(index).equals(".json");
    }
    
    public static String removeExtension(final String stringIn) {
        final StringBuilder newString = new StringBuilder();
        for (final char n : stringIn.toCharArray()) {
            if (n == '.') {
                break;
            }
            newString.append(n);
        }
        return newString.toString();
    }
    
    static {
        GATE_FOLDER = new File(Minecraft.getMinecraft().gameDir, "Config");
        PRESET_FOLDER = new File(DirectoryUtil.GATE_FOLDER, "Presets");
    }
}
