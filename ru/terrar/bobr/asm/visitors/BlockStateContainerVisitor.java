//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class BlockStateContainerVisitor extends ClassVisitor
{
    final String SHOULD_SIDE;
    final String SHOULD_SIDE_DESCRIPTOR;
    final String GET_LIGHT;
    final String GET_LIGHT_DESCRIPTOR;
    
    public BlockStateContainerVisitor(final ClassVisitor cv, final boolean isObfuscated) {
        super(327680, cv);
        this.SHOULD_SIDE = (isObfuscated ? "c" : "shouldSideBeRendered");
        this.SHOULD_SIDE_DESCRIPTOR = (isObfuscated ? "(Lamy;Let;Lfa;)Z" : "(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z");
        this.GET_LIGHT = (isObfuscated ? "j" : "getAmbientOcclusionLightValue");
        this.GET_LIGHT_DESCRIPTOR = "()F";
    }
    
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(this.SHOULD_SIDE) && desc.equals(this.SHOULD_SIDE_DESCRIPTOR)) {
            mv = new ShouldSideBeRenderedVisitor(mv);
            System.out.println("Method " + name + " transformed");
        }
        else if (name.equals(this.GET_LIGHT) && desc.equals(this.GET_LIGHT_DESCRIPTOR)) {
            mv = new GetAmbientOcclusionLightValueVisitor(mv);
            System.out.println("Method " + name + " transformed");
        }
        return mv;
    }
    
    public static class ShouldSideBeRenderedVisitor extends MethodVisitor
    {
        public ShouldSideBeRenderedVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitInsn(final int opcode) {
            if (opcode == 172) {
                this.mv.visitVarInsn(25, 0);
                this.mv.visitVarInsn(25, 1);
                this.mv.visitVarInsn(25, 2);
                this.mv.visitVarInsn(25, 3);
                this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "shouldSideBeRendered", "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z", false);
            }
            super.visitInsn(opcode);
        }
    }
    
    public static class GetAmbientOcclusionLightValueVisitor extends MethodVisitor
    {
        public GetAmbientOcclusionLightValueVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitInsn(final int opcode) {
            if (opcode == 174) {
                this.mv.visitVarInsn(25, 0);
                this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "getAmbientOcclusionLightValue", "(Lnet/minecraft/block/state/IBlockState;)F", false);
            }
            super.visitInsn(opcode);
        }
    }
}
