//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlow extends Module
{
    public static final NoSlow INSTANCE;
    public final BooleanSetting sneak;
    public final BooleanSetting item;
    
    public NoSlow() {
        super("NoSlow", "noslow", Module.ModuleCategory.MOVEMENT);
        this.sneak = new BooleanSetting("Sneaking", "sneak", true);
        this.item = new BooleanSetting("Item", "item", true);
        this.addSettings(new Setting[] { this.sneak, this.item });
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
    public void onInput(final InputUpdateEvent event) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
            if (this.item.getValue() && Minecraft.getMinecraft().player.isHandActive() && !Minecraft.getMinecraft().player.isRiding()) {
                final MovementInput movementInput = Minecraft.getMinecraft().player.movementInput;
                movementInput.moveStrafe /= 0.2f;
                final MovementInput movementInput2 = Minecraft.getMinecraft().player.movementInput;
                movementInput2.moveForward /= 0.2f;
            }
            if (this.sneak.getValue() && Minecraft.getMinecraft().player.isSneaking()) {
                Minecraft.getMinecraft().player.movementInput.moveStrafe /= (float)0.3;
                Minecraft.getMinecraft().player.movementInput.moveForward /= (float)0.3;
            }
        }
    }
    
    static {
        INSTANCE = new NoSlow();
    }
}
