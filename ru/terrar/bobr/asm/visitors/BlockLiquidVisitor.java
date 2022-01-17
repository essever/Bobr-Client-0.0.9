//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class BlockLiquidVisitor extends ClassVisitor
{
    final String GET_COLLISION;
    final String GET_COLLISION_DESC;
    
    public BlockLiquidVisitor(final ClassVisitor cv, final boolean isObfuscated) {
        super(327680, cv);
        this.GET_COLLISION = (isObfuscated ? "a" : "getCollisionBoundingBox");
        this.GET_COLLISION_DESC = (isObfuscated ? "(Lawt;Lamy;Let;)Lbhb;" : "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;");
    }
    
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(this.GET_COLLISION) && desc.equals(this.GET_COLLISION_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new GetCollisionBoundingBoxVisitor(mv);
        }
        return mv;
    }
    
    public static class GetCollisionBoundingBoxVisitor extends MethodVisitor
    {
        public GetCollisionBoundingBoxVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitInsn(final int opcode) {
            if (opcode == 176) {
                this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "getCollisionBoundingBox", "()Lnet/minecraft/util/math/AxisAlignedBB;", false);
            }
            super.visitInsn(opcode);
        }
    }
}
