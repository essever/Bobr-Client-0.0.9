//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.gui.clickgui.components.settings.*;
import java.util.*;
import ru.terrar.bobr.util.*;

public class ModuleComponent extends ClickComponent
{
    private final Module module;
    private boolean expanded;
    
    public ModuleComponent(final Module module, final float posX, final float posY) {
        super(posX, posY);
        this.expanded = false;
        this.module = module;
        this.children.add(new KeybindComponent(module, posX, 0.0f));
        for (final Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                this.children.add(new BooleanComponent((BooleanSetting)setting, posX, 0.0f));
            }
            else if (setting instanceof EnumSetting) {
                this.children.add(new EnumComponent((EnumSetting)setting, posX, 0.0f));
            }
            else if (setting instanceof FloatSetting) {
                this.children.add(new SliderComponent((FloatSetting)setting, posX, 0.0f));
            }
            else {
                if (!(setting instanceof RGBSetting)) {
                    continue;
                }
                this.children.add(new RGBComponent((RGBSetting)setting, posX, 0.0f));
            }
        }
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        int textColor = -1;
        float r;
        float g;
        float b;
        if (this.module.isEnabled()) {
            r = 0.85f;
            g = 0.43f;
            b = 0.0f;
        }
        else if (this.isMouseHover(mouseX, mouseY)) {
            r = 0.42f;
            g = 0.21f;
            b = 0.0f;
        }
        else {
            r = 0.1f;
            g = 0.1f;
            b = 0.1f;
            textColor = -5592406;
        }
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, r, g, b, 1.0f);
        this.fontRenderer.drawString(this.module.getName(), this.posX + this.padding, this.posY + this.padding, textColor, true);
        if (this.expanded) {
            for (final ClickComponent setting : this.children) {
                setting.drawComponent(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            }
            else if (mouseButton == 1) {
                this.expanded = !this.expanded;
                this.updateHierarchy();
            }
        }
        if (this.expanded) {
            for (final ClickComponent setting : this.children) {
                setting.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        if (this.expanded) {
            for (final ClickComponent setting : this.children) {
                setting.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        for (final ClickComponent component : this.children) {
            component.keyTyped(typedChar, keyCode);
        }
    }
    
    public float updatedByParent(final float offsetY) {
        this.posY = offsetY;
        return this.expanded ? this.updateChildren() : (offsetY + this.height);
    }
}
