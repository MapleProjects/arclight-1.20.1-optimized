/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.common.collect.Maps
 *  com.google.common.io.ByteStreams
 *  io.izzel.arclight.api.Unsafe
 *  io.izzel.arclight.i18n.ArclightConfig
 *  io.izzel.tools.product.Product
 *  io.izzel.tools.product.Product2
 *  net.md_5.specialsource.CustomRemapper
 *  net.md_5.specialsource.JarMapping
 *  net.md_5.specialsource.JarRemapper
 *  net.md_5.specialsource.RemappingClassAdapter
 *  net.md_5.specialsource.provider.InheritanceProvider
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.ClassRemapper
 *  org.objectweb.asm.commons.Remapper
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.spongepowered.asm.service.MixinService
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightClassCache;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapper;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRepo;
import io.izzel.arclight.common.mod.util.remapper.ClassRepoWrapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.LenientJarRemapper;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import io.izzel.arclight.common.mod.util.remapper.generated.ArclightReflectionHandler;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.tools.product.Product;
import io.izzel.tools.product.Product2;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarFile;
import net.md_5.specialsource.CustomRemapper;
import net.md_5.specialsource.JarMapping;
import net.md_5.specialsource.JarRemapper;
import net.md_5.specialsource.RemappingClassAdapter;
import net.md_5.specialsource.provider.InheritanceProvider;
import net.md_5.specialsource.repo.ClassRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.spongepowered.asm.service.MixinService;

