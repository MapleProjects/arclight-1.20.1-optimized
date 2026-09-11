/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import io.izzel.arclight.api.PluginPatcher;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

public class RuntimeRepo
implements ClassRepo,
PluginPatcher.ClassRepo {
    private final Map<String, ClassNode> map = new ConcurrentHashMap<String, ClassNode>();

    public ClassNode findClass(String internalName) {
        return this.map.get(internalName);
    }

    public ClassNode findClass(String internalName, int parsingOptions) {
        return this.map.get(internalName);
    }

    public void put(byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept((ClassVisitor)node, 1);
        this.map.put(reader.getClassName(), node);
    }
}

