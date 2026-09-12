/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TryCatchBlockNode
 */
package io.izzel.arclight.boot.asm;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import io.izzel.arclight.boot.asm.ArclightImplementer;
import io.izzel.arclight.boot.asm.EnumDefinalizer;
import io.izzel.arclight.boot.asm.Implementer;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.function.Function;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

public class SwitchTableFixer
implements Implementer,
Function<byte[], byte[]> {
    public static final SwitchTableFixer INSTANCE = new SwitchTableFixer();
    private static final Marker MARKER = MarkerManager.getMarker((String)"SWITCH_TABLE");
    private static final Set<String> ENUMS = EnumDefinalizer.ENUM;

    @Override
    public byte[] apply(byte[] bytes) {
        ClassNode node = new ClassNode();
        new ClassReader(bytes).accept((ClassVisitor)node, 0);
        this.processClass(node, null);
        ClassWriter writer = new ClassWriter(0);
        node.accept((ClassVisitor)writer);
        return writer.toByteArray();
    }

    @Override
    public boolean processClass(ClassNode node, ILaunchPluginService.ITransformerLoader transformerLoader) {
        boolean success = false;
        for (MethodNode method : node.methods) {
            if (this.inject1(node, method)) {
                success = true;
                continue;
            }
            if (!this.inject2(node, method)) continue;
            success = true;
        }
        return success;
    }

    private boolean inject1(ClassNode node, MethodNode method) {
        if (Modifier.isStatic(method.access) && (method.access & 0x1000) != 0 && method.desc.equals("()[I")) {
            boolean foundTryCatch = false;
            for (TryCatchBlockNode tryCatchBlock : method.tryCatchBlocks) {
                if ("java/lang/NoSuchFieldError".equals(tryCatchBlock.type)) {
                    foundTryCatch = true;
                    continue;
                }
                return false;
            }
            if (!foundTryCatch) {
                return false;
            }
            ArclightImplementer.LOGGER.debug(MARKER, "Candidate switch enum method {} class {}", (Object)(method.name + method.desc), (Object)node.name);
            FieldInsnNode fieldInsnNode = null;
            String enumType = null;
            for (AbstractInsnNode insnNode : method.instructions) {
                AbstractInsnNode newArray;
                AbstractInsnNode next;
                String retType;
                Type methodType;
                Type returnType;
                if (enumType != null) break;
                if (insnNode.getOpcode() == 178 && ((FieldInsnNode)insnNode).desc.equals("[I")) {
                    fieldInsnNode = (FieldInsnNode)insnNode;
                }
                if (insnNode.getOpcode() != 184 || !((MethodInsnNode)insnNode).name.equals("values") || (returnType = (methodType = Type.getMethodType((String)((MethodInsnNode)insnNode).desc)).getReturnType()).getSort() != 9 || returnType.getDimensions() != 1 || !ENUMS.contains(retType = returnType.getElementType().getInternalName()) || (next = insnNode.getNext()).getOpcode() != 190 || (newArray = next.getNext()).getOpcode() != 188 || ((IntInsnNode)newArray).operand != 10) continue;
                enumType = retType;
            }
            if (fieldInsnNode != null && enumType != null) {
                AbstractInsnNode last;
                ArclightImplementer.LOGGER.debug(MARKER, "Find switch(enum {}) table method {} in class {}", enumType, (Object)(method.name + method.desc), (Object)node.name);
                for (last = method.instructions.getLast(); last != null && last.getOpcode() != 176; last = last.getPrevious()) {
                }
                if (last == null) {
                    return false;
                }
                InsnList list = new InsnList();
                list.add((AbstractInsnNode)new LdcInsnNode((Object)Type.getObjectType(enumType)));
                list.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(SwitchTableFixer.class), "fillSwitchTable1", "([ILjava/lang/Class;)[I", false));
                list.add((AbstractInsnNode)new InsnNode(89));
                list.add((AbstractInsnNode)new FieldInsnNode(179, fieldInsnNode.owner, fieldInsnNode.name, fieldInsnNode.desc));
                method.instructions.insertBefore(last, list);
                ArclightImplementer.LOGGER.debug(MARKER, "Inject method in method {}:{}, switch table field is {}", (Object)node.name, (Object)(method.name + method.desc), (Object)(fieldInsnNode.name + fieldInsnNode.desc));
                return true;
            }
        }
        return false;
    }

    public static int[] fillSwitchTable1(int[] arr, Class<? extends Enum<?>> cl) {
        ArclightImplementer.LOGGER.debug(MARKER, "Filling switch table for {}", cl);
        Enum<?>[] enums = cl.getEnumConstants();
        if (arr.length < enums.length) {
            int[] ints = new int[enums.length];
            System.arraycopy(arr, 0, ints, 0, arr.length);
            arr = ints;
        }
        int i = -1;
        for (int j : arr) {
            if (j <= i) continue;
            i = j;
        }
        if (i != -1) {
            for (int k = i; k < enums.length; ++k) {
                arr[k] = enums[k].ordinal();
            }
        }
        return arr;
    }

    private boolean inject2(ClassNode node, MethodNode method) {
        if ((node.access & 0x1000) != 0 && node.methods.size() == 1 && Modifier.isStatic(method.access) && method.name.equals("<clinit>")) {
            boolean foundTryCatch = false;
            for (TryCatchBlockNode tryCatchBlock : method.tryCatchBlocks) {
                if ("java/lang/NoSuchFieldError".equals(tryCatchBlock.type)) {
                    foundTryCatch = true;
                    continue;
                }
                return false;
            }
            if (!foundTryCatch) {
                return false;
            }
            ArclightImplementer.LOGGER.debug(MARKER, "Candidate switch enum method {} class {}", (Object)(method.name + method.desc), (Object)node.name);
            FieldInsnNode fieldInsnNode = null;
            String enumType = null;
            for (AbstractInsnNode insnNode : method.instructions) {
                AbstractInsnNode putStatic;
                AbstractInsnNode newArray;
                AbstractInsnNode next;
                String retType;
                Type methodType;
                Type returnType;
                if (insnNode.getOpcode() != 184 || !((MethodInsnNode)insnNode).name.equals("values") || (returnType = (methodType = Type.getMethodType((String)((MethodInsnNode)insnNode).desc)).getReturnType()).getSort() != 9 || returnType.getDimensions() != 1 || !ENUMS.contains(retType = returnType.getElementType().getInternalName()) || (next = insnNode.getNext()).getOpcode() != 190 || (newArray = next.getNext()).getOpcode() != 188 || ((IntInsnNode)newArray).operand != 10 || (putStatic = newArray.getNext()).getOpcode() != 179 || !((FieldInsnNode)putStatic).desc.equals("[I")) continue;
                enumType = retType;
                fieldInsnNode = (FieldInsnNode)putStatic;
                break;
            }
            if (fieldInsnNode != null) {
                AbstractInsnNode last;
                ArclightImplementer.LOGGER.debug(MARKER, "Find switch(enum {}) table method {} in class {}", enumType, (Object)(method.name + method.desc), (Object)node.name);
                for (last = method.instructions.getLast(); last != null && last.getOpcode() != 177; last = last.getPrevious()) {
                }
                if (last == null) {
                    return false;
                }
                InsnList list = new InsnList();
                list.add((AbstractInsnNode)new FieldInsnNode(178, fieldInsnNode.owner, fieldInsnNode.name, fieldInsnNode.desc));
                list.add((AbstractInsnNode)new LdcInsnNode((Object)Type.getObjectType(enumType)));
                list.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(SwitchTableFixer.class), "fillSwitchTable2", "([ILjava/lang/Class;)[I", false));
                list.add((AbstractInsnNode)new FieldInsnNode(179, fieldInsnNode.owner, fieldInsnNode.name, fieldInsnNode.desc));
                method.instructions.insertBefore(last, list);
                ArclightImplementer.LOGGER.debug(MARKER, "Inject method in method {}:{}, switch table field is {}", (Object)node.name, (Object)(method.name + method.desc), (Object)(fieldInsnNode.name + fieldInsnNode.desc));
                return true;
            }
        }
        return false;
    }

    public static int[] fillSwitchTable2(int[] arr, Class<? extends Enum<?>> cl) {
        ArclightImplementer.LOGGER.debug(MARKER, "Filling switch table for {}", cl);
        Enum<?>[] enums = cl.getEnumConstants();
        if (arr.length < enums.length) {
            int[] ints = new int[enums.length];
            System.arraycopy(arr, 0, ints, 0, arr.length);
            arr = ints;
        }
        return arr;
    }
}

