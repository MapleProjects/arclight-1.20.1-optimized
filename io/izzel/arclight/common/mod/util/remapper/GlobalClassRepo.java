/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.tree.ClassNode
 *  org.spongepowered.asm.service.MixinService
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.common.mod.util.remapper.PluginInheritanceProvider;
import io.izzel.arclight.common.mod.util.remapper.RuntimeRepo;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.MixinService;

public class GlobalClassRepo
implements ClassRepo,
PluginPatcher.ClassRepo {
    public static final GlobalClassRepo INSTANCE = new GlobalClassRepo();
    private static final PluginInheritanceProvider PROVIDER = new PluginInheritanceProvider(INSTANCE);
    private static final PluginInheritanceProvider REMAPPING = new PluginInheritanceProvider.Remapping(INSTANCE, PROVIDER);
    private final LoadingCache<String, ClassNode> cache = CacheBuilder.newBuilder().maximumSize(256L).expireAfterAccess(1L, TimeUnit.MINUTES).build(CacheLoader.from(this::findParallel));
    private final Set<ClassRepo> repos = Collections.newSetFromMap(new ConcurrentHashMap());
    private final RuntimeRepo runtimeRepo = new RuntimeRepo();

    private GlobalClassRepo() {
        this.repos.add(this.runtimeRepo);
    }

    public ClassNode findClass(String internalName) {
        try {
            return (ClassNode)this.cache.get((Object)internalName);
        }
        catch (Throwable e) {
            return null;
        }
    }

    public ClassNode findClass(String internalName, int parsingOptions) {
        if (parsingOptions == 1) {
            return this.findClass(internalName);
        }
        return this.repos.parallelStream().filter(PluginPatcher.ClassRepo.class::isInstance).map(PluginPatcher.ClassRepo.class::cast).map(it -> it.findClass(internalName, parsingOptions)).filter(Objects::nonNull).findAny().orElseGet(() -> this.findMinecraft(internalName));
    }

    private ClassNode findParallel(String internalName) {
        return this.repos.parallelStream().map(it -> it.findClass(internalName)).filter(Objects::nonNull).findAny().orElseGet(() -> this.findMinecraft(internalName));
    }

    private ClassNode findMinecraft(String internalName) {
        try {
            return MixinService.getService().getBytecodeProvider().getClassNode(internalName);
        }
        catch (Exception e) {
            throw new RuntimeException(internalName, e);
        }
    }

    public void addRepo(ClassRepo repo) {
        this.repos.add(repo);
    }

    public void removeRepo(ClassRepo repo) {
        this.repos.remove(repo);
    }

    public static PluginInheritanceProvider inheritanceProvider() {
        return PROVIDER;
    }

    public static PluginInheritanceProvider remappingProvider() {
        return REMAPPING;
    }

    public static RuntimeRepo runtimeRepo() {
        return GlobalClassRepo.INSTANCE.runtimeRepo;
    }
}

