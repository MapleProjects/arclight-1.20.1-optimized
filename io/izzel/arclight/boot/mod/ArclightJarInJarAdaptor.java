/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.loading.FMLLoader
 *  net.minecraftforge.fml.loading.moddiscovery.JarInJarDependencyLocator
 *  net.minecraftforge.fml.loading.moddiscovery.ModDiscoverer
 *  net.minecraftforge.forgespi.locating.IDependencyLocator
 *  net.minecraftforge.forgespi.locating.IModFile
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package io.izzel.arclight.boot.mod;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.JarInJarDependencyLocator;
import net.minecraftforge.fml.loading.moddiscovery.ModDiscoverer;
import net.minecraftforge.forgespi.locating.IDependencyLocator;
import net.minecraftforge.forgespi.locating.IModFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArclightJarInJarAdaptor
implements IDependencyLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"ArclightJiJ");
    private final IDependencyLocator delegate;

    public ArclightJarInJarAdaptor(IDependencyLocator delegate) {
        this.delegate = delegate;
    }

    public List<IModFile> scanMods(Iterable<IModFile> loadedMods) {
        return this.delegate.scanMods(loadedMods).stream().filter(it -> {
            Optional<Module> optional = this.getClass().getModule().getLayer().findModule(it.getModFileInfo().moduleName());
            optional.ifPresent(module -> LOGGER.info("Skip jij dependency {}@{} because Arclight has {}", new Object[]{it.getModFileInfo().moduleName(), it.getModFileInfo().versionString(), module.getDescriptor().toNameAndVersion()}));
            return optional.isEmpty();
        }).toList();
    }

    public String name() {
        return "arclight_jij";
    }

    public void scanFile(IModFile modFile, Consumer<Path> pathConsumer) {
        this.delegate.scanFile(modFile, pathConsumer);
    }

    public void initArguments(Map<String, ?> arguments) {
        this.delegate.initArguments(arguments);
    }

    public boolean isValid(IModFile modFile) {
        return this.delegate.isValid(modFile);
    }

    static void inject() {
        try {
            Field field = FMLLoader.class.getDeclaredField("modDiscoverer");
            field.setAccessible(true);
            ModDiscoverer discoverer = (ModDiscoverer)field.get(null);
            Field locatorField = ModDiscoverer.class.getDeclaredField("dependencyLocatorList");
            locatorField.setAccessible(true);
            List locatorList = (List)locatorField.get(discoverer);
            List<IDependencyLocator> newList = locatorList.stream().map(it -> {
                if (it instanceof JarInJarDependencyLocator) {
                    return new ArclightJarInJarAdaptor((IDependencyLocator)it);
                }
                return it;
            }).toList();
            locatorField.set(discoverer, newList);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

