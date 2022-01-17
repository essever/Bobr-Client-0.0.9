//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class ViewModel extends Module
{
    public static final ViewModel INSTANCE;
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
    public final FloatSetting armPitch;
    public final FloatSetting armYaw;
    
    public ViewModel() {
        super("ViewModel", "viewmodel", Module.ModuleCategory.RENDER);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.armPitch = new FloatSetting("armPitch", "armPitch", 2.0f, 0.0f, 10.0f);
        this.armYaw = new FloatSetting("armYaw", "armYaw", 2.0f, 0.0f, 10.0f);
        this.addSettings(new Setting[] { this.armPitch, this.armYaw });
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        final ItemRenderer itemRenderer = this.mc.entityRenderer.itemRenderer;
        this.mc.player.renderArmPitch = this.armPitch.getValue();
        this.mc.player.renderArmYaw = this.armYaw.getValue();
    }
    
    static {
        INSTANCE = new ViewModel();
    }
}
