//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.player;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import ru.terrar.bobr.util.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoArmor extends Module
{
    public static final AutoArmor INSTANCE;
    
    public AutoArmor() {
        super("Auto Armor", "autoarmor", Module.ModuleCategory.PLAYER);
    }
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
            if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
                return;
            }
            boolean hasBoots = !((ItemStack)Minecraft.getMinecraft().player.inventory.armorInventory.get(0)).isEmpty();
            boolean hasLeggings = !((ItemStack)Minecraft.getMinecraft().player.inventory.armorInventory.get(1)).isEmpty();
            boolean hasChestplate = !((ItemStack)Minecraft.getMinecraft().player.inventory.armorInventory.get(2)).isEmpty();
            boolean hasHelmet = !((ItemStack)Minecraft.getMinecraft().player.inventory.armorInventory.get(3)).isEmpty();
            if (!hasBoots || !hasLeggings || !hasChestplate || !hasHelmet) {
                for (int slot = 0; slot < Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.size(); ++slot) {
                    final Item slotItem = Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.get(slot).getStack().getItem();
                    if (slotItem instanceof ItemArmor) {
                        final ItemArmor itemArmor = (ItemArmor)slotItem;
                        System.out.println(slot);
                        if (!hasHelmet && itemArmor.armorType == EntityEquipmentSlot.HEAD) {
                            PlayerUtil.swapInventoryItems(slot, 5);
                            hasHelmet = true;
                        }
                        if (!hasChestplate && itemArmor.armorType == EntityEquipmentSlot.CHEST) {
                            PlayerUtil.swapInventoryItems(slot, 6);
                            hasChestplate = true;
                        }
                        if (!hasLeggings && itemArmor.armorType == EntityEquipmentSlot.LEGS) {
                            PlayerUtil.swapInventoryItems(slot, 7);
                            hasLeggings = true;
                        }
                        if (!hasBoots && itemArmor.armorType == EntityEquipmentSlot.FEET) {
                            PlayerUtil.swapInventoryItems(slot, 8);
                            hasBoots = true;
                        }
                    }
                }
            }
        }
    }
    
    static {
        INSTANCE = new AutoArmor();
    }
}
