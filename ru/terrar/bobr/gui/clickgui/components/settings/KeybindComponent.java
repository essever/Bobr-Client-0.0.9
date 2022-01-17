//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components.settings;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.util.*;
import org.lwjgl.input.*;

public class KeybindComponent extends ClickComponent
{
    private final Module module;
    private boolean listening;
    
    public KeybindComponent(final Module module, final float posX, final float posY) {
        super(posX, posY);
        this.listening = false;
        this.module = module;
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
        this.fontRenderer.drawString("Keybind", this.posX + this.padding, this.posY + this.padding, -1, true);
        if (this.listening) {
            this.fontRenderer.drawString("...", this.posX + this.width - this.padding - this.fontRenderer.getStringWidth("..."), this.posY + this.padding, -1, true);
        }
        else {
            final String key = Keyboard.getKeyName(this.module.getKeyBind());
            this.fontRenderer.drawString(key, this.posX + this.width - this.padding - this.fontRenderer.getStringWidth(key), this.posY + this.padding, -1, true);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.listening) {
            if (this.isMouseHover(mouseX, mouseY) && mouseButton == 1) {
                this.module.setKeyBind(0);
            }
            this.listening = false;
        }
        else if (this.isMouseHover(mouseX, mouseY)) {
            this.listening = true;
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.listening) {
            this.module.setKeyBind(keyCode);
            this.listening = false;
        }
    }
}
