/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.boot.asm;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import io.izzel.arclight.boot.asm.ArclightImplementer;
import io.izzel.arclight.boot.asm.Implementer;
import java.lang.reflect.Modifier;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class InventoryImplementer
implements Implementer {
    private static final Marker MARKER = MarkerManager.getMarker((String)"INVENTORY");
    private static final String BRIDGE_TYPE = "io/izzel/arclight/common/bridge/core/inventory/IInventoryBridge";

    @Override
    public boolean processClass(ClassNode node, ILaunchPluginService.ITransformerLoader transformerLoader) {
        if (Modifier.isInterface(node.access) || node.interfaces.contains(BRIDGE_TYPE)) {
            return false;
        }
        return this.tryImplement(node);
    }

    private boolean tryImplement(ClassNode node) {
        MethodNode stackLimitMethod = null;
        for (MethodNode method : node.methods) {
            if (Modifier.isAbstract(method.access) || !method.name.equals("m_6893_") || !method.desc.equals("()I")) continue;
            stackLimitMethod = method;
            break;
        }
        if (stackLimitMethod == null) {
            return false;
        }
        for (MethodNode method : node.methods) {
            if (!method.name.equals("setMaxStackSize") || !method.desc.equals("(I)V")) continue;
            ArclightImplementer.LOGGER.debug(MARKER, "Found implemented class {}", (Object)node.name);
            return false;
        }
        ArclightImplementer.LOGGER.debug(MARKER, "Implementing inventory for class {}", (Object)node.name);
        FieldNode maxStack = new FieldNode(2, "arclight$maxStack", Type.getType(Integer.class).getDescriptor(), null, null);
        node.fields.add(maxStack);
        node.interfaces.add(BRIDGE_TYPE);
        InsnList list = new InsnList();
        LabelNode labelNode = new LabelNode();
        list.add((AbstractInsnNode)new VarInsnNode(25, 0));
        list.add((AbstractInsnNode)new FieldInsnNode(180, node.name, maxStack.name, maxStack.desc));
        list.add((AbstractInsnNode)new InsnNode(89));
        list.add((AbstractInsnNode)new JumpInsnNode(198, labelNode));
        list.add((AbstractInsnNode)new MethodInsnNode(182, Type.getInternalName(Integer.class), "intValue", "()I", false));
        list.add((AbstractInsnNode)new InsnNode(172));
        list.add((AbstractInsnNode)labelNode);
        list.add((AbstractInsnNode)new InsnNode(87));
        stackLimitMethod.instructions.insert(list);
        MethodNode methodNode = new MethodNode(4097, "setMaxStackSize", "(I)V", null, null);
        InsnList insnList = new InsnList();
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)new VarInsnNode(21, 1));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(Integer.class), "valueOf", Type.getMethodDescriptor((Type)Type.getType(Integer.class), (Type[])new Type[]{Type.INT_TYPE})));
        insnList.add((AbstractInsnNode)new FieldInsnNode(181, node.name, maxStack.name, maxStack.desc));
        insnList.add((AbstractInsnNode)new InsnNode(177));
        methodNode.instructions = insnList;
        node.methods.add(methodNode);
        return true;
    }
}

