//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.gui.clickgui;

import net.minecraft.client.gui.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.gui.clickgui.components.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import ru.terrar.bobr.modules.hud.*;
import java.io.*;
import ru.terrar.bobr.*;

public class ClickGui extends GuiScreen
{
    private final List<ClickComponent> components;
    
    public ClickGui() {
        this.components = new ArrayList<ClickComponent>();
    }
    
    public void init() {
        this.components.add(new CategoryComponent(Module.ModuleCategory.COMBAT, 20.0f, 20.0f));
        this.components.add(new CategoryComponent(Module.ModuleCategory.HUD, 146.0f, 20.0f));
        this.components.add(new CategoryComponent(Module.ModuleCategory.MOVEMENT, 272.0f, 20.0f));
        this.components.add(new CategoryComponent(Module.ModuleCategory.PLAYER, 398.0f, 20.0f));
        this.components.add(new CategoryComponent(Module.ModuleCategory.RENDER, 524.0f, 20.0f));
        this.components.add(new PresetComponent(650.0f, 20.0f));
        this.onUpdate();
    }
    
    public void onUpdate() {
        for (final ClickComponent component : this.components) {
            if (component.isExpanded()) {
                component.updateChildren();
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.pushMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.glLineWidth(1.0f);
        for (final ClickComponent category : this.components) {
            category.drawComponent(mouseX, mouseY, partialTicks);
        }
        GlStateManager.disableTexture2D();
        GlStateManager.popMatrix();
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == ClickGuiModule.INSTANCE.getKeyBind()) {
            this.mc.displayGuiScreen((GuiScreen)null);
            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
        for (final ClickComponent component : this.components) {
            component.keyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        for (final ClickComponent component : this.components) {
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        for (final ClickComponent component : this.components) {
            component.mouseReleased(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    public void onGuiClosed() {
        ClickGuiModule.INSTANCE.setEnabled(false);
        if (bobr.getGate().presetManager.isAutoSave()) {
            bobr.getGate().presetManager.saveActivePreset();
        }
        super.onGuiClosed();
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}
