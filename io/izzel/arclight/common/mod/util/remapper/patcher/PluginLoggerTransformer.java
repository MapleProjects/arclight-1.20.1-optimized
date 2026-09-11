/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package io.izzel.arclight.common.mod.util.remapper.patcher;

import io.izzel.arclight.common.mod.util.log.ArclightPluginLogger;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class PluginLoggerTransformer
implements PluginTransformer {
    @Override
    public void handleClass(ClassNode node, ClassLoaderRemapper remapper, ArclightRemapConfig config) {
        for (MethodNode mn : node.methods) {
            for (AbstractInsnNode insn : mn.instructions) {
                if (insn.getOpcode() != 184 || !(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode method = (MethodInsnNode)insn;
                if (!method.owner.equals("java/util/logging/Logger") || !method.name.equals("getLogger")) continue;
                method.owner = Type.getInternalName(ArclightPluginLogger.class);
            }
        }
    }
}

