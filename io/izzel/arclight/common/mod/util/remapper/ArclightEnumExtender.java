/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import io.izzel.arclight.common.mod.util.remapper.ArclightRedirectAdapter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ArclightEnumExtender {
    private static final Logger LOGGER = LogManager.getLogger((String)"EnumExtender");

    public static void process(ClassNode node, List<String> names) {
        String desc = Type.getObjectType((String)node.name).getDescriptor();
        FieldNode values = ArclightEnumExtender.tryGetEnumArray(node);
        values.access &= 0xFFFFFFEF;
        Set<String> set = ArclightEnumExtender.countEnum(node);
        ArclightEnumExtender.tryCreateCtor(node);
        int count = set.size();
        for (MethodNode method : node.methods) {
            if (!method.name.equals("<clinit>")) continue;
            InsnList list = new InsnList();
            InsnList postList = new InsnList();
            for (String name : names) {
                boolean found = false;
                if (name.startsWith("minecraft:")) {
                    if (!set.contains(ArclightEnumExtender.standardize(name.substring("minecraft".length() + 1)))) {
                        LOGGER.warn("Expect {} found in {}, but not", (Object)name, (Object)node.name);
                    } else {
                        found = true;
                    }
                }
                if (!found) {
                    name = ArclightEnumExtender.standardize(name);
                    FieldNode fieldNode = new FieldNode(16409, name, desc, null, null);
                    node.fields.add(fieldNode);
                    list.add((AbstractInsnNode)new TypeInsnNode(187, node.name));
                    list.add((AbstractInsnNode)new InsnNode(89));
                    list.add((AbstractInsnNode)new LdcInsnNode((Object)name));
                    list.add(ArclightRedirectAdapter.loadInt(count));
                    list.add((AbstractInsnNode)new MethodInsnNode(183, node.name, "<init>", "(Ljava/lang/String;I)V", false));
                    list.add((AbstractInsnNode)new FieldInsnNode(179, node.name, name, desc));
                    postList.add((AbstractInsnNode)new InsnNode(89));
                    postList.add(ArclightRedirectAdapter.loadInt(count));
                    postList.add((AbstractInsnNode)new FieldInsnNode(178, node.name, name, desc));
                    postList.add((AbstractInsnNode)new InsnNode(83));
                    LOGGER.info("Added {} to {}", (Object)name, (Object)node.name);
                }
                ++count;
            }
            list.add((AbstractInsnNode)new FieldInsnNode(178, node.name, values.name, values.desc));
            list.add(ArclightRedirectAdapter.loadInt(0));
            list.add(ArclightRedirectAdapter.loadInt(count));
            list.add((AbstractInsnNode)new TypeInsnNode(189, node.name));
            list.add((AbstractInsnNode)new InsnNode(89));
            list.add((AbstractInsnNode)new FieldInsnNode(179, node.name, values.name, values.desc));
            list.add(ArclightRedirectAdapter.loadInt(0));
            list.add(ArclightRedirectAdapter.loadInt(set.size()));
            list.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V"));
            list.add((AbstractInsnNode)new FieldInsnNode(178, node.name, values.name, values.desc));
            postList.add((AbstractInsnNode)new InsnNode(87));
            for (AbstractInsnNode insnNode : method.instructions) {
                if (insnNode.getOpcode() != 177) continue;
                method.instructions.insertBefore(insnNode, list);
                method.instructions.insertBefore(insnNode, postList);
            }
        }
    }

    private static void tryCreateCtor(ClassNode node) {
        boolean found = false;
        for (MethodNode method : node.methods) {
            if (!method.name.equals("<init>") || !method.desc.equals("(Ljava/lang/String;I)V")) continue;
            found = true;
            break;
        }
        if (!found) {
            MethodNode methodNode = new MethodNode(4098, "<init>", "(Ljava/lang/String;I)V", null, null);
            InsnList list = new InsnList();
            list.add((AbstractInsnNode)new VarInsnNode(25, 0));
            list.add((AbstractInsnNode)new VarInsnNode(25, 1));
            list.add((AbstractInsnNode)new VarInsnNode(21, 2));
            list.add((AbstractInsnNode)new MethodInsnNode(183, "java/lang/Enum", "<init>", "(Ljava/lang/String;I)V", false));
            list.add((AbstractInsnNode)new InsnNode(177));
            methodNode.instructions = list;
            node.methods.add(methodNode);
        }
    }

    private static String standardize(String str) {
        return str.replace(':', '_').replaceAll("\\s+", "_").replaceAll("\\W", "").toUpperCase(Locale.ENGLISH);
    }

    private static Set<String> countEnum(ClassNode node) {
        HashSet<String> ret = new HashSet<String>();
        for (FieldNode field : node.fields) {
            if ((field.access & 0x4000) == 0) continue;
            ret.add(field.name);
        }
        return ret;
    }

    private static FieldNode tryGetEnumArray(ClassNode node) {
        String desc = "[" + Type.getObjectType((String)node.name).getDescriptor();
        ArrayList<FieldNode> candidates = new ArrayList<FieldNode>();
        for (FieldNode field : node.fields) {
            if (!Modifier.isStatic(field.access) || !field.desc.equals(desc)) continue;
            candidates.add(field);
        }
        if (candidates.size() != 1) {
            throw new RuntimeException("No $VALUES candidate found in enum class " + node.name);
        }
        return (FieldNode)candidates.get(0);
    }
}

