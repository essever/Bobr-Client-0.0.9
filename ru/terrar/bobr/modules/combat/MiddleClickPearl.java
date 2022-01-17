//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class MiddleClickPearl extends Module
{
    private transient long time;
    public static final MiddleClickPearl INSTANCE;
    
    public MiddleClickPearl() {
        super("MiddleClickPearl", "middleclickpearl", ModuleCategory.COMBAT);
        this.addSettings(new Setting[0]);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.time = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
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
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null && Mouse.isButtonDown(2)) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack itemStack = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
                if (itemStack.getItem() == Items.ENDER_PEARL) {
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketHeldItemChange(Minecraft.getMinecraft().player.inventory.currentItem));
                }
            }
        }
    }
    
    static {
        INSTANCE = new MiddleClickPearl();
    }
}
