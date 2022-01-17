//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoFall extends Module
{
    public static final NoFall INSTANCE;
    
    public NoFall() {
        super("NoFall", "nofall", Module.ModuleCategory.MOVEMENT);
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
    public void onSendPacket(final SendPacketEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (event.getPacket() instanceof CPacketPlayer && !((CPacketPlayer)event.getPacket()).isOnGround() && mc.player.fallDistance > 3.0f) {
            final EntityPlayerSP player = Minecraft.getMinecraft().player;
            event.setPacket((Packet)new CPacketPlayer.PositionRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch, true));
        }
    }
    
    static {
        INSTANCE = new NoFall();
    }
}
