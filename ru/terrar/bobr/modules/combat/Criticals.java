//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.common.*;
import ru.terrar.bobr.events.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Criticals extends Module
{
    public static final Criticals INSTANCE;
    
    public Criticals() {
        super("Criticals", "criticals", ModuleCategory.COMBAT);
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
    
    @SubscribeEvent
    public void onSendPacket(final SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                final Minecraft mc = Minecraft.getMinecraft();
                if (packet.getEntityFromWorld((World)mc.world) instanceof EntityLivingBase && mc.player.onGround) {
                    mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1, mc.player.posZ, false));
                    mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                }
            }
        }
    }
    
    static {
        INSTANCE = new Criticals();
    }
}
