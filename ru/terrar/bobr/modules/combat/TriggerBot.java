//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.combat;

import ru.terrar.bobr.modules.*;
import ru.terrar.bobr.settings.impl.*;
import ru.terrar.bobr.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.terrar.bobr.managers.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import java.util.*;

public class TriggerBot extends Module
{
    private transient long time;
    public static final TriggerBot INSTANCE;
    public final FloatSetting mincps;
    public final FloatSetting maxcps;
    public final FloatSetting reach;
    public final BooleanSetting useCooldown;
    public final BooleanSetting attackPlayers;
    public final BooleanSetting attackMobs;
    public final BooleanSetting attackAnimals;
    public final BooleanSetting attackInvisibleEntities;
    public final BooleanSetting useAxeToBreakShield;
    public final BooleanSetting attackWhileMainhandInUse;
    public Entity entity;
    private Entity focusTarget;
    
    public TriggerBot() {
        super("TriggerBot", "TriggerBot", ModuleCategory.COMBAT);
        this.mincps = new FloatSetting("MinCPS", "mincps", 12.0f, 0.0f, 30.0f);
        this.maxcps = new FloatSetting("MaxCPS", "maxcps", 26.0f, 0.0f, 30.0f);
        this.reach = new FloatSetting("Reach", "reach", 3.0f, 0.0f, 6.0f);
        this.useCooldown = new BooleanSetting("Cooldown", "cooldown", true);
        this.attackPlayers = new BooleanSetting("AttackPlayers", "attackplayers", true);
        this.attackMobs = new BooleanSetting("attackMobs", "attackmobs", false);
        this.attackAnimals = new BooleanSetting("attackAnimals", "attackanimals", false);
        this.attackInvisibleEntities = new BooleanSetting("attackInvisibleEntities", "attackinvisibleentities", true);
        this.useAxeToBreakShield = new BooleanSetting("AutoBreakShield", "autobreakshield", true);
        this.attackWhileMainhandInUse = new BooleanSetting("attackWhileMainhandInUse", "attackwhileMainhandinUse", true);
        this.addSettings(this.mincps, this.maxcps, this.reach, this.useCooldown, this.attackPlayers, this.attackMobs, this.attackAnimals, this.attackInvisibleEntities, this.useAxeToBreakShield, this.attackWhileMainhandInUse);
        this.setEnabled(false);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.time = System.currentTimeMillis();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        try {
            final Entity entity = Minecraft.getMinecraft().objectMouseOver.entityHit;
            if (entity != null && entity instanceof EntityPlayer) {
                final int mincpsi = (int)this.mincps.getValue();
                final int maccpsi = (int)this.maxcps.getValue();
                final float cps = (float)generateRandomIntIntRange(mincpsi, maccpsi);
                if (this.useCooldown.getValue()) {
                    if (Minecraft.getMinecraft().player.getCooledAttackStrength(0.0f) < 1.0f) {
                        return;
                    }
                }
                else if (cps == 0.0f || System.currentTimeMillis() - this.time < 1000.0 / cps) {
                    return;
                }
                if (Minecraft.getMinecraft().objectMouseOver != null) {
                    final long newTime = System.currentTimeMillis();
                    if (entity == null) {
                        return;
                    }
                    if (Minecraft.getMinecraft().player.getDistance(entity) > this.reach.getValue()) {
                        return;
                    }
                    if (entity instanceof EntityLivingBase && (entity.isDead || ((EntityLivingBase)entity).getHealth() < 0.0f)) {
                        return;
                    }
                    if (entity instanceof EntityPlayer) {
                        if (!this.attackPlayers.getValue()) {
                            return;
                        }
                        if (AntiBot.isBot(entity.getName())) {
                            return;
                        }
                        if (FriendManager.isFriend(entity.getName())) {
                            return;
                        }
                        double angle1 = (entity.rotationYaw + 180.0f) % 360.0f;
                        double angle2 = Minecraft.getMinecraft().player.rotationYaw % 360.0f;
                        if (angle1 < 0.0) {
                            angle1 += 360.0;
                        }
                        if (angle2 < 0.0) {
                            angle2 += 360.0;
                        }
                        if (isPlayerShielded((EntityPlayer)entity) && 180.0 - Math.abs(Math.abs(angle1 - angle2) - 180.0) < 95.0 && (!this.useAxeToBreakShield.getValue() || !(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemAxe))) {
                            return;
                        }
                    }
                    else if (entity instanceof IMob) {
                        if (!this.attackMobs.getValue()) {
                            return;
                        }
                    }
                    else {
                        if (!isAnimal(entity)) {
                            return;
                        }
                        if (!this.attackAnimals.getValue()) {
                            return;
                        }
                    }
                    if (!isPlayerShielded((EntityPlayer)Minecraft.getMinecraft().player) && (this.attackWhileMainhandInUse.getValue() || !isPlayerUsingMainhand((EntityPlayer)Minecraft.getMinecraft().player))) {
                        Minecraft.getMinecraft().playerController.attackEntity((EntityPlayer)Minecraft.getMinecraft().player, entity);
                        Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
                        this.time = newTime;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
    
    private static boolean isPlayerShielded(final EntityPlayer player) {
        return player.getItemInUseCount() > 0 && (player.getHeldItemMainhand().getItem() instanceof ItemShield || (player.getHeldItemOffhand().getItem() instanceof ItemShield && !isPlayerUsingMainhand(player)));
    }
    
    private static boolean isPlayerUsingMainhand(final EntityPlayer player) {
        final ItemStack main = player.getHeldItemMainhand();
        return player.getItemInUseCount() > 0 && ((main.getItemUseAction() == EnumAction.EAT && !player.isCreative() && (player.getFoodStats().needFood() || main.getItem() instanceof ItemAppleGold)) || (main.getItemUseAction() == EnumAction.BOW && canShootBow(player)) || main.getItemUseAction() == EnumAction.DRINK || main.getItemUseAction() == EnumAction.BLOCK);
    }
    
    private static boolean canShootBow(final EntityPlayer player) {
        if (player.isCreative()) {
            return true;
        }
        for (final ItemStack stack : player.inventory.mainInventory) {
            if (stack.getItem() == Items.ARROW) {
                return true;
            }
        }
        return false;
    }
    
    public static int generateRandomIntIntRange(final int min, final int max) {
        final Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    static {
        INSTANCE = new TriggerBot();
    }
}
