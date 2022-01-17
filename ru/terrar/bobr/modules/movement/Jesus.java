//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import ru.terrar.bobr.events.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class Jesus extends Module
{
    public static final Jesus INSTANCE;
    public final FloatSetting offset;
    private final Minecraft mc;
    
    public Jesus() {
        super("Jesus", "jesus", Module.ModuleCategory.MOVEMENT);
        this.offset = new FloatSetting("Offset", "offset", 0.2f, 0.0f, 1.0f);
        this.mc = Minecraft.getMinecraft();
        this.addSettings(new Setting[] { this.offset });
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onLiquidCollision(final GetLiquidCollisionBoundingBoxEvent event) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (this.mc.player.isSneaking() || this.mc.player.fallDistance > 3.0f || this.isInLiquid() || this.mc.player.motionY >= 0.10000000149011612) {
            return;
        }
        event.setCollisionBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 - this.offset.getValue(), 1.0));
    }
    
    @SubscribeEvent
    public void onUndateWalking(final UpdateWalkingPlayerEvent event) {
        if (this.isWaterWalking() && !this.mc.player.isSneaking() && this.mc.player.ticksExisted % 2 == 0) {
            final EntityPlayerSP player = this.mc.player;
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(player.posX, player.posY + this.offset.getValue(), player.posZ, player.rotationYaw, player.rotationPitch, player.onGround));
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onMove(final PlayerMoveEvent event) {
        if (this.isInLiquid() && !this.mc.player.isSneaking()) {
            event.y = 0.1;
        }
    }
    
    private boolean isOverLiquid() {
        if (this.mc.player != null) {
            final AxisAlignedBB bb = this.mc.player.getEntityBoundingBox().offset(0.0, -0.1, 0.0);
            for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX) + 1; ++x) {
                for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.minZ) + 1; ++z) {
                    final Block block = this.mc.world.getBlockState(new BlockPos((double)x, bb.minY, (double)z)).getBlock();
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    private boolean isWaterWalking() {
        return !this.isInLiquid() && this.isOverLiquid();
    }
    
    private boolean isInLiquid() {
        final AxisAlignedBB bb = this.mc.player.getEntityBoundingBox().offset(0.0, (double)this.offset.getValue(), 0.0);
        for (int x = MathHelper.floor(bb.minX); x < MathHelper.ceil(bb.maxX); ++x) {
            for (int z = MathHelper.floor(bb.minZ); z < MathHelper.ceil(bb.minZ); ++z) {
                final Block block = this.mc.world.getBlockState(new BlockPos((double)x, bb.minY, (double)z)).getBlock();
                if (block instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        INSTANCE = new Jesus();
    }
}
