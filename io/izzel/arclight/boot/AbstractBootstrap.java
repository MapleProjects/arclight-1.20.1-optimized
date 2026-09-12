/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.TypeAdapters
 *  com.google.gson.reflect.TypeToken
 *  net.minecraftforge.forgespi.locating.IModLocator
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.boot;

import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.boot.EnumTypeFactory;
import io.izzel.arclight.i18n.ArclightLocale;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import net.minecraftforge.forgespi.locating.IModLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class AbstractBootstrap {
    protected void dirtyHacks() throws Exception {
        TypeAdapters.ENUM_FACTORY.create(null, TypeToken.get(Object.class));
        Field field = TypeAdapters.class.getDeclaredField("ENUM_FACTORY");
        Object base = Unsafe.staticFieldBase(field);
        long offset = Unsafe.staticFieldOffset(field);
        Unsafe.putObjectVolatile(base, offset, new EnumTypeFactory());
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/mojang/brigadier/tree/CommandNode.class");){
            ClassNode node = new ClassNode();
            new ClassReader(in).accept((ClassVisitor)node, 0);
            FieldNode fieldNode = new FieldNode(73, "CURRENT_COMMAND", "Lcom/mojang/brigadier/tree/CommandNode;", null, null);
            node.fields.add(fieldNode);
            block5: for (MethodNode method : node.methods) {
                if (!method.name.equals("canUse")) continue;
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction.getOpcode() != 185 && instruction.getOpcode() != 182) continue;
                    InsnList assign = new InsnList();
                    assign.add((AbstractInsnNode)new VarInsnNode(25, 0));
                    assign.add((AbstractInsnNode)new FieldInsnNode(179, "com/mojang/brigadier/tree/CommandNode", fieldNode.name, fieldNode.desc));
                    method.instructions.insertBefore(instruction, assign);
                    InsnList reset = new InsnList();
                    reset.add((AbstractInsnNode)new InsnNode(1));
                    reset.add((AbstractInsnNode)new FieldInsnNode(179, "com/mojang/brigadier/tree/CommandNode", fieldNode.name, fieldNode.desc));
                    method.instructions.insert(instruction, assign);
                    continue block5;
                }
            }
            MethodNode removeCommand = new MethodNode();
            removeCommand.access = 1;
            removeCommand.name = "removeCommand";
            removeCommand.desc = Type.getMethodDescriptor((Type)Type.VOID_TYPE, (Type[])new Type[]{Type.getType(String.class)});
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
            removeCommand.instructions.add((AbstractInsnNode)new FieldInsnNode(180, "com/mojang/brigadier/tree/CommandNode", "children", Type.getDescriptor(Map.class)));
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 1));
            removeCommand.instructions.add((AbstractInsnNode)new MethodInsnNode(185, Type.getInternalName(Map.class), "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
            removeCommand.instructions.add((AbstractInsnNode)new FieldInsnNode(180, "com/mojang/brigadier/tree/CommandNode", "literals", Type.getDescriptor(Map.class)));
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 1));
            removeCommand.instructions.add((AbstractInsnNode)new MethodInsnNode(185, Type.getInternalName(Map.class), "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
            removeCommand.instructions.add((AbstractInsnNode)new FieldInsnNode(180, "com/mojang/brigadier/tree/CommandNode", "arguments", Type.getDescriptor(Map.class)));
            removeCommand.instructions.add((AbstractInsnNode)new VarInsnNode(25, 1));
            removeCommand.instructions.add((AbstractInsnNode)new MethodInsnNode(185, Type.getInternalName(Map.class), "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
            removeCommand.instructions.add((AbstractInsnNode)new InsnNode(177));
            node.methods.add(removeCommand);
            ClassWriter cw = new ClassWriter(1);
            node.accept((ClassVisitor)cw);
            byte[] bytes = cw.toByteArray();
            Unsafe.defineClass("com.mojang.brigadier.tree.CommandNode", bytes, 0, bytes.length, IModLocator.class.getClassLoader(), this.getClass().getProtectionDomain());
        }
    }

    protected void setupMod() throws Exception {
        ArclightVersion.setVersion(ArclightVersion.TRIALS);
        Logger logger = LogManager.getLogger((String)"Arclight");
        try (InputStream stream = this.getClass().getModule().getResourceAsStream("/META-INF/MANIFEST.MF");){
            Manifest manifest = new Manifest(stream);
            Attributes attributes = manifest.getMainAttributes();
            String version = attributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            this.extract(this.getClass().getModule().getResourceAsStream("/common.jar"), version);
            String buildTime = attributes.getValue("Implementation-Timestamp");
            logger.info(ArclightLocale.getInstance().get("logo"), (Object)ArclightLocale.getInstance().get("release-name." + ArclightVersion.current().getReleaseName()), (Object)version, (Object)buildTime);
        }
    }

    private void extract(InputStream path, String version) throws Exception {
        Path mod;
        System.setProperty("arclight.version", version);
        Path dir = Paths.get(".arclight", "mod_file");
        if (!Files.exists(dir, new LinkOption[0])) {
            Files.createDirectories(dir, new FileAttribute[0]);
        }
        if (!Files.exists(mod = dir.resolve(version + ".jar"), new LinkOption[0]) || Boolean.getBoolean("arclight.alwaysExtract")) {
            for (Path old : Files.list(dir).toList()) {
                Files.delete(old);
            }
            Files.copy(path, mod, new CopyOption[0]);
        }
    }
}

