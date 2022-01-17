//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;

public class Coords extends Module
{
    public static final Coords INSTANCE;
    private final Minecraft mc;
    private final FontRenderer fr;
    
    public Coords() {
        super("Coords", "coords", ModuleCategory.HUD);
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
    }
    
    public void drawCoords(final ScaledResolution sr) {
        final Vec3d pos = new Vec3d(Math.round(this.mc.player.posX * 100.0) / 100.0, Math.round(this.mc.player.posY * 100.0) / 100.0, Math.round(this.mc.player.posZ * 100.0) / 100.0);
        final String coords = TextFormatting.GRAY + "XYZ: " + TextFormatting.RESET + pos.x + ", " + pos.y + ", " + pos.z;
        this.fr.drawStringWithShadow(coords, 4.0f, (float)(sr.getScaledHeight() - this.fr.FONT_HEIGHT - 4), 16777215);
        if (this.mc.player.dimension == 0) {
            final Vec3d netherPos = new Vec3d(Math.round(this.mc.player.posX * 12.5) / 100.0, Math.round(this.mc.player.posY * 100.0) / 100.0, Math.round(this.mc.player.posZ * 12.5) / 100.0);
            final String nether = TextFormatting.GRAY + "Nether: " + TextFormatting.RESET + netherPos.x + ", " + netherPos.y + ", " + netherPos.z;
            this.fr.drawStringWithShadow(nether, 4.0f, (float)(sr.getScaledHeight() - 2 * this.fr.FONT_HEIGHT - 4), 16777215);
        }
        else if (this.mc.player.dimension == -1) {
            final Vec3d OWPos = new Vec3d(Math.round(this.mc.player.posX * 800.0) / 100.0, Math.round(this.mc.player.posY * 100.0) / 100.0, Math.round(this.mc.player.posZ * 800.0) / 100.0);
            final String overworld = TextFormatting.GRAY + "Overworld: " + TextFormatting.RESET + OWPos.x + ", " + OWPos.y + ", " + OWPos.z;
            this.fr.drawStringWithShadow(overworld, 4.0f, (float)(sr.getScaledHeight() - 2 * this.fr.FONT_HEIGHT - 4), 16777215);
        }
    }
    
    static {
        INSTANCE = new Coords();
    }
}
