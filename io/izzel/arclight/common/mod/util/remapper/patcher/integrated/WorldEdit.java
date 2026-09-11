/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.common.mod.util.remapper.patcher.integrated;

import io.izzel.arclight.api.PluginPatcher;
import java.util.Locale;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class WorldEdit {
    public static void handleBukkitAdapter(ClassNode node, PluginPatcher.ClassRepo repo) {
        MethodNode standardize = new MethodNode(4106, "patcher$standardize", Type.getMethodDescriptor((Type)Type.getType(String.class), (Type[])new Type[]{Type.getType(String.class)}), null, null);
        try {
            GeneratorAdapter adapter = new GeneratorAdapter((MethodVisitor)standardize, standardize.access, standardize.name, standardize.desc);
            adapter.loadArg(0);
            adapter.push(58);
            adapter.push(95);
            adapter.invokeVirtual(Type.getType(String.class), Method.getMethod((java.lang.reflect.Method)String.class.getMethod("replace", Character.TYPE, Character.TYPE)));
            adapter.push("\\s+");
            adapter.push("_");
            adapter.invokeVirtual(Type.getType(String.class), Method.getMethod((java.lang.reflect.Method)String.class.getMethod("replaceAll", String.class, String.class)));
            adapter.push("\\W");
            adapter.push("");
            adapter.invokeVirtual(Type.getType(String.class), Method.getMethod((java.lang.reflect.Method)String.class.getMethod("replaceAll", String.class, String.class)));
            adapter.getStatic(Type.getType(Locale.class), "ENGLISH", Type.getType(Locale.class));
            adapter.invokeVirtual(Type.getType(String.class), Method.getMethod((java.lang.reflect.Method)String.class.getMethod("toUpperCase", Locale.class)));
            adapter.returnValue();
            adapter.endMethod();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        node.methods.add(standardize);
        for (MethodNode method : node.methods) {
            if (!method.name.equals("adapt")) continue;
            WorldEdit.handleAdapt(node, standardize, method);
        }
    }

    public static void handlePickName(ClassNode node, PluginPatcher.ClassRepo repo) {
        for (MethodNode method : node.methods) {
            if (!method.name.equals("pickName")) continue;
            method.instructions.clear();
            method.instructions.add((AbstractInsnNode)new VarInsnNode(25, 1));
            method.instructions.add((AbstractInsnNode)new InsnNode(176));
            return;
        }
    }

    private static void handleAdapt(ClassNode node, MethodNode standardize, MethodNode method) {
        switch (method.desc) {
            case "(Lcom/sk89q/worldedit/world/item/ItemType;)Lorg/bukkit/Material;": 
            case "(Lcom/sk89q/worldedit/world/block/BlockType;)Lorg/bukkit/Material;": 
            case "(Lcom/sk89q/worldedit/world/biome/BiomeType;)Lorg/bukkit/block/Biome;": 
            case "(Lcom/sk89q/worldedit/world/entity/EntityType;)Lorg/bukkit/entity/EntityType;": {
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction.getOpcode() != 191) continue;
                    InsnList list = new InsnList();
                    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
                    list.add((AbstractInsnNode)new MethodInsnNode(182, Type.getMethodType((String)method.desc).getArgumentTypes()[0].getInternalName(), "getId", "()Ljava/lang/String;", false));
                    list.add((AbstractInsnNode)new MethodInsnNode(184, node.name, standardize.name, standardize.desc, false));
                    switch (Type.getMethodType((String)method.desc).getReturnType().getInternalName()) {
                        case "org/bukkit/Material": {
                            list.add((AbstractInsnNode)new MethodInsnNode(184, "org/bukkit/Material", "getMaterial", "(Ljava/lang/String;)Lorg/bukkit/Material;", false));
                            break;
                        }
                        case "org/bukkit/block/Biome": {
                            list.add((AbstractInsnNode)new MethodInsnNode(184, "org/bukkit/block/Biome", "valueOf", "(Ljava/lang/String;)Lorg/bukkit/block/Biome;", false));
                            break;
                        }
                        case "org/bukkit/entity/EntityType": {
                            list.add((AbstractInsnNode)new MethodInsnNode(184, "org/bukkit/entity/EntityType", "fromName", "(Ljava/lang/String;)Lorg/bukkit/entity/EntityType;", false));
                        }
                    }
                    list.add((AbstractInsnNode)new InsnNode(176));
                    method.instructions.insert(instruction, list);
                    method.instructions.set(instruction, (AbstractInsnNode)new InsnNode(87));
                    return;
                }
                break;
            }
        }
    }

    public static void handleWatchdog(ClassNode node, PluginPatcher.ClassRepo repo) {
        if (node.interfaces.size() == 1 && ((String)node.interfaces.get(0)).equals("com/sk89q/worldedit/extension/platform/Watchdog") && node.name.contains("SpigotWatchdog")) {
            for (MethodNode method : node.methods) {
                if (!method.name.equals("<init>")) continue;
                method.instructions.clear();
                method.instructions.add((AbstractInsnNode)new TypeInsnNode(187, "java/lang/ClassNotFoundException"));
                method.instructions.add((AbstractInsnNode)new InsnNode(89));
                method.instructions.add((AbstractInsnNode)new MethodInsnNode(183, "java/lang/ClassNotFoundException", "<init>", "()V", false));
                method.instructions.add((AbstractInsnNode)new InsnNode(191));
                method.tryCatchBlocks.clear();
                method.localVariables.clear();
                return;
            }
        }
    }
}

