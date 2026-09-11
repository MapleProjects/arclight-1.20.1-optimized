/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  net.md_5.specialsource.provider.InheritanceProvider
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.collect.Maps;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.md_5.specialsource.provider.InheritanceProvider;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ArclightInterfaceInvokerGen
implements PluginTransformer {
    public static final ArclightInterfaceInvokerGen INSTANCE = new ArclightInterfaceInvokerGen();
    private static final String PREFIX = "net/minecraft/";

    @Override
    public void handleClass(ClassNode node, ClassLoaderRemapper remapper, ArclightRemapConfig config) {
        if (config.remap()) {
            ArclightInterfaceInvokerGen.generate(node, remapper, GlobalClassRepo.inheritanceProvider());
        }
    }

    private static void generate(ClassNode classNode, ClassLoaderRemapper remapper, InheritanceProvider inheritanceProvider) {
        if (ArclightInterfaceInvokerGen.shouldGenerate(classNode.name, inheritanceProvider)) {
            HashSet<Map.Entry<String, String>> set = new HashSet<Map.Entry<String, String>>();
            ArclightInterfaceInvokerGen.interfaceMethods(classNode.name, set, GlobalClassRepo.INSTANCE);
            for (Map.Entry<String, String> entry : set) {
                String name = entry.getKey();
                String desc = entry.getValue();
                MethodInsnNode node = remapper.mapMethod(classNode.name, name, desc);
                desc = remapper.mapMethodDesc(desc);
                if (node == null || node.name.equals(name)) continue;
                boolean extend = false;
                for (MethodNode methodNode : classNode.methods) {
                    if (!methodNode.name.equals(name) || !methodNode.desc.equals(desc)) continue;
                    extend = true;
                    break;
                }
                if (extend) continue;
                MethodNode methodNode = ArclightInterfaceInvokerGen.generateSynthetic(name, desc, node, remapper);
                classNode.methods.add(methodNode);
                ArclightMod.LOGGER.debug("Generated {} redirecting to {}", (Object)(classNode.name + "/" + name + " " + desc), (Object)(node.owner + "/" + node.name + " " + node.desc));
            }
        }
    }

    private static MethodNode generateSynthetic(String name, String desc, MethodInsnNode node, ClassLoaderRemapper remapper) {
        name = remapper.mapType(name);
        MethodNode methodNode = new MethodNode(4097, name, desc, null, null);
        methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
        Type[] types = Type.getArgumentTypes((String)desc);
        int offset = 1;
        for (Type type : types) {
            methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), offset));
            offset += type.getSize();
        }
        methodNode.instructions.add((AbstractInsnNode)node);
        Type returnType = Type.getReturnType((String)desc);
        methodNode.instructions.add((AbstractInsnNode)new InsnNode(returnType.getOpcode(172)));
        return methodNode;
    }

    private static boolean shouldGenerate(String internalName, InheritanceProvider provider) {
        if (internalName == null) {
            return false;
        }
        if (internalName.startsWith(PREFIX)) {
            return true;
        }
        for (String parent : provider.getParents(internalName)) {
            if (!ArclightInterfaceInvokerGen.shouldGenerate(parent, provider)) continue;
            return true;
        }
        return false;
    }

    private static void interfaceMethods(String internalName, Set<Map.Entry<String, String>> set, ClassRepo classRepo) {
        if (internalName == null || internalName.equals("java/lang/Object") || internalName.startsWith(PREFIX)) {
            return;
        }
        ClassNode classNode = classRepo.findClass(internalName);
        if (classNode == null) {
            return;
        }
        ArclightInterfaceInvokerGen.interfaceMethods(classNode.superName, set, classRepo);
        if (classNode.interfaces != null && classNode.interfaces.size() > 0) {
            for (String intf : classNode.interfaces) {
                ArclightInterfaceInvokerGen.interfaceMethods(intf, set, classRepo);
            }
        }
        for (MethodNode methodNode : classNode.methods) {
            set.add(Maps.immutableEntry((Object)methodNode.name, (Object)methodNode.desc));
        }
    }
}

