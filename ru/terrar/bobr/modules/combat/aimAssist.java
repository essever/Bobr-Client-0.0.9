//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.terrar.bobr.managers.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class aimAssist extends Module
{
    public static final aimAssist INSTANCE;
    public Minecraft mc;
    public final FloatSetting reach;
    public final FloatSetting aimSpeed;
    public float[] facing;
    public Entity target;
    private Entity focusTarget;
    
    public aimAssist() {
        super("AimAssist", "aimAssist", ModuleCategory.COMBAT);
        this.mc = Minecraft.getMinecraft();
        this.reach = new FloatSetting("Reach", "reach", 6.0f, 0.0f, 16.0f);
        this.aimSpeed = new FloatSetting("AimSpeed", "aimspeed", 0.0f, 0.0f, 5.0f);
        this.addSettings(this.reach, this.aimSpeed);
        this.setEnabled(false);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public static int generateRandomIntIntRange(final int min, final int max) {
        final Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        this.target = null;
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        this.target = null;
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (this.isTarget(entity) && !AntiBot.isBot(entity.getName()) && !FriendManager.isFriend(entity.getName()) && entity instanceof EntityPlayer) {
                if (this.target == null) {
                    this.target = entity;
                }
                else {
                    if (Minecraft.getMinecraft().player.getDistanceSq(entity) >= Minecraft.getMinecraft().player.getDistanceSq(this.target)) {
                        continue;
                    }
                    this.target = entity;
                }
            }
        }
        if (this.target != null) {
            this.facing = faceTarget(this.target, 360.0f, 360.0f, false);
            final float f = this.facing[0];
            final float f2 = this.facing[1];
            float sp = 0.2f;
            sp = this.aimSpeed.getValue();
            if (Minecraft.getMinecraft().player.rotationYaw < f) {
                final EntityPlayerSP player = Minecraft.getMinecraft().player;
                player.rotationYaw += sp;
            }
            if (Minecraft.getMinecraft().player.rotationYaw > f) {
                final EntityPlayerSP player2 = Minecraft.getMinecraft().player;
                player2.rotationYaw -= sp;
            }
            if (Minecraft.getMinecraft().player.rotationPitch < f2) {
                final EntityPlayerSP player3 = Minecraft.getMinecraft().player;
                player3.rotationPitch += sp;
            }
            if (Minecraft.getMinecraft().player.rotationPitch <= f2) {
                final EntityPlayerSP player4 = Minecraft.getMinecraft().player;
                player4.rotationPitch -= sp;
            }
        }
    }
    
    public boolean isTarget(final Entity entity) {
        return entity != Minecraft.getMinecraft().player && entity != Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().player.getDistanceSq(entity) <= Math.pow(this.reach.getValue(), 2.0) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static float[] faceTarget(final Entity target, final float p_706252, final float p_706253, final boolean miss) {
        final double var4 = target.posX - Minecraft.getMinecraft().player.posX;
        final double var5 = target.posZ - Minecraft.getMinecraft().player.posZ;
        double var7;
        if (target instanceof EntityLivingBase) {
            final EntityLivingBase var6 = (EntityLivingBase)target;
            var7 = var6.posY + var6.getEyeHeight() - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        }
        else {
            var7 = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        }
        final double var8 = MathHelper.sqrt(var4 * var4 + var5 * var5);
        final float var9 = (float)(Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
        final float var10 = (float)(-(Math.atan2(var7 - ((target instanceof EntityPlayer) ? 0.25 : 0.0), var8) * 180.0 / 3.141592653589793));
        final float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        float pitch = updateRotation(Minecraft.getMinecraft().player.rotationPitch, var10, p_706253);
        float yaw = updateRotation(Minecraft.getMinecraft().player.rotationYaw, var9, p_706252);
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[] { yaw, pitch };
    }
    
    public static float updateRotation(final float current, final float intended, final float speed) {
        float f = MathHelper.wrapDegrees(intended - current);
        if (f > speed) {
            f = speed;
        }
        if (f < -speed) {
            f = -speed;
        }
        return current + f;
    }
    
    static {
        INSTANCE = new aimAssist();
    }
    
    public enum Priority
    {
        CLOSEST("Closest"), 
        FOCUS("Focus");
        
        private final String name;
        
        private Priority(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
