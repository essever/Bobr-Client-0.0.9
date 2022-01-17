//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import ru.terrar.bobr.modules.combat.*;
import ru.terrar.bobr.managers.*;
import java.util.*;

public class PlayerRadar extends Module
{
    public static final PlayerRadar INSTANCE;
    public Minecraft mc;
    private Entity target;
    private String hef;
    private Entity focusTarget;
    private boolean checks;
    private boolean old;
    private String enter;
    private int x;
    private int y;
    private final FontRenderer fr;
    public final FloatSetting reach;
    
    public PlayerRadar() {
        super("PlayerRadar", "PlayerRadar", ModuleCategory.HUD);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.reach = new FloatSetting("Range", "range", 100.0f, 0.0f, 300.0f);
        this.setEnabled(false);
        this.addSettings(this.reach);
    }
    
    public boolean isTarget(final Entity entity) {
        return entity != Minecraft.getMinecraft().player && entity != Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().player.getDistanceSq(entity) <= Math.pow(this.reach.getValue(), 2.0) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public void drawradar() {
        this.target = null;
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        int n = 0;
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().player) {
                String s = entity.getName();
                int color = Color.WHITE.getRGB();
                if (AntiBot.isBot(entity.getName())) {
                    color = Color.RED.getRGB();
                }
                else if (FriendManager.isFriend(entity.getName())) {
                    color = Color.GREEN.getRGB();
                }
                s = s + " [" + ((EntityPlayer)entity).getHealth() + "/" + ((EntityPlayer)entity).getMaxHealth() + "] [" + (int)Minecraft.getMinecraft().player.getDistance(entity) + ']';
                this.fr.drawStringWithShadow(s, 0.0f, (float)(30 + n * this.fr.FONT_HEIGHT), color);
                ++n;
            }
        }
    }
    
    static {
        INSTANCE = new PlayerRadar();
    }
}
