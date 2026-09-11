/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraftforge.eventbus.EventBus
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.IEventListener
 *  net.minecraftforge.eventbus.api.IGenericEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 */
package io.izzel.arclight.common.mod.util;

import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.ArclightMod;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventListener;
import net.minecraftforge.eventbus.api.IGenericEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.plugin.Plugin;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class PluginEventHandler
implements IEventListener {
    private static final String HANDLER_DESC = org.objectweb.asm.Type.getInternalName(IEventListener.class);
    private static final String HANDLER_FUNC_DESC = org.objectweb.asm.Type.getMethodDescriptor((org.objectweb.asm.Type)org.objectweb.asm.Type.VOID_TYPE, (org.objectweb.asm.Type[])new org.objectweb.asm.Type[]{org.objectweb.asm.Type.getType(Event.class)});
    private static final MethodHandle MH_GET_LISTENERS;
    private static final MethodHandle MH_ADD_LISTENERS;
    private final IEventListener handler;
    private final SubscribeEvent subInfo;
    private Type filter = null;
    private String readable;

    public PluginEventHandler(Plugin plugin, Object target, Method method, boolean isGeneric) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Type type;
        this.handler = Modifier.isStatic(method.getModifiers()) ? (IEventListener)this.createWrapper(method).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]) : (IEventListener)this.createWrapper(method).getConstructor(Object.class).newInstance(target);
        this.subInfo = method.getAnnotation(SubscribeEvent.class);
        this.readable = "PL: " + plugin.getName() + " ASM: " + String.valueOf(target) + " " + method.getName() + org.objectweb.asm.Type.getMethodDescriptor((Method)method);
        if (isGeneric && (type = method.getGenericParameterTypes()[0]) instanceof ParameterizedType) {
            this.filter = ((ParameterizedType)type).getActualTypeArguments()[0];
            if (this.filter instanceof ParameterizedType) {
                this.filter = ((ParameterizedType)this.filter).getRawType();
            } else {
                WildcardType wfilter;
                Type type2 = this.filter;
                if (type2 instanceof WildcardType && (wfilter = (WildcardType)type2).getUpperBounds().length == 1 && wfilter.getUpperBounds()[0] == Object.class && wfilter.getLowerBounds().length == 0) {
                    this.filter = null;
                }
            }
        }
    }

    public static void register(Plugin plugin, EventBus bus, Object target) throws Throwable {
        ConcurrentHashMap listeners = MH_GET_LISTENERS.invokeExact(bus);
        if (!listeners.containsKey(target)) {
            if (target.getClass() == Class.class) {
                PluginEventHandler.registerClass((Class)target, plugin, bus);
            } else {
                PluginEventHandler.registerObject(target, plugin, bus);
            }
        }
    }

    private static void registerClass(Class<?> clazz, Plugin plugin, EventBus bus) {
        Arrays.stream(clazz.getMethods()).filter(m -> Modifier.isStatic(m.getModifiers())).filter(m -> m.isAnnotationPresent(SubscribeEvent.class)).forEach(m -> PluginEventHandler.registerListener(clazz, m, m, plugin, bus));
    }

    private static Optional<Method> getDeclMethod(Class<?> clz, Method in) {
        try {
            return Optional.of(clz.getDeclaredMethod(in.getName(), in.getParameterTypes()));
        }
        catch (NoSuchMethodException nse) {
            return Optional.empty();
        }
    }

    private static void registerObject(Object obj, Plugin plugin, EventBus bus) {
        HashSet classes = new HashSet();
        PluginEventHandler.typesFor(obj.getClass(), classes);
        Arrays.stream(obj.getClass().getMethods()).filter(m -> !Modifier.isStatic(m.getModifiers())).forEach(m -> classes.stream().map(c -> PluginEventHandler.getDeclMethod(c, m)).filter(rm -> rm.isPresent() && ((Method)rm.get()).isAnnotationPresent(SubscribeEvent.class)).findFirst().ifPresent(rm -> PluginEventHandler.registerListener(obj, m, (Method)rm.get(), plugin, bus)));
    }

    private static void typesFor(Class<?> clz, Set<Class<?>> visited) {
        if (clz.getSuperclass() == null) {
            return;
        }
        PluginEventHandler.typesFor(clz.getSuperclass(), visited);
        Arrays.stream(clz.getInterfaces()).forEach(i -> PluginEventHandler.typesFor(i, visited));
        visited.add(clz);
    }

    private static void registerListener(Object target, Method method, Method real, Plugin plugin, EventBus bus) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Method " + String.valueOf(method) + " has @SubscribeEvent annotation. It has " + parameterTypes.length + " arguments, but event handler methods require a single argument only.");
        }
        Class<?> eventType = parameterTypes[0];
        if (!Event.class.isAssignableFrom(eventType)) {
            throw new IllegalArgumentException("Method " + String.valueOf(method) + " has @SubscribeEvent annotation, but takes an argument that is not an Event subtype : " + String.valueOf(eventType));
        }
        PluginEventHandler.register(eventType, target, real, plugin, bus);
    }

    private static void register(Class<?> eventType, Object target, Method method, Plugin plugin, EventBus bus) {
        try {
            PluginEventHandler asm = new PluginEventHandler(plugin, target, method, IGenericEvent.class.isAssignableFrom(eventType));
            MH_ADD_LISTENERS.invokeExact(bus, target, eventType, asm, asm.getPriority());
        }
        catch (Throwable e) {
            ArclightMod.LOGGER.error("Error registering event handler: {} {}", eventType, (Object)method, (Object)e);
        }
    }

    private String getUniqueName(Method callback) {
        return String.format("%s.__%s_%s_%s", callback.getDeclaringClass().getPackageName(), callback.getDeclaringClass().getSimpleName(), callback.getName(), callback.getParameterTypes()[0].getSimpleName());
    }

    public Class<?> createWrapper(Method callback) {
        ClassWriter cw = new ClassWriter(0);
        boolean isStatic = Modifier.isStatic(callback.getModifiers());
        String name = this.getUniqueName(callback);
        String desc = name.replace('.', '/');
        String instType = org.objectweb.asm.Type.getInternalName(callback.getDeclaringClass());
        String eventType = org.objectweb.asm.Type.getInternalName(callback.getParameterTypes()[0]);
        cw.visit(50, 33, desc, null, "java/lang/Object", new String[]{HANDLER_DESC});
        cw.visitSource(".dynamic", null);
        if (!isStatic) {
            cw.visitField(1, "instance", "Ljava/lang/Object;", null, null).visitEnd();
        }
        MethodVisitor mv = cw.visitMethod(1, "<init>", isStatic ? "()V" : "(Ljava/lang/Object;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
        if (!isStatic) {
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(25, 1);
            mv.visitFieldInsn(181, desc, "instance", "Ljava/lang/Object;");
        }
        mv.visitInsn(177);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        mv = cw.visitMethod(1, "invoke", HANDLER_FUNC_DESC, null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        if (!isStatic) {
            mv.visitFieldInsn(180, desc, "instance", "Ljava/lang/Object;");
            mv.visitTypeInsn(192, instType);
        }
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, eventType);
        mv.visitMethodInsn(isStatic ? 184 : 182, instType, callback.getName(), org.objectweb.asm.Type.getMethodDescriptor((Method)callback), false);
        mv.visitInsn(177);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        return Unsafe.defineClass((String)name, (byte[])bytes, (int)0, (int)bytes.length, (ClassLoader)callback.getDeclaringClass().getClassLoader(), (ProtectionDomain)callback.getDeclaringClass().getProtectionDomain());
    }

    public EventPriority getPriority() {
        return this.subInfo.priority();
    }

    public void invoke(Event event) {
        if (!(this.handler == null || event.isCancelable() && event.isCanceled() && !this.subInfo.receiveCanceled() || this.filter != null && this.filter != ((IGenericEvent)event).getGenericType())) {
            this.handler.invoke(event);
        }
    }

    public String toString() {
        return this.readable;
    }

    static {
        try {
            MH_GET_LISTENERS = Unsafe.lookup().findGetter(EventBus.class, "listeners", ConcurrentHashMap.class);
            MH_ADD_LISTENERS = Unsafe.lookup().findVirtual(EventBus.class, "addToListeners", MethodType.methodType(Void.TYPE, Object.class, Class.class, IEventListener.class, EventPriority.class));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

