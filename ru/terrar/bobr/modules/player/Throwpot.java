//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.player;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class Throwpot extends Module
{
    public static final Throwpot INSTANCE;
    public Minecraft mc;
    private Entity target;
    private String hef;
    private Entity focusTarget;
    private boolean checks;
    private boolean old;
    private String enter;
    private int x;
    private int y;
    public final FloatSetting hp;
    private final FontRenderer fr;
    
    public Throwpot() {
        super("AutoPot", "autopot", Module.ModuleCategory.PLAYER);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.hp = new FloatSetting("HP", "hp", 26.0f, 0.0f, 20.0f);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.addSettings(new Setting[] { this.hp });
    }
    
    public void onEnable() {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        if (Minecraft.getMinecraft().player.getHealth() <= this.hp.getValue()) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack itemStack = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
                if (itemStack.getItem() == Items.SPLASH_POTION) {
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                    final int yaw = (int)Minecraft.getMinecraft().player.rotationYaw;
                    final int pitch = (int)Minecraft.getMinecraft().player.rotationPitch;
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ, (float)yaw, 90.0f, Minecraft.getMinecraft().player.onGround));
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ, (float)yaw, (float)pitch, Minecraft.getMinecraft().player.onGround));
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketHeldItemChange(Minecraft.getMinecraft().player.inventory.currentItem));
                    return;
                }
            }
        }
    }
    
    static {
        INSTANCE = new Throwpot();
    }
}
