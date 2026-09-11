/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.TransformingClassLoader
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FrameNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LineNumberNode
 *  org.objectweb.asm.tree.LocalVariableNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import cpw.mods.modlauncher.TransformingClassLoader;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public interface RemappingClassLoader {
    public ClassLoaderRemapper getRemapper();

    public ArclightRemapConfig getRemapConfig();

    public static ClassLoader tryRedirect(ClassLoader classLoader) {
        return classLoader instanceof TransformingClassLoader ? RemappingClassLoader.class.getClassLoader() : classLoader;
    }

    public static boolean needRemap(ClassLoader cl) {
        while (cl != null) {
            if (cl instanceof TransformingClassLoader) {
                return true;
            }
            cl = cl.getParent();
        }
        return false;
    }

    public static void implementNeedRemap(ClassNode node) {
        MethodNode needRemap = new MethodNode(4106, "needRemap", Type.getMethodDescriptor((Type)Type.getType(Boolean.TYPE), (Type[])new Type[]{Type.getType(ClassLoader.class)}), null, null);
        InsnList l = needRemap.instructions;
        LabelNode label0 = new LabelNode();
        l.add((AbstractInsnNode)label0);
        l.add((AbstractInsnNode)new LineNumberNode(-101, label0));
        if (node.version >= 50) {
            l.add((AbstractInsnNode)new FrameNode(3, 0, null, 0, null));
        }
        l.add((AbstractInsnNode)new VarInsnNode(25, 0));
        LabelNode label1 = new LabelNode();
        l.add((AbstractInsnNode)new JumpInsnNode(198, label1));
        LabelNode label2 = new LabelNode();
        l.add((AbstractInsnNode)label2);
        l.add((AbstractInsnNode)new LineNumberNode(-102, label2));
        l.add((AbstractInsnNode)new VarInsnNode(25, 0));
        l.add((AbstractInsnNode)new TypeInsnNode(193, Type.getInternalName(TransformingClassLoader.class)));
        LabelNode label3 = new LabelNode();
        l.add((AbstractInsnNode)new JumpInsnNode(153, label3));
        LabelNode label4 = new LabelNode();
        l.add((AbstractInsnNode)label4);
        l.add((AbstractInsnNode)new LineNumberNode(-103, label4));
        l.add((AbstractInsnNode)new InsnNode(4));
        l.add((AbstractInsnNode)new InsnNode(172));
        l.add((AbstractInsnNode)label3);
        l.add((AbstractInsnNode)new LineNumberNode(-101, label3));
        if (node.version >= 50) {
            l.add((AbstractInsnNode)new FrameNode(3, 0, null, 0, null));
        }
        l.add((AbstractInsnNode)new VarInsnNode(25, 0));
        l.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/ClassLoader", "getParent", "()Ljava/lang/ClassLoader;", false));
        l.add((AbstractInsnNode)new VarInsnNode(58, 0));
        l.add((AbstractInsnNode)new JumpInsnNode(167, label0));
        l.add((AbstractInsnNode)label1);
        l.add((AbstractInsnNode)new LineNumberNode(-106, label1));
        if (node.version >= 50) {
            l.add((AbstractInsnNode)new FrameNode(3, 0, null, 0, null));
        }
        l.add((AbstractInsnNode)new InsnNode(3));
        l.add((AbstractInsnNode)new InsnNode(172));
        LabelNode label5 = new LabelNode();
        l.add((AbstractInsnNode)label5);
        needRemap.localVariables.add(new LocalVariableNode("cl", "Ljava/lang/ClassLoader;", null, label0, label5, 0));
        needRemap.visitMaxs(1, 1);
        node.methods.add(needRemap);
    }
}

