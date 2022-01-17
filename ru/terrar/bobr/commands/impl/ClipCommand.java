//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.commands.impl;

import ru.terrar.bobr.commands.*;
import net.minecraft.client.*;
import ru.terrar.bobr.util.*;
import net.minecraft.entity.*;

public class ClipCommand extends Command
{
    private final Minecraft mc;
    
    public ClipCommand() {
        super("clip", "Allows you to clip in a direction", new String[] { "clip <v/h> <blocks>", "clip <blocks horizontal> <blocks vertical>" });
        this.mc = Minecraft.getMinecraft();
    }
    
    public void onCommand(final String[] args) {
        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("h")) {
                try {
                    final double distance = Double.parseDouble(args[2]);
                    final double[] vector = MathUtil.getDirection(this.mc.player.rotationYaw);
                    final Entity entity = (Entity)(this.mc.player.isRiding() ? this.mc.player.getRidingEntity() : this.mc.player);
                    if (entity == null) {
                        return;
                    }
                    entity.setPosition(entity.posX + vector[0] * distance, entity.posY, entity.posZ + vector[1] * distance);
                    ChatUtil.clientMessage("Teleported you " + args[2] + " blocks forward");
                }
                catch (Exception e) {
                    this.syntaxError();
                }
            }
            else if (args[1].equalsIgnoreCase("v")) {
                try {
                    final double distance = Double.parseDouble(args[2]);
                    final Entity entity2 = (Entity)(this.mc.player.isRiding() ? this.mc.player.getRidingEntity() : this.mc.player);
                    if (entity2 == null) {
                        return;
                    }
                    entity2.setPosition(entity2.posX, entity2.posY + distance, entity2.posZ);
                    ChatUtil.clientMessage("Teleported you " + args[2] + " blocks up");
                }
                catch (Exception e) {
                    this.syntaxError();
                }
            }
            else {
                try {
                    final double dh = Double.parseDouble(args[1]);
                    final double dv = Double.parseDouble(args[2]);
                    final double[] vector2 = MathUtil.getDirection(this.mc.player.rotationYaw);
                    final Entity entity3 = (Entity)(this.mc.player.isRiding() ? this.mc.player.getRidingEntity() : this.mc.player);
                    if (entity3 == null) {
                        return;
                    }
                    entity3.setPosition(entity3.posX + vector2[0] * dh, entity3.posY + dv, entity3.posZ + vector2[1] * dh);
                    ChatUtil.clientMessage("Teleported you " + args[1] + " blocks forward and " + args[2] + " blocks up");
                }
                catch (Exception e) {
                    this.syntaxError();
                }
            }
            return;
        }
        this.syntaxError();
    }
}
