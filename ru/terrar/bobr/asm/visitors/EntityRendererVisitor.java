//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class EntityRendererVisitor extends ClassVisitor
{
    final String UPDATE_CAMERA;
    final String UPDATE_CAMERA_DESCRIPTOR;
    boolean isObfuscated;
    
    public EntityRendererVisitor(final ClassVisitor classVisitor, final boolean isObfuscated) {
        super(327680, classVisitor);
        this.UPDATE_CAMERA = (isObfuscated ? "a" : "updateCameraAndRender");
        this.UPDATE_CAMERA_DESCRIPTOR = (isObfuscated ? "(FJ)V" : "(FJ)V");
        this.isObfuscated = isObfuscated;
    }
    
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(this.UPDATE_CAMERA) && desc.equals(this.UPDATE_CAMERA_DESCRIPTOR)) {
            mv = new UpdateCameraAndRendererVisitor(mv, this.isObfuscated);
            System.out.println("Method " + this.UPDATE_CAMERA + " transformed");
        }
        return mv;
    }
    
    public static class UpdateCameraAndRendererVisitor extends MethodVisitor
    {
        boolean line1033;
        boolean line1039;
        boolean isObfuscated;
        
        public UpdateCameraAndRendererVisitor(final MethodVisitor mv, final boolean isObfuscated) {
            super(327680, mv);
            this.isObfuscated = isObfuscated;
        }
        
        public void visitLineNumber(final int line, final Label start) {
            super.visitLineNumber(line, start);
            this.line1033 = (line == 1033);
            this.line1039 = (line == 1039);
        }
        
        public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
            if ((this.line1033 || this.line1039) && owner.equals(this.isObfuscated ? "bib" : "net/minecraft/client/Minecraft")) {
                this.mv.visitMethodInsn(182, this.isObfuscated ? "bib" : "net/minecraft/client/Minecraft", this.isObfuscated ? "aa" : "getRenderViewEntity", this.isObfuscated ? "()Lvg;" : "()Lnet/minecraft/entity/Entity;", false);
            }
            else {
                super.visitFieldInsn(opcode, owner, name, desc);
            }
        }
        
        public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
            if (this.line1033 || this.line1039) {
                this.mv.visitMethodInsn(182, this.isObfuscated ? "vg" : "net/minecraft/entity/Entity", this.isObfuscated ? "c" : "turn", "(FF)V", false);
            }
            else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }
}
