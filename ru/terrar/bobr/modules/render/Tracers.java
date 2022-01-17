//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import ru.terrar.bobr.util.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class Tracers extends Module
{
    public static final Tracers INSTANCE;
    public final BooleanSetting targetPlayer;
    public final BooleanSetting targetHostile;
    public final RGBSetting color;
    public final FloatSetting colorAlpha;
    
    public Tracers() {
        super("Tracers", "tracers", Module.ModuleCategory.RENDER);
        this.targetPlayer = new BooleanSetting("Players", "players", true);
        this.targetHostile = new BooleanSetting("Monsters", "monsters", false);
        this.color = new RGBSetting("Tracer Color", "color", 255, 255, 255);
        this.colorAlpha = new FloatSetting("Color Alpha", "coloralpha", 1.0f, 0.0f, 1.0f);
        this.targetPlayer.setParent("Entities to Target");
        this.targetHostile.setParent("Entities to Target");
        this.addSettings(new Setting[] { this.targetPlayer, this.targetHostile, this.color, this.colorAlpha });
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
    public void onRender(final RenderWorldLastEvent event) {
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
            final RenderManager rm = Minecraft.getMinecraft().getRenderManager();
            final Vec3d playerVector = ActiveRenderInfo.getCameraPosition();
            GlStateManager.pushMatrix();
            GlStateManager.clear(256);
            GlStateManager.disableTexture2D();
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.disableDepth();
            GlStateManager.glLineWidth(0.5f);
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (this.isTarget(entity) && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
                    final Tessellator tessellator = Tessellator.getInstance();
                    final BufferBuilder buffer = tessellator.getBuffer();
                    final Vec3d entityPos = MathUtil.interpolateEntity(entity);
                    buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
                    buffer.pos(playerVector.x, playerVector.y, playerVector.z).color(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.colorAlpha.getValue()).endVertex();
                    buffer.pos(entityPos.x - rm.viewerPosX, entityPos.y - rm.viewerPosY, entityPos.z - rm.viewerPosZ).color(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.colorAlpha.getValue()).endVertex();
                    tessellator.draw();
                }
            }
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
        }
    }
    
    public boolean isTarget(final Entity entity) {
        return (this.targetPlayer.getValue() && entity instanceof EntityPlayer) || (this.targetHostile.getValue() && entity.isCreatureType(EnumCreatureType.MONSTER, false));
    }
    
    static {
        INSTANCE = new Tracers();
    }
}
