/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteStreams
 *  cpw.mods.modlauncher.ClassTransformer
 *  cpw.mods.modlauncher.TransformingClassLoader
 *  io.izzel.arclight.api.Unsafe
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.ClassReader
 */
package io.izzel.arclight.common.mod.util.remapper.resource;

import com.google.common.io.ByteStreams;
import cpw.mods.modlauncher.ClassTransformer;
import cpw.mods.modlauncher.TransformingClassLoader;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Hashtable;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.ClassReader;

public class RemapSourceHandler
extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new RemapSourceConnection(new URL(u.getFile()));
    }

    public static void register() {
        try {
            Unsafe.ensureClassInitialized(URL.class);
            MethodHandle getter = Unsafe.lookup().findStaticGetter(URL.class, "handlers", Hashtable.class);
            Hashtable handlers = getter.invokeExact();
            handlers.put("remap", new RemapSourceHandler());
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static class RemapSourceConnection
    extends URLConnection {
        private static final MethodHandle MH_TRANSFORM;
        private byte[] array;

        protected RemapSourceConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException {
            byte[] bytes = ByteStreams.toByteArray((InputStream)this.url.openStream());
            String className = new ClassReader(bytes).getClassName();
            if (className.startsWith("net/minecraft/") || className.equals("com/mojang/brigadier/tree/CommandNode")) {
                try {
                    bytes = MH_TRANSFORM.invokeExact(bytes, className.replace('/', '.'), "source");
                }
                catch (Throwable e) {
                    throw new IOException(e);
                }
            }
            this.array = ArclightRemapper.getResourceMapper().remapClassFile(bytes, (ClassRepo)GlobalClassRepo.INSTANCE);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            this.connect();
            if (this.array == null) {
                throw new FileNotFoundException(this.url.getFile());
            }
            return new ByteArrayInputStream(this.array);
        }

        static {
            try {
                ClassLoader classLoader = RemapSourceConnection.class.getClassLoader();
                Field classTransformer = TransformingClassLoader.class.getDeclaredField("classTransformer");
                classTransformer.setAccessible(true);
                ClassTransformer tranformer = (ClassTransformer)classTransformer.get(classLoader);
                Method transform = tranformer.getClass().getDeclaredMethod("transform", byte[].class, String.class, String.class);
                MH_TRANSFORM = Unsafe.lookup().unreflect(transform).bindTo(tranformer);
            }
            catch (Throwable t) {
                throw new IllegalStateException("Unknown modlauncher version", t);
            }
        }
    }
}

