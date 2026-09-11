/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.PluginPatcher
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper.patcher;

import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import io.izzel.arclight.common.mod.util.remapper.patcher.integrated.IntegratedPatcher;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.objectweb.asm.tree.ClassNode;

public class ArclightPluginPatcher
implements PluginTransformer {
    private final List<PluginPatcher> list;

    public ArclightPluginPatcher(List<PluginPatcher> list) {
        this.list = list;
    }

    @Override
    public void handleClass(ClassNode node, ClassLoaderRemapper remapper, ArclightRemapConfig config) {
        for (PluginPatcher patcher : this.list) {
            patcher.handleClass(node, (PluginPatcher.ClassRepo)GlobalClassRepo.INSTANCE);
        }
    }

    public static List<PluginPatcher> load(List<PluginTransformer> transformerList) {
        ArrayList<PluginPatcher> list = new ArrayList<PluginPatcher>();
        File pluginFolder = new File("plugins");
        if (pluginFolder.exists()) {
            ArclightMod.LOGGER.info("patcher.loading");
            File[] files = pluginFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isFile() || !file.getName().endsWith(".jar")) continue;
                    ArclightPluginPatcher.loadFromJar(file).ifPresent(list::add);
                }
                if (!list.isEmpty()) {
                    ArclightMod.LOGGER.info("patcher.loaded", (Object)list.size());
                }
            }
        }
        list.add(new IntegratedPatcher());
        list.sort(Comparator.comparing(PluginPatcher::priority));
        transformerList.add(new ArclightPluginPatcher(list));
        return list;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Optional<PluginPatcher> loadFromJar(File file) {
        try (JarFile jarFile = new JarFile(file);){
            JarEntry jarEntry = jarFile.getJarEntry("plugin.yml");
            if (jarEntry == null) return Optional.empty();
            try (InputStream stream = jarFile.getInputStream(jarEntry);){
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
                String name = configuration.getString("arclight.patcher");
                if (name == null) return Optional.empty();
                URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, ArclightPluginPatcher.class.getClassLoader());
                Class<?> clazz = Class.forName(name, false, loader);
                PluginPatcher patcher = clazz.asSubclass(PluginPatcher.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                Optional<PluginPatcher> optional = Optional.of(patcher);
                return optional;
            }
        }
        catch (Throwable e) {
            ArclightMod.LOGGER.debug("patcher.load-error", e);
        }
        return Optional.empty();
    }
}

