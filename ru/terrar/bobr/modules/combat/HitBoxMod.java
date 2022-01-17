//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.entity.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.managers.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

public class HitBoxMod extends Module
{
    private transient long time;
    public static final HitBoxMod INSTANCE;
    public final FloatSetting horizontal;
    public final FloatSetting vertical;
    private Entity target;
    private Entity focusTarget;
    
    public HitBoxMod() {
        super("HitBox", "hitbox", ModuleCategory.COMBAT);
        this.horizontal = new FloatSetting("Horizontal", "horizontal", 0.5f, 0.0f, 3.0f);
        this.vertical = new FloatSetting("Vertical", "vertical", 0.0f, 0.0f, 3.0f);
        this.addSettings(this.horizontal, this.vertical);
        this.setEnabled(false);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.time = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public HitBox createHitBox(final Entity entity) {
        return new HitBox(0.6f, 1.8f);
    }
    
    public void changeEntityHitBox(final Entity entity, final float width, final float height) {
        entity.width = width;
        entity.height = height;
        final double d = width / 2.0;
        entity.setEntityBoundingBox(new AxisAlignedBB(entity.posX - d, entity.posY, entity.posZ - d, entity.posX + d, entity.posY + entity.height, d + entity.posZ));
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        this.target = null;
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity != null && entity != Minecraft.getMinecraft().player && entity instanceof EntityPlayer && !AntiBot.isBot(entity.getName()) && !FriendManager.isFriend(entity.getName())) {
                    final HitBox hitbox = this.createHitBox(entity);
                    this.changeEntityHitBox(entity, hitbox.width + this.horizontal.getValue(), hitbox.height + this.vertical.getValue());
                }
            }
        }
    }
    
    static {
        INSTANCE = new HitBoxMod();
    }
    
    private static class HitBox
    {
        public float width;
        public float height;
        
        public HitBox(final float width, final float height) {
            this.width = width;
            this.height = height;
        }
    }
}
