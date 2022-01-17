//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.handlers;

import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.client.*;
import ru.terrar.bobr.*;
import ru.terrar.bobr.modules.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.client.event.*;
import ru.terrar.bobr.commands.*;
import ru.terrar.bobr.util.*;
import net.minecraft.util.text.*;

public class EventHandler
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyPressed(final InputEvent.KeyInputEvent event) {
        if (Keyboard.isCreated() && Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && Keyboard.getEventKeyState()) {
            for (final Module module : bobr.getGate().moduleManager.MODULE_LIST) {
                if (Keyboard.getEventKey() == module.getKeyBind()) {
                    module.toggle();
                    if (!bobr.getGate().presetManager.isAutoSave()) {
                        continue;
                    }
                    bobr.getGate().presetManager.saveActivePreset();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onMessageSent(final ClientChatEvent event) {
        final String message = event.getOriginalMessage();
        if (message.startsWith(bobr.getGate().commandManager.getPrefix())) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(message);
            final String[] args = message.substring(bobr.getGate().commandManager.getPrefix().length()).split(" ");
            boolean found = false;
            for (final Command command : bobr.getGate().commandManager.COMMAND_LIST) {
                if (ChatUtil.isCommand(args[0], command)) {
                    if (args.length == 2 && args[1].equalsIgnoreCase("help")) {
                        ChatUtil.clientMessage("Syntax for " + TextFormatting.GOLD + command.getName() + TextFormatting.RESET + " command:");
                        for (final String syntax : command.getSyntax()) {
                            ChatUtil.clientMessage(TextFormatting.YELLOW + bobr.getGate().commandManager.getPrefix() + syntax);
                        }
                    }
                    else {
                        command.onCommand(args);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                ChatUtil.clientMessage("Command Not Found");
            }
            event.setCanceled(true);
        }
    }
}
