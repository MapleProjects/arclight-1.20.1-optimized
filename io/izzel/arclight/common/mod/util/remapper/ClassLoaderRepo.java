/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  io.izzel.arclight.api.Unsafe
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.api.Unsafe;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

public class ClassLoaderRepo
implements ClassRepo,
PluginPatcher.ClassRepo {
    private final ClassLoader classLoader;
    private static final MethodHandle H_FIND_RESOURCE;

    public ClassLoaderRepo(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassNode findClass(String internalName) {
        return this.findClass(internalName, 1);
    }

    public ClassNode findClass(String internalName, int parsingOptions) {
        ClassNode classNode;
        block9: {
            URL url;
            URL uRL = url = this.classLoader instanceof URLClassLoader ? ((URLClassLoader)this.classLoader).findResource(internalName + ".class") : H_FIND_RESOURCE.invokeExact(this.classLoader, internalName + ".class");
            if (url == null) {
                return null;
            }
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try {
                ClassReader reader = new ClassReader(inputStream);
                ClassNode classNode2 = new ClassNode();
                reader.accept((ClassVisitor)classNode2, parsingOptions);
                classNode = classNode2;
                if (inputStream == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Throwable throwable3) {
                    return null;
                }
            }
            inputStream.close();
        }
        return classNode;
    }

    static {
        try {
            H_FIND_RESOURCE = Unsafe.lookup().findVirtual(ClassLoader.class, "findResource", MethodType.methodType(URL.class, String.class));
        }
        catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

