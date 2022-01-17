//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components.settings;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.util.*;
import java.util.*;

public class RGBComponent extends ClickComponent
{
    private final RGBSetting setting;
    private final List<ColorSlider> sliders;
    
    public RGBComponent(final RGBSetting setting, final float posX, final float posY) {
        super(posX, posY);
        this.sliders = new ArrayList<ColorSlider>();
        this.setting = setting;
        this.sliders.add(new ColorSlider(this.posX, 0.0f));
        this.sliders.add(new ColorSlider(this.posX, 0.0f));
        this.sliders.add(new ColorSlider(this.posX, 0.0f));
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
        this.fontRenderer.drawString(this.setting.getName(), this.posX + this.padding, this.posY + this.padding, -1, true);
        if (this.expanded) {
            RenderUtil.draw2DTriangleRight(this.posX + this.width - 2 * this.padding - 6.0f, this.posY + this.padding, 4.0, this.height - 2 * this.padding, 1.0f, 1.0f, 1.0f, 1.0f);
            this.setting.setRed(this.sliders.get(0).renderSlider("Red", this.setting.getRed(), this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), mouseX));
            this.setting.setGreen(this.sliders.get(1).renderSlider("Green", this.setting.getGreen(), this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), mouseX));
            this.setting.setBlue(this.sliders.get(2).renderSlider("Blue", this.setting.getBlue(), this.setting.getRed(), this.setting.getGreen(), this.setting.getBlue(), mouseX));
        }
        else {
            RenderUtil.draw2DTriangleDown(this.posX + this.width - 2 * this.padding - 8.0f, this.posY + this.padding + 2.0f, 8.0, this.height - 2 * this.padding - 4, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            this.expanded = !this.expanded;
            this.updateHierarchy();
        }
        if (this.expanded) {
            for (final ClickComponent setting : this.sliders) {
                setting.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        if (this.expanded) {
            for (final ClickComponent setting : this.sliders) {
                setting.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
    
    public float updatedByParent(final float offsetY) {
        this.posY = offsetY;
        return this.expanded ? this.updateChildren() : (offsetY + this.height);
    }
    
    public float updateChildren() {
        float offsetY = this.posY + this.height;
        for (final ClickComponent component : this.sliders) {
            offsetY = component.updatedByParent(offsetY);
        }
        return offsetY;
    }
    
    public static class ColorSlider extends ClickComponent
    {
        private boolean dragging;
        
        public ColorSlider(final float posX, final float posY) {
            super(posX, posY);
            this.dragging = false;
            this.height += 4;
        }
        
        public int renderSlider(final String color, final int slider, final int red, final int green, final int blue, final int mouseX) {
            RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
            this.fontRenderer.drawString(color, this.posX + 2 * this.padding, this.posY + this.padding, red << 16 | green << 8 | blue, true);
            this.fontRenderer.drawString(Integer.valueOf(slider).toString(), this.posX + this.width - this.fontRenderer.getStringWidth(Integer.valueOf(slider).toString()) - 2 * this.padding, this.posY + this.padding, -1, true);
            RenderUtil.draw2DRect(this.posX + 2 * this.padding, this.posY + this.height - this.padding - 3.0f, this.width - 4 * this.padding, 2.0, 0.0f, 0.0f, 0.0f, 1.0f);
            RenderUtil.draw2DRect(this.posX + 2 * this.padding, this.posY + this.height - this.padding - 3.0f, (this.width - 4 * this.padding) * slider / 255.0f, 2.0, 0.85f, 0.43f, 0.0f, 1.0f);
            if (!this.dragging) {
                return slider;
            }
            final double wMin = this.posX + 2 * this.padding;
            final double wMax = this.posX + this.width - 2 * this.padding;
            if (mouseX > wMax) {
                return 255;
            }
            if (mouseX < wMin) {
                return 0;
            }
            return Math.round(255.0f * (float)(mouseX - wMin) / (this.width - 4 * this.padding));
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
}
