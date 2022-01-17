//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.terrar.bobr.events.*;
import net.minecraft.block.*;
import net.minecraft.init.*;

public class XRay extends Module
{
    public static final XRay INSTANCE;
    private float lastGamma;
    
    public XRay() {
        super("XRay", "xray", Module.ModuleCategory.RENDER);
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        this.lastGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 10000.0f;
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        if (!FullBright.INSTANCE.isEnabled()) {
            Minecraft.getMinecraft().gameSettings.gammaSetting = this.lastGamma;
        }
    }
    
    @SubscribeEvent
    public void onRenderModel(final RenderModelEvent event) {
        if (!this.isXrayBlock(event.getState().getBlock())) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onSetOpaqueCube(final SetOpaqueCubeEvent event) {
        event.setCanceled(true);
    }
    
    @SubscribeEvent
    public void onSideRendered(final ShouldSideBeRenderedEvent event) {
        event.setShouldBeRendered(this.isXrayBlock(event.getBlockState().getBlock()));
    }
    
    @SubscribeEvent
    public void onGetAmbientOcclusion(final GetAmbientOcclusionLightValueEvent event) {
        event.setLightValue(1.0f);
    }
    
    public boolean isXrayBlock(final Block block) {
        return block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE || block == Blocks.GOLD_BLOCK || block == Blocks.IRON_ORE || block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE || block == Blocks.LAPIS_ORE || block == Blocks.COAL_ORE;
    }
    
    static {
        INSTANCE = new XRay();
    }
}
