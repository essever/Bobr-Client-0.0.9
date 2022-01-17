//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import ru.terrar.bobr.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Speed extends Module
{
    public static final Speed INSTANCE;
    private final Minecraft mc;
    
    public Speed() {
        super("Speed", "speed", Module.ModuleCategory.MOVEMENT);
        this.mc = Minecraft.getMinecraft();
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        super.onEnable();
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        super.onDisable();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onMove(final PlayerMoveEvent event) {
        if (this.mc.player.isSneaking() || this.mc.player.isInWater() || this.mc.player.isInLava() || this.mc.player.isOnLadder() || this.mc.player.isElytraFlying() || this.mc.player.capabilities.isFlying) {
            return;
        }
        final float playerSpeed = 0.28634363f;
        final double[] moveVec = PlayerUtil.getPlayerMoveVec();
        event.x = playerSpeed * moveVec[0];
        event.z = playerSpeed * moveVec[1];
    }
    
    static {
        INSTANCE = new Speed();
    }
}
