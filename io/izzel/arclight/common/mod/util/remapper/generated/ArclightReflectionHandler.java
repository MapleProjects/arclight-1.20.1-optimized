/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.ArclightVersion
 *  io.izzel.arclight.api.Unsafe
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.Type
 */
package io.izzel.arclight.common.mod.util.remapper.generated;

import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.util.remapper.ArclightRedirectAdapter;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.ClassRepoWrapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.RemappingClassLoader;
import io.izzel.arclight.common.util.Enumerations;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.StringJoiner;
import org.objectweb.asm.ClassReader;

public class ArclightReflectionHandler
extends ClassLoader {
    private static final String PREFIX = "net.minecraft.";
    public static ClassLoaderRemapper remapper;

    public static Method[] redirectGetDeclaredMethods(Class<?> cl) {
        try {
            return cl.getDeclaredMethods();
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                remapper.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return ArclightReflectionHandler.redirectGetDeclaredMethods(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            remapper.tryDefineClass(error.getMessage());
            return ArclightReflectionHandler.redirectGetDeclaredMethods(cl);
        }
    }

    public static Method[] redirectGetMethods(Class<?> cl) {
        try {
            return cl.getMethods();
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                remapper.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return ArclightReflectionHandler.redirectGetMethods(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            remapper.tryDefineClass(error.getMessage());
            return ArclightReflectionHandler.redirectGetMethods(cl);
        }
    }

    public static Field[] redirectGetDeclaredFields(Class<?> cl) {
        try {
            return cl.getDeclaredFields();
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                remapper.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return ArclightReflectionHandler.redirectGetDeclaredFields(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            remapper.tryDefineClass(error.getMessage());
            return ArclightReflectionHandler.redirectGetDeclaredFields(cl);
        }
    }

    public static Field[] redirectGetFields(Class<?> cl) {
        try {
            return cl.getFields();
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                remapper.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return ArclightReflectionHandler.redirectGetFields(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            remapper.tryDefineClass(error.getMessage());
            return ArclightReflectionHandler.redirectGetFields(cl);
        }
    }

    public static String redirectFieldGetName(Field field) {
        return remapper.tryMapFieldToBukkit(field.getDeclaringClass(), field.getName(), field);
    }

    public static String redirectMethodGetName(Method method) {
        return remapper.tryMapMethodToBukkit(method.getDeclaringClass(), method);
    }

    public static String redirectClassGetCanonicalName(Class<?> cl) {
        if (cl.isArray()) {
            String name = ArclightReflectionHandler.redirectClassGetCanonicalName(cl.getComponentType());
            if (name == null) {
                return null;
            }
            return name + "[]";
        }
        if (cl.isLocalClass() || cl.isAnonymousClass()) {
            return null;
        }
        String canonicalName = cl.getCanonicalName();
        if (canonicalName == null) {
            return null;
        }
        Class<?> enclosingClass = cl.getEnclosingClass();
        if (enclosingClass == null) {
            return ArclightReflectionHandler.redirectClassGetName(cl);
        }
        String name = ArclightReflectionHandler.redirectClassGetCanonicalName(enclosingClass);
        if (name == null) {
            return null;
        }
        return name + "." + ArclightReflectionHandler.redirectClassGetSimpleName(cl);
    }

    public static String redirectClassGetSimpleName(Class<?> cl) {
        String simpleName = cl.getSimpleName();
        if (simpleName.length() == 0) {
            return simpleName;
        }
        Class<?> enclosingClass = cl.getEnclosingClass();
        if (enclosingClass == null) {
            String mapped = ArclightReflectionHandler.redirectClassGetName(cl);
            return mapped.substring(mapped.lastIndexOf(46) + 1);
        }
        String outer = ArclightReflectionHandler.redirectClassGetName(enclosingClass);
        String inner = ArclightReflectionHandler.redirectClassGetName(cl);
        return inner.substring(outer.length() + 1);
    }

    public static String handleClassGetName(String cl) {
        return remapper.toBukkitRemapper().mapType(cl.replace('.', '/')).replace('/', '.');
    }

    public static String redirectClassGetName(Class<?> cl) {
        String internalName = org.objectweb.asm.Type.getInternalName(cl);
        org.objectweb.asm.Type type = org.objectweb.asm.Type.getObjectType((String)remapper.toBukkitRemapper().mapType(internalName));
        return type.getInternalName().replace('/', '.');
    }

    public static String handlePackageGetName(String name) {
        if (name.startsWith(PREFIX)) {
            return "net.minecraft.server." + ArclightVersion.current().packageName();
        }
        return name;
    }

    public static String redirectPackageGetName(Package pkg) {
        return ArclightReflectionHandler.handlePackageGetName(pkg.getName());
    }

    public static String redirectTypeGetName(Type type) {
        if (type instanceof Class) {
            Class cl = (Class)type;
            if (cl.isArray()) {
                return ArclightReflectionHandler.redirectTypeGetName(cl.getComponentType()) + "[]";
            }
            return ArclightReflectionHandler.redirectClassGetName(cl);
        }
        if (type instanceof WildcardType) {
            StringBuilder sb;
            Type[] bounds;
            WildcardType wType = (WildcardType)type;
            if (wType.getLowerBounds().length == 0) {
                if (wType.getUpperBounds().length == 0 || Object.class == wType.getUpperBounds()[0]) {
                    return "?";
                }
                bounds = wType.getUpperBounds();
                sb = new StringBuilder("? extends ");
            } else {
                bounds = wType.getLowerBounds();
                sb = new StringBuilder("? super ");
            }
            for (int i = 0; i < bounds.length; ++i) {
                if (i > 0) {
                    sb.append(" & ");
                }
                sb.append(ArclightReflectionHandler.redirectTypeGetName(bounds[i]));
            }
            return sb.toString();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            StringBuilder sb = new StringBuilder();
            if (pType.getOwnerType() != null) {
                sb.append(ArclightReflectionHandler.redirectTypeGetName(pType.getOwnerType()));
                sb.append("$");
                sb.append(ArclightReflectionHandler.redirectClassGetSimpleName((Class)pType.getRawType()));
            } else {
                sb.append(ArclightReflectionHandler.redirectTypeGetName(pType.getRawType()));
            }
            if (pType.getActualTypeArguments() != null) {
                StringJoiner sj = new StringJoiner(", ", "<", ">");
                sj.setEmptyValue("");
                for (Type t : pType.getActualTypeArguments()) {
                    sj.add(ArclightReflectionHandler.redirectTypeGetName(t));
                }
                sb.append(sj);
            }
            return sb.toString();
        }
        if (type instanceof GenericArrayType) {
            GenericArrayType arrayType = (GenericArrayType)type;
            return ArclightReflectionHandler.redirectTypeGetName(arrayType.getGenericComponentType()) + "[]";
        }
        return type.getTypeName();
    }

    public static Class<?> redirectClassForName(String cl) throws ClassNotFoundException {
        return ArclightReflectionHandler.redirectClassForName(cl, true, Unsafe.getCallerClass().getClassLoader());
    }

    public static Class<?> redirectClassForName(String cl, boolean initialize, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            String replace = remapper.mapType(cl.replace('.', '/')).replace('/', '.');
            return Class.forName(replace, initialize, classLoader);
        }
        catch (ClassNotFoundException e) {
            int i = cl.lastIndexOf(46);
            if (i > 0) {
                Object replace = cl.substring(0, i).replace('.', '/') + "$" + cl.substring(i + 1);
                replace = remapper.mapType((String)replace).replace('/', '.').replace('$', '.');
                return Class.forName((String)replace, initialize, classLoader);
            }
            throw e;
        }
    }

    public static Object[] handleClassGetField(Class<?> cl, String bukkitName) {
        return new Object[]{cl, remapper.tryMapFieldToSrg(cl, bukkitName)};
    }

    public static Field redirectClassGetField(Class<?> cl, String bukkitName) throws NoSuchFieldException {
        String field = remapper.tryMapFieldToSrg(cl, bukkitName);
        return cl.getField(field);
    }

    public static Object[] handleClassGetDeclaredField(Class<?> cl, String bukkitName) {
        return ArclightReflectionHandler.handleClassGetField(cl, bukkitName);
    }

    public static Field redirectClassGetDeclaredField(Class<?> cl, String bukkitName) throws NoSuchFieldException {
        String field = remapper.tryMapDecFieldToSrg(cl, bukkitName);
        return cl.getDeclaredField(field);
    }

    public static Object[] handleClassGetMethod(Class<?> cl, String bukkitName, Class<?> ... pTypes) {
        Method method = remapper.tryMapMethodToSrg(cl, bukkitName, pTypes);
        return new Object[]{cl, method == null ? bukkitName : method.getName(), pTypes};
    }

    public static Method redirectClassGetMethod(Class<?> cl, String bukkitName, Class<?> ... pTypes) throws NoSuchMethodException {
        Method method = remapper.tryMapMethodToSrg(cl, bukkitName, pTypes);
        if (method != null) {
            return method;
        }
        return cl.getMethod(bukkitName, pTypes);
    }

    public static Object[] handleClassGetDeclaredMethod(Class<?> cl, String bukkitName, Class<?> ... pTypes) {
        return ArclightReflectionHandler.handleClassGetMethod(cl, bukkitName, pTypes);
    }

    public static Method redirectClassGetDeclaredMethod(Class<?> cl, String bukkitName, Class<?> ... pTypes) throws NoSuchMethodException {
        Method method = remapper.tryMapMethodToSrg(cl, bukkitName, pTypes);
        if (method != null) {
            return method;
        }
        return cl.getDeclaredMethod(bukkitName, pTypes);
    }

    public static Object[] handleFromDescStr(String desc, ClassLoader classLoader) {
        return new Object[]{remapper.mapMethodDesc(desc), classLoader};
    }

    public static MethodType redirectFromDescStr(String desc, ClassLoader classLoader) {
        String methodDesc = remapper.mapMethodDesc(desc);
        return MethodType.fromMethodDescriptorString(methodDesc, classLoader);
    }

    public static Object[] handleLookupFindStatic(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType) {
        Method method = remapper.tryMapMethodToSrg(cl, name, methodType.parameterArray());
        return new Object[]{lookup, cl, method == null ? name : method.getName(), methodType};
    }

    public static MethodHandle redirectLookupFindStatic(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType) throws NoSuchMethodException, IllegalAccessException {
        Method method = remapper.tryMapMethodToSrg(cl, name, methodType.parameterArray());
        if (method != null) {
            return lookup.findStatic(cl, method.getName(), methodType);
        }
        return lookup.findStatic(cl, name, methodType);
    }

    public static Object[] handleLookupFindVirtual(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType) {
        return ArclightReflectionHandler.handleLookupFindStatic(lookup, cl, name, methodType);
    }

    public static MethodHandle redirectLookupFindVirtual(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType) throws NoSuchMethodException, IllegalAccessException {
        Method method = remapper.tryMapMethodToSrg(cl, name, methodType.parameterArray());
        if (method != null) {
            return lookup.findVirtual(cl, method.getName(), methodType);
        }
        return lookup.findVirtual(cl, name, methodType);
    }

    public static Object[] handleLookupFindSpecial(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType, Class<?> spec) {
        Method method = remapper.tryMapMethodToSrg(cl, name, methodType.parameterArray());
        return new Object[]{lookup, cl, method == null ? name : method.getName(), methodType, spec};
    }

    public static MethodHandle redirectLookupFindSpecial(MethodHandles.Lookup lookup, Class<?> cl, String name, MethodType methodType, Class<?> spec) throws NoSuchMethodException, IllegalAccessException {
        Method method = remapper.tryMapMethodToSrg(cl, name, methodType.parameterArray());
        if (method != null) {
            return lookup.findSpecial(cl, method.getName(), methodType, spec);
        }
        return lookup.findSpecial(cl, name, methodType, spec);
    }

    public static Object[] handleLookupFindGetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return new Object[]{lookup, cl, field, type};
    }

    public static MethodHandle redirectLookupFindGetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws IllegalAccessException, NoSuchFieldException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findGetter(cl, field, type);
    }

    public static Object[] handleLookupFindSetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        return ArclightReflectionHandler.handleLookupFindGetter(lookup, cl, name, type);
    }

    public static MethodHandle redirectLookupFindSetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws IllegalAccessException, NoSuchFieldException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findSetter(cl, field, type);
    }

    public static Object[] handleLookupFindStaticGetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        return ArclightReflectionHandler.handleLookupFindGetter(lookup, cl, name, type);
    }

    public static MethodHandle redirectLookupFindStaticGetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws IllegalAccessException, NoSuchFieldException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findStaticGetter(cl, field, type);
    }

    public static Object[] handleLookupFindStaticSetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        return ArclightReflectionHandler.handleLookupFindGetter(lookup, cl, name, type);
    }

    public static MethodHandle redirectLookupFindStaticSetter(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws IllegalAccessException, NoSuchFieldException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findStaticSetter(cl, field, type);
    }

    public static Class<?> redirectLookupFindClass(MethodHandles.Lookup lookup, String name) throws ClassNotFoundException {
        return ArclightReflectionHandler.redirectClassForName(name, false, lookup.lookupClass().getClassLoader());
    }

    public static Object[] handleLookupFindVarHandle(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        return ArclightReflectionHandler.handleLookupFindGetter(lookup, cl, name, type);
    }

    public static VarHandle redirectLookupFindVarHandle(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findVarHandle(cl, field, type);
    }

    public static Object[] handleLookupFindStaticVarHandle(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) {
        return ArclightReflectionHandler.handleLookupFindGetter(lookup, cl, name, type);
    }

    public static VarHandle redirectLookupFindStaticVarHandle(MethodHandles.Lookup lookup, Class<?> cl, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
        String field = remapper.tryMapFieldToSrg(cl, name);
        return lookup.findStaticVarHandle(cl, field, type);
    }

    public static Object[] handleClassLoaderLoadClass(ClassLoader loader, String binaryName) {
        return new Object[]{loader, remapper.mapType(binaryName.replace('.', '/')).replace('/', '.')};
    }

    public static Class<?> redirectClassLoaderLoadClass(ClassLoader loader, String binaryName) throws ClassNotFoundException {
        String replace = remapper.mapType(binaryName.replace('.', '/')).replace('/', '.');
        return loader.loadClass(replace);
    }

    public static String findMappedResource(Class<?> cl, String name) {
        Object className;
        if (name.isEmpty() || !name.endsWith(".class")) {
            return null;
        }
        name = name.substring(0, name.length() - 6);
        if (cl != null) {
            if (name.charAt(0) == '/') {
                className = name.substring(1);
            } else {
                Class<?> c = cl;
                while (c.isArray()) {
                    c = c.getComponentType();
                }
                String mapped = remapper.toBukkitRemapper().mapType(c.getName().replace('.', '/'));
                int index = mapped.lastIndexOf(47);
                className = index == -1 ? name : mapped.substring(0, index) + "/" + name;
            }
        } else {
            className = name;
        }
        className = remapper.mapType((String)className);
        if (((String)className).startsWith("java/") || ((String)className).startsWith("jdk/") || ((String)className).startsWith("javax/")) {
            return null;
        }
        if (cl != null) {
            return "/" + (String)className + ".class";
        }
        return (String)className + ".class";
    }

    public static URL redirectClassGetResource(Class<?> cl, String name) throws MalformedURLException {
        String mappedResource = ArclightReflectionHandler.findMappedResource(cl, name);
        if (mappedResource == null) {
            if (name.startsWith("/java/") || name.startsWith("/jdk/") || name.startsWith("/javax/")) {
                return ClassLoader.getPlatformClassLoader().getResource(name);
            }
            return cl.getResource(name);
        }
        URL resource = cl.getResource(mappedResource);
        return resource == null ? null : new URL("remap:" + String.valueOf(resource));
    }

    public static InputStream redirectClassGetResourceAsStream(Class<?> cl, String name) throws IOException {
        String mappedResource = ArclightReflectionHandler.findMappedResource(cl, name);
        if (mappedResource == null) {
            if (name.startsWith("/java/") || name.startsWith("/jdk/") || name.startsWith("/javax/")) {
                return ClassLoader.getPlatformClassLoader().getResourceAsStream(name);
            }
            return cl.getResourceAsStream(name);
        }
        URL resource = cl.getResource(mappedResource);
        if (resource == null) {
            return null;
        }
        return new URL("remap:" + String.valueOf(resource)).openStream();
    }

    public static URL redirectClassLoaderGetResource(ClassLoader loader, String name) throws MalformedURLException {
        String mappedResource = ArclightReflectionHandler.findMappedResource(null, name);
        if (mappedResource == null) {
            if (name.startsWith("java/") || name.startsWith("jdk/") || name.startsWith("javax/")) {
                return ClassLoader.getPlatformClassLoader().getResource(name);
            }
            return loader.getResource(name);
        }
        URL resource = loader.getResource(mappedResource);
        return resource == null ? null : new URL("remap:" + String.valueOf(resource));
    }

    public static Enumeration<URL> redirectClassLoaderGetResources(ClassLoader loader, String name) throws IOException {
        String mappedResource = ArclightReflectionHandler.findMappedResource(null, name);
        if (mappedResource == null) {
            if (name.startsWith("java/") || name.startsWith("jdk/") || name.startsWith("javax/")) {
                return ClassLoader.getPlatformClassLoader().getResources(name);
            }
            return loader.getResources(name);
        }
        Enumeration<URL> resources = loader.getResources(mappedResource);
        return Enumerations.remapped(resources);
    }

    public static InputStream redirectClassLoaderGetResourceAsStream(ClassLoader loader, String name) throws IOException {
        URL url = ArclightReflectionHandler.redirectClassLoaderGetResource(loader, name);
        if (url == null) {
            return null;
        }
        return url.openStream();
    }

    public static Object[] handleMethodInvoke(Method method, Object src, Object[] param) throws Throwable {
        Object[] ret = ArclightRedirectAdapter.runHandle(remapper, method, src, param);
        if (ret != null) {
            return ret;
        }
        return new Object[]{method, src, param};
    }

    public static Object redirectMethodInvoke(Method method, Object src, Object[] param) throws Throwable {
        Object ret = ArclightRedirectAdapter.runRedirect(remapper, method, src, param);
        if (ret != remapper) {
            return ret;
        }
        return method.invoke(src, param);
    }

    public static Object[] handleDefineClass(ClassLoader loader, byte[] b, int off, int len) {
        byte[] bytes = ArclightReflectionHandler.transformOrAdd(loader, b);
        return new Object[]{loader, bytes, 0, bytes.length};
    }

    public static Class<?> redirectDefineClass(ClassLoader loader, byte[] b, int off, int len) {
        return ArclightReflectionHandler.redirectDefineClass(loader, null, b, off, len);
    }

    public static Object[] handleDefineClass(ClassLoader loader, String name, byte[] b, int off, int len) {
        byte[] bytes = ArclightReflectionHandler.transformOrAdd(loader, b);
        return new Object[]{loader, new ClassReader(bytes).getClassName().replace('/', '.'), bytes, 0, bytes.length};
    }

    public static Class<?> redirectDefineClass(ClassLoader loader, String name, byte[] b, int off, int len) {
        return ArclightReflectionHandler.redirectDefineClass(loader, name, b, off, len, null);
    }

    public static Object[] handleDefineClass(ClassLoader loader, String name, byte[] b, int off, int len, ProtectionDomain pd) {
        byte[] bytes = ArclightReflectionHandler.transformOrAdd(loader, b);
        return new Object[]{loader, new ClassReader(bytes).getClassName().replace('/', '.'), bytes, 0, bytes.length, pd};
    }

    public static Class<?> redirectDefineClass(ClassLoader loader, String name, byte[] b, int off, int len, ProtectionDomain pd) {
        byte[] bytes = ArclightReflectionHandler.transformOrAdd(loader, b);
        return Unsafe.defineClass((String)new ClassReader(bytes).getClassName().replace('/', '.'), (byte[])bytes, (int)0, (int)bytes.length, (ClassLoader)loader, (ProtectionDomain)pd);
    }

    public static Object[] handleDefineClass(ClassLoader loader, String name, ByteBuffer b, ProtectionDomain pd) {
        byte[] bytes = new byte[b.remaining()];
        b.get(bytes);
        bytes = ArclightReflectionHandler.transformOrAdd(loader, bytes);
        return new Object[]{loader, new ClassReader(bytes).getClassName().replace('/', '.'), ByteBuffer.wrap(bytes), pd};
    }

    public static Class<?> redirectDefineClass(ClassLoader loader, String name, ByteBuffer b, ProtectionDomain pd) {
        byte[] bytes = new byte[b.remaining()];
        b.get(bytes);
        return ArclightReflectionHandler.redirectDefineClass(loader, name, bytes, 0, bytes.length, pd);
    }

    public static Object[] handleDefineClass(SecureClassLoader loader, String name, byte[] b, int off, int len, CodeSource cs) {
        byte[] bytes = ArclightReflectionHandler.transformOrAdd(loader, b);
        return new Object[]{loader, new ClassReader(bytes).getClassName().replace('/', '.'), bytes, 0, bytes.length, cs};
    }

    public static Class<?> redirectDefineClass(SecureClassLoader loader, String name, byte[] b, int off, int len, CodeSource cs) {
        return ArclightReflectionHandler.redirectDefineClass((ClassLoader)loader, name, b, off, len, new ProtectionDomain(cs, new Permissions()));
    }

    public static Object[] handleDefineClass(SecureClassLoader loader, String name, ByteBuffer b, CodeSource cs) {
        byte[] bytes = new byte[b.remaining()];
        b.get(bytes);
        bytes = ArclightReflectionHandler.transformOrAdd(loader, bytes);
        return new Object[]{loader, new ClassReader(bytes).getClassName().replace('/', '.'), ByteBuffer.wrap(bytes), cs};
    }

    public static Class<?> redirectDefineClass(SecureClassLoader loader, String name, ByteBuffer b, CodeSource cs) {
        return ArclightReflectionHandler.redirectDefineClass((ClassLoader)loader, name, b, new ProtectionDomain(cs, new Permissions()));
    }

    public static Object[] handleUnsafeDefineClass(Object unsafe, String name, byte[] bytes, int off, int len, ClassLoader loader, ProtectionDomain pd) {
        bytes = ArclightReflectionHandler.transformOrAdd(loader, bytes);
        return new Object[]{unsafe, new ClassReader(bytes).getClassName().replace('/', '.'), bytes, 0, bytes.length, loader, pd};
    }

    public static Class<?> redirectUnsafeDefineClass(Object unsafe, String name, byte[] bytes, int off, int len, ClassLoader loader, ProtectionDomain pd) {
        return ArclightReflectionHandler.redirectDefineClass(loader, name, bytes, off, len, pd);
    }

    public static Object[] handleLookupDefineClass(MethodHandles.Lookup lookup, byte[] bytes) {
        return new Object[]{lookup, ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes)};
    }

    public static Class<?> redirectLookupDefineClass(MethodHandles.Lookup lookup, byte[] bytes) throws Throwable {
        byte[] transform = ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes);
        MethodHandle mh = Unsafe.lookup().findVirtual(MethodHandles.Lookup.class, "defineClass", MethodType.methodType(Class.class, byte[].class));
        return mh.invokeExact(lookup, transform);
    }

    public static Object[] handleLookupDefineHiddenClass(MethodHandles.Lookup lookup, byte[] bytes, boolean initialize, MethodHandles.Lookup.ClassOption[] options) {
        return new Object[]{lookup, ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes), initialize, options};
    }

    public static MethodHandles.Lookup redirectLookupDefineHiddenClass(MethodHandles.Lookup lookup, byte[] bytes, boolean initialize, MethodHandles.Lookup.ClassOption[] options) throws IllegalAccessException {
        byte[] transform = ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes);
        return lookup.defineHiddenClass(transform, initialize, options);
    }

    public static Object[] handleLookupDefineHiddenClassWithClassData(MethodHandles.Lookup lookup, byte[] bytes, Object classData, boolean initialize, MethodHandles.Lookup.ClassOption[] options) {
        byte[] transform = ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes);
        return new Object[]{lookup, transform, classData, initialize, options};
    }

    public static MethodHandles.Lookup redirectLookupDefineHiddenClassWithClassData(MethodHandles.Lookup lookup, byte[] bytes, Object classData, boolean initialize, MethodHandles.Lookup.ClassOption[] options) throws IllegalAccessException {
        byte[] transform = ArclightReflectionHandler.transformOrAdd(lookup.lookupClass().getClassLoader(), bytes);
        return lookup.defineHiddenClassWithClassData(transform, classData, initialize, options);
    }

    public static byte[] transformOrAdd(ClassLoader loader, byte[] bytes) {
        RemappingClassLoader rcl = null;
        while (loader != null) {
            if (loader instanceof RemappingClassLoader) {
                rcl = (RemappingClassLoader)((Object)loader);
                break;
            }
            loader = loader.getParent();
        }
        if (rcl != null) {
            ClassRepoWrapper repo = new ClassRepoWrapper(GlobalClassRepo.INSTANCE, rcl.getRemapConfig());
            return rcl.getRemapper().remapClassFile(bytes, repo, true);
        }
        ArclightRedirectAdapter.scanMethod(bytes);
        return bytes;
    }
}

