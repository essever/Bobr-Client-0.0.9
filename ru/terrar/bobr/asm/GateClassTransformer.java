//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm;

import net.minecraft.launchwrapper.*;
import java.util.*;
import org.objectweb.asm.*;
import ru.terrar.bobr.asm.visitors.*;

public class GateClassTransformer implements IClassTransformer
{
    private static final String[] transformedClasses;
    
    public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
        final boolean isObfuscated = !name.equals(transformedName);
        final int index = Arrays.asList(GateClassTransformer.transformedClasses).indexOf(transformedName);
        return (index != -1) ? this.transform(index, basicClass, isObfuscated) : basicClass;
    }
    
    public byte[] transform(final int index, final byte[] basicClass, final boolean isObfuscated) {
        System.out.println("Transforming " + GateClassTransformer.transformedClasses[index]);
        try {
            final ClassReader classReader = new ClassReader(basicClass);
            final ClassWriter classWriter = new ClassWriter(3);
            ClassVisitor classVisitor = null;
            switch (index) {
                case 0: {
                    classVisitor = new VisGraphVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 1: {
                    classVisitor = new EntityRendererVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 2: {
                    classVisitor = new NetworkManagerVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 3: {
                    classVisitor = new BlockRendererDispatcherVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 4: {
                    classVisitor = new BlockStateContainerVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 5: {
                    classVisitor = new BlockLiquidVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                case 6: {
                    classVisitor = new EntityPlayerSPVisitor((ClassVisitor)classWriter, isObfuscated);
                    break;
                }
                default: {
                    classVisitor = new ClassVisitor(327680, classWriter) {};
                    break;
                }
            }
            classReader.accept(classVisitor, 0);
            return classWriter.toByteArray();
        }
        catch (Exception e) {
            e.printStackTrace();
            return basicClass;
        }
    }
    
    static {
        transformedClasses = new String[] { "net.minecraft.client.renderer.chunk.VisGraph", "net.minecraft.client.renderer.EntityRenderer", "net.minecraft.network.NetworkManager", "net.minecraft.client.renderer.BlockRendererDispatcher", "net.minecraft.block.state.BlockStateContainer$StateImplementation", "net.minecraft.block.BlockLiquid", "net.minecraft.client.entity.EntityPlayerSP" };
    }
}