public class ClassLoaderRemapper
extends LenientJarRemapper {
    private static final Logger LOGGER = LogManager.getLogger((String)"Arclight");
    private static final String PREFIX = "net/minecraft/";
    private static final String REPLACED_NAME = Type.getInternalName(ArclightReflectionHandler.class);
    private final JarMapping toBukkitMapping;
    private final JarRemapper toBukkitRemapper;
    private final ClassLoader classLoader;
    private final String generatedHandler;
    private final Class<?> generatedHandlerClass;
    private final GeneratedHandlerAdapter generatedHandlerAdapter;
    private final Map<String, Boolean> secureJarInfo = new ConcurrentHashMap<String, Boolean>();
    private final Map<String, BiMap<Field, String>> cacheFields = new ConcurrentHashMap<String, BiMap<Field, String>>();
    private final Map<String, Map.Entry<Map<Method, String>, Map<WrappedMethod, Method>>> cacheMethods = new ConcurrentHashMap<String, Map.Entry<Map<Method, String>, Map<WrappedMethod, Method>>>();
    private final Map<String, Boolean> cacheRemap = new ConcurrentHashMap<String, Boolean>();
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public ClassLoaderRemapper(JarMapping jarMapping, JarMapping toBukkitMapping, ClassLoader classLoader) {
        super(jarMapping);
        this.toBukkitMapping = toBukkitMapping;
        this.classLoader = classLoader;
        this.jarMapping.setInheritanceMap(ArclightRemapper.INSTANCE.inheritanceMap);
        this.jarMapping.setFallbackInheritanceProvider((InheritanceProvider)GlobalClassRepo.inheritanceProvider());
        this.toBukkitMapping.setFallbackInheritanceProvider((InheritanceProvider)GlobalClassRepo.inheritanceProvider());
        this.toBukkitRemapper = new LenientJarRemapper(this.toBukkitMapping);
        this.generatedHandlerClass = this.generateReflectionHandler();
        this.generatedHandler = Type.getInternalName(this.generatedHandlerClass);
        this.generatedHandlerAdapter = new GeneratedHandlerAdapter(REPLACED_NAME, this.generatedHandler);
        GlobalClassRepo.INSTANCE.addRepo(new ClassLoaderRepo(this.classLoader));
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public JarMapping toBukkitMapping() {
        return this.toBukkitMapping;
    }

    public JarMapping toNmsMapping() {
        return this.jarMapping;
    }

    public JarRemapper toBukkitRemapper() {
        return this.toBukkitRemapper;
    }

    public String getGeneratedHandler() {
        return this.generatedHandler;
    }

    public Class<?> getGeneratedHandlerClass() {
        return this.generatedHandlerClass;
    }

    private Map.Entry<Map<Method, String>, Map<WrappedMethod, Method>> getMethods(Class<?> cl, String internalName) {
        return this.cacheMethods.computeIfAbsent(internalName, k -> this.tryGetMethods(cl));
    }

    private Map.Entry<Map<Method, String>, Map<WrappedMethod, Method>> tryGetMethods(Class<?> cl) {
        try {
            WrappedMethod wrapped;
            String name;
            HashMap<Method, String> names = new HashMap<Method, String>();
            HashMap<WrappedMethod, Method> types = new HashMap<WrappedMethod, Method>();
            for (Method method : cl.getMethods()) {
                this.checkMethodTypes(method);
                name = this.mapMethod(method);
                names.put(method, name);
                wrapped = new WrappedMethod(name, method.getParameterTypes());
                types.put(wrapped, method);
            }
            for (Method method : cl.getDeclaredMethods()) {
                this.checkMethodTypes(method);
                name = this.mapMethod(method);
                names.put(method, name);
                wrapped = new WrappedMethod(name, method.getParameterTypes());
                types.put(wrapped, method);
            }
            return Maps.immutableEntry(names, types);
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                this.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return this.tryGetMethods(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            this.tryDefineClass(error.getMessage());
            return this.tryGetMethods(cl);
        }
    }

    private BiMap<Field, String> getFields(Class<?> cl, String internalName) {
        return this.cacheFields.computeIfAbsent(internalName, k -> this.tryGetFields(cl));
    }

    private BiMap<Field, String> tryGetFields(Class<?> cl) {
        try {
            HashBiMap map = HashBiMap.create();
            for (Field field : cl.getFields()) {
                this.checkFieldTypes(field);
                map.forcePut((Object)field, (Object)this.mapField(field));
            }
            for (Field field : cl.getDeclaredFields()) {
                this.checkFieldTypes(field);
                map.forcePut((Object)field, (Object)this.mapField(field));
            }
            return map;
        }
        catch (TypeNotPresentException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                this.tryDefineClass(e.getCause().getMessage().replace('.', '/'));
                return this.tryGetFields(cl);
            }
            throw e;
        }
        catch (NoClassDefFoundError error) {
            this.tryDefineClass(error.getMessage());
            return this.tryGetFields(cl);
        }
    }

    private void checkFieldTypes(Field field) throws TypeNotPresentException {
        field.getGenericType();
    }

    private void checkMethodTypes(Method method) throws TypeNotPresentException {
        method.getGenericReturnType();
        method.getGenericParameterTypes();
    }

    public void tryDefineClass(String internalName) {
        if (!internalName.startsWith(PREFIX)) {
            throw new NoClassDefFoundError(internalName);
        }
        LOGGER.warn("Loading CLIENT side class: {}", (Object)internalName);
        ClassWriter writer = new ClassWriter(0);
        writer.visit(52, 131073, internalName, null, "java/lang/Object", new String[0]);
        writer.visitEnd();
        byte[] bytes = writer.toByteArray();
        Unsafe.defineClass((String)Type.getObjectType((String)internalName).getClassName(), (byte[])bytes, (int)0, (int)bytes.length, (ClassLoader)((Object)((Object)this)).getClass().getClassLoader(), (ProtectionDomain)((Object)((Object)this)).getClass().getProtectionDomain());
    }

    private String mapMethod(Method method) {
        String owner = Type.getInternalName(method.getDeclaringClass());
        String srgName = method.getName();
        String desc = Type.getMethodDescriptor((Method)method);
        return this.toBukkitRemapper.mapMethodName(owner, srgName, desc, -1);
    }

    private String mapField(Field field) {
        String owner = Type.getInternalName(field.getDeclaringClass());
        String srgName = field.getName();
        String desc = Type.getDescriptor(field.getType());
        return this.toBukkitRemapper.mapFieldName(owner, srgName, desc, -1);
    }

    public String tryMapDecFieldToSrg(Class<?> cl, String bukkitName) {
        String internalName = Type.getInternalName(cl);
        if (internalName.startsWith(PREFIX)) {
            Field field = (Field)this.getFields(cl, internalName).inverse().get((Object)bukkitName);
            return field == null ? bukkitName : field.getName();
        }
        return bukkitName;
    }

    public String tryMapFieldToSrg(Class<?> cl, String bukkitName) {
        String internalName = Type.getInternalName(cl);
        if (this.shouldRemap(internalName)) {
            Field field = (Field)this.getFields(cl, internalName).inverse().get((Object)bukkitName);
            return field == null ? bukkitName : field.getName();
        }
        return bukkitName;
    }

    public String tryMapFieldToBukkit(Class<?> cl, String srgName, Field field) {
        String internalName = Type.getInternalName(cl);
        if (internalName.startsWith(PREFIX)) {
            BiMap<Field, String> fields = this.getFields(cl, internalName);
            return (String)fields.getOrDefault((Object)field, (Object)srgName);
        }
        return srgName;
    }

    public Method tryMapMethodToSrg(Class<?> cl, String bukkitName, Class<?>[] pTypes) {
        String internalName = Type.getInternalName(cl);
        if (this.shouldRemap(internalName)) {
            return this.getMethods(cl, internalName).getValue().get(new WrappedMethod(bukkitName, pTypes));
        }
        return null;
    }

    public String tryMapMethodToBukkit(Class<?> cl, Method method) {
        String internalName = Type.getInternalName(cl);
        if (this.shouldRemap(internalName)) {
            return this.getMethods(cl, internalName).getKey().getOrDefault(method, method.getName());
        }
        return method.getName();
    }

    private boolean shouldRemap(String internalName) {
        Boolean b = this.cacheRemap.get(internalName);
        if (b != null) {
            return b;
        }
        for (String s : GlobalClassRepo.inheritanceProvider().getAll(internalName)) {
            if (!s.startsWith(PREFIX)) continue;
            this.cacheRemap.put(internalName, true);
            return true;
        }
        this.cacheRemap.put(internalName, false);
        return false;
    }

    public MethodInsnNode mapMethod(String owner, String name, String desc) {
        Map.Entry<String, String> entry = this.tryClimb(this.jarMapping.methods, owner, name + " " + desc, -1);
        if (entry == null) {
            return null;
        }
        return new MethodInsnNode(182, this.mapType(entry.getKey()), entry.getValue(), this.mapMethodDesc(desc), false);
    }

    public Map.Entry<String, String> tryClimb(Map<String, String> map, String owner, String name, int access) {
        String key = owner + "/" + name;
        String mapped = map.get(key);
        if (mapped == null && (access == -1 || !Modifier.isPrivate(access) && !Modifier.isStatic(access))) {
            List parents;
            if (ArclightRemapper.INSTANCE.inheritanceMap.hasParents(owner)) {
                parents = ArclightRemapper.INSTANCE.inheritanceMap.getParents(owner);
            } else {
                parents = GlobalClassRepo.inheritanceProvider().getParents(owner);
                ArclightRemapper.INSTANCE.inheritanceMap.setParents(owner, (Collection)parents);
            }
            if (parents != null) {
                for (String parent : parents) {
                    Map.Entry<String, String> entry = this.tryClimb(map, parent, name, access);
                    if (entry == null) continue;
                    return entry;
                }
            }
        }
        if (mapped == null) {
            return null;
        }
        return Maps.immutableEntry((Object)owner, (Object)mapped);
    }

    private boolean isSecureJar(JarFile jarFile) {
        return this.secureJarInfo.computeIfAbsent(jarFile.getName(), key -> jarFile.stream().anyMatch(it -> {
            if (it.isDirectory()) {
                return false;
            }
            String name = it.getName().toUpperCase(Locale.ROOT);
            return name.startsWith("META-INF") && (name.endsWith(".DSA") || name.endsWith(".RSA") || name.endsWith(".EC") || name.endsWith(".SF"));
        }));
    }

    public Product2<byte[], CodeSource> remapClass(String className, Callable<byte[]> byteSource, URLConnection connection, ArclightRemapConfig config) throws ClassNotFoundException {
        try {
            CodeSigner[] signers;
            URL url;
            ArclightClassCache.CacheSegment segment = ArclightClassCache.instance().makeSegment(connection);
            Optional<byte[]> optional = segment.findByName(className, config);
            if (optional.isPresent()) {
                CodeSigner[] signers2;
                URL url2;
                byte[] bytes = optional.get();
                ClassWriter cw = new ClassWriter(0);
                new ClassReader(bytes).accept((ClassVisitor)new ClassRemapper((ClassVisitor)cw, (Remapper)this.generatedHandlerAdapter), 0);
                if (connection instanceof JarURLConnection) {
                    url2 = ((JarURLConnection)connection).getJarFileURL();
                    if (this.isSecureJar(((JarURLConnection)connection).getJarFile())) {
                        ByteStreams.exhaust((InputStream)connection.getInputStream());
                        signers2 = ((JarURLConnection)connection).getJarEntry().getCodeSigners();
                    } else {
                        signers2 = null;
                    }
                } else {
                    url2 = connection.getURL();
                    signers2 = null;
                }
                return Product.of((Object)cw.toByteArray(), (Object)new CodeSource(url2, signers2));
            }
            byte[] bytes = this.remapClassFile(byteSource.call(), (ClassRepo)new ClassRepoWrapper(GlobalClassRepo.INSTANCE, config));
            if (ArclightConfig.spec().getOptimization().isCachePluginClass()) {
                ClassWriter cw = new ClassWriter(0);
                new ClassReader(bytes).accept((ClassVisitor)new ClassRemapper((ClassVisitor)cw, (Remapper)new GeneratedHandlerAdapter(this.generatedHandler, REPLACED_NAME)), 0);
                byte[] store = cw.toByteArray();
                segment.addToCache(className, store, config);
            }
            if (connection instanceof JarURLConnection) {
                url = ((JarURLConnection)connection).getJarFileURL();
                signers = ((JarURLConnection)connection).getJarEntry().getCodeSigners();
            } else {
                url = connection.getURL();
                signers = null;
            }
            return Product.of((Object)bytes, (Object)new CodeSource(url, signers));
        }
        catch (Exception e) {
            throw new ClassNotFoundException(className, e);
        }
    }

    public byte[] remapClassFile(byte[] in, ClassRepo repo) {
        return this.remapClassFile(in, repo, false);
    }

    public byte[] remapClassFile(byte[] in, ClassRepo repo, boolean runtime) {
        if (runtime) {
            GlobalClassRepo.runtimeRepo().put(in);
        }
        return this.remapClassFile(new ClassReader(in), repo);
    }

    private byte[] remapClassFile(ClassReader reader, ClassRepo repo) {
        ArclightRemapConfig config;
        ClassNode node = new ClassNode();
        RemappingClassAdapter mapper = new RemappingClassAdapter((ClassVisitor)node, (CustomRemapper)this, repo);
        reader.accept((ClassVisitor)mapper, 0);
        if (repo instanceof ClassRepoWrapper) {
            ClassRepoWrapper wrapper = (ClassRepoWrapper)repo;
            config = wrapper.config();
        } else {
            ArclightMod.LOGGER.warn("No class remap config is provided for class {}, using PLUGIN", (Object)node.name.replace('/', '.'));
            config = ArclightRemapConfig.PLUGIN;
        }
        for (PluginTransformer transformer : ArclightRemapper.INSTANCE.getTransformerList()) {
            transformer.handleClass(node, this, config);
        }
        PluginClassWriter wr = new PluginClassWriter(1);
        node.accept((ClassVisitor)wr);
        return ClassLoaderRemapper.dump(wr.toByteArray());
    }

    private Class<?> generateReflectionHandler() {
        try {
            ClassNode node = MixinService.getService().getBytecodeProvider().getClassNode(Type.getInternalName(ArclightReflectionHandler.class));
            Preconditions.checkNotNull((Object)node, (Object)"node");
            ClassWriter writer = new ClassWriter(0);
            String name = Type.getInternalName(ArclightReflectionHandler.class) + "_" + COUNTER.getAndIncrement();
            ClassRemapper visitor = new ClassRemapper((ClassVisitor)writer, (Remapper)new NameRemapper(name));
            node.accept((ClassVisitor)visitor);
            byte[] bytes = writer.toByteArray();
            ClassLoaderRemapper.dump(bytes);
            Class cl = Unsafe.defineClass((String)name.replace('/', '.'), (byte[])bytes, (int)0, (int)bytes.length, (ClassLoader)((Object)((Object)this)).getClass().getClassLoader(), (ProtectionDomain)((Object)((Object)this)).getClass().getProtectionDomain());
            Unsafe.ensureClassInitialized((Class)cl);
            Field remapper = cl.getField("remapper");
            remapper.set(null, (Object)this);
            return cl;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] dump(byte[] bytes) {
        try {
            if (ArclightRemapper.DUMP != null) {
                String className = new ClassReader(bytes).getClassName() + ".class";
                int index = className.lastIndexOf(47);
                if (index != -1) {
                    File file = new File(ArclightRemapper.DUMP, className.substring(0, index));
                    file.mkdirs();
                    Files.write(file.toPath().resolve(className.substring(index + 1)), bytes, new OpenOption[0]);
                } else {
                    Files.write(ArclightRemapper.DUMP.toPath().resolve(className), bytes, new OpenOption[0]);
                }
            }
        }
        catch (Exception e) {
            LOGGER.error("Failed to dump class " + new ClassReader(bytes).getClassName(), (Throwable)e);
        }
        return bytes;
    }

    private static class GeneratedHandlerAdapter
    extends Remapper {
        private final String from;
        private final String to;

        private GeneratedHandlerAdapter(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String map(String internalName) {
            if (this.from.equals(internalName)) {
                return this.to;
            }
            return internalName;
        }
    }

    static class WrappedMethod {
        private final String name;
        private final Class<?>[] pTypes;

        public WrappedMethod(String name, Class<?>[] pTypes) {
            this.name = name;
            this.pTypes = pTypes;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            WrappedMethod that = (WrappedMethod)o;
            return Objects.equals(this.name, that.name) && Arrays.equals(this.pTypes, that.pTypes);
        }

        public int hashCode() {
            int result = Objects.hash(this.name);
            result = 31 * result + Arrays.hashCode(this.pTypes);
            return result;
        }
    }

    private static class PluginClassWriter
    extends ClassWriter {
        public PluginClassWriter(int flags) {
            super(flags);
        }

        protected String getCommonSuperClass(String type1, String type2) {
            Collection<String> parents = GlobalClassRepo.remappingProvider().getAll(type2);
            if (parents.contains(type1)) {
                return type1;
            }
            if (GlobalClassRepo.remappingProvider().getAll(type1).contains(type2)) {
                return type2;
            }
            while (!parents.contains(type1 = this.getSuper(type1))) {
            }
            return type1;
        }

        private String getSuper(String typeName) {
            ClassNode node = GlobalClassRepo.INSTANCE.findClass(typeName);
            if (node == null) {
                LOGGER.warn("Failed to find class {}", (Object)typeName);
                return "java/lang/Object";
            }
            return ArclightRemapper.getNmsMapper().map(node.superName);
        }
    }

    private static class NameRemapper
    extends Remapper {
        private static final String ORIGIN = Type.getInternalName(ArclightReflectionHandler.class);
        private final String internal;

        private NameRemapper(String internal) {
            this.internal = internal;
        }

        public String map(String internalName) {
            if (internalName.equals(ORIGIN)) {
                return this.internal;
            }
            return super.map(internalName);
        }
    }
}

