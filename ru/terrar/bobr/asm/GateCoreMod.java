//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm;

import net.minecraftforge.fml.relauncher.*;
import javax.annotation.*;
import java.util.*;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({ "me.thef1xer.gateclient.asm" })
public class GateCoreMod implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass() {
        return new String[] { "me.thef1xer.gateclient.asm.GateClassTransformer" };
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    @Nullable
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
