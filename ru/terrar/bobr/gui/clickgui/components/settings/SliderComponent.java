//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components.settings;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.util.*;

public class SliderComponent extends ClickComponent
{
    private final FloatSetting setting;
    private boolean dragging;
    
    public SliderComponent(final FloatSetting setting, final float posX, final float posY) {
        super(posX, posY);
        this.dragging = false;
        this.setting = setting;
        this.height += 4;
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
        this.fontRenderer.drawString(this.setting.getName(), this.posX + this.padding, this.posY + this.padding, -1, true);
        final String s = Float.valueOf(Math.round(this.setting.getValue() * 10.0f) / 10.0f).toString();
        this.fontRenderer.drawString(s, this.posX + this.width - this.padding - this.fontRenderer.getStringWidth(s), this.posY + this.padding, -1, true);
        RenderUtil.draw2DRect(this.posX + this.padding, this.posY + this.height - this.padding - 3.0f, this.width - 2 * this.padding, 2.0, 0.1f, 0.1f, 0.1f, 1.0f);
        RenderUtil.draw2DRect(this.posX + this.padding, this.posY + this.height - this.padding - 3.0f, (this.width - 2 * this.padding) * this.setting.getValue() / this.setting.getMax(), 2.0, 0.85f, 0.43f, 0.0f, 1.0f);
        if (this.dragging) {
            final double wMin = this.posX + this.padding;
            final double wMax = this.posX + this.width - this.padding;
            if (mouseX > wMax) {
                this.setting.setValue(this.setting.getMax());
            }
            else if (mouseX < wMin) {
                this.setting.setValue(this.setting.getMin());
            }
            else {
                final float f1 = (mouseX - this.posX - this.padding) / (this.width - 2 * this.padding) * (this.setting.getMax() - this.setting.getMin()) + this.setting.getMin();
                this.setting.setValueWithStep(f1);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            this.dragging = true;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        if (this.dragging) {
            this.dragging = false;
        }
    }
}
