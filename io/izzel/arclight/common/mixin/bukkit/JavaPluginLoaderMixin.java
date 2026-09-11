/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  io.izzel.arclight.api.Unsafe
 *  io.izzel.arclight.i18n.ArclightConfig
 *  org.apache.commons.lang3.Validate
 *  org.jetbrains.annotations.NotNull
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Label
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.bridge.bukkit.JavaPluginLoaderBridge;
import io.izzel.arclight.common.bridge.bukkit.PluginClassLoaderBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.izzel.arclight.i18n.ArclightConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import org.apache.commons.lang3.Validate;
import org.bukkit.Server;
import org.bukkit.Warning;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={JavaPluginLoader.class}, remap=false)
public abstract class JavaPluginLoaderMixin
implements JavaPluginLoaderBridge {
    @Shadow
    @Final
    Server server;
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Cache<Method, Class<? extends EventExecutor>> EXECUTOR_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.HOURS).build();
    private static final String HIDDEN_FORM = Float.parseFloat(System.getProperty("java.class.version")) < 57.0f ? "Ljava/lang/invoke/LambdaForm$Hidden;" : "Ljdk/internal/vm/annotation/Hidden;";

    @Override
    @Invoker(value="setClass")
    public abstract void bridge$setClass(String var1, Class<?> var2);

    @Override
    @Invoker(value="getClassByName")
    public abstract Class<?> arclight$getClassByName(String var1, boolean var2, PluginDescriptionFile var3);

    @Override
    @Accessor(value="loaders")
    public abstract <T extends URLClassLoader> List<T> arclight$getLoaders();

    @Overwrite
    Class<?> getClassByName(String name, boolean resolve, PluginDescriptionFile description) {
        SimplePluginManager manager = (SimplePluginManager)this.server.getPluginManager();
        if (ArclightConfig.spec().getCompat().isIsolatedPluginClassLoaders(name)) {
            Set<String> loaders = ArclightServer.iterateDepends(description);
            for (PluginClassLoaderBridge loader : this.arclight$getLoaders()) {
                PluginDescriptionFile desc = loader.arclight$desc();
                if (!loaders.contains(desc.getName()) && Collections.disjoint(loaders, desc.getProvides())) continue;
                try {
                    return loader.arclight$loadFromExternal(name, resolve, true);
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
            }
        } else {
            for (PluginClassLoaderBridge loader : this.arclight$getLoaders()) {
                try {
                    return loader.arclight$loadFromExternal(name, resolve, manager.isTransitiveDepend(description, loader.arclight$desc()));
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
            }
        }
        return null;
    }

    @Overwrite
    @NotNull
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener, @NotNull Plugin plugin) {
        HashSet<Method> methods;
        Validate.notNull((Object)plugin, (String)"Plugin can not be null", (Object[])new Object[0]);
        Validate.notNull((Object)listener, (String)"Listener can not be null", (Object[])new Object[0]);
        boolean useTimings = this.server.getPluginManager().useTimings();
        HashMap<Class<? extends Event>, Set<RegisteredListener>> ret = new HashMap<Class<? extends Event>, Set<RegisteredListener>>();
        try {
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet<Method>(publicMethods.length + privateMethods.length, 1.0f);
            methods.addAll(Arrays.asList(publicMethods));
            methods.addAll(Arrays.asList(privateMethods));
        }
        catch (NoClassDefFoundError e) {
            plugin.getLogger().severe("Plugin " + plugin.getDescription().getFullName() + " has failed to register events for " + String.valueOf(listener.getClass()) + " because " + e.getMessage() + " does not exist.");
            return ret;
        }
        for (Method method : methods) {
            Class<?> checkClass;
            EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null || method.isBridge() || method.isSynthetic()) continue;
            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                plugin.getLogger().severe(plugin.getDescription().getFullName() + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + String.valueOf(listener.getClass()));
                continue;
            }
            Class<Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            HashSet<RegisteredListener> eventSet = (HashSet<RegisteredListener>)ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet<RegisteredListener>();
                ret.put(eventClass, eventSet);
            }
            Class<Event> clazz = eventClass;
            while (Event.class.isAssignableFrom(clazz)) {
                if (clazz.getAnnotation(Deprecated.class) != null) {
                    Warning warning = clazz.getAnnotation(Warning.class);
                    Warning.WarningState warningState = this.server.getWarningState();
                    if (!warningState.printFor(warning)) break;
                    plugin.getLogger().log(Level.WARNING, String.format("\"%s\" has registered a listener for %s on method \"%s\", but the event is Deprecated. \"%s\"; please notify the authors %s.", plugin.getDescription().getFullName(), clazz.getName(), method.toGenericString(), warning != null && warning.reason().length() != 0 ? warning.reason() : "Server performance will be affected", Arrays.toString(plugin.getDescription().getAuthors().toArray())), warningState == Warning.WarningState.ON ? new AuthorNagException(null) : null);
                    break;
                }
                clazz = clazz.getSuperclass();
            }
            try {
                Class<? extends EventExecutor> executorClass = this.createExecutor(method, eventClass);
                Constructor<? extends EventExecutor> constructor = executorClass.getDeclaredConstructor(new Class[0]);
                constructor.setAccessible(true);
                EventExecutor executor = constructor.newInstance(new Object[0]);
                eventSet.add(new RegisteredListener(listener, executor, eh.priority(), plugin, eh.ignoreCancelled()));
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return ret;
    }

    private Class<? extends EventExecutor> createExecutor(Method method, Class<? extends Event> eventClass) throws ExecutionException {
        return (Class)EXECUTOR_CACHE.get((Object)method, () -> {
            ClassWriter cv = new ClassWriter(1);
            cv.visit(52, 4144, Type.getInternalName(method.getDeclaringClass()) + "$$arclight$" + COUNTER.getAndIncrement(), null, Type.getInternalName(Object.class), new String[]{Type.getInternalName(EventExecutor.class)});
            cv.visitOuterClass(Type.getInternalName(method.getDeclaringClass()), null, null);
            this.createConstructor((ClassVisitor)cv);
            this.createImpl(method, eventClass, (ClassVisitor)cv);
            cv.visitEnd();
            return Unsafe.defineAnonymousClass(method.getDeclaringClass(), (byte[])cv.toByteArray(), null);
        });
    }

    private void createConstructor(ClassVisitor cv) {
        MethodVisitor mv = cv.visitMethod(2, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, Type.getInternalName(Object.class), "<init>", "()V", false);
        mv.visitInsn(177);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private void createImpl(Method method, Class<? extends Event> eventClass, ClassVisitor cv) {
        String ownerType = Type.getInternalName(method.getDeclaringClass());
        MethodVisitor mv = cv.visitMethod(1, "execute", Type.getMethodDescriptor((Type)Type.VOID_TYPE, (Type[])new Type[]{Type.getType(Listener.class), Type.getType(Event.class)}), null, null);
        mv.visitAnnotation(HIDDEN_FORM, true);
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        mv.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable");
        Label label3 = new Label();
        Label label4 = new Label();
        mv.visitTryCatchBlock(label3, label4, label2, "java/lang/Throwable");
        mv.visitLabel(label0);
        mv.visitVarInsn(25, 2);
        mv.visitTypeInsn(193, Type.getInternalName(eventClass));
        mv.visitJumpInsn(154, label3);
        mv.visitLabel(label1);
        mv.visitInsn(177);
        mv.visitLabel(label3);
        mv.visitFrame(3, 0, null, 0, null);
        int invokeCode = Modifier.isStatic(method.getModifiers()) ? 184 : (method.getDeclaringClass().isInterface() ? 185 : 182);
        if (invokeCode != 184) {
            mv.visitVarInsn(25, 1);
            mv.visitTypeInsn(192, ownerType);
        }
        mv.visitVarInsn(25, 2);
        mv.visitTypeInsn(192, Type.getInternalName(eventClass));
        mv.visitMethodInsn(invokeCode, ownerType, method.getName(), Type.getMethodDescriptor((Method)method), invokeCode == 185);
        int retSize = Type.getType(method.getReturnType()).getSize();
        if (retSize > 0) {
            mv.visitInsn(87 + retSize - 1);
        }
        mv.visitLabel(label4);
        Label label5 = new Label();
        mv.visitJumpInsn(167, label5);
        mv.visitLabel(label2);
        mv.visitFrame(4, 0, null, 1, new Object[]{"java/lang/Throwable"});
        mv.visitVarInsn(58, 3);
        Label label6 = new Label();
        mv.visitLabel(label6);
        mv.visitTypeInsn(187, "org/bukkit/event/EventException");
        mv.visitInsn(89);
        mv.visitVarInsn(25, 3);
        mv.visitMethodInsn(183, "org/bukkit/event/EventException", "<init>", "(Ljava/lang/Throwable;)V", false);
        mv.visitInsn(191);
        mv.visitLabel(label5);
        mv.visitFrame(3, 0, null, 0, null);
        mv.visitInsn(177);
        Label label7 = new Label();
        mv.visitLabel(label7);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }
}

