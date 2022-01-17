//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class BlockRendererDispatcherVisitor extends ClassVisitor
{
    final String RENDER_MODEL_FLAT;
    final String RENDER_BLOCK_DESCRIPTOR;
    
    public BlockRendererDispatcherVisitor(final ClassVisitor classVisitor, final boolean isObfuscated) {
        super(327680, classVisitor);
        this.RENDER_MODEL_FLAT = (isObfuscated ? "a" : "renderBlock");
        this.RENDER_BLOCK_DESCRIPTOR = (isObfuscated ? "(Lawt;Let;Lamy;Lbuk;)Z" : "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)Z");
    }
    
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(this.RENDER_MODEL_FLAT) && desc.equals(this.RENDER_BLOCK_DESCRIPTOR)) {
            System.out.println("Method " + name + " transformed");
            mv = new RenderBlockVisitor(mv);
        }
        return mv;
    }
    
    public static class RenderBlockVisitor extends MethodVisitor
    {
        public RenderBlockVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitCode() {
            super.visitCode();
            this.mv.visitVarInsn(25, 1);
            this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "renderBlock", "(Lnet/minecraft/block/state/IBlockState;)Z", false);
            final Label l1 = new Label();
            this.mv.visitJumpInsn(153, l1);
            final Label l2 = new Label();
            this.mv.visitLabel(l2);
            this.mv.visitLineNumber(17, l2);
            this.mv.visitInsn(3);
            this.mv.visitInsn(172);
            this.mv.visitLabel(l1);
            this.mv.visitLineNumber(19, l1);
            this.mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
        }
    }
}
