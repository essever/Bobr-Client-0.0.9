//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components.settings;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.util.*;
import java.util.*;

public class EnumComponent extends ClickComponent
{
    private final EnumSetting setting;
    private final List<Option> options;
    
    public EnumComponent(final EnumSetting setting, final float posX, final float posY) {
        super(posX, posY);
        this.options = new ArrayList<Option>();
        this.setting = setting;
        for (final Enum<?> option : setting.getValues()) {
            this.options.add(new Option(option, posX, 0.0f));
        }
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
        this.fontRenderer.drawString(this.setting.getName(), this.posX + this.padding, this.posY + this.padding, -1, true);
        if (this.expanded) {
            for (final Option option : this.options) {
                option.renderCheck(this.setting.getCurrentValue() == option.getOption());
            }
            RenderUtil.draw2DTriangleRight(this.posX + this.width - 2 * this.padding - 6.0f, this.posY + this.padding, 4.0, this.height - 2 * this.padding, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        else {
            RenderUtil.draw2DTriangleDown(this.posX + this.width - 2 * this.padding - 8.0f, this.posY + this.padding + 2.0f, 8.0, this.height - 2 * this.padding - 4, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public float updatedByParent(final float offsetY) {
        this.posY = offsetY;
        return this.expanded ? this.updateChildren() : (offsetY + this.height);
    }
    
    public float updateChildren() {
        float offsetY = this.posY + this.height;
        for (final ClickComponent component : this.options) {
            offsetY = component.updatedByParent(offsetY);
        }
        return offsetY;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            this.expanded = !this.expanded;
            this.updateHierarchy();
        }
        for (final Option option : this.options) {
            if (option.checkBox(mouseX, mouseY)) {
                this.setting.setCurrentValue(option.getOption());
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        for (final Option option : this.options) {
            option.mouseReleased(mouseX, mouseY, state);
        }
    }
    
    public static class Option extends ClickComponent
    {
        private final Enum<?> option;
        
        public Option(final Enum<?> option, final float posX, final float posY) {
            super(posX, posY);
            this.option = option;
        }
        
        public void renderCheck(final boolean isCurrent) {
            RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.15f, 0.15f, 0.15f, 1.0f);
            if (isCurrent) {
                RenderUtil.draw2DRect(this.posX + 2 * this.padding, this.posY + this.padding, 8.0, 8.0, 0.85f, 0.43f, 0.0f, 1.0f);
            }
            RenderUtil.draw2DRectLines(this.posX + 2 * this.padding, this.posY + this.padding, 8.0, 8.0, 0.8f, 0.8f, 0.8f, 0.8f);
            this.fontRenderer.drawString(this.option.toString(), this.posX + 3 * this.padding + 9.0f, this.posY + this.padding, -1, true);
        }
        
        public boolean checkBox(final int mouseX, final int mouseY) {
            return this.isMouseHover(mouseX, mouseY);
        }
        
        public Enum<?> getOption() {
            return this.option;
        }
    }
}
