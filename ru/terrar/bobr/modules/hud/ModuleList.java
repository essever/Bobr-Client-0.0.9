//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.hud;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import net.minecraft.client.*;
import ru.terrar.bobr.settings.*;
import net.minecraft.client.gui.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import java.util.*;

public class ModuleList extends Module
{
    public static final ModuleList INSTANCE;
    private List<Module> modulesSorted;
    private final FontRenderer fr;
    public final EnumSetting color;
    public final RGBSetting staticColor;
    public final FloatSetting speed;
    public final BooleanSetting glicheffect;
    
    public ModuleList() {
        super("Module List", "modulelist", ModuleCategory.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.color = new EnumSetting("Li\u0420°st Color", "color", Color.values(), Color.STATIC);
        this.staticColor = new RGBSetting("Static Color", "static", 255, 255, 255);
        this.speed = new FloatSetting("GlichSpeed", "glichspeed", 15.0f, 2.0f, 50.0f);
        this.glicheffect = new BooleanSetting("GlichEffect", "glicheffect", true);
        this.addSettings(this.color, this.staticColor, this.speed, this.glicheffect);
    }
    
    public static int generateRandomIntIntRange(final int min, final int max) {
        final Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    public void drawList(final ScaledResolution sr) {
        int i = 0;
        for (final Module module : this.modulesSorted) {
            if (module.isEnabled() && module.drawOnHud.getValue()) {
                int hexColor;
                if (this.color.getCurrentValue() == Color.RAINBOW) {
                    final int[] rainbow = ColorUtil.getRainbow(5, 0.1f * i);
                    hexColor = ColorUtil.RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
                }
                else if (this.color.getCurrentValue() == Color.CATEGORY) {
                    hexColor = module.getModuleCategory().getColor();
                }
                else {
                    hexColor = ColorUtil.RGBtoHex(this.staticColor.getRed(), this.staticColor.getGreen(), this.staticColor.getBlue());
                }
                final int s = generateRandomIntIntRange(1, (int)this.speed.getValue());
                if (s == 1 && this.glicheffect.getValue()) {
                    final int m = module.getName().length();
                    final int n = generateRandomIntIntRange(0, module.getName().length() - 1);
                    String ser = module.getName().substring(0, n);
                    final int rad = generateRandomIntIntRange(1, 3);
                    if (rad == 1) {
                        ser += "&";
                    }
                    else if (rad == 2) {
                        ser += "%";
                    }
                    else if (rad == 3) {
                        ser += "$";
                    }
                    ser += module.getName().substring(n + 1, m);
                    this.fr.drawStringWithShadow(ser, (float)(sr.getScaledWidth() - this.fr.getStringWidth(module.getName()) - 4), (float)(4 + i * this.fr.FONT_HEIGHT), hexColor);
                }
                else {
                    this.fr.drawStringWithShadow(module.getName(), (float)(sr.getScaledWidth() - this.fr.getStringWidth(module.getName()) - 4), (float)(4 + i * this.fr.FONT_HEIGHT), hexColor);
                }
                ++i;
            }
        }
    }
    
    public void sortList() {
        (this.modulesSorted = new ArrayList<Module>(bobr.getGate().moduleManager.MODULE_LIST)).sort((module1, module2) -> {
            if (this.fr.getStringWidth(module1.getName()) < this.fr.getStringWidth(module2.getName())) {
                return 1;
            }
            else if (this.fr.getStringWidth(module1.getName()) > this.fr.getStringWidth(module2.getName())) {
                return -1;
            }
            else {
                return 0;
            }
        });
    }
    
    static {
        INSTANCE = new ModuleList();
    }
    
    public enum Color
    {
        STATIC("Static"), 
        CATEGORY("Category"), 
        RAINBOW("Rainbow");
        
        private final String name;
        
        private Color(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
