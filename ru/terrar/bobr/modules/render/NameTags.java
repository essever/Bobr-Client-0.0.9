//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.client.resources.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.entity.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.network.*;
import java.util.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.managers.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NameTags extends Module
{
    public static final NameTags INSTANCE;
    private Minecraft mc;
    public final EnumSetting Healthmode;
    public final FloatSetting Scale;
    public final BooleanSetting armor;
    public final BooleanSetting Player;
    public final BooleanSetting Mob;
    public final BooleanSetting Hostile;
    public final BooleanSetting Ping;
    public final BooleanSetting Gamemode;
    private static RenderItem kappita;
    private static final RenderItem itemRender;
    
    public NameTags() {
        super("NameTags", "NameTags", Module.ModuleCategory.RENDER);
        this.mc = Minecraft.getMinecraft();
        this.Healthmode = new EnumSetting("Healthmode", "Healthmode", Modes.values(), Modes.Bar);
        this.Scale = new FloatSetting("Scale", "Scale", 2.0f, 0.0f, 4.0f);
        this.armor = new BooleanSetting("armor", "armor", true);
        this.Player = new BooleanSetting("Player", "Player", true);
        this.Mob = new BooleanSetting("Mob", "Mob", false);
        this.Hostile = new BooleanSetting("Hostile", "Hostile", false);
        this.Ping = new BooleanSetting("Ping", "Ping", true);
        this.Gamemode = new BooleanSetting("Gamemode", "Gamemode", false);
        this.addSettings(new Setting[] { this.Scale, this.armor, this.Player, this.Mob, this.Hostile, this.Ping, this.Gamemode, this.Healthmode });
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    private void Processtext(final String s, final int stringWidth, final Integer getcolor, final Entity entity, final double rofl, final double scale) {
        try {
            glSetup(entity.posX, entity.posY + rofl, entity.posZ);
            GlStateManager.scale(-0.025 * scale, -0.025 * scale, 0.025 * scale);
            GlStateManager.disableTexture2D();
            final Tessellator tessellator = Tessellator.getInstance();
            final BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos((double)(-stringWidth - 1), -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            bufferbuilder.pos((double)(-stringWidth - 1), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            bufferbuilder.pos((double)(stringWidth + 1), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            bufferbuilder.pos((double)(stringWidth + 1), -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            this.mc.fontRenderer.drawStringWithShadow(s, (float)(-stringWidth), 0.0f, (int)getcolor);
            glCleanup();
        }
        catch (Exception ex) {}
    }
    
    public static void drawItem(final double x, final double y, final double z, final double offX, final double offY, final double scale, final ItemStack item) {
        glSetup(x, y, z);
        GlStateManager.scale(0.4 * scale, 0.4 * scale, 0.0);
        GlStateManager.translate(offX, offY, 0.0);
        final ItemRenderer renderer = new ItemRenderer(Minecraft.getMinecraft());
        renderer.renderItemSide((EntityLivingBase)Minecraft.getMinecraft().player, item, ItemCameraTransforms.TransformType.NONE, false);
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.scale(-0.05f, -0.05f, 0.0f);
        try {
            if (item.getCount() > 0) {
                final int w = Minecraft.getMinecraft().fontRenderer.getStringWidth("x" + item.getCount()) / 2;
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("x" + item.getCount(), (float)(7 - w), 5.0f, 16777215);
            }
            GlStateManager.scale(0.85f, 0.85f, 0.85f);
            int c = 0;
            for (final Map.Entry<Enchantment, Integer> m : EnchantmentHelper.getEnchantments(item).entrySet()) {
                final int w2 = Minecraft.getMinecraft().fontRenderer.getStringWidth(I18n.format(m.getKey().getName().substring(0, 2), new Object[0]) + m.getValue() / 2);
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(I18n.format(m.getKey().getName(), new Object[0]).substring(0, 2) + m.getValue(), (float)(-4 - w2 + 3), (float)(c * 10 - 1), (m.getKey() == Enchantments.VANISHING_CURSE || m.getKey() == Enchantments.BINDING_CURSE) ? 16732240 : 16756960);
                --c;
            }
            GlStateManager.scale(0.6f, 0.6f, 0.6f);
            final String dur = item.getMaxDamage() - item.getItemDamage() + "";
            final int color = MathHelper.hsvToRGB((item.getMaxDamage() - item.getItemDamage()) / (float)item.getMaxDamage() / 3.0f, 1.0f, 1.0f);
            if (item.isItemStackDamageable()) {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(dur, (float)(-8 - dur.length() * 3), 15.0f, new Color(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF).getRGB());
            }
        }
        catch (Exception ex) {}
        glCleanup();
    }
    
    public static void glSetup(final double x, final double y, final double z) {
        GlStateManager.pushMatrix();
        final RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        GlStateManager.translate(x - Minecraft.getMinecraft().getRenderManager().viewerPosX, y - Minecraft.getMinecraft().getRenderManager().viewerPosY, z - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((Minecraft.getMinecraft().gameSettings.thirdPersonView == 2) ? (-renderManager.playerViewX) : renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
        GlStateManager.disableLighting();
        GL11.glDisable(2929);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }
    
    public static void glCleanup() {
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2929);
        GlStateManager.enableDepth();
        GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
        GlStateManager.popMatrix();
    }
    
    private void Runnametag(final EntityLivingBase e) {
        final double scale = Math.max(this.Scale.getValue() * (this.mc.player.getDistance((Entity)e) / 20.0f), 2.0f);
        final StringBuilder healthBuilder = new StringBuilder();
        for (int i = 0; i < e.getHealth(); ++i) {
            healthBuilder.append(ChatFormatting.GREEN + "|");
        }
        final StringBuilder health = new StringBuilder(healthBuilder.toString());
        for (int j = 0; j < MathHelper.clamp(e.getAbsorptionAmount(), 0.0f, e.getMaxHealth() - e.getHealth()); ++j) {
            health.append(ChatFormatting.RED + "|");
        }
        for (int j = 0; j < e.getMaxHealth() - (e.getHealth() + e.getAbsorptionAmount()); ++j) {
            health.append(ChatFormatting.YELLOW + "|");
        }
        if (e.getAbsorptionAmount() - (e.getMaxHealth() - e.getHealth()) > 0.0f) {
            health.append(ChatFormatting.BLUE + " +").append((int)(e.getAbsorptionAmount() - (e.getMaxHealth() - e.getHealth())));
        }
        String tag = "";
        if (this.Ping.getValue() && e instanceof EntityPlayer) {
            try {
                tag = tag + " " + (int)MathHelper.clamp((float)Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(e.getUniqueID()).getResponseTime(), 1.0f, 300.0f) + " P ";
            }
            catch (NullPointerException ex) {}
        }
        if (this.Gamemode.getValue() && e instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)e;
            if (entityPlayer.isCreative()) {
                tag += "[C]";
            }
            if (entityPlayer.isSpectator()) {
                tag += " [I]";
            }
            if (!entityPlayer.isAllowEdit() && !entityPlayer.isSpectator()) {
                tag += " [A]";
            }
            if (!entityPlayer.isCreative() && !entityPlayer.isSpectator() && entityPlayer.isAllowEdit()) {
                tag += " [S]";
            }
        }
        final int[] rainbow = ColorUtil.getRainbow(5, 1.0f);
        final int hexColor = ColorUtil.RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
        if (FriendManager.isFriend(e.getName())) {
            if (this.Healthmode.getCurrentValue() == Modes.Number) {
                this.Processtext(e.getName() + " [" + (int)(e.getHealth() + e.getAbsorptionAmount()) + "/" + (int)e.getMaxHealth() + "]" + tag, this.mc.fontRenderer.getStringWidth(e.getName() + tag + "[]") / 2, hexColor, (Entity)e, e.height + 0.5 * scale, scale);
            }
            else {
                this.Processtext(e.getName() + tag, this.mc.fontRenderer.getStringWidth(e.getName() + tag) / 2, hexColor, (Entity)e, e.height + 0.5 * scale, scale);
                this.Processtext(health.toString(), this.mc.fontRenderer.getStringWidth(health.toString()) / 2, hexColor, (Entity)e, e.height + 0.75 * scale, scale);
            }
        }
        else if (this.Healthmode.getCurrentValue() == Modes.Number) {
            this.Processtext(e.getName() + " [" + (int)(e.getHealth() + e.getAbsorptionAmount()) + "/" + (int)e.getMaxHealth() + "]" + tag, this.mc.fontRenderer.getStringWidth(e.getName() + tag + "[]") / 2, Color.white.getRGB(), (Entity)e, e.height + 0.5 * scale, scale);
        }
        else {
            this.Processtext(e.getName() + tag, this.mc.fontRenderer.getStringWidth(e.getName() + tag) / 2, hexColor, (Entity)e, e.height + 0.5 * scale, scale);
            this.Processtext(health.toString(), this.mc.fontRenderer.getStringWidth(health.toString()) / 2, Color.white.getRGB(), (Entity)e, e.height + 0.75 * scale, scale);
        }
        if (this.armor.getValue()) {
            double c = 0.0;
            double higher;
            if (this.Healthmode.getCurrentValue() == Modes.Number) {
                higher = 0.0;
            }
            else {
                higher = 0.25;
            }
            drawItem(e.posX, e.posY + e.height + (0.75 + higher) * scale, e.posZ, -2.5, 0.0, scale, e.getHeldItemMainhand());
            drawItem(e.posX, e.posY + e.height + (0.75 + higher) * scale, e.posZ, 2.5, 0.0, scale, e.getHeldItemOffhand());
            for (final ItemStack k : e.getArmorInventoryList()) {
                drawItem(e.posX, e.posY + e.height + (0.75 + higher) * scale, e.posZ, c + 1.5, 0.0, scale, k);
                --c;
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final Entity object : this.mc.world.loadedEntityList) {
            if ((this.Player.getValue() && object instanceof EntityPlayer) || (this.Mob.getValue() && object instanceof IAnimals) || (this.Hostile.getValue() && object instanceof IMob)) {
                assert object instanceof EntityLivingBase;
                if (object == this.mc.player) {
                    continue;
                }
                this.Runnametag((EntityLivingBase)object);
            }
        }
    }
    
    static {
        INSTANCE = new NameTags();
        itemRender = Minecraft.getMinecraft().getRenderItem();
    }
    
    public enum Modes
    {
        Number("Number"), 
        Bar("Bar");
        
        private final String name;
        
        private Modes(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
