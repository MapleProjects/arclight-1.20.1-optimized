/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  org.apache.logging.log4j.jul.LogManager
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package io.izzel.arclight.boot.asm;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import io.izzel.arclight.boot.asm.Implementer;
import java.util.logging.Logger;
import org.apache.logging.log4j.jul.LogManager;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class LoggerTransformer
implements Implementer {
    private static final LogManager JUL_MANAGER = new LogManager();

    @Override
    public boolean processClass(ClassNode node, ILaunchPluginService.ITransformerLoader transformerLoader) {
        boolean transform = false;
        for (MethodNode mn : node.methods) {
            for (AbstractInsnNode insn : mn.instructions) {
                if (insn.getOpcode() != 184 || !(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode method = (MethodInsnNode)insn;
                if (!method.owner.equals("java/util/logging/Logger") || !method.name.equals("getLogger")) continue;
                method.owner = Type.getInternalName(LoggerTransformer.class);
                transform = true;
            }
        }
        return transform;
    }

    public static Logger getLogger(String name) {
        return JUL_MANAGER.getLogger(name);
    }

    public static Logger getLogger(String name, String rb) {
        return JUL_MANAGER.getLogger(name);
    }
}

