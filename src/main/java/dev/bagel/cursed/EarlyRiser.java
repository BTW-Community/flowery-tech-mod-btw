package dev.bagel.cursed;

import com.chocohead.mm.api.ClassTinkerers;
import org.lwjgl.Sys;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

import java.lang.reflect.Modifier;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        ClassTinkerers.addTransformation("api.world.data.DataEntry$PlayerDataEntry", classNode -> {
            classNode.access &= ~ Modifier.FINAL;
            classNode.access |= Modifier.PUBLIC;
            for (MethodNode method : classNode.methods) {
                if (method.name.equals("<init>")) {
                    System.out.println("Forcefully access widening DataEntry$PlayerDataEntry");
                    // Remove any existing access modifiers, then set public
                    method.access &= ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED | Opcodes.ACC_PUBLIC);
                    method.access |= Opcodes.ACC_PUBLIC;
                }
            }
        });
        ClassTinkerers.addTransformation("api.world.data.DataEntry", classNode -> {
            classNode.access &= ~ Modifier.FINAL;
            classNode.access |= Modifier.PUBLIC;
        });
    }
}
