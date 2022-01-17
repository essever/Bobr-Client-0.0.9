//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import ru.terrar.bobr.modules.*;
import java.util.*;
import ru.terrar.bobr.modules.combat.*;
import ru.terrar.bobr.modules.hud.*;
import ru.terrar.bobr.modules.movement.*;
import ru.terrar.bobr.modules.player.*;
import ru.terrar.bobr.modules.render.*;

public class ModuleManager
{
    public final List<Module> MODULE_LIST;
    
    public ModuleManager() {
        this.MODULE_LIST = new ArrayList<Module>();
    }
    
    public void init() {
        this.MODULE_LIST.add(AutoTotem.INSTANCE);
        this.MODULE_LIST.add(TriggerBot.INSTANCE);
        this.MODULE_LIST.add(KillAura.INSTANCE);
        this.MODULE_LIST.add(aimAssist.INSTANCE);
        this.MODULE_LIST.add(HitBoxMod.INSTANCE);
        this.MODULE_LIST.add(Velocity.INSTANCE);
        this.MODULE_LIST.add(AntiBot.INSTANCE);
        this.MODULE_LIST.add(MiddleClickPearl.INSTANCE);
        this.MODULE_LIST.add(AntiAim.INSTANCE);
        this.MODULE_LIST.add(AimPistot.INSTANCE);
        this.MODULE_LIST.add(ClickGuiModule.INSTANCE);
        this.MODULE_LIST.add(Coords.INSTANCE);
        this.MODULE_LIST.add(ModuleList.INSTANCE);
        this.MODULE_LIST.add(Watermark.INSTANCE);
        this.MODULE_LIST.add(TargetHud.INSTANCE);
        this.MODULE_LIST.add(DiscordRPC.INSTANCE);
        this.MODULE_LIST.add(PlayerRadar.INSTANCE);
        this.MODULE_LIST.add(GuiMove.INSTANCE);
        this.MODULE_LIST.add(NoSlow.INSTANCE);
        this.MODULE_LIST.add(SafeWalk.INSTANCE);
        this.MODULE_LIST.add(Sprint.INSTANCE);
        this.MODULE_LIST.add(AutoArmor.INSTANCE);
        this.MODULE_LIST.add(AutoDisconnect.INSTANCE);
        this.MODULE_LIST.add(Freecam.INSTANCE);
        this.MODULE_LIST.add(NoPush.INSTANCE);
        this.MODULE_LIST.add(Throwpot.INSTANCE);
        this.MODULE_LIST.add(EntityESP.INSTANCE);
        this.MODULE_LIST.add(StorageESP.INSTANCE);
        this.MODULE_LIST.add(FullBright.INSTANCE);
        this.MODULE_LIST.add(NoOverlay.INSTANCE);
        this.MODULE_LIST.add(Tracers.INSTANCE);
        this.MODULE_LIST.add(XRay.INSTANCE);
        this.MODULE_LIST.add(NameTags.INSTANCE);
        this.MODULE_LIST.add(Glowing.INSTANCE);
        this.MODULE_LIST.add(WallHack.INSTANCE);
    }
}
