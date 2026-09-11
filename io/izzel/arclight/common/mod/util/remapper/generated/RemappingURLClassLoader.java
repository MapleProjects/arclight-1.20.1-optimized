/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteStreams
 *  io.izzel.tools.product.Product2
 */
package io.izzel.arclight.common.mod.util.remapper.generated;

import com.google.common.io.ByteStreams;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.RemappingClassLoader;
import io.izzel.tools.product.Product2;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.security.CodeSource;
import java.util.concurrent.Callable;
import java.util.jar.Manifest;

public class RemappingURLClassLoader
extends URLClassLoader
implements RemappingClassLoader {
    public ArclightRemapConfig config = new ArclightRemapConfig(RemappingClassLoader.needRemap(this));
    private ClassLoaderRemapper remapper;

    public RemappingURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, RemappingClassLoader.tryRedirect(parent));
    }

    public RemappingURLClassLoader(URL[] urls) {
        super(urls, RemappingClassLoader.tryRedirect(null));
    }

    public RemappingURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, RemappingClassLoader.tryRedirect(parent), factory);
    }

    public RemappingURLClassLoader(String name, URL[] urls, ClassLoader parent) {
        super(name, urls, RemappingClassLoader.tryRedirect(parent));
    }

    public RemappingURLClassLoader(String name, URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(name, urls, RemappingClassLoader.tryRedirect(parent), factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = null;
        String path = name.replace('.', '/').concat(".class");
        URL resource = this.getResource(path);
        if (resource != null) {
            String pkgName;
            Callable<byte[]> byteSource;
            Manifest manifest;
            URLConnection connection;
            try {
                connection = resource.openConnection();
                connection.connect();
                manifest = connection instanceof JarURLConnection && ((JarURLConnection)connection).getManifest() != null ? ((JarURLConnection)connection).getManifest() : null;
                byteSource = () -> {
                    try (InputStream is = connection.getInputStream();){
                        byte[] classBytes = ByteStreams.toByteArray((InputStream)is);
                        byte[] byArray = classBytes = ArclightRemapper.SWITCH_TABLE_FIXER.apply(classBytes);
                        return byArray;
                    }
                };
            }
            catch (IOException e) {
                throw new ClassNotFoundException(name, e);
            }
            Product2<byte[], CodeSource> classBytes = this.getRemapper().remapClass(name, byteSource, connection, this.config);
            int i = name.lastIndexOf(46);
            if (i != -1 && this.getPackage(pkgName = name.substring(0, i)) == null) {
                if (manifest != null) {
                    this.definePackage(pkgName, manifest, ((JarURLConnection)connection).getJarFileURL());
                } else {
                    this.definePackage(pkgName, null, null, null, null, null, null, null);
                }
            }
            result = this.defineClass(name, (byte[])classBytes._1, 0, ((byte[])classBytes._1).length, (CodeSource)classBytes._2);
        }
        if (result == null) {
            throw new ClassNotFoundException(name);
        }
        return result;
    }

    @Override
    public ClassLoaderRemapper getRemapper() {
        if (this.remapper == null) {
            this.remapper = ArclightRemapper.createClassLoaderRemapper(this);
        }
        return this.remapper;
    }

    @Override
    public ArclightRemapConfig getRemapConfig() {
        return this.config;
    }

    static {
        ClassLoader.registerAsParallelCapable();
    }
}

