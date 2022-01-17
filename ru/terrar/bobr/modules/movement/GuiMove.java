//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.gui.clickgui.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class GuiMove extends Module
{
    public static final GuiMove INSTANCE;
    public BooleanSetting sneak;
    
    public GuiMove() {
        super("GUI Move", "guimove", Module.ModuleCategory.MOVEMENT);
        this.sneak = new BooleanSetting("Sneak", "sneak", false);
        this.addSettings(new Setting[] { this.sneak });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onKeyUpdate(final InputUpdateEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.world != null && mc.player != null && (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiOptions || mc.currentScreen instanceof ClickGui)) {
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
                final MovementInput movementInput = mc.player.movementInput;
                ++movementInput.moveForward;
                mc.player.movementInput.forwardKeyDown = true;
            }
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
                final MovementInput movementInput2 = mc.player.movementInput;
                --movementInput2.moveForward;
                mc.player.movementInput.backKeyDown = true;
            }
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
                final MovementInput movementInput3 = mc.player.movementInput;
                --movementInput3.moveStrafe;
                mc.player.movementInput.rightKeyDown = true;
            }
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
                final MovementInput movementInput4 = mc.player.movementInput;
                ++movementInput4.moveStrafe;
                mc.player.movementInput.rightKeyDown = true;
            }
            mc.player.movementInput.jump = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
            mc.player.movementInput.sneak = (this.sneak.getValue() && Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()));
            if (mc.player.movementInput.sneak) {
                mc.player.movementInput.moveStrafe *= (float)0.3;
                mc.player.movementInput.moveForward *= (float)0.3;
            }
        }
    }
    
    static {
        INSTANCE = new GuiMove();
    }
}
