//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.managers.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Glowing extends Module
{
    public static final Glowing INSTANCE;
    public final BooleanSetting targetPlayer;
    public final BooleanSetting targetHostile;
    public final BooleanSetting targetPassive;
    public final BooleanSetting rustme;
    
    public Glowing() {
        super("Glowing", "Glowing", Module.ModuleCategory.RENDER);
        this.targetPlayer = new BooleanSetting("Players", "players", true);
        this.targetHostile = new BooleanSetting("Monsters", "monsters", true);
        this.targetPassive = new BooleanSetting("Passive", "passive", true);
        this.rustme = new BooleanSetting("rustme", "rustme", true);
        this.targetPlayer.setParent("Target");
        this.targetHostile.setParent("Target");
        this.addSettings(new Setting[] { this.targetPlayer, this.targetHostile, this.targetPassive, this.rustme });
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
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (this.targetPlayer.getValue() && entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
                if (FriendManager.isFriend(entity.getName())) {
                    if (entity.isGlowing()) {
                        continue;
                    }
                    entity.setGlowing(true);
                }
                else {
                    if (entity.isGlowing()) {
                        continue;
                    }
                    entity.setGlowing(true);
                }
            }
            else if (this.targetHostile.getValue() && entity.isCreatureType(EnumCreatureType.MONSTER, false)) {
                if (entity.isGlowing()) {
                    continue;
                }
                entity.setGlowing(true);
            }
            else if (this.targetPassive.getValue() && (entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity.isCreatureType(EnumCreatureType.WATER_CREATURE, false) || entity.isCreatureType(EnumCreatureType.CREATURE, false))) {
                if (entity.isGlowing()) {
                    continue;
                }
                entity.setGlowing(true);
            }
            else {
                if (!this.rustme.getValue() || !(entity instanceof EntityArmorStand) || entity.isGlowing()) {
                    continue;
                }
                entity.setGlowing(true);
            }
        }
    }
    
    static {
        INSTANCE = new Glowing();
    }
}
