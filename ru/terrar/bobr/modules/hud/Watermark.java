//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import ru.terrar.bobr.util.*;
import net.minecraft.client.renderer.*;
import ru.terrar.bobr.*;
import java.io.*;

public class Watermark extends Module
{
    public static final Watermark INSTANCE;
    public Minecraft mc;
    private Entity target;
    private String hef;
    private Entity focusTarget;
    private boolean checks;
    private boolean old;
    private String enter;
    private final FontRenderer fr;
    private final ResourceLocation logo;
    
    public Watermark() {
        super("Watermark", "watermark", ModuleCategory.HUD);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.logo = new ResourceLocation("bobr", "textures/bobr.png");
    }
    
    public void drawWatermark() throws IOException {
        final int[] rainbow = ColorUtil.getRainbow(5, 0.0f);
        final int rainbowHex = ColorUtil.RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
        GlStateManager.pushMatrix();
        GlStateManager.translate(5.0f, 5.0f, 0.0f);
        GlStateManager.scale(1.5f, 1.5f, 1.0f);
        final int nameEnd = this.fr.drawStringWithShadow("Bobr Client", 0.0f, 0.0f, rainbowHex);
        GlStateManager.scale(0.6666667f, 0.6666667f, 1.0f);
        GlStateManager.translate(1.5 * nameEnd, 4.0, 0.0);
        if (bobr.getGate().old) {
            this.fr.drawStringWithShadow("0.0.9 - old", 0.0f, 0.0f, 9474192);
        }
        else {
            this.fr.drawStringWithShadow("0.0.9", 0.0f, 0.0f, 9474192);
        }
        GlStateManager.popMatrix();
    }
    
    static {
        INSTANCE = new Watermark();
    }
}
