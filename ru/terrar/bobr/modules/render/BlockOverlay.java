//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BlockOverlay extends Module
{
    public static final BlockOverlay INSTANCE;
    private float lastGamma;
    public final RGBSetting color;
    public final FloatSetting colorAlpha;
    
    public BlockOverlay() {
        super("Block Overlay", "BlockOverlay", Module.ModuleCategory.RENDER);
        this.color = new RGBSetting("Color", "color", 50, 50, 190);
        this.colorAlpha = new FloatSetting("Color Alpha", "coloralpha", 1.0f, 0.0f, 1.0f);
        this.color.setParent("Color");
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
        this.addSettings(new Setting[] { this.color, this.colorAlpha });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public static AxisAlignedBB Standardbb(final BlockPos pos) {
        final double renderPosX = pos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double renderPosY = pos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double renderPosZ = pos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        return new AxisAlignedBB(renderPosX, renderPosY, renderPosZ, renderPosX + 1.0, renderPosY + 1.0, renderPosZ + 1.0);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        if (Minecraft.getMinecraft().objectMouseOver != null) {
            final BlockPos blockPos = Minecraft.getMinecraft().objectMouseOver.getBlockPos();
            RenderGlobal.drawSelectionBoundingBox(Standardbb(blockPos), this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.colorAlpha.getValue());
            RenderGlobal.renderFilledBox(Standardbb(blockPos), this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.colorAlpha.getValue() / 3.0f);
        }
    }
    
    static {
        INSTANCE = new BlockOverlay();
    }
}
