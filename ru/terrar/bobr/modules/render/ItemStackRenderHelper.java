//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import net.minecraft.client.*;
import net.minecraft.nbt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class ItemStackRenderHelper
{
    public static void drawitemStackEnchants(final ItemStack itemStack, final int n, final int n2) {
        final NBTTagList nBTTagList = itemStack.getEnchantmentTagList();
        if (nBTTagList != null) {
            int n3 = 0;
            for (int i = 0; i < nBTTagList.tagCount(); ++i) {
                final short s = nBTTagList.getCompoundTagAt(i).getShort("id");
                final short s2 = nBTTagList.getCompoundTagAt(i).getShort("lvl");
                final Enchantment enchantment = Enchantment.getEnchantmentByID((int)s);
                if (enchantment != null) {
                    final Enchantment enchantment2 = enchantment;
                    final String string = enchantment2.getTranslatedName((int)s2).substring(0, 2).toLowerCase();
                    final String[] arrstring = { "Efficiency", "Unbreaking", "Sharpness", "FireAspect", "" };
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(String.valueOf(string))).append("§b").append(s2)), (float)n, (float)(n2 + n3), -5592406);
                    n3 += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
                    if (i > 4) {
                        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("§f+ others", (float)n, (float)(n2 + n3), -5592406);
                        break;
                    }
                }
            }
        }
    }
    
    public static final void finish3DOGLConstants() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
    }
    
    public static void renderItemStack(final ItemStack itemStack, final int n, final int n2) {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(itemStack, n, n2);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, itemStack, n, n2);
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        drawitemStackEnchants(itemStack, n * 2, n2 * 2);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.enableLighting();
        GL11.glPopMatrix();
    }
    
    public static final void start3DOGLConstants() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
    }
}
