//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import ru.terrar.bobr.util.*;
import net.minecraft.client.settings.*;
import org.lwjgl.input.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SafeWalk extends Module
{
    public static final SafeWalk INSTANCE;
    
    public SafeWalk() {
        super("Safe Walk", "safewalk", Module.ModuleCategory.MOVEMENT);
        this.addSettings(new Setting[0]);
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onMove(final PlayerMoveEvent event) {
        if (Minecraft.getMinecraft().player.onGround && !Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed() && !BlockUtil.isCollidable(Minecraft.getMinecraft().world.getBlockState(new BlockPos(Minecraft.getMinecraft().player.getPositionVector().add(new Vec3d(0.0, -0.5, 0.0)))).getBlock())) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), true);
        }
        else if (!Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), false);
        }
    }
    
    static {
        INSTANCE = new SafeWalk();
    }
}
