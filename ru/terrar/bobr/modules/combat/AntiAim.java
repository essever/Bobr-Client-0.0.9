//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import java.util.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class AntiAim extends Module
{
    private transient long time;
    public static final AntiAim INSTANCE;
    private float rot;
    private final Random e;
    private final Random e2;
    public int serv;
    
    public AntiAim() {
        super("AntiAim", "antaim", ModuleCategory.COMBAT);
        this.rot = 0.0f;
        this.serv = 0;
        this.addSettings(new Setting[0]);
        this.e = new Random();
        this.e2 = new Random();
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
        final float f = this.e2.nextFloat() * 360.0f;
        final float cfr_ignored_0 = this.e2.nextFloat() * 180.0f - 90.0f;
        Minecraft.getMinecraft().player.rotationYawHead = f;
        Minecraft.getMinecraft().player.renderYawOffset = f;
    }
    
    static {
        INSTANCE = new AntiAim();
    }
}
