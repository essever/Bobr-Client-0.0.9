//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;

public class PlayerUtil
{
    public static void swapItems(final int slot1, final int slot2) {
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot1, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot2, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot1, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.updateController();
    }
    
    public static void swapInventoryItems(final int slot1, final int slot2) {
        final short short1 = Minecraft.getMinecraft().player.inventoryContainer.getNextTransactionID(Minecraft.getMinecraft().player.inventory);
        ItemStack itemstack = Minecraft.getMinecraft().player.inventoryContainer.slotClick(slot1, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot1, 0, ClickType.PICKUP, itemstack, short1));
        itemstack = Minecraft.getMinecraft().player.inventoryContainer.slotClick(slot2, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot2, 0, ClickType.PICKUP, itemstack, short1));
        itemstack = Minecraft.getMinecraft().player.inventoryContainer.slotClick(slot1, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, slot1, 0, ClickType.PICKUP, itemstack, short1));
        Minecraft.getMinecraft().playerController.updateController();
    }
    
    public static double[] getPlayerMoveVec() {
        float yaw = Minecraft.getMinecraft().player.rotationYaw;
        final float forward = Minecraft.getMinecraft().player.moveForward;
        final float strafe = Minecraft.getMinecraft().player.moveStrafing;
        if (forward == 0.0f && strafe == 0.0f) {
            return new double[] { 0.0, 0.0 };
        }
        if (forward > 0.0f) {
            if (strafe > 0.0f) {
                yaw -= 45.0f;
            }
            else if (strafe < 0.0f) {
                yaw += 45.0f;
            }
        }
        else if (forward < 0.0f) {
            yaw -= 180.0f;
            if (strafe > 0.0f) {
                yaw += 45.0f;
            }
            else if (strafe < 0.0f) {
                yaw -= 45.0f;
            }
        }
        else if (strafe > 0.0f) {
            yaw -= 90.0f;
        }
        else if (strafe < 0.0f) {
            yaw += 90.0f;
        }
        return new double[] { -Math.sin(Math.toRadians(yaw)), Math.cos(Math.toRadians(yaw)) };
    }
}
