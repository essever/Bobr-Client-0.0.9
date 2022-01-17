//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import ru.terrar.bobr.modules.combat.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.util.*;
import org.lwjgl.opengl.*;
import ru.terrar.bobr.wint.*;
import ru.terrar.bobr.managers.*;
import net.minecraft.client.network.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;
import java.util.*;

public class TargetHud extends Module
{
    public static final TargetHud INSTANCE;
    public Minecraft mc;
    private Entity target;
    private String hef;
    private Entity focusTarget;
    private boolean checks;
    private boolean old;
    private String enter;
    private int x;
    private int y;
    private static RenderItem kappita;
    private static final RenderItem itemRender;
    private final FontRenderer fr;
    private final ResourceLocation logo;
    public final BooleanSetting targetPassive;
    public final FloatSetting posX;
    public final FloatSetting posY;
    
    public TargetHud() {
        super("TargetHud", "targethud", ModuleCategory.HUD);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.logo = new ResourceLocation("bobr", "textures/bobr.png");
        this.targetPassive = new BooleanSetting("TargetHud", "targethud", true);
        this.posX = new FloatSetting("PosX", "posX", 0.0f, 0.0f, 250.0f);
        this.posY = new FloatSetting("PosY", "posY", 0.0f, 0.0f, 250.0f);
        this.setEnabled(false);
        this.addSettings(this.posX, this.posY);
    }
    
    public void drawitem(final ItemStack item, final int x, final int y) {
        GlStateManager.enableDepth();
        TargetHud.itemRender.zLevel = 200.0f;
        TargetHud.itemRender.renderItemAndEffectIntoGUI(item, x, y);
        TargetHud.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, item, x, y, "");
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        this.fr.drawString("" + item.getCount(), x + 13, y + 10, Color.white.getRGB());
    }
    
    public void drawtartet() {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        try {
            GlStateManager.enableTexture2D();
            Entity target = Minecraft.getMinecraft().objectMouseOver.entityHit;
            if (AimPistot.INSTANCE.isEnabled()) {
                target = AimPistot.INSTANCE.target;
            }
            else if (aimAssist.INSTANCE.isEnabled()) {
                target = aimAssist.INSTANCE.target;
            }
            else if (TriggerBot.INSTANCE.isEnabled()) {
                target = TriggerBot.INSTANCE.entity;
            }
            else if (KillAura.INSTANCE.isEnabled()) {
                target = KillAura.INSTANCE.target;
            }
            if (target != null && target instanceof EntityPlayer) {
                final int width = 100;
                final ScaledResolution sr = new ScaledResolution(this.mc);
                final int[] rainbow = ColorUtil.getRainbow(5, 1.0f);
                final int hexColor = ColorUtil.RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
                GL11.glPushMatrix();
                GL11.glTranslated((double)(width / 100 + this.posX.getValue()), (double)(sr.getScaledHeight() / 100 + this.posY.getValue()), 0.0);
                GL11.glTranslated((double)(sr.getScaledWidth() / 2 + 10 + this.posX.getValue()), (double)(sr.getScaledHeight() / 2 + this.posY.getValue()), (double)(sr.getScaledWidth() / 2 + 10));
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 110.0f, 45.0f, new Color(35, 35, 40, 230).getRGB());
                if (FriendManager.isFriend(target.getName())) {
                    this.fr.drawString(target.getName(), 34, 2, Color.GREEN.getRGB());
                }
                else {
                    this.fr.drawString(target.getName(), 34, 2, 16777215);
                }
                final float one_HP = 110.0f / ((EntityPlayer)target).getMaxHealth();
                this.x = 0;
                this.y = 46;
                ItemStack item = ((EntityPlayer)target).getHeldItemMainhand();
                this.drawitem(item, this.x, this.y);
                item = ((EntityPlayer)target).getHeldItemOffhand();
                this.drawitem(item, this.x + 18, this.y);
                int i = 0;
                for (final ItemStack is : target.getArmorInventoryList()) {
                    if (is.isEmpty()) {
                        continue;
                    }
                    if (i == 3) {
                        this.drawitem(is, this.x + 36, this.y);
                    }
                    if (i == 2) {
                        this.drawitem(is, this.x + 54, this.y);
                    }
                    if (i == 1) {
                        this.drawitem(is, this.x + 72, this.y);
                    }
                    if (i == 0) {
                        this.drawitem(is, this.x + 90, this.y);
                    }
                    ++i;
                }
                try {
                    this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(target.getUniqueID()).getLocationSkin());
                    Gui.drawScaledCustomSizeModalRect(0, 0, 8.0f, 8.0f, 8, 8, 32, 32, 64.0f, 64.0f);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.fr.drawString(TextFormatting.BLUE + "Dist: " + TextFormatting.RED + (int)Minecraft.getMinecraft().player.getDistance(target), 34, 14, 16777215);
                if (((EntityPlayer)target).hurtTime > 0) {
                    RenderUtil.drawRect(0.0, 32.0, one_HP * ((EntityPlayer)target).getHealth(), 45.0, Color.RED.getRGB());
                }
                else {
                    RenderUtil.drawRect(0.0, 32.0, one_HP * ((EntityPlayer)target).getHealth(), 45.0, Color.GREEN.getRGB());
                }
                if (AimPistot.INSTANCE.isEnabled()) {
                    if (AimPistot.INSTANCE.can_attack) {
                        RenderUtil.drawSmoothRect(95.0f, 0.0f, 110.0f, 15.0f, Color.red.getRGB());
                    }
                    else {
                        RenderUtil.drawSmoothRect(95.0f, 0.0f, 110.0f, 15.0f, Color.GREEN.getRGB());
                    }
                }
                this.fr.drawString((int)((EntityPlayer)target).getHealth() + " - " + ((EntityPlayer)target).getMaxHealth(), 5, 34, hexColor);
                GlStateManager.enableDepth();
                GlStateManager.disableLighting();
                GL11.glPopMatrix();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    static {
        INSTANCE = new TargetHud();
        itemRender = Minecraft.getMinecraft().getRenderItem();
    }
}
