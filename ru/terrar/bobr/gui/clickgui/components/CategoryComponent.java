//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui.components;

import ru.terrar.bobr.gui.clickgui.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.*;
import java.util.*;
import ru.terrar.bobr.util.*;

public class CategoryComponent extends ClickComponent
{
    private final String displayName;
    private final float fontX;
    
    public CategoryComponent(final Module.ModuleCategory category, final float posX, final float posY) {
        super(posX, posY);
        this.padding = 5;
        this.height = 18;
        this.expanded = true;
        this.displayName = category.getName();
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            if (module.getModuleCategory() == category) {
                this.children.add(new ModuleComponent(module, posX, 0.0f));
            }
        }
        this.fontX = (this.width - this.fontRenderer.getStringWidth(this.displayName)) / 2.0f;
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.draw2DRect(this.posX, this.posY, this.width, this.height, 0.18f, 0.38f, 0.9f, 1.0f);
        this.fontRenderer.drawString(this.displayName, this.posX + this.fontX, this.posY + this.padding, -1, true);
        if (this.expanded) {
            for (final ClickComponent module : this.children) {
                module.drawComponent(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseHover(mouseX, mouseY)) {
            this.expanded = !this.expanded;
            this.updateHierarchy();
        }
        if (this.expanded) {
            for (final ClickComponent module : this.children) {
                module.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        if (this.expanded) {
            for (final ClickComponent module : this.children) {
                module.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        for (final ClickComponent module : this.children) {
            module.keyTyped(typedChar, keyCode);
        }
    }
}
