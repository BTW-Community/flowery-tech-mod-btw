package dev.bagel.cursed;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

public class EarlyRiser implements Runnable {
    @Override
    public void run() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            System.out.println("EarlyRiser is running!");
        }
        ClassTinkerers.addTransformation("api.world.data.DataEntry$PlayerDataEntry", classNode -> {
            classNode.access &= ~ Opcodes.ACC_FINAL;
            classNode.access |= Opcodes.ACC_PUBLIC;
            for (MethodNode method : classNode.methods) {
                if (method.name.equals("<init>")) {
                    System.out.println("Forcefully access widening DataEntry$PlayerDataEntry");
                    // Remove any existing access modifiers, then set public
                    method.access &= ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED | Opcodes.ACC_PUBLIC);
                    method.access |= Opcodes.ACC_PUBLIC;
                    break;
                }
            }
        });
        ClassTinkerers.addTransformation("api.world.data.DataEntry", classNode -> {
            classNode.access &= ~ Opcodes.ACC_FINAL;
            classNode.access |= Opcodes.ACC_PUBLIC;
        });
        ClassTinkerers.enumBuilder("net.minecraft.src.EnumArmorMaterial", int.class, int[].class, int.class)
                .addEnum("MANASTEEL", 16, new int[] { 2, 6, 5, 2 }, 18)
                .addEnum("ELEMENTIUM", 18, new int[] { 2, 6, 5, 2 }, 18)
                .addEnum("TERRASTEEL", 34, new int[] {3, 8, 6, 3}, 26)
                .addEnum("MANAWEAVE", 5, new int[] { 1, 2, 2, 1 }, 18)
                .build();

        ClassTinkerers.enumBuilder("net.minecraft.src.EnumToolMaterial", int.class, int.class, float.class, float.class, int.class, int.class, int.class)
                .addEnum("MANASTEEL", 3, 300, 6.2F, 2F, 20, 25, 2)
                .addEnum("B_ELEMENTIUM", 3, 720, 6.2F, 2F, 20, 35, 3)
                .addEnum("TERRASTEEL", 4, 2300, 9F, 3F, 26, 30, 5)
                .build();

        ClassTinkerers.enumBuilder("net.minecraft.src.EnumRarity", int.class, String.class)
                .addEnum("RELIC", 6, "Relic")
                .build();
    }
}
