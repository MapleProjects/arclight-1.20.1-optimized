/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.FrameNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LineNumberNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.collect.ImmutableMap;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import io.izzel.arclight.common.mod.util.remapper.RemappingClassLoader;
import io.izzel.arclight.common.mod.util.remapper.generated.RemappingURLClassLoader;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Map;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ClassLoaderAdapter
implements PluginTransformer {
    public static final ClassLoaderAdapter INSTANCE = new ClassLoaderAdapter();
    private static final Marker MARKER = MarkerManager.getMarker((String)"CLADAPTER");
    private static final String CLASSLOADER = "java/lang/ClassLoader";
    private final Map<String, String> classLoaderTypes = ImmutableMap.builder().put((Object)Type.getInternalName(URLClassLoader.class), (Object)Type.getInternalName(RemappingURLClassLoader.class)).build();

    @Override
    public void handleClass(ClassNode node, ClassLoaderRemapper remapper, ArclightRemapConfig config) {
        for (MethodNode methodNode : node.methods) {
            for (AbstractInsnNode insnNode : methodNode.instructions) {
                AbstractInsnNode next;
                if (insnNode.getOpcode() != 187) continue;
                TypeInsnNode typeInsnNode = (TypeInsnNode)insnNode;
                String replace = this.classLoaderTypes.get(typeInsnNode.desc);
                if (replace == null) continue;
                for (next = typeInsnNode.getNext(); !(next == null || next.getOpcode() == 183 && ((MethodInsnNode)next).name.equals("<init>") && ((MethodInsnNode)next).owner.equals(typeInsnNode.desc)); next = next.getNext()) {
                }
                if (next == null) continue;
                ArclightMod.LOGGER.debug(MARKER, "Found new {}/{} call in {} {}", (Object)typeInsnNode.desc, (Object)(((MethodInsnNode)next).name + ((MethodInsnNode)next).desc), (Object)node.name, (Object)(methodNode.name + methodNode.desc));
                ((MethodInsnNode)next).owner = replace;
                typeInsnNode.desc = replace;
            }
        }
        ClassInfo info = this.classInfo(node);
        if (info == null) {
            return;
        }
        ArclightMod.LOGGER.debug(MARKER, "Transforming classloader class {}", (Object)node.name);
        if (!info.remapping) {
            this.implementIntf(node);
        }
        for (MethodNode methodNode : node.methods) {
            for (AbstractInsnNode insnNode : methodNode.instructions) {
                MethodInsnNode methodInsnNode;
                if (!(insnNode instanceof MethodInsnNode) || (methodInsnNode = (MethodInsnNode)insnNode).getOpcode() != 183 || !methodNode.name.equals("<init>") || !methodInsnNode.name.equals("<init>") || !methodInsnNode.owner.equals(node.superName)) continue;
                methodInsnNode.owner = info.superName;
            }
        }
        node.superName = info.superName;
    }

    private void implementIntf(ClassNode node) {
        ArclightMod.LOGGER.debug(MARKER, "Implementing RemappingClassLoader for class {}", (Object)node.name);
        FieldNode remapper = new FieldNode(4098, "remapper", Type.getDescriptor(ClassLoaderRemapper.class), null, null);
        MethodNode getRemapper = new MethodNode(4097, "getRemapper", Type.getMethodDescriptor((Type)Type.getType(ClassLoaderRemapper.class), (Type[])new Type[0]), null, null);
        InsnList list = new InsnList();
        LabelNode labelNode = new LabelNode();
        list.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list.add((AbstractInsnNode)new FieldInsnNode(180, node.name, remapper.name, remapper.desc));
        list.add((AbstractInsnNode)new JumpInsnNode(199, labelNode));
        list.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list.add((AbstractInsnNode)new InsnNode(89));
        list.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(ArclightRemapper.class), "createClassLoaderRemapper", Type.getMethodDescriptor((Type)Type.getType(ClassLoaderRemapper.class), (Type[])new Type[]{Type.getType(ClassLoader.class)}), false));
        list.add((AbstractInsnNode)new FieldInsnNode(181, node.name, remapper.name, remapper.desc));
        list.add((AbstractInsnNode)labelNode);
        if ((node.version & 0xFFFF) >= 50) {
            list.add((AbstractInsnNode)new FrameNode(3, 0, null, 0, null));
        }
        list.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list.add((AbstractInsnNode)new FieldInsnNode(180, node.name, remapper.name, remapper.desc));
        list.add((AbstractInsnNode)new InsnNode(176));
        getRemapper.instructions = list;
        FieldNode remapConfig = new FieldNode(4098, "arclight$remapConfig", Type.getDescriptor(ArclightRemapConfig.class), null, null);
        MethodNode getConfig = new MethodNode(4097, "getRemapConfig", Type.getMethodDescriptor((Type)Type.getType(ArclightRemapConfig.class), (Type[])new Type[0]), null, null);
        String config = Type.getInternalName(ArclightRemapConfig.class);
        InsnList list2 = new InsnList();
        LabelNode getfield = new LabelNode();
        LabelNode putfield = new LabelNode();
        LabelNode aret = new LabelNode();
        list2.add((AbstractInsnNode)getfield);
        list2.add((AbstractInsnNode)new LineNumberNode(-101, getfield));
        list2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list2.add((AbstractInsnNode)new FieldInsnNode(180, node.name, remapConfig.name, remapConfig.desc));
        list2.add((AbstractInsnNode)new JumpInsnNode(199, aret));
        list2.add((AbstractInsnNode)putfield);
        list2.add((AbstractInsnNode)new LineNumberNode(-103, putfield));
        list2.add((AbstractInsnNode)new TypeInsnNode(187, config));
        list2.add((AbstractInsnNode)new InsnNode(89));
        list2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list2.add((AbstractInsnNode)new MethodInsnNode(184, node.name, "needRemap", Type.getMethodDescriptor((Type)Type.getType(Boolean.TYPE), (Type[])new Type[]{Type.getType(ClassLoader.class)})));
        list2.add((AbstractInsnNode)new MethodInsnNode(183, config, "<init>", "(Z)V"));
        list2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list2.add((AbstractInsnNode)new InsnNode(95));
        list2.add((AbstractInsnNode)new FieldInsnNode(181, node.name, remapConfig.name, remapConfig.desc));
        list2.add((AbstractInsnNode)aret);
        list2.add((AbstractInsnNode)new LineNumberNode(-105, aret));
        if ((node.version & 0xFFFF) >= 50) {
            list2.add((AbstractInsnNode)new FrameNode(3, 0, null, 0, null));
        }
        list2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list2.add((AbstractInsnNode)new FieldInsnNode(180, node.name, remapConfig.name, remapConfig.desc));
        list2.add((AbstractInsnNode)new InsnNode(176));
        getConfig.instructions = list2;
        getConfig.visitMaxs(3, 1);
        node.fields.add(remapper);
        node.fields.add(remapConfig);
        RemappingClassLoader.implementNeedRemap(node);
        node.methods.add(getRemapper);
        node.methods.add(getConfig);
        node.interfaces.add(Type.getInternalName(RemappingClassLoader.class));
    }

    private ClassInfo classInfo(ClassNode node) {
        String s;
        ClassInfo info = new ClassInfo();
        Collection<String> parents = GlobalClassRepo.inheritanceProvider().getAll(node.superName);
        if (!parents.contains(CLASSLOADER)) {
            return null;
        }
        for (String s2 : this.classLoaderTypes.keySet()) {
            if (!parents.contains(s2)) continue;
            info.remapping = true;
            break;
        }
        info.superName = (s = this.classLoaderTypes.get(node.superName)) != null ? s : node.superName;
        return info;
    }

    private static class ClassInfo {
        private String superName;
        private boolean remapping;

        private ClassInfo() {
        }
    }
}

