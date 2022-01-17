//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class VisGraphVisitor extends ClassVisitor
{
    final String SET_OPAQUE;
    final String SET_OPAQUE_DESCRIPTOR;
    
    public VisGraphVisitor(final ClassVisitor classVisitor, final boolean isObfuscated) {
        super(327680, classVisitor);
        this.SET_OPAQUE = (isObfuscated ? "a" : "setOpaqueCube");
        this.SET_OPAQUE_DESCRIPTOR = (isObfuscated ? "(Let;)V" : "(Lnet/minecraft/util/math/BlockPos;)V");
    }
    
    public MethodVisitor visitMethod(final int i, final String s, final String s1, final String s2, final String[] strings) {
        MethodVisitor mv = this.cv.visitMethod(i, s, s1, s2, strings);
        if (s.equals(this.SET_OPAQUE) && s1.equals(this.SET_OPAQUE_DESCRIPTOR)) {
            mv = new SetOpaqueCubeVisitor(mv);
            System.out.println("Method " + this.SET_OPAQUE + " transformed");
        }
        return mv;
    }
    
    public static class SetOpaqueCubeVisitor extends MethodVisitor
    {
        public SetOpaqueCubeVisitor(final MethodVisitor methodVisitor) {
            super(327680, methodVisitor);
        }
        
        public void visitCode() {
            super.visitCode();
            final Label l0 = new Label();
            this.mv.visitLabel(l0);
            this.mv.visitLineNumber(29, l0);
            this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "setOpaqueCube", "()Z", false);
            final Label l2 = new Label();
            this.mv.visitJumpInsn(153, l2);
            final Label l3 = new Label();
            this.mv.visitLabel(l3);
            this.mv.visitLineNumber(30, l3);
            this.mv.visitInsn(177);
            this.mv.visitLabel(l2);
            this.mv.visitLineNumber(32, l2);
            this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
        }
    }
}
