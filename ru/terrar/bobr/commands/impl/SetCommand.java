//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.modules.*;
import net.minecraft.util.text.*;
import ru.terrar.bobr.util.*;
import ru.terrar.bobr.settings.*;
import ru.terrar.bobr.*;
import org.apache.logging.log4j.core.util.*;
import ru.terrar.bobr.settings.impl.*;
import java.util.*;

public class SetCommand extends Command
{
    private Module module;
    
    public SetCommand() {
        super("set", "Changes the settings of a Module", new String[] { "set <module> <setting> <value>", "set <module> <setting>", "set <module> list" });
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 3 && this.isModule(args[1])) {
            if (args[2].equalsIgnoreCase("list")) {
                ChatUtil.clientMessage(TextFormatting.BOLD + "Settings for " + this.module.getName() + " module:");
                for (final Setting setting : this.module.getSettings()) {
                    this.sendMessageSetting(setting);
                }
                return;
            }
            for (final Setting setting : this.module.getSettings()) {
                if (setting.getId().equalsIgnoreCase(args[2])) {
                    this.sendMessageSetting(setting);
                    return;
                }
            }
        }
        if (args.length >= 4 && this.isModule(args[1])) {
            for (final Setting setting : this.module.getSettings()) {
                if (args[2].equalsIgnoreCase(setting.getId())) {
                    if (setting instanceof BooleanSetting) {
                        if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("true")) {
                                ((BooleanSetting)setting).setValue(true);
                                ChatUtil.clientMessage(setting.getName() + " set to " + TextFormatting.GOLD + "true");
                                if (bobr.getGate().presetManager.isAutoSave()) {
                                    bobr.getGate().presetManager.saveActivePreset();
                                }
                                return;
                            }
                            if (args[3].equalsIgnoreCase("false")) {
                                ((BooleanSetting)setting).setValue(false);
                                ChatUtil.clientMessage(setting.getName() + " set to " + TextFormatting.GOLD + "false");
                                if (bobr.getGate().presetManager.isAutoSave()) {
                                    bobr.getGate().presetManager.saveActivePreset();
                                }
                                return;
                            }
                        }
                        ChatUtil.clientMessage("Value must be " + TextFormatting.GOLD + "true" + TextFormatting.WHITE + " or " + TextFormatting.GOLD + "false");
                    }
                    else if (setting instanceof RGBSetting) {
                        if (args.length == 6) {
                            int r;
                            int g;
                            int b;
                            try {
                                r = Integers.parseInt(args[3]);
                                g = Integers.parseInt(args[4]);
                                b = Integers.parseInt(args[5]);
                            }
                            catch (Exception e2) {
                                ChatUtil.clientMessage("Red, Green and Blue must be integers");
                                return;
                            }
                            if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
                                ChatUtil.clientMessage("Red, Green and Blue must be between " + TextFormatting.GOLD + "0" + TextFormatting.WHITE + " and " + TextFormatting.GOLD + "255");
                                return;
                            }
                            ((RGBSetting)setting).setRed(r);
                            ((RGBSetting)setting).setGreen(g);
                            ((RGBSetting)setting).setBlue(b);
                            ChatUtil.clientMessage(setting.getName() + " set to " + TextFormatting.GOLD + "(" + r + ", " + g + ", " + b + ")");
                            if (bobr.getGate().presetManager.isAutoSave()) {
                                bobr.getGate().presetManager.saveActivePreset();
                            }
                        }
                        else {
                            ChatUtil.clientMessage("Values must be " + TextFormatting.GOLD + "<red> <green> <blue>");
                        }
                    }
                    else if (setting instanceof EnumSetting) {
                        if (args.length == 4 && ((EnumSetting)setting).setValueFromName(args[3])) {
                            ChatUtil.clientMessage(setting.getName() + " set to " + TextFormatting.GOLD + ((EnumSetting)setting).getCurrentValueName());
                            if (bobr.getGate().presetManager.isAutoSave()) {
                                bobr.getGate().presetManager.saveActivePreset();
                            }
                            return;
                        }
                        ChatUtil.clientMessage("Value not valid");
                    }
                    else if (setting instanceof FloatSetting) {
                        if (args.length == 4) {
                            try {
                                final float f = Float.parseFloat(args[3]);
                                if (((FloatSetting)setting).setValue(f)) {
                                    ChatUtil.clientMessage(setting.getName() + " set to " + TextFormatting.GOLD + f);
                                    if (bobr.getGate().presetManager.isAutoSave()) {
                                        bobr.getGate().presetManager.saveActivePreset();
                                    }
                                    return;
                                }
                            }
                            catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        ChatUtil.clientMessage("Value must be a float value between " + TextFormatting.GOLD + ((FloatSetting)setting).getMin() + TextFormatting.WHITE + " and " + TextFormatting.GOLD + ((FloatSetting)setting).getMax());
                    }
                    return;
                }
            }
        }
        this.syntaxError();
    }
    
    private boolean isModule(final String s) {
        for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
            if (module.getId().equalsIgnoreCase(s)) {
                this.module = module;
                return true;
            }
        }
        return false;
    }
    
    private void sendMessageSetting(final Setting setting) {
        String value = "";
        String current = "";
        if (setting instanceof BooleanSetting) {
            value = "<true / false>";
            current = (((BooleanSetting)setting).getValue() ? "true" : "false");
        }
        else if (setting instanceof RGBSetting) {
            value = "<red> <green> <blue>";
            current = ((RGBSetting)setting).getRed() + ", " + ((RGBSetting)setting).getGreen() + ", " + ((RGBSetting)setting).getBlue();
        }
        else if (setting instanceof EnumSetting) {
            value = "<";
            for (int i = 0; i < ((EnumSetting)setting).getValues().length; ++i) {
                value = value.concat(((EnumSetting)setting).getValues()[i].toString());
                if (i == ((EnumSetting)setting).getValues().length - 1) {
                    value = value.concat(">");
                }
                else {
                    value = value.concat(" / ");
                }
            }
            current = ((EnumSetting)setting).getCurrentValueName() + "]";
        }
        else if (setting instanceof FloatSetting) {
            value = "<number between " + ((FloatSetting)setting).getMin() + " and " + ((FloatSetting)setting).getMax() + ">";
            current = ((FloatSetting)setting).getValue() + "";
        }
        ChatUtil.clientMessage(TextFormatting.GOLD.toString() + TextFormatting.ITALIC.toString() + setting.getName() + ": " + TextFormatting.RESET.toString() + setting.getId() + " " + value + TextFormatting.GOLD.toString() + TextFormatting.ITALIC.toString() + " [" + current + "]");
    }
}
