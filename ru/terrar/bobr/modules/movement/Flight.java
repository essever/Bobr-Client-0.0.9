//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.terrar.bobr.events.*;
import ru.terrar.bobr.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Flight extends Module
{
    public static final Flight INSTANCE;
    public final EnumSetting mode;
    private final Minecraft mc;
    
    public Flight() {
        super("Flight", "flight", Module.ModuleCategory.MOVEMENT);
        this.mode = new EnumSetting("Mode", "mode", Mode.values(), Mode.VANILLA);
        this.mc = Minecraft.getMinecraft();
        this.addSettings(new Setting[] { this.mode });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        if (this.mc.player != null) {
            this.mc.player.capabilities.isFlying = false;
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (this.mc.player != null) {
            this.mc.player.capabilities.isFlying = true;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerMove(final PlayerMoveEvent event) {
        if (this.mode.getCurrentValue() == Mode.PACKET) {
            event.x = 0.0;
            event.y = 0.0;
            event.z = 0.0;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalking(final UpdateWalkingPlayerEvent event) {
        if (this.mode.getCurrentValue() == Mode.PACKET) {
            final double[] moveVec = PlayerUtil.getPlayerMoveVec();
            final double speedX = moveVec[0] * 0.2;
            final double speedZ = moveVec[1] * 0.2;
            double speedY = 0.0;
            if (this.mc.player.movementInput.jump) {
                speedY = 0.2;
            }
            else if (this.mc.player.movementInput.sneak) {
                speedY = -0.2;
            }
            this.mc.player.setPositionAndRotation(this.mc.player.posX + speedX, this.mc.player.posY + speedY, this.mc.player.posZ + speedZ, this.mc.player.rotationYaw, this.mc.player.rotationPitch);
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, this.mc.player.rotationYaw, this.mc.player.rotationPitch, this.mc.player.onGround));
            this.mc.player.setVelocity(0.0, 0.0, 0.0);
            event.setCanceled(true);
        }
    }
    
    static {
        INSTANCE = new Flight();
    }
    
    public enum Mode
    {
        VANILLA("Vanilla"), 
        PACKET("Packet");
        
        private final String name;
        
        private Mode(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
