/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.md_5.specialsource.JarRemapper
 *  net.md_5.specialsource.provider.InheritanceProvider
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.collect.ImmutableSet;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.md_5.specialsource.JarRemapper;
import net.md_5.specialsource.provider.InheritanceProvider;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.tree.ClassNode;

public class PluginInheritanceProvider
implements InheritanceProvider {
    private static final Map<String, Collection<String>> SHARED_INHERITANCE_MAP = new ConcurrentHashMap<String, Collection<String>>();
    private final ClassRepo classRepo;

    public PluginInheritanceProvider(ClassRepo classRepo) {
        this.classRepo = classRepo;
    }

    public Collection<String> getParents(String className) {
        ClassNode node = this.classRepo.findClass(className);
        if (node == null) {
            return Collections.emptyList();
        }
        HashSet<String> parents = new HashSet<String>(node.interfaces);
        if (node.superName != null) {
            parents.add(node.superName);
        }
        return parents;
    }

    public Collection<String> getAll(String className) {
        Collection<String> collection = SHARED_INHERITANCE_MAP.get(className);
        if (collection != null) {
            return collection;
        }
        ClassNode node = this.classRepo.findClass(className);
        if (node == null) {
            return ImmutableSet.of((Object)"java/lang/Object");
        }
        HashSet<String> parents = new HashSet<String>(node.interfaces);
        parents.add(node.name);
        if (node.superName != null) {
            parents.add(node.superName);
            parents.addAll(this.getAll(node.superName));
        } else {
            parents.add("java/lang/Object");
        }
        SHARED_INHERITANCE_MAP.put(className, parents);
        return parents;
    }

    static class Remapping
    extends PluginInheritanceProvider {
        private final PluginInheritanceProvider provider;

        public Remapping(ClassRepo classRepo, PluginInheritanceProvider provider) {
            super(classRepo);
            this.provider = provider;
        }

        @Override
        public Collection<String> getAll(String className) {
            return this.provider.getAll(className).stream().map(arg_0 -> ((JarRemapper)ArclightRemapper.getNmsMapper()).map(arg_0)).collect(Collectors.toSet());
        }

        @Override
        public Collection<String> getParents(String className) {
            return this.provider.getParents(className).stream().map(arg_0 -> ((JarRemapper)ArclightRemapper.getNmsMapper()).map(arg_0)).collect(Collectors.toSet());
        }
    }
}

