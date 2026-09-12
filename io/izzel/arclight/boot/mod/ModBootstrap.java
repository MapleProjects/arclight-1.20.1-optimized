/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.cl.JarModuleFinder
 *  cpw.mods.cl.ModuleClassLoader
 *  cpw.mods.jarhandling.SecureJar
 *  cpw.mods.jarhandling.SecureJar$ModuleDataProvider
 *  cpw.mods.jarhandling.impl.Jar
 *  cpw.mods.modlauncher.LaunchPluginHandler
 *  cpw.mods.modlauncher.Launcher
 *  cpw.mods.util.LambdaExceptionUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package io.izzel.arclight.boot.mod;

import cpw.mods.cl.JarModuleFinder;
import cpw.mods.cl.ModuleClassLoader;
import cpw.mods.jarhandling.SecureJar;
import cpw.mods.jarhandling.impl.Jar;
import cpw.mods.modlauncher.LaunchPluginHandler;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.util.LambdaExceptionUtils;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.boot.AbstractBootstrap;
import io.izzel.arclight.boot.asm.ArclightImplementer;
import io.izzel.arclight.forgeinstaller.ForgeInstaller;
import java.io.File;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ResolvedModule;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSigner;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.jar.Manifest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class ModBootstrap
extends AbstractBootstrap {
    private static ModBoot modBoot;
    private static final Set<String> EXCLUDES;

    static void run() {
        Optional plugin = Launcher.INSTANCE.environment().findLaunchPlugin("arclight_implementer");
        if (plugin.isPresent()) {
            return;
        }
        Logger logger = LogManager.getLogger((String)"Arclight");
        Marker marker = MarkerManager.getMarker((String)"INSTALL");
        try {
            List<Path> paths = ForgeInstaller.modInstall(s -> logger.info(marker, s));
            ModBootstrap.load(paths.toArray(new Path[0]));
            new ModBootstrap().inject();
        }
        catch (Throwable e) {
            logger.error("Error bootstrap Arclight", e);
            throw new RuntimeException(e);
        }
    }

    public static void postRun() {
        if (modBoot == null) {
            return;
        }
        try {
            Configuration conf = modBoot.configuration();
            ClassLoader parent = modBoot.parent();
            ModuleClassLoader classLoader = (ModuleClassLoader)Thread.currentThread().getContextClassLoader();
            Field parentField = ModuleClassLoader.class.getDeclaredField("parentLoaders");
            Map parentLoaders = (Map)Unsafe.getObject(classLoader, Unsafe.objectFieldOffset(parentField));
            for (ResolvedModule mod : conf.modules()) {
                for (String pk : mod.reference().descriptor().packages()) {
                    parentLoaders.put(pk, parent);
                }
            }
            modBoot = null;
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void inject() throws Throwable {
        this.dirtyHacks();
        this.setupMod();
        this.injectClassPath();
        this.injectLaunchPlugin();
    }

    private void injectClassPath() throws Throwable {
        ClassLoader platform = ClassLoader.getPlatformClassLoader();
        Field ucpField = platform.getClass().getSuperclass().getDeclaredField("ucp");
        Object ucp = Unsafe.lookup().unreflectGetter(ucpField).invoke(platform);
        if (ucp == null) {
            for (ResolvedModule module : ModuleLayer.boot().configuration().modules()) {
                URI uri;
                Optional<URI> optional = module.reference().location();
                if (!optional.isPresent() || !(uri = optional.get()).getScheme().equals("file")) continue;
                ForgeInstaller.addToPath(new File(uri).toPath());
            }
        }
    }

    private void injectLaunchPlugin() throws Exception {
        Launcher instance = Launcher.INSTANCE;
        Field launchPlugins = Launcher.class.getDeclaredField("launchPlugins");
        launchPlugins.setAccessible(true);
        LaunchPluginHandler handler = (LaunchPluginHandler)launchPlugins.get(instance);
        Field plugins = LaunchPluginHandler.class.getDeclaredField("plugins");
        plugins.setAccessible(true);
        Map map = (Map)plugins.get(handler);
        ArclightImplementer plugin = new ArclightImplementer();
        map.put(plugin.name(), plugin);
    }

    private static void load(Path[] file) throws Throwable {
        ModuleClassLoader classLoader = (ModuleClassLoader)ModBootstrap.class.getClassLoader();
        SecureJar secureJar = SecureJar.from((path, base) -> EXCLUDES.stream().noneMatch(path::startsWith), (Path[])file);
        Field configurationField = ModuleClassLoader.class.getDeclaredField("configuration");
        long confOffset = Unsafe.objectFieldOffset(configurationField);
        Configuration oldConf = (Configuration)Unsafe.getObject(classLoader, confOffset);
        Configuration conf = oldConf.resolveAndBind((ModuleFinder)JarModuleFinder.of((SecureJar[])new SecureJar[]{secureJar}), ModuleFinder.of(new Path[0]), List.of(secureJar.name()));
        modBoot = new ModBoot(conf, (ClassLoader)classLoader);
        Unsafe.putObjectVolatile(classLoader, confOffset, conf);
        Field pkgField = ModuleClassLoader.class.getDeclaredField("packageLookup");
        Map packageLookup = (Map)Unsafe.getObject(classLoader, Unsafe.objectFieldOffset(pkgField));
        Field rootField = ModuleClassLoader.class.getDeclaredField("resolvedRoots");
        Map resolvedRoots = (Map)Unsafe.getObject(classLoader, Unsafe.objectFieldOffset(rootField));
        MethodHandle moduleRefCtor = Unsafe.lookup().findConstructor(Class.forName("cpw.mods.cl.JarModuleFinder$JarModuleReference"), MethodType.methodType(Void.TYPE, SecureJar.ModuleDataProvider.class));
        for (ResolvedModule mod : conf.modules()) {
            for (String pk : mod.reference().descriptor().packages()) {
                packageLookup.put(pk, mod);
            }
            resolvedRoots.put(mod.name(), moduleRefCtor.invokeWithArguments(new JarModuleDataProvider((Jar)secureJar)));
        }
    }

    static {
        EXCLUDES = Set.of("org/apache/maven/artifact/repository/metadata");
    }

    public record ModBoot(Configuration configuration, ClassLoader parent) {
    }

    private record JarModuleDataProvider(Jar jar) implements SecureJar.ModuleDataProvider
    {
        public String name() {
            return this.jar.name();
        }

        public ModuleDescriptor descriptor() {
            return this.jar.computeDescriptor();
        }

        public URI uri() {
            return this.jar.getURI();
        }

        public Optional<URI> findFile(String name) {
            return this.jar.findFile(name);
        }

        public Optional<InputStream> open(String name) {
            return this.jar.findFile(name).map(Paths::get).map(LambdaExceptionUtils.rethrowFunction(x$0 -> Files.newInputStream(x$0, new OpenOption[0])));
        }

        public Manifest getManifest() {
            return this.jar.getManifest();
        }

        public CodeSigner[] verifyAndGetSigners(String cname, byte[] bytes) {
            return this.jar.verifyAndGetSigners(cname, bytes);
        }
    }
}

