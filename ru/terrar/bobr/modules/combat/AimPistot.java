//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.terrar.bobr.managers.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class AimPistot extends Module
{
    public static final AimPistot INSTANCE;
    public Minecraft mc;
    public final FloatSetting reach;
    public final FloatSetting Predict;
    public final FloatSetting Predictuse;
    public final BooleanSetting wals;
    public Entity target;
    public float[] facing;
    public boolean can_attack;
    public float[] pred;
    private Entity focusTarget;
    private float old_must;
    private float old_posX;
    private float old_posZ;
    private float[] old_pred;
    private int life_time_pred;
    private float post_f;
    private float post_f2;
    
    public AimPistot() {
        super("AimPistol", "AimPistol", ModuleCategory.COMBAT);
        this.mc = Minecraft.getMinecraft();
        this.reach = new FloatSetting("Reach", "reach", 250.0f, 0.0f, 250.0f);
        this.Predict = new FloatSetting("Predict", "Predict", 6.0f, 0.0f, 10.0f);
        this.Predictuse = new FloatSetting("Predictuse", "Predictuse", 6.0f, 0.0f, 100.0f);
        this.wals = new BooleanSetting("Wals", "Wals", false);
        this.addSettings(this.reach, this.wals, this.Predict);
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
    public void onSendPacket(final SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                this.focusTarget = packet.getEntityFromWorld((World)Minecraft.getMinecraft().world);
            }
        }
        if (this.target != null && (event.getPacket() instanceof CPacketPlayer.PositionRotation || event.getPacket() instanceof CPacketPlayer.Rotation)) {
            final double deltaX = this.target.posX - Minecraft.getMinecraft().player.posX;
            final double deltaY = this.target.posY + this.target.height / 2.0f - Minecraft.getMinecraft().player.posY - Minecraft.getMinecraft().player.getEyeHeight();
            final double deltaZ = this.target.posZ - Minecraft.getMinecraft().player.posZ;
            final double deltaGround = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            final float pitch = (float)(-Math.toDegrees(Math.atan(deltaY / deltaGround)));
            float yaw = (float)(-Math.toDegrees(Math.atan(deltaX / deltaZ)));
            if (deltaZ <= 0.0) {
                if (deltaX > 0.0) {
                    yaw -= 180.0f;
                }
                else {
                    yaw += 180.0f;
                }
            }
            final EntityPlayerSP player = Minecraft.getMinecraft().player;
            event.setPacket((Packet)new CPacketPlayer.PositionRotation(player.posX, player.posY, player.posZ, yaw, pitch, player.onGround));
        }
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        this.target = null;
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        this.target = null;
        int yaw_min = 1000000000;
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (this.isTarget(entity)) {
                if (!this.wals.getValue() && !AntiBot.isBot(entity.getName()) && !FriendManager.isFriend(entity.getName()) && entity instanceof EntityPlayer && Minecraft.getMinecraft().player.canEntityBeSeen(entity)) {
                    this.facing = faceTarget(entity, 360.0f, 360.0f, false);
                    final float f = this.facing[0];
                    final float f2 = this.facing[1];
                    int n;
                    if (Math.abs(Minecraft.getMinecraft().player.rotationYaw) >= Math.abs(f)) {
                        n = (int)(Math.abs(Minecraft.getMinecraft().player.rotationYaw) - Math.abs(f));
                    }
                    else {
                        n = (int)((int)Math.abs(f) - Math.abs(Minecraft.getMinecraft().player.rotationYaw));
                    }
                    if (n < yaw_min) {
                        yaw_min = n;
                        this.target = entity;
                    }
                }
                if (!this.wals.getValue() || AntiBot.isBot(entity.getName()) || FriendManager.isFriend(entity.getName()) || !(entity instanceof EntityPlayer)) {
                    continue;
                }
                this.facing = faceTarget(entity, 360.0f, 360.0f, false);
                final float f = this.facing[0];
                final float f2 = this.facing[1];
                int n;
                if (Math.abs(Minecraft.getMinecraft().player.rotationYaw) >= Math.abs(f)) {
                    n = (int)(Math.abs(Minecraft.getMinecraft().player.rotationYaw) - Math.abs(f));
                }
                else {
                    n = (int)((int)Math.abs(f) - Math.abs(Minecraft.getMinecraft().player.rotationYaw));
                }
                if (n >= yaw_min) {
                    continue;
                }
                yaw_min = n;
                this.target = entity;
            }
        }
        if (this.target != null) {
            this.facing = faceTarget(this.target, 360.0f, 360.0f, false);
            final float f3 = this.facing[0];
            final float f4 = this.facing[1];
            final float old_f = f3;
            float[] n2 = facePredict(this.target, this.old_posX, this.old_posZ);
            float pred_f = n2[0];
            float pred_f2 = n2[1];
            if (Minecraft.getMinecraft().player.canEntityBeSeen(this.target)) {
                this.can_attack = true;
            }
            else {
                this.can_attack = false;
            }
            n2 = faceCords(pred_f, (float)this.target.posY, pred_f2, 360.0f, 360.0f, false);
            pred_f = n2[0];
            pred_f2 = n2[1];
            Minecraft.getMinecraft().player.rotationYaw = pred_f;
            Minecraft.getMinecraft().player.rotationPitch = pred_f2;
            this.old_must = old_f;
            this.old_posX = (float)this.target.posX;
            this.old_posZ = (float)this.target.posZ;
        }
    }
    
    public boolean isTarget(final Entity entity) {
        return entity != Minecraft.getMinecraft().player && entity != Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().player.getDistanceSq(entity) <= Math.pow(this.reach.getValue(), 2.0) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static float[] facePredict(final Entity target, final float old_posX, final float old_posZ) {
        final float new_posX = (float)target.posX;
        final float new_posZ = (float)target.posZ;
        float differenceX;
        if (new_posX >= 0.0f) {
            differenceX = Math.abs(new_posX) - Math.abs(old_posX);
        }
        else {
            differenceX = Math.abs(old_posX) - Math.abs(new_posX);
        }
        float differenceZ;
        if (new_posZ >= 0.0f) {
            differenceZ = Math.abs(new_posZ) - Math.abs(old_posZ);
        }
        else {
            differenceZ = Math.abs(old_posZ) - Math.abs(new_posZ);
        }
        if (differenceX != 0.0f) {
            if (3 != AimPistot.INSTANCE.life_time_pred) {
                final AimPistot instance = AimPistot.INSTANCE;
                ++instance.life_time_pred;
            }
        }
        else if (0 != AimPistot.INSTANCE.life_time_pred) {
            differenceX = AimPistot.INSTANCE.old_pred[0];
            final AimPistot instance2 = AimPistot.INSTANCE;
            --instance2.life_time_pred;
        }
        if (differenceZ != 0.0f) {
            if (3 != AimPistot.INSTANCE.life_time_pred) {
                final AimPistot instance3 = AimPistot.INSTANCE;
                ++instance3.life_time_pred;
            }
        }
        else if (0 != AimPistot.INSTANCE.life_time_pred) {
            differenceZ = AimPistot.INSTANCE.old_pred[1];
            final AimPistot instance4 = AimPistot.INSTANCE;
            --instance4.life_time_pred;
        }
        AimPistot.INSTANCE.old_pred = new float[] { differenceX, differenceZ };
        final float returnX = new_posX + differenceX * AimPistot.INSTANCE.Predict.getValue();
        final float returnZ = new_posZ + differenceZ * AimPistot.INSTANCE.Predict.getValue();
        return new float[] { returnX, returnZ };
    }
    
    public static float[] faceCords(final float posX, final float posY, final float posZ, final float p_706252, final float p_706253, final boolean miss) {
        final double var4 = posX - Minecraft.getMinecraft().player.posX;
        final double var5 = posZ - Minecraft.getMinecraft().player.posZ;
        final double var6 = posY + 1.6200000047683716 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        final double var7 = MathHelper.sqrt(var4 * var4 + var5 * var5);
        final float var8 = (float)(Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
        final float var9 = (float)(-(Math.atan2(var6 - 0.25, var7) * 180.0 / 3.141592653589793));
        final float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        float pitch = updateRotation(Minecraft.getMinecraft().player.rotationPitch, var9, p_706253);
        float yaw = updateRotation(Minecraft.getMinecraft().player.rotationYaw, var8, p_706252);
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[] { yaw, pitch };
    }
    
    public static float[] faceTarget(final Entity target, final float p_706252, final float p_706253, final boolean miss) {
        final double var4 = target.posX - Minecraft.getMinecraft().player.posX;
        final double var5 = target.posZ - Minecraft.getMinecraft().player.posZ;
        double var7;
        if (target instanceof EntityLivingBase) {
            final EntityLivingBase var6 = (EntityLivingBase)target;
            final double s = target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY;
            var7 = var6.posY + var6.getEyeHeight() - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        }
        else {
            var7 = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight()) + 2.0;
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
        INSTANCE = new AimPistot();
    }
    
    public enum Pred
    {
        Client("Client"), 
        Silent("Silent");
        
        private final String name;
        
        private Pred(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    public enum Modes
    {
        Client("Client"), 
        Silent("Silent");
        
        private final String name;
        
        private Modes(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
