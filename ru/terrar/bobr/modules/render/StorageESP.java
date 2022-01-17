//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import net.minecraft.tileentity.*;
import net.minecraft.client.renderer.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class StorageESP extends Module
{
    public static final StorageESP INSTANCE;
    public final RGBSetting chestColor;
    public final RGBSetting shulkerColor;
    public final RGBSetting enderChestColor;
    public final FloatSetting colorAlpha;
    
    public StorageESP() {
        super("Storage ESP", "storageesp", Module.ModuleCategory.RENDER);
        this.chestColor = new RGBSetting("Chest Color", "chestcolor", 50, 50, 190);
        this.shulkerColor = new RGBSetting("Shulker Color", "shulkercolor", 255, 80, 240);
        this.enderChestColor = new RGBSetting("E-Chest Color", "echestcolor", 130, 0, 150);
        this.colorAlpha = new FloatSetting("Color Alpha", "coloralpha", 1.0f, 0.0f, 1.0f);
        this.chestColor.setParent("Color");
        this.shulkerColor.setParent("Color");
        this.enderChestColor.setParent("Color");
        this.addSettings(new Setting[] { this.chestColor, this.shulkerColor, this.enderChestColor, this.colorAlpha });
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
        final RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableDepth();
        for (final TileEntity entity : Minecraft.getMinecraft().world.loadedTileEntityList) {
            if (entity instanceof TileEntityChest) {
                final AxisAlignedBB bb = new AxisAlignedBB(entity.getPos()).offset(-rm.viewerPosX, -rm.viewerPosY, -rm.viewerPosZ);
                RenderGlobal.drawSelectionBoundingBox(bb, this.chestColor.getRed() / 255.0f, this.chestColor.getGreen() / 255.0f, this.chestColor.getBlue() / 255.0f, this.colorAlpha.getValue());
                RenderGlobal.renderFilledBox(bb, this.chestColor.getRed() / 255.0f, this.chestColor.getGreen() / 255.0f, this.chestColor.getBlue() / 255.0f, this.colorAlpha.getValue() / 3.0f);
            }
            else if (entity instanceof TileEntityShulkerBox) {
                final AxisAlignedBB bb = new AxisAlignedBB(entity.getPos()).offset(-rm.viewerPosX, -rm.viewerPosY, -rm.viewerPosZ);
                RenderGlobal.drawSelectionBoundingBox(bb, this.shulkerColor.getRed() / 255.0f, this.shulkerColor.getGreen() / 255.0f, this.shulkerColor.getBlue() / 255.0f, this.colorAlpha.getValue());
                RenderGlobal.renderFilledBox(bb, this.shulkerColor.getRed() / 255.0f, this.shulkerColor.getGreen() / 255.0f, this.shulkerColor.getBlue() / 255.0f, this.colorAlpha.getValue() / 3.0f);
            }
            else {
                if (!(entity instanceof TileEntityEnderChest)) {
                    continue;
                }
                final AxisAlignedBB bb = new AxisAlignedBB(entity.getPos()).offset(-rm.viewerPosX, -rm.viewerPosY, -rm.viewerPosZ);
                RenderGlobal.drawSelectionBoundingBox(bb, this.enderChestColor.getRed() / 255.0f, this.enderChestColor.getGreen() / 255.0f, this.enderChestColor.getBlue() / 255.0f, this.colorAlpha.getValue());
                RenderGlobal.renderFilledBox(bb, this.enderChestColor.getRed() / 255.0f, this.enderChestColor.getGreen() / 255.0f, this.enderChestColor.getBlue() / 255.0f, this.colorAlpha.getValue() / 3.0f);
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }
    
    static {
        INSTANCE = new StorageESP();
    }
}
