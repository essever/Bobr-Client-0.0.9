//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.util.*;
import net.minecraft.client.entity.*;
import org.lwjgl.input.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;
import java.util.*;

public class AntiBot extends Module
{
    private transient long time;
    public static final AntiBot INSTANCE;
    public final EnumSetting click;
    public final BooleanSetting clicks;
    public final BooleanSetting InvisibleRemove;
    private int but;
    public static List<String> BOTS;
    public boolean toggle;
    
    public AntiBot() {
        super("AntiBot", "antibot", ModuleCategory.COMBAT);
        this.click = new EnumSetting("Click", "Click", Click.values(), Click.LEFT);
        this.clicks = new BooleanSetting("Clicks", "Clicks", true);
        this.InvisibleRemove = new BooleanSetting("InvisibleRemove", "InvisibleRemove", true);
        this.but = 0;
        this.addSettings(this.clicks, this.click, this.InvisibleRemove);
    }
    
    @Override
    public void onEnable() {
        this.toggle = true;
        super.onEnable();
        this.time = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register((Object)this);
        AntiBot.BOTS.clear();
    }
    
    @Override
    public void onDisable() {
        this.toggle = false;
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity.isInvisibleToPlayer((EntityPlayer)Minecraft.getMinecraft().player) && this.InvisibleRemove.getValue() && entity instanceof EntityPlayer) {
                Minecraft.getMinecraft().world.removeEntity(entity);
                ChatUtil.clientMessage(entity.getName() + " Removed");
            }
        }
        try {
            for (final Entity e : Minecraft.getMinecraft().world.loadedEntityList) {
                if (e != Minecraft.getMinecraft().player && e.ticksExisted < 5 && e instanceof EntityOtherPlayerMP && e instanceof EntityPlayer && Minecraft.getMinecraft().player.getDistance(e) <= 8.0f && Minecraft.getMinecraft().getConnection().getPlayerInfo(e.getUniqueID()).getResponseTime() != 0) {
                    Minecraft.getMinecraft().world.removeEntity(e);
                    ChatUtil.clientMessage(e.getName() + " Removed");
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            final Entity entity2 = Minecraft.getMinecraft().objectMouseOver.entityHit;
            if (entity2 != null && entity2 instanceof EntityPlayer && this.clicks.getValue()) {
                if (this.click.getCurrentValue() == Click.LEFT) {
                    this.but = 0;
                }
                else if (this.click.getCurrentValue() == Click.RIGHT) {
                    this.but = 1;
                }
                else if (this.click.getCurrentValue() == Click.MIDDLE) {
                    this.but = 2;
                }
                else if (this.click.getCurrentValue() == Click.NO && isBot(entity2.getName())) {
                    AntiBot.BOTS.add(entity2.getName());
                    ChatUtil.clientMessage(entity2.getName() + " is Not Bot");
                }
                if (Mouse.isButtonDown(this.but) && this.click.getCurrentValue() != Click.NO && isBot(entity2.getName())) {
                    AntiBot.BOTS.add(entity2.getName());
                    ChatUtil.clientMessage(entity2.getName() + " is Not Bot");
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static boolean isBot(final String nick) {
        if (AntiBot.INSTANCE.toggle && AntiBot.INSTANCE.clicks.getValue()) {
            for (final String friend : AntiBot.BOTS) {
                if (friend.equalsIgnoreCase(nick)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    static {
        INSTANCE = new AntiBot();
        AntiBot.BOTS = new ArrayList<String>();
    }
    
    public enum Click
    {
        NO("NO"), 
        LEFT("Left"), 
        MIDDLE("Middle"), 
        RIGHT("Right");
        
        private final String name;
        
        private Click(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
