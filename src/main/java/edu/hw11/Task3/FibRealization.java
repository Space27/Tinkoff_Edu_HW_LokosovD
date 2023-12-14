package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public enum FibRealization implements ByteCodeAppender {

    INSTANCE;

    @Override
    public ByteCodeAppender.Size apply(MethodVisitor mv, Implementation.Context context, MethodDescription md) {
        final int maxStack = 5;
        final int maxLocals = 2;
        final Label fibSum = new Label();

        mv.visitCode();

        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, fibSum); // if (n >= 2)

        // n < 2 -> return n
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.LRETURN);

        // n >= 2 -> fib(n - 2) + fib(n - 1)
        mv.visitLabel(fibSum);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        // fib(n - 2)
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        subIConst(mv, Opcodes.ICONST_2);
        invokeVirtual(mv, md);

        // fib(n - 1)
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        subIConst(mv, Opcodes.ICONST_1);
        invokeVirtual(mv, md);

        // return fib(n - 2) + fib(n - 1)
        mv.visitInsn(Opcodes.LADD);
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitMaxs(maxStack, maxLocals);
        mv.visitEnd();

        return new ByteCodeAppender.Size(maxStack, maxLocals);
    }

    private void invokeVirtual(MethodVisitor mv, MethodDescription md) {
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            md.getDeclaringType().getTypeName(),
            md.getName(),
            md.getDescriptor(),
            false
        );
    }

    private void subIConst(MethodVisitor mv, int opcode) {
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(opcode);
        mv.visitInsn(Opcodes.ISUB);
    }
}
