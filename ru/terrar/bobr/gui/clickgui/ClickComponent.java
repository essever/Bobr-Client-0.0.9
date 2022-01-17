//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.util.*;
import ru.terrar.bobr.*;

public class ClickComponent
{
    public float posX;
    public float posY;
    public int padding;
    public int width;
    public int height;
    protected List<ClickComponent> children;
    protected boolean expanded;
    protected FontRenderer fontRenderer;
    
    public ClickComponent(final float posX, final float posY) {
        this.padding = 3;
        this.width = 106;
        this.height = 14;
        this.children = new ArrayList<ClickComponent>();
        this.expanded = false;
        this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.posX = posX;
        this.posY = posY;
    }
    
    public void drawComponent(final int mouseX, final int mouseY, final float partialTicks) {
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
    }
    
    public boolean isExpanded() {
        return this.expanded;
    }
    
    protected boolean isMouseHover(final int mouseX, final int mouseY) {
        return mouseX > this.posX && mouseX < this.posX + this.width && mouseY > this.posY && mouseY < this.posY + this.height;
    }
    
    public float updatedByParent(final float offsetY) {
        this.posY = offsetY;
        return offsetY + this.height;
    }
    
    public float updateChildren() {
        float offsetY = this.posY + this.height;
        for (final ClickComponent component : this.children) {
            offsetY = component.updatedByParent(offsetY);
        }
        return offsetY;
    }
    
    protected void updateHierarchy() {
        bobr.getGate().guiManager.CLICK_GUI.onUpdate();
    }
}
