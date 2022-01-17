//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class ConfigManager
{
    public File configFile;
    
    public ConfigManager() {
        this.configFile = new File(DirectoryUtil.GATE_FOLDER, "config.json");
    }
    
    public void init() {
        DirectoryUtil.GATE_FOLDER.mkdir();
        DirectoryUtil.PRESET_FOLDER.mkdir();
        this.load();
    }
    
    public void save() {
        final JsonObject config = new JsonObject();
        config.addProperty("Active Config", (bobr.getGate().presetManager.getActivePreset() != null) ? bobr.getGate().presetManager.getActivePreset().toString() : new File(DirectoryUtil.PRESET_FOLDER, "default.json").toString());
        config.addProperty("Prefix", bobr.getGate().commandManager.getPrefix());
        try {
            final FileWriter writer = new FileWriter(this.configFile);
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson((JsonElement)config));
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
        bobr.getGate().presetManager.updatePresetList();
        final JsonParser parser = new JsonParser();
        if (!this.configFile.exists()) {
            this.save();
        }
        try {
            final JsonObject config = parser.parse((Reader)new FileReader(this.configFile)).getAsJsonObject();
            for (final Map.Entry<String, JsonElement> entry : config.entrySet()) {
                final String key = entry.getKey();
                final JsonElement val = entry.getValue();
                if (key.equals("Active Config")) {
                    bobr.getGate().presetManager.setActivePreset(new File(val.getAsString()));
                }
                else {
                    if (!key.equals("Prefix")) {
                        continue;
                    }
                    bobr.getGate().commandManager.setPrefix(val.getAsString());
                }
            }
            this.save();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.save();
    }
}
