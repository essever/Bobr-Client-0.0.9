//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;

public class Strafe extends Module
{
    public static final Strafe INSTANCE;
    int waitCounter;
    int forward;
    private double motionSpeed;
    private int currentState;
    private double prevDist;
    private final Minecraft mc;
    public final BooleanSetting jump;
    
    public Strafe() {
        super("Strafe", "Strafe", Module.ModuleCategory.MOVEMENT);
        this.waitCounter = 0;
        this.forward = 1;
        this.mc = Minecraft.getMinecraft();
        this.jump = new BooleanSetting("Jump", "jump", false);
        this.addSettings(new Setting[] { this.jump });
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
        final boolean boost = Math.abs(this.mc.player.rotationYawHead - this.mc.player.rotationYaw) < 90.0f;
        if ((this.isPlayerTryingMoveForward() || this.isPlayerTryingStrafe()) && this.mc.player.onGround) {
            this.mc.player.motionY = 0.4;
        }
        if (this.mc.player.moveForward != 0.0f) {
            if (!this.mc.player.isSprinting()) {
                this.mc.player.setSprinting(true);
            }
            float yaw = this.mc.player.rotationYaw;
            if (this.mc.player.moveForward > 0.0f) {
                if (this.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += ((this.mc.player.movementInput.moveStrafe > 0.0f) ? -45.0f : 45.0f);
                }
                this.forward = 1;
                this.mc.player.moveForward = 1.5f;
                this.mc.player.moveStrafing = 1.5f;
            }
            else if (this.mc.player.moveForward < 0.0f) {
                if (this.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += ((this.mc.player.movementInput.moveStrafe > 0.0f) ? 45.0f : -45.0f);
                }
                this.forward = -1;
                this.mc.player.moveForward = -1.5f;
                this.mc.player.moveStrafing = 1.5f;
            }
            if (this.mc.player.onGround) {
                final float f = (float)Math.toRadians(yaw);
                if (this.jump.getValue() && this.mc.gameSettings.keyBindJump.isPressed()) {
                    this.Move(f);
                }
            }
            else {
                if (this.waitCounter < 1) {
                    ++this.waitCounter;
                    return;
                }
                this.waitCounter = 0;
                final double currentSpeed = Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ);
                double speed = boost ? 1.05 : 1.025;
                if (this.mc.player.motionY < 0.0) {
                    speed = 1.0;
                }
                final double direction = Math.toRadians(yaw);
                this.mc.player.motionX = -Math.sin(direction) * speed * currentSpeed * this.forward;
                this.mc.player.motionZ = Math.cos(direction) * speed * currentSpeed * this.forward;
            }
        }
        if (!this.isPlayerTryingMoveForward() && !this.isPlayerTryingStrafe()) {
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
        }
    }
    
    private void Move(final float f) {
        if (this.jump.getValue()) {
            this.mc.player.motionY = 0.4;
        }
        final EntityPlayerSP player = this.mc.player;
        player.motionX -= MathHelper.sin(f) * 0.2f * (double)this.forward;
        final EntityPlayerSP player2 = this.mc.player;
        player2.motionZ += MathHelper.cos(f) * 0.2f * (double)this.forward;
    }
    
    public boolean isPlayerTryingMoveForward() {
        return this.mc.gameSettings.keyBindForward.isKeyDown() || this.mc.gameSettings.keyBindBack.isKeyDown();
    }
    
    public boolean isPlayerTryingStrafe() {
        return this.mc.gameSettings.keyBindRight.isKeyDown() || this.mc.gameSettings.keyBindLeft.isKeyDown();
    }
    
    static {
        INSTANCE = new Strafe();
    }
}
