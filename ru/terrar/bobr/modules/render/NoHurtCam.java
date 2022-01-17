//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class NoHurtCam extends Module
{
    public static final NoHurtCam INSTANCE;
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
    
    public NoHurtCam() {
        super("NoHurtCam", "nohurtcam", Module.ModuleCategory.RENDER);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.addSettings(new Setting[0]);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        this.mc.player.hurtTime = 0;
        this.mc.player.hurtResistantTime = 0;
        this.mc.player.maxHurtResistantTime = 0;
        this.mc.player.maxHurtTime = 0;
    }
    
    static {
        INSTANCE = new NoHurtCam();
    }
}
