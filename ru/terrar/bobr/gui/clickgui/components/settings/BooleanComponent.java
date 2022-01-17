//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components.settings;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.util.*;

public class BooleanComponent extends ClickComponent
{
    private final BooleanSetting setting;
    
    public BooleanComponent(final BooleanSetting setting, final float posX, final float posY) {
        super(posX, posY);
        this.setting = setting;
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
        this.fontRenderer.drawString(this.setting.getName(), this.posX + this.padding, this.posY + this.padding, -1, true);
        if (this.setting.getValue()) {
            RenderUtil.draw2DRect(this.posX + this.width - this.padding - 8.0f, this.posY + this.padding, 8.0, 8.0, 0.85f, 0.43f, 0.0f, 1.0f);
        }
        RenderUtil.draw2DRectLines(this.posX + this.width - this.padding - 8.0f, this.posY + this.padding, 8.0, 8.0, 0.8f, 0.8f, 0.8f, 0.8f);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            this.setting.toggle();
        }
    }
}
