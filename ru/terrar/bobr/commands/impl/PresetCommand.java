//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.managers.*;
import ru.terrar.bobr.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;
import java.io.*;
import java.util.*;

public class PresetCommand extends Command
{
    private final PresetManager presetManager;
    
    public PresetCommand() {
        super("preset", "Allows you to have multiple configurations", new String[] { "preset", "preset list", "preset clear", "preset load <name>", "preset create <name>", "preset save", "preset remove <name>", "preset autosave <true/false>" });
        this.presetManager = bobr.getGate().presetManager;
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 1) {
            final String preset = DirectoryUtil.removeExtension(this.presetManager.getActivePreset().getName());
            ChatUtil.clientMessage("Current Preset: " + TextFormatting.GOLD + preset + TextFormatting.WHITE + " Auto Save: " + TextFormatting.GOLD + this.presetManager.isAutoSave());
            return;
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("list")) {
                this.presetManager.updatePresetList();
                ChatUtil.clientMessage(TextFormatting.BOLD + "Preset List:");
                for (final File file : this.presetManager.PRESET_LIST) {
                    if (this.presetManager.getActivePreset().equals(file)) {
                        ChatUtil.clientMessage(TextFormatting.GOLD + DirectoryUtil.removeExtension(file.getName()));
                    }
                    else {
                        ChatUtil.clientMessage(DirectoryUtil.removeExtension(file.getName()));
                    }
                }
                return;
            }
            if (args[1].equalsIgnoreCase("clear")) {
                this.presetManager.updatePresetList();
                for (final File file : this.presetManager.PRESET_LIST) {
                    file.delete();
                }
                this.presetManager.createNewPreset("default.json");
                ChatUtil.clientMessage("Preset list cleared");
                return;
            }
            if (args[1].equalsIgnoreCase("save")) {
                this.presetManager.saveActivePreset();
                ChatUtil.clientMessage("Preset saved");
                return;
            }
        }
        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("load")) {
                this.presetManager.updatePresetList();
                for (final File file : this.presetManager.PRESET_LIST) {
                    if (file.getName().equalsIgnoreCase(args[2] + ".json")) {
                        this.presetManager.setActivePreset(file);
                        bobr.getGate().configManager.save();
                        ChatUtil.clientMessage("Preset " + TextFormatting.GOLD + args[2].toLowerCase() + TextFormatting.RESET + " loaded");
                        return;
                    }
                }
                ChatUtil.clientMessage("Preset not found");
                return;
            }
            if (args[1].equalsIgnoreCase("create")) {
                if (this.presetManager.createNewPreset(args[2] + ".json")) {
                    ChatUtil.clientMessage("Preset " + TextFormatting.GOLD + args[2] + TextFormatting.RESET + " created");
                }
                else {
                    ChatUtil.clientMessage("That preset already exists, try another name");
                }
                return;
            }
            if (args[1].equalsIgnoreCase("remove")) {
                if (this.presetManager.getActivePreset().getName().equalsIgnoreCase(args[2] + ".json")) {
                    this.presetManager.removeActivePreset();
                    ChatUtil.clientMessage("Preset " + TextFormatting.GOLD + args[2] + TextFormatting.RESET + " removed");
                    return;
                }
                if (this.presetManager.removePreset(args[2] + ".json")) {
                    ChatUtil.clientMessage("Preset " + TextFormatting.GOLD + args[2] + TextFormatting.RESET + " removed");
                }
                else {
                    ChatUtil.clientMessage("Preset not found");
                }
                return;
            }
            else if (args[1].equalsIgnoreCase("autosave")) {
                if (args[2].equalsIgnoreCase("true")) {
                    this.presetManager.setAutoSave(true);
                    ChatUtil.clientMessage("The current Preset will now be saved automatically");
                }
                else if (args[2].equalsIgnoreCase("false")) {
                    this.presetManager.setAutoSave(false);
                    ChatUtil.clientMessage("The current Preset will no longer be saved automatically");
                }
                else {
                    ChatUtil.clientMessage("The second argument must be either true or false");
                }
                return;
            }
        }
        this.syntaxError();
    }
}
