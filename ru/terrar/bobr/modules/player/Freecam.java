//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.player;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.entity.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.terrar.bobr.events.*;
import net.minecraft.network.play.client.*;

public class Freecam extends Module
{
    public static final Freecam INSTANCE;
    public final FloatSetting verticalSpeed;
    public final FloatSetting horizontalSpeed;
    private int lastThirdPerson;
    public EntityOtherPlayerMP camera;
    private boolean activeThisSession;
    
    public Freecam() {
        super("Freecam", "freecam", Module.ModuleCategory.PLAYER);
        this.verticalSpeed = new FloatSetting("Vertical Speed", "verticalspeed", 3.0f);
        this.horizontalSpeed = new FloatSetting("Horizontal Speed", "horizontalspeed", 3.0f);
        this.activeThisSession = false;
        this.verticalSpeed.setParent("Speed");
        this.horizontalSpeed.setParent("Speed");
        this.addSettings(new Setting[] { this.verticalSpeed, this.horizontalSpeed });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.activeThisSession = true;
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote) {
            Minecraft.getMinecraft().setRenderViewEntity((Entity)Minecraft.getMinecraft().player);
            if (this.activeThisSession) {
                Minecraft.getMinecraft().gameSettings.thirdPersonView = this.lastThirdPerson;
                Minecraft.getMinecraft().world.removeEntity((Entity)this.camera);
            }
        }
        this.camera = null;
        this.activeThisSession = false;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            this.camera = null;
            return;
        }
        if (this.camera == null) {
            this.lastThirdPerson = Minecraft.getMinecraft().gameSettings.thirdPersonView;
            this.camera = new EntityOtherPlayerMP((World)Minecraft.getMinecraft().world, Minecraft.getMinecraft().getSession().getProfile());
            Minecraft.getMinecraft().world.addEntityToWorld(333393333, (Entity)this.camera);
            this.camera.copyLocationAndAnglesFrom((Entity)Minecraft.getMinecraft().player);
            Minecraft.getMinecraft().setRenderViewEntity((Entity)this.camera);
            this.camera.noClip = true;
        }
        Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
        this.camera.inventory = Minecraft.getMinecraft().player.inventory;
        this.camera.setHealth(Minecraft.getMinecraft().player.getHealth());
        final float forward = (Minecraft.getMinecraft().player.movementInput.forwardKeyDown ? this.horizontalSpeed.getValue() : 0.0f) - (Minecraft.getMinecraft().player.movementInput.backKeyDown ? this.horizontalSpeed.getValue() : 0.0f);
        final float strafe = (Minecraft.getMinecraft().player.movementInput.leftKeyDown ? this.horizontalSpeed.getValue() : 0.0f) - (Minecraft.getMinecraft().player.movementInput.rightKeyDown ? this.horizontalSpeed.getValue() : 0.0f);
        final float vertical = (Minecraft.getMinecraft().player.movementInput.jump ? this.verticalSpeed.getValue() : 0.0f) - (Minecraft.getMinecraft().player.movementInput.sneak ? this.verticalSpeed.getValue() : 0.0f);
        final Vec3d vector = new Vec3d((double)strafe, (double)vertical, (double)forward).rotateYaw((float)(-Math.toRadians(this.camera.rotationYaw)));
        this.camera.setPositionAndRotationDirect(this.camera.posX + vector.x, this.camera.posY + vector.y, this.camera.posZ + vector.z, this.camera.rotationYaw, this.camera.rotationPitch, 3, false);
    }
    
    @SubscribeEvent
    public void onIsUser(final PlayerIsUserEvent event) {
        event.setCanceled(true);
    }
    
    @SubscribeEvent
    public void onOpaqueCube(final SetOpaqueCubeEvent event) {
        event.setCanceled(true);
    }
    
    @SubscribeEvent
    public void onSendPacket(final SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity useEntity = (CPacketUseEntity)event.getPacket();
            if (useEntity.getEntityFromWorld((World)Minecraft.getMinecraft().world) == Minecraft.getMinecraft().player) {
                event.setCanceled(true);
            }
        }
    }
    
    static {
        INSTANCE = new Freecam();
    }
}
