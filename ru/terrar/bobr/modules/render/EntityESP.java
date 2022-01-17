//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.managers.*;
import ru.terrar.bobr.util.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EntityESP extends Module
{
    public static final EntityESP INSTANCE;
    public final BooleanSetting targetPlayer;
    public final BooleanSetting targetHostile;
    public final BooleanSetting targetPassive;
    public final RGBSetting playerColor;
    public final RGBSetting friendColor;
    public final RGBSetting hostileColor;
    public final RGBSetting passiveColor;
    public final FloatSetting colorAlpha;
    
    public EntityESP() {
        super("Entity ESP", "entityesp", Module.ModuleCategory.RENDER);
        this.targetPlayer = new BooleanSetting("Players", "players", true);
        this.targetHostile = new BooleanSetting("Monsters", "monsters", true);
        this.targetPassive = new BooleanSetting("Passive", "passive", true);
        this.playerColor = new RGBSetting("Player Color", "playercolor", 255, 0, 0);
        this.friendColor = new RGBSetting("Friend Color", "friendcolor", 0, 255, 255);
        this.hostileColor = new RGBSetting("Monster Color", "hostilecolor", 255, 255, 0);
        this.passiveColor = new RGBSetting("Passive Color", "passivecolor", 0, 255, 0);
        this.colorAlpha = new FloatSetting("Color Alpha", "coloralpha", 1.0f, 0.0f, 1.0f);
        this.targetPlayer.setParent("Target");
        this.targetHostile.setParent("Target");
        this.playerColor.setParent("Color");
        this.hostileColor.setParent("Color");
        this.addSettings(new Setting[] { this.targetPlayer, this.targetHostile, this.targetPassive, this.playerColor, this.friendColor, this.hostileColor, this.passiveColor, this.colorAlpha });
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
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableDepth();
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (this.targetPlayer.getValue() && entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
                if (FriendManager.isFriend(entity.getName())) {
                    RenderUtil.renderEntityBoundingBox(entity, this.friendColor, this.colorAlpha.getValue());
                    RenderUtil.renderEntityFilledBoundingBox(entity, this.friendColor, this.colorAlpha.getValue() / 3.0f);
                }
                else {
                    RenderUtil.renderEntityBoundingBox(entity, this.playerColor, this.colorAlpha.getValue());
                    RenderUtil.renderEntityFilledBoundingBox(entity, this.playerColor, this.colorAlpha.getValue() / 3.0f);
                }
            }
            else if (this.targetHostile.getValue() && entity.isCreatureType(EnumCreatureType.MONSTER, false)) {
                RenderUtil.renderEntityBoundingBox(entity, this.hostileColor, this.colorAlpha.getValue());
                RenderUtil.renderEntityFilledBoundingBox(entity, this.hostileColor, this.colorAlpha.getValue() / 3.0f);
            }
            else {
                if (!this.targetPassive.getValue() || (!entity.isCreatureType(EnumCreatureType.AMBIENT, false) && !entity.isCreatureType(EnumCreatureType.WATER_CREATURE, false) && !entity.isCreatureType(EnumCreatureType.CREATURE, false))) {
                    continue;
                }
                RenderUtil.renderEntityBoundingBox(entity, this.passiveColor, this.colorAlpha.getValue());
                RenderUtil.renderEntityFilledBoundingBox(entity, this.passiveColor, this.colorAlpha.getValue() / 3.0f);
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }
    
    static {
        INSTANCE = new EntityESP();
    }
}
