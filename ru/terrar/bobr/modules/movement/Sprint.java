//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.movement;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Sprint extends Module
{
    public static final Sprint INSTANCE;
    
    public Sprint() {
        super("Sprint", "sprint", Module.ModuleCategory.MOVEMENT);
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
    public void onLivingEvent(final LivingEvent.LivingUpdateEvent event) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.moveForward > 0.0f && !Minecraft.getMinecraft().player.collidedHorizontally && !Minecraft.getMinecraft().player.isSprinting() && !Minecraft.getMinecraft().player.isSneaking()) {
            Minecraft.getMinecraft().player.setSprinting(true);
        }
    }
    
    static {
        INSTANCE = new Sprint();
    }
}
