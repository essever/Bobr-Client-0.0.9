//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import ru.terrar.bobr.util.*;
import ru.terrar.bobr.*;
import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.*;
import ru.terrar.bobr.settings.impl.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class PresetManager
{
    public final List<File> PRESET_LIST;
    private File activePreset;
    private boolean autoSave;
    
    public PresetManager() {
        this.PRESET_LIST = new ArrayList<File>();
    }
    
    public void init() {
        this.updatePresetList();
        this.loadActivePreset();
    }
    
    public void setActivePreset(final File activePreset) {
        this.activePreset = activePreset;
    }
    
    public File getActivePreset() {
        return this.activePreset;
    }
    
    public void setAutoSave(final boolean autoSave) {
        if (this.autoSave != autoSave) {
            this.autoSave = autoSave;
            this.saveActivePreset();
        }
    }
    
    public boolean isAutoSave() {
        return this.autoSave;
    }
    
    public void updatePresetList() {
        this.PRESET_LIST.clear();
        if (DirectoryUtil.PRESET_FOLDER.listFiles() != null) {
            for (final File file : DirectoryUtil.PRESET_FOLDER.listFiles()) {
                if (!file.isDirectory() && DirectoryUtil.isJson(file)) {
                    this.PRESET_LIST.add(file);
                }
            }
        }
    }
    
    public void loadActivePreset() {
        if (!this.presetExists(this.getActivePreset())) {
            System.out.println("Preset not found");
            this.setActivePreset(new File(DirectoryUtil.PRESET_FOLDER, "default.json"));
            bobr.getGate().configManager.save();
            if (!this.presetExists(this.getActivePreset())) {
                System.out.println("Default preset created");
                this.setAutoSave(true);
                this.saveActivePreset();
            }
        }
        final JsonParser parser = new JsonParser();
        try {
            final JsonObject object = parser.parse((Reader)new FileReader(this.getActivePreset())).getAsJsonObject();
            final JsonElement autoSave = object.get("auto save");
            this.setAutoSave(autoSave == null || autoSave.getAsBoolean());
            final JsonArray moduleArray = object.getAsJsonArray("modules");
            for (final JsonElement element : moduleArray) {
                if (!(element instanceof JsonObject)) {
                    continue;
                }
                final JsonObject moduleObject = (JsonObject)element;
                final Set<Map.Entry<String, JsonElement>> moduleSet = (Set<Map.Entry<String, JsonElement>>)moduleObject.entrySet();
                for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
                    if (!this.contains(moduleSet, "name", (JsonElement)new JsonPrimitive(module.getName()))) {
                        continue;
                    }
                    for (final Map.Entry<String, JsonElement> value : moduleSet) {
                        final String key = value.getKey();
                        final JsonElement val = value.getValue();
                        if (key.equals("enabled")) {
                            module.setEnabled(val.getAsBoolean());
                        }
                        else if (key.equals("keybind")) {
                            module.setKeyBind(val.getAsInt());
                        }
                        else {
                            if (!key.equals("settings")) {
                                continue;
                            }
                            for (final JsonElement element2 : val.getAsJsonArray()) {
                                if (!(element2 instanceof JsonObject)) {
                                    continue;
                                }
                                final JsonObject settingObject = (JsonObject)element2;
                                final Set<Map.Entry<String, JsonElement>> settingSet = (Set<Map.Entry<String, JsonElement>>)settingObject.entrySet();
                                for (final Setting setting : module.getSettings()) {
                                    if (!this.contains(settingSet, "id", (JsonElement)new JsonPrimitive(setting.getId()))) {
                                        continue;
                                    }
                                    for (final Map.Entry<String, JsonElement> value2 : settingSet) {
                                        final String settingKey = value2.getKey();
                                        final JsonElement settingVal = value2.getValue();
                                        if (setting instanceof BooleanSetting) {
                                            if (!settingKey.equals("value")) {
                                                continue;
                                            }
                                            ((BooleanSetting)setting).setValue(settingVal.getAsBoolean());
                                        }
                                        else if (setting instanceof EnumSetting) {
                                            if (!settingKey.equals("value")) {
                                                continue;
                                            }
                                            ((EnumSetting)setting).setValueFromName(settingVal.getAsString());
                                        }
                                        else if (setting instanceof FloatSetting) {
                                            if (!settingKey.equals("value")) {
                                                continue;
                                            }
                                            ((FloatSetting)setting).setValue(settingVal.getAsFloat());
                                        }
                                        else {
                                            if (!(setting instanceof RGBSetting)) {
                                                continue;
                                            }
                                            if (settingKey.equals("red")) {
                                                ((RGBSetting)setting).setRed(settingVal.getAsInt());
                                            }
                                            else if (settingKey.equals("green")) {
                                                ((RGBSetting)setting).setGreen(settingVal.getAsInt());
                                            }
                                            else {
                                                if (!settingKey.equals("blue")) {
                                                    continue;
                                                }
                                                ((RGBSetting)setting).setBlue(settingVal.getAsInt());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Preset loaded");
        this.saveActivePreset();
    }
    
    public void saveActivePreset() {
        final JsonObject presetJson = new JsonObject();
        presetJson.addProperty("auto save", Boolean.valueOf(this.isAutoSave()));
        final JsonArray moduleArray = new JsonArray();
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            final JsonObject moduleObject = new JsonObject();
            moduleObject.addProperty("name", module.getName());
            moduleObject.addProperty("enabled", Boolean.valueOf(module.isEnabled()));
            moduleObject.addProperty("keybind", (Number)module.getKeyBind());
            final JsonArray settingsArray = new JsonArray();
            for (final Setting setting : module.getSettings()) {
                final JsonObject settingObject = new JsonObject();
                settingObject.addProperty("id", setting.getId());
                if (setting instanceof BooleanSetting) {
                    settingObject.addProperty("value", Boolean.valueOf(((BooleanSetting)setting).getValue()));
                }
                else if (setting instanceof RGBSetting) {
                    settingObject.addProperty("red", (Number)((RGBSetting)setting).getRed());
                    settingObject.addProperty("green", (Number)((RGBSetting)setting).getGreen());
                    settingObject.addProperty("blue", (Number)((RGBSetting)setting).getBlue());
                }
                else if (setting instanceof EnumSetting) {
                    settingObject.addProperty("value", ((EnumSetting)setting).getCurrentValueName());
                }
                else if (setting instanceof FloatSetting) {
                    settingObject.addProperty("value", (Number)((FloatSetting)setting).getValue());
                }
                settingsArray.add((JsonElement)settingObject);
            }
            moduleObject.add("settings", (JsonElement)settingsArray);
            moduleArray.add((JsonElement)moduleObject);
        }
        presetJson.add("modules", (JsonElement)moduleArray);
        try {
            final FileWriter writer = new FileWriter(this.getActivePreset());
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson((JsonElement)presetJson));
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Preset saved");
    }
    
    public boolean createNewPreset(final String path) {
        this.updatePresetList();
        for (final File file : this.PRESET_LIST) {
            if (file.getName().equalsIgnoreCase(path)) {
                return false;
            }
        }
        this.setActivePreset(new File(DirectoryUtil.PRESET_FOLDER, path));
        this.setAutoSave(true);
        this.saveActivePreset();
        bobr.getGate().configManager.save();
        return true;
    }
    
    public boolean removePreset(final String presetName) {
        this.updatePresetList();
        for (final File file : this.PRESET_LIST) {
            if (file.getName().equalsIgnoreCase(presetName)) {
                return file.delete();
            }
        }
        return false;
    }
    
    public void removeActivePreset() {
        this.getActivePreset().delete();
        this.updatePresetList();
        if (this.PRESET_LIST.size() != 0) {
            this.setActivePreset(this.PRESET_LIST.get(0));
            this.loadActivePreset();
        }
        else {
            this.setActivePreset(new File(DirectoryUtil.PRESET_FOLDER, "default.json"));
            this.setAutoSave(true);
            this.saveActivePreset();
            bobr.getGate().configManager.save();
        }
    }
    
    private boolean presetExists(final File preset) {
        for (final File file : this.PRESET_LIST) {
            if (file.getPath().equals(preset.getPath())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean contains(final Set<Map.Entry<String, JsonElement>> set, final String key, final JsonElement value) {
        for (final Map.Entry<String, JsonElement> entry : set) {
            if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
