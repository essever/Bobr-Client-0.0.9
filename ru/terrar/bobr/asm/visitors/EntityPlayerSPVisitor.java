//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.asm.visitors;

import org.objectweb.asm.*;

public class EntityPlayerSPVisitor extends ClassVisitor
{
    final String IS_USER;
    final String IS_USER_DESC;
    final String MOVE;
    final String MOVE_DESC;
    final String ON_UPDATE_WALKING_PLAYER;
    final String ON_UPDATE_WALKING_PLAYER_DESC;
    
    public EntityPlayerSPVisitor(final ClassVisitor cv, final boolean isObfuscated) {
        super(327680, cv);
        this.IS_USER = (isObfuscated ? "cZ" : "isUser");
        this.IS_USER_DESC = "()Z";
        this.MOVE = (isObfuscated ? "a" : "move");
        this.MOVE_DESC = (isObfuscated ? "(Lvv;DDD)V" : "(Lnet/minecraft/entity/MoverType;DDD)V");
        this.ON_UPDATE_WALKING_PLAYER = (isObfuscated ? "N" : "onUpdateWalkingPlayer");
        this.ON_UPDATE_WALKING_PLAYER_DESC = "()V";
    }
    
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(this.IS_USER) && desc.equals(this.IS_USER_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new IsUserVisitor(mv);
        }
        else if (name.equals(this.MOVE) && desc.equals(this.MOVE_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new MoveVisitor(mv);
        }
        else if (name.equals(this.ON_UPDATE_WALKING_PLAYER) && desc.equals(this.ON_UPDATE_WALKING_PLAYER_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new OnUpdateWalkingPlayer(mv);
        }
        return mv;
    }
    
    public static class IsUserVisitor extends MethodVisitor
    {
        public IsUserVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitInsn(final int opcode) {
            if (opcode == 172) {
                this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "isUser", "()Z", false);
            }
            super.visitInsn(opcode);
        }
    }
    
    public static class MoveVisitor extends MethodVisitor
    {
        public MoveVisitor(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitCode() {
            this.mv.visitTypeInsn(187, "ru/terrar/bobr/events/PlayerMoveEvent");
            this.mv.visitInsn(89);
            this.mv.visitVarInsn(24, 2);
            this.mv.visitVarInsn(24, 4);
            this.mv.visitVarInsn(24, 6);
            this.mv.visitMethodInsn(183, "ru/terrar/bobr/events/PlayerMoveEvent", "<init>", "(DDD)V", false);
            this.mv.visitVarInsn(58, 8);
            this.mv.visitFieldInsn(178, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;");
            this.mv.visitVarInsn(25, 8);
            this.mv.visitMethodInsn(182, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
            final Label l0 = new Label();
            this.mv.visitJumpInsn(153, l0);
            this.mv.visitInsn(177);
            this.mv.visitLabel(l0);
            this.mv.visitFrame(1, 1, new Object[] { "ru/terrar/bobr/events/PlayerMoveEvent" }, 0, (Object[])null);
            this.mv.visitVarInsn(25, 8);
            this.mv.visitFieldInsn(180, "ru/terrar/bobr/events/PlayerMoveEvent", "x", "D");
            this.mv.visitVarInsn(57, 2);
            this.mv.visitVarInsn(25, 8);
            this.mv.visitFieldInsn(180, "ru/terrar/bobr/events/PlayerMoveEvent", "y", "D");
            this.mv.visitVarInsn(57, 4);
            this.mv.visitVarInsn(25, 8);
            this.mv.visitFieldInsn(180, "ru/terrar/bobr/events/PlayerMoveEvent", "z", "D");
            this.mv.visitVarInsn(57, 6);
            super.visitCode();
        }
    }
    
    public static class OnUpdateWalkingPlayer extends MethodVisitor
    {
        public OnUpdateWalkingPlayer(final MethodVisitor mv) {
            super(327680, mv);
        }
        
        public void visitCode() {
            super.visitCode();
            this.mv.visitMethodInsn(184, "ru/terrar/bobr/util/EventFactory", "onUpdateWalkingPlayer", "()Z", false);
            final Label l1 = new Label();
            this.mv.visitJumpInsn(153, l1);
            this.mv.visitInsn(177);
            this.mv.visitLabel(l1);
        }
    }
}
