//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import ru.terrar.bobr.events.*;

public class EventFactory
{
    public static boolean setOpaqueCube() {
        final SetOpaqueCubeEvent setOpaqueCube = new SetOpaqueCubeEvent();
        return MinecraftForge.EVENT_BUS.post((Event)setOpaqueCube);
    }
    
    public static Packet<?> onSendPacket(final Packet<?> packet) {
        final SendPacketEvent event = new SendPacketEvent((Packet)packet);
        return (Packet<?>)(MinecraftForge.EVENT_BUS.post((Event)event) ? null : event.getPacket());
    }
    
    public static Packet<?> onReceivePacket(final Packet<?> packet) {
        final ReceivePacketEvent event = new ReceivePacketEvent((Packet)packet);
        return (Packet<?>)(MinecraftForge.EVENT_BUS.post((Event)event) ? null : event.getPacket());
    }
    
    public static boolean renderBlock(final IBlockState stateIn) {
        return MinecraftForge.EVENT_BUS.post((Event)new RenderModelEvent(stateIn));
    }
    
    public static boolean shouldSideBeRendered(final IBlockState state, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing facing) {
        final ShouldSideBeRenderedEvent event = new ShouldSideBeRenderedEvent(state, state.getBlock().shouldSideBeRendered(state, blockAccess, pos, facing));
        MinecraftForge.EVENT_BUS.post((Event)event);
        return event.getShouldBeRendered();
    }
    
    public static float getAmbientOcclusionLightValue(final IBlockState state) {
        final GetAmbientOcclusionLightValueEvent event = new GetAmbientOcclusionLightValueEvent(state);
        MinecraftForge.EVENT_BUS.post((Event)event);
        return event.getLightValue();
    }
    
    public static AxisAlignedBB getCollisionBoundingBox() {
        final GetLiquidCollisionBoundingBoxEvent event = new GetLiquidCollisionBoundingBoxEvent();
        MinecraftForge.EVENT_BUS.post((Event)event);
        return event.getCollisionBoundingBox();
    }
    
    public static boolean isUser() {
        return !MinecraftForge.EVENT_BUS.post((Event)new PlayerIsUserEvent());
    }
    
    public static boolean onUpdateWalkingPlayer() {
        return MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent());
    }
}
