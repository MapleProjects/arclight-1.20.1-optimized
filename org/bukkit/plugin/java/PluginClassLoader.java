/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.io.ByteStreams
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin.java;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class PluginClassLoader
extends URLClassLoader {
    private final JavaPluginLoader loader;
    private final Map<String, Class<?>> classes = new ConcurrentHashMap();
    private final PluginDescriptionFile description;
    private final File dataFolder;
    private final File file;
    private final JarFile jar;
    private final Manifest manifest;
    private final URL url;
    private final ClassLoader libraryLoader;
    final JavaPlugin plugin;
    private JavaPlugin pluginInit;
    private IllegalStateException pluginState;
    private final Set<String> seenIllegalAccess = Collections.newSetFromMap(new ConcurrentHashMap());

    static {
        ClassLoader.registerAsParallelCapable();
    }

    PluginClassLoader(@NotNull JavaPluginLoader loader, @Nullable ClassLoader parent, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file, @Nullable ClassLoader libraryLoader) throws IOException, InvalidPluginException, MalformedURLException {
        super(new URL[]{file.toURI().toURL()}, parent);
        Preconditions.checkArgument((loader != null ? 1 : 0) != 0, (Object)"Loader cannot be null");
        this.loader = loader;
        this.description = description;
        this.dataFolder = dataFolder;
        this.file = file;
        this.jar = new JarFile(file);
        this.manifest = this.jar.getManifest();
        this.url = file.toURI().toURL();
        this.libraryLoader = libraryLoader;
        try {
            Class<JavaPlugin> pluginClass;
            Class<?> jarClass;
            try {
                jarClass = Class.forName(description.getMain(), true, this);
            }
            catch (ClassNotFoundException ex) {
                throw new InvalidPluginException("Cannot find main class `" + description.getMain() + "'", ex);
            }
            try {
                pluginClass = jarClass.asSubclass(JavaPlugin.class);
            }
            catch (ClassCastException ex) {
                throw new InvalidPluginException("main class `" + description.getMain() + "' does not extend JavaPlugin", ex);
            }
            this.plugin = pluginClass.newInstance();
        }
        catch (IllegalAccessException ex) {
            throw new InvalidPluginException("No public constructor", ex);
        }
        catch (InstantiationException ex) {
            throw new InvalidPluginException("Abnormal plugin type", ex);
        }
    }

    @Override
    public URL getResource(String name) {
        return this.findResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return this.findResources(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return this.loadClass0(name, resolve, true, true);
    }

    Class<?> loadClass0(@NotNull String name, boolean resolve, boolean checkGlobal, boolean checkLibraries) throws ClassNotFoundException {
        try {
            Class<?> result = super.loadClass(name, resolve);
            if (checkGlobal || result.getClassLoader() == this) {
                return result;
            }
        }
        catch (ClassNotFoundException result) {
            // empty catch block
        }
        if (checkLibraries && this.libraryLoader != null) {
            try {
                return this.libraryLoader.loadClass(name);
            }
            catch (ClassNotFoundException result) {
                // empty catch block
            }
        }
        if (checkGlobal && (result = this.loader.getClassByName(name, resolve, this.description)) != null) {
            PluginDescriptionFile provider;
            if (result.getClassLoader() instanceof PluginClassLoader && (provider = ((PluginClassLoader)result.getClassLoader()).description) != this.description && !this.seenIllegalAccess.contains(provider.getName()) && !((SimplePluginManager)this.loader.server.getPluginManager()).isTransitiveDepend(this.description, provider)) {
                this.seenIllegalAccess.add(provider.getName());
                if (this.plugin != null) {
                    this.plugin.getLogger().log(Level.WARNING, "Loaded class {0} from {1} which is not a depend or softdepend of this plugin.", new Object[]{name, provider.getFullName()});
                } else {
                    this.loader.server.getLogger().log(Level.WARNING, "[{0}] Loaded class {1} from {2} which is not a depend or softdepend of this plugin.", new Object[]{this.description.getName(), name, provider.getFullName()});
                }
            }
            return result;
        }
        throw new ClassNotFoundException(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
            throw new ClassNotFoundException(name);
        }
        Class<?> result = this.classes.get(name);
        if (result == null) {
            String path = name.replace('.', '/').concat(".class");
            JarEntry entry = this.jar.getJarEntry(path);
            if (entry != null) {
                byte[] classBytes;
                block21: {
                    String pkgName;
                    try {
                        Throwable throwable = null;
                        Object var7_9 = null;
                        try (InputStream is = this.jar.getInputStream(entry);){
                            classBytes = ByteStreams.toByteArray((InputStream)is);
                        }
                        catch (Throwable throwable2) {
                            if (throwable == null) {
                                throwable = throwable2;
                            } else if (throwable != throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            throw throwable;
                        }
                    }
                    catch (IOException ex) {
                        throw new ClassNotFoundException(name, ex);
                    }
                    classBytes = this.loader.server.getUnsafe().processClass(this.description, path, classBytes);
                    int dot = name.lastIndexOf(46);
                    if (dot != -1 && this.getPackage(pkgName = name.substring(0, dot)) == null) {
                        try {
                            if (this.manifest != null) {
                                this.definePackage(pkgName, this.manifest, this.url);
                            } else {
                                this.definePackage(pkgName, null, null, null, null, null, null, null);
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            if (this.getPackage(pkgName) != null) break block21;
                            throw new IllegalStateException("Cannot find package " + pkgName);
                        }
                    }
                }
                CodeSigner[] signers = entry.getCodeSigners();
                CodeSource source = new CodeSource(this.url, signers);
                result = this.defineClass(name, classBytes, 0, classBytes.length, source);
            }
            if (result == null) {
                result = super.findClass(name);
            }
            this.loader.setClass(name, result);
            this.classes.put(name, result);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        }
        finally {
            this.jar.close();
        }
    }

    @NotNull
    Collection<Class<?>> getClasses() {
        return this.classes.values();
    }

    synchronized void initialize(@NotNull JavaPlugin javaPlugin) {
        Preconditions.checkArgument((javaPlugin != null ? 1 : 0) != 0, (Object)"Initializing plugin cannot be null");
        Preconditions.checkArgument((javaPlugin.getClass().getClassLoader() == this ? 1 : 0) != 0, (Object)"Cannot initialize plugin outside of this class loader");
        if (this.plugin != null || this.pluginInit != null) {
            throw new IllegalArgumentException("Plugin already initialized!", this.pluginState);
        }
        this.pluginState = new IllegalStateException("Initial initialization");
        this.pluginInit = javaPlugin;
        javaPlugin.init(this.loader, this.loader.server, this.description, this.dataFolder, this.file, this);
    }
}

