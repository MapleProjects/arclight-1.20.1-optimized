/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterators
 *  com.google.common.io.ByteStreams
 *  io.izzel.arclight.i18n.ArclightConfig
 *  io.izzel.tools.product.Product2
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.collect.Iterators;
import com.google.common.io.ByteStreams;
import io.izzel.arclight.common.bridge.bukkit.JavaPluginLoaderBridge;
import io.izzel.arclight.common.bridge.bukkit.PluginClassLoaderBridge;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.RemappingClassLoader;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.tools.product.Product2;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets={"org.bukkit.plugin.java.PluginClassLoader"}, remap=false)
public abstract class PluginClassLoaderMixin
extends URLClassLoader
implements RemappingClassLoader,
PluginClassLoaderBridge {
    @Mutable
    @Shadow
    @Final
    private Map<String, Class<?>> classes;
    @Mutable
    @Shadow
    @Final
    private JavaPluginLoader loader;
    @Mutable
    @Shadow
    @Final
    private PluginDescriptionFile description;
    @Mutable
    @Shadow
    @Final
    private Manifest manifest;
    @Mutable
    @Shadow
    @Final
    private URL url;
    @Mutable
    @Shadow
    @Final
    private ClassLoader libraryLoader;
    @Mutable
    @Shadow
    @Final
    JavaPlugin plugin;
    @Mutable
    @Shadow
    @Final
    private Set<String> seenIllegalAccess;
    private ClassLoaderRemapper remapper;

    @Override
    public ClassLoaderRemapper getRemapper() {
        if (this.remapper == null) {
            this.remapper = ArclightRemapper.createClassLoaderRemapper(this);
        }
        return this.remapper;
    }

    @Override
    public ArclightRemapConfig getRemapConfig() {
        return ArclightRemapConfig.PLUGIN;
    }

    @Override
    public PluginDescriptionFile arclight$desc() {
        return this.description;
    }

    @Override
    public Class<?> arclight$loadFromExternal(String name, boolean initialize, boolean checkLibraries) throws ClassNotFoundException {
        return this.loadClass0(name, initialize, false, checkLibraries);
    }

    @Override
    public SimplePluginManager arclight$getPluginManager() {
        return (SimplePluginManager)this.plugin.getServer().getPluginManager();
    }

    @Override
    public Logger arclight$systemLogger() {
        return this.plugin.getServer().getLogger();
    }

    public PluginClassLoaderMixin(URL[] urls) {
        super(urls);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Overwrite
    Class<?> loadClass0(String name, boolean resolve, boolean checkGlobal, boolean checkLibraries) throws ClassNotFoundException {
        Class<?> result;
        Object object = this.getClassLoadingLock(name);
        synchronized (object) {
            Class<?> c = this.findLoadedClass(name);
            if (c != null) {
                if (resolve) {
                    this.resolveClass(c);
                }
                return c;
            }
            if (!ArclightConfig.spec().getCompat().isAdventureIsolatedFromML() || !name.startsWith("net.kyori.adventure.")) {
                try {
                    c = this.getParent().loadClass(name);
                    if (resolve) {
                        this.resolveClass(c);
                    }
                    return c;
                }
                catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            if (c == null) {
                try {
                    c = this.findClass(name);
                    if (resolve) {
                        this.resolveClass(c);
                    }
                    return c;
                }
                catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
        }
        if (checkLibraries && this.libraryLoader != null) {
            try {
                return this.libraryLoader.loadClass(name);
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        if (checkGlobal && (result = ((JavaPluginLoaderBridge)((Object)this.loader)).arclight$getClassByName(name, resolve, this.description)) != null) {
            PluginClassLoaderBridge cl;
            PluginDescriptionFile provider;
            ClassLoader classLoader = result.getClassLoader();
            if (classLoader instanceof PluginClassLoaderBridge && (provider = (cl = (PluginClassLoaderBridge)((Object)classLoader)).arclight$desc()) != this.description && !this.seenIllegalAccess.contains(provider.getName()) && !cl.arclight$getPluginManager().isTransitiveDepend(this.description, provider)) {
                this.seenIllegalAccess.add(provider.getName());
                if (this.plugin != null) {
                    this.plugin.getLogger().log(Level.WARNING, "Loaded class {0} from {1} which is not a depend or softdepend of this plugin.", new Object[]{name, provider.getFullName()});
                } else {
                    this.arclight$systemLogger().log(Level.WARNING, "[{0}] Loaded class {1} from {2} which is not a depend or softdepend of this plugin.", new Object[]{this.description.getName(), name, provider.getFullName()});
                }
            }
            return result;
        }
        throw new ClassNotFoundException(String.format("Plugin %s cannot load class %s", this.description.getName(), name));
    }

    @Override
    @Overwrite
    public URL getResource(String name) {
        Objects.requireNonNull(name);
        URL url = this.findResource(name);
        if (url == null && this.getParent() != null) {
            url = this.getParent().getResource(name);
        }
        return url;
    }

    @Override
    @Overwrite
    public Enumeration<URL> getResources(String name) throws IOException {
        Objects.requireNonNull(name);
        Enumeration[] tmp = new Enumeration[2];
        if (this.getParent() != null) {
            tmp[1] = this.getParent().getResources(name);
        }
        tmp[0] = this.findResources(name);
        return Iterators.asEnumeration((Iterator)Iterators.concat((Iterator)Iterators.forEnumeration((Enumeration)tmp[0]), (Iterator)Iterators.forEnumeration((Enumeration)tmp[1])));
    }

    @Override
    @Overwrite
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
            throw new ClassNotFoundException(name);
        }
        Class<?> result = this.classes.get(name);
        if (result == null) {
            String path = name.replace('.', '/').concat(".class");
            URL url = this.findResource(path);
            if (url != null) {
                Product2<byte[], CodeSource> classBytes;
                block11: {
                    String pkgName;
                    Callable<byte[]> byteSource;
                    URLConnection connection;
                    try {
                        connection = url.openConnection();
                        connection.connect();
                        byteSource = () -> {
                            try (InputStream is = connection.getInputStream();){
                                byte[] classBytes = ByteStreams.toByteArray((InputStream)is);
                                classBytes = ArclightRemapper.SWITCH_TABLE_FIXER.apply(classBytes);
                                byte[] byArray = classBytes = Bukkit.getUnsafe().processClass(this.description, path, classBytes);
                                return byArray;
                            }
                        };
                    }
                    catch (IOException e) {
                        throw new ClassNotFoundException(name, e);
                    }
                    classBytes = this.getRemapper().remapClass(name, byteSource, connection, ArclightRemapConfig.PLUGIN);
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
                            if (this.getPackage(pkgName) != null) break block11;
                            throw new IllegalStateException("Cannot find package " + pkgName);
                        }
                    }
                }
                result = this.defineClass(name, (byte[])classBytes._1, 0, ((byte[])classBytes._1).length, (CodeSource)classBytes._2);
            }
            if (result == null) {
                result = super.findClass(name);
            }
            ((JavaPluginLoaderBridge)((Object)this.loader)).bridge$setClass(name, result);
            this.classes.put(name, result);
        }
        return result;
    }

    @Redirect(method={"loadClass0"}, at=@At(value="NEW", target="(Ljava/lang/String;)Ljava/lang/ClassNotFoundException;"))
    private ClassNotFoundException arclight$addPluginInfo(String s) {
        return new ClassNotFoundException(String.format("Plugin %s cannot load class %s", this.description.getName(), s));
    }
}

