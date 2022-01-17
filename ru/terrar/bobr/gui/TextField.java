//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import ru.terrar.bobr.util.*;
import net.minecraft.util.*;

public class TextField
{
    private final float posX;
    private final float posY;
    private final int width;
    private String text;
    private boolean focused;
    private int tickCounter;
    private final FontRenderer fr;
    
    public TextField(final float posX, final float posY, final int width, final String text) {
        this.tickCounter = 0;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.text = text;
        this.focused = false;
    }
    
    public TextField(final float posX, final float posY, final int width) {
        this(posX, posY, width, "");
    }
    
    public void drawField() {
        final String trimmedMessage = this.fr.trimStringToWidth(this.text, this.width, true);
        final int stringEnd = this.fr.drawString(trimmedMessage, this.posX, this.posY, -1, true);
        if (this.focused && this.tickCounter <= 15) {
            RenderUtil.draw2DRect(stringEnd, this.posY, 1.0, 8.0, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        this.updateTickCounter();
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        System.out.println(typedChar + " " + keyCode);
        if (keyCode == 14) {
            if (!this.text.isEmpty()) {
                this.text = this.text.substring(0, this.text.length() - 1);
            }
        }
        else {
            this.writeChar(typedChar);
        }
    }
    
    public void writeChar(final char c) {
        if (ChatAllowedCharacters.isAllowedCharacter(c)) {
            this.text = this.text.concat(String.valueOf(c));
        }
    }
    
    private void updateTickCounter() {
        if (this.tickCounter == 30) {
            this.tickCounter = 0;
        }
        else {
            ++this.tickCounter;
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public boolean isFocused() {
        return this.focused;
    }
    
    public void setFocused(final boolean focused) {
        this.focused = focused;
    }
}
