//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr;

import net.minecraftforge.fml.common.*;
import ru.terrar.bobr.managers.*;
import ru.terrar.bobr.handlers.*;
import java.net.*;
import java.nio.charset.*;
import java.io.*;
import ru.terrar.bobr.util.*;
import net.minecraftforge.common.*;
import java.awt.image.*;
import java.nio.*;
import com.google.common.primitives.*;
import net.minecraftforge.fml.common.event.*;
import javax.imageio.*;
import org.lwjgl.opengl.*;

@Mod(modid = "sound", name = "SoundMod", version = "0.6.5")
public class bobr
{
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public ConfigManager configManager;
    public PresetManager presetManager;
    public GuiManager guiManager;
    public HUDManager hudManager;
    public boolean old;
    public EventHandler eventHandler;
    @Mod.Instance
    private static bobr gate;
    
    public static bobr getGate() {
        return bobr.gate;
    }
    
    public static String requestURLSRC(final String BLviCHHy76v5Ch39PB3hpcX7W2qe45YaBPQyn285Dcg27) throws IOException {
        final URL urlObject = new URL(BLviCHHy76v5Ch39PB3hpcX7W2qe45YaBPQyn285Dcg27);
        final URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        return AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(urlConnection.getInputStream());
    }
    
    private static String AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(final InputStream L58C336iNBkwz86u4QV3HcDJ94i34gWv4gpzbqBC5ZCdG) throws IOException {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(L58C336iNBkwz86u4QV3HcDJ94i34gWv4gpzbqBC5ZCdG, StandardCharsets.UTF_8))) {
            final StringBuilder stringBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            final String var5;
            final String string3;
            final String string2 = string3 = (var5 = stringBuilder.toString());
            return string2;
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws IOException {
        final String check = requestURLSRC("https://gifted-northcutt-d6c937.netlify.app/");
        System.out.println(check);
        if (check.contains("enable: true")) {
            if (check.contains("later_version: 0.0.9")) {
                this.old = false;
            }
            else {
                this.old = true;
            }
            Sound.playSound("C://sound/sound.wav").join();
            this.moduleManager = new ModuleManager();
            this.commandManager = new CommandManager();
            this.configManager = new ConfigManager();
            this.presetManager = new PresetManager();
            this.guiManager = new GuiManager();
            this.hudManager = new HUDManager();
            this.eventHandler = new EventHandler();
            MinecraftForge.EVENT_BUS.register((Object)this.eventHandler);
            MinecraftForge.EVENT_BUS.register((Object)this.hudManager);
        }
    }
    
    public static ByteBuffer convertToByteBuffer(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final ByteBuffer data = ByteBuffer.allocateDirect(4 * width * height);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                final int argb = image.getRGB(x, y);
                final int r = argb >> 16 & 0xFF;
                final int g = argb >> 8 & 0xFF;
                final int b = argb & 0xFF;
                final int a = argb >> 24 & 0xFF;
                data.put(UnsignedBytes.checkedCast((long)r));
                data.put(UnsignedBytes.checkedCast((long)g));
                data.put(UnsignedBytes.checkedCast((long)b));
                data.put(UnsignedBytes.checkedCast((long)a));
            }
        }
        data.rewind();
        return data;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        try {
            final String root = "assets/ref/textures/";
            final ClassLoader classLoader = this.getClass().getClassLoader();
            final BufferedImage icon16 = ImageIO.read(classLoader.getResourceAsStream(root + "16x.png"));
            final BufferedImage icon17 = ImageIO.read(classLoader.getResourceAsStream(root + "32x.png"));
            final BufferedImage icon18 = ImageIO.read(classLoader.getResourceAsStream(root + "64x.png"));
            final BufferedImage icon19 = ImageIO.read(classLoader.getResourceAsStream(root + "128x.png"));
            Display.setIcon(new ByteBuffer[] { convertToByteBuffer(icon16), convertToByteBuffer(icon17), convertToByteBuffer(icon18), convertToByteBuffer(icon19) });
        }
        catch (Exception e) {
            System.out.println(e);
        }
        this.moduleManager.init();
        this.commandManager.init();
        this.guiManager.init();
        this.configManager.init();
        this.presetManager.init();
        this.hudManager.init();
    }
}
