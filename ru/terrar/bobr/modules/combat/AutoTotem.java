//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.*;
import net.minecraft.inventory.*;
import ru.terrar.bobr.util.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoTotem extends Module
{
    public static final AutoTotem INSTANCE;
    
    public AutoTotem() {
        super("Auto Totem", "autototem", ModuleCategory.COMBAT);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
            return;
        }
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getHeldItemOffhand().getItem() != Item.getItemById(449)) {
            for (int slot = 0; slot < Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.size(); ++slot) {
                final ItemStack itemStack = Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.get(slot).getStack();
                if (itemStack.getItem() == Item.getItemById(449)) {
                    PlayerUtil.swapInventoryItems(slot, 45);
                    break;
                }
            }
        }
    }
    
    static {
        INSTANCE = new AutoTotem();
    }
}
