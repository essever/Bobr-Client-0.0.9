//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.player;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoDisconnect extends Module
{
    public static final AutoDisconnect INSTANCE;
    public final BooleanSetting players;
    public final FloatSetting health;
    public final BooleanSetting ignoreTotems;
    public final BooleanSetting disable;
    
    public AutoDisconnect() {
        super("Auto Disconnect", "autodisconnect", Module.ModuleCategory.PLAYER);
        this.players = new BooleanSetting("Players", "players", false);
        this.health = new FloatSetting("Health", "health", 10.0f, 0.0f, 20.0f, 1.0f);
        this.ignoreTotems = new BooleanSetting("Ignore Totems", "ignoretotems", true);
        this.disable = new BooleanSetting("Disable", "disable", true);
        this.addSettings(new Setting[] { this.players, this.health, this.ignoreTotems, this.disable });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        if (this.players.getValue()) {
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().player && entity != Freecam.INSTANCE.camera) {
                    Minecraft.getMinecraft().player.connection.onDisconnect((ITextComponent)new TextComponentString("A Player was found"));
                    if (this.disable.getValue()) {
                        this.setEnabled(false);
                    }
                    return;
                }
            }
        }
        if (!this.ignoreTotems.getValue() && Minecraft.getMinecraft().player.getHeldItemOffhand().getItem() == Item.getItemById(449)) {
            return;
        }
        if (Minecraft.getMinecraft().player.getHealth() <= this.health.getValue()) {
            Minecraft.getMinecraft().player.connection.onDisconnect((ITextComponent)new TextComponentString("Health was " + Minecraft.getMinecraft().player.getHealth()));
            if (this.disable.getValue()) {
                this.setEnabled(false);
            }
        }
    }
    
    static {
        INSTANCE = new AutoDisconnect();
    }
}
