/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Multimap
 *  io.izzel.arclight.api.Unsafe
 *  io.izzel.tools.func.Func4
 *  io.izzel.tools.product.Product
 *  io.izzel.tools.product.Product2
 *  org.apache.commons.lang3.ClassUtils
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.Handle
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.InvokeDynamicInsnNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.spongepowered.asm.util.Bytecode
 */
package io.izzel.arclight.common.mod.util.remapper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import io.izzel.arclight.common.mod.util.remapper.GlobalClassRepo;
import io.izzel.arclight.common.mod.util.remapper.PluginTransformer;
import io.izzel.arclight.common.mod.util.remapper.generated.ArclightReflectionHandler;
import io.izzel.arclight.common.util.ArrayUtil;
import io.izzel.tools.func.Func4;
import io.izzel.tools.product.Product;
import io.izzel.tools.product.Product2;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.spongepowered.asm.util.Bytecode;

public class ArclightRedirectAdapter
implements PluginTransformer {
    public static final ArclightRedirectAdapter INSTANCE = new ArclightRedirectAdapter();
    private static final Marker MARKER = MarkerManager.getMarker((String)"REDIRECT");
    private static final String REPLACED_NAME = org.objectweb.asm.Type.getInternalName(ArclightReflectionHandler.class);
    private static final Multimap<String, Product2<String, MethodInsnNode>> METHOD_MODIFY = HashMultimap.create();
    private static final Multimap<String, Product2<String, MethodInsnNode>> METHOD_REDIRECT = HashMultimap.create();
    private static final Map<String, Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]>> METHOD_TO_HANDLER = new ConcurrentHashMap<String, Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]>>();

    public static Object[] runHandle(ClassLoaderRemapper remapper, Method method, Object src, Object[] param) {
        Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> handler = METHOD_TO_HANDLER.get(ArclightRedirectAdapter.methodToString(method));
        if (handler != null) {
            return (Object[])handler.apply((Object)remapper, (Object)method, src, (Object)param);
        }
        return null;
    }

    public static Object runRedirect(ClassLoaderRemapper remapper, Method method, Object src, Object[] param) throws Throwable {
        Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> handler = METHOD_TO_HANDLER.get(ArclightRedirectAdapter.methodToString(method));
        if (handler != null) {
            Object[] ret = (Object[])handler.apply((Object)remapper, (Object)method, src, (Object)param);
            return ((Method)ret[0]).invoke(ret[1], (Object[])ret[2]);
        }
        return remapper;
    }

    public static void scanMethod(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ArclightMod.LOGGER.debug(MARKER, "Scanning {}", (Object)reader.getClassName());
        ClassNode node = new ClassNode();
        reader.accept((ClassVisitor)node, 6);
        block2: for (MethodNode method : node.methods) {
            ListIterator iterator = method.instructions.iterator();
            while (iterator.hasNext()) {
                AbstractInsnNode instruction = (AbstractInsnNode)iterator.next();
                int opcode = instruction.getOpcode();
                if (opcode >= 182 && opcode <= 185) {
                    if (iterator.nextIndex() < method.instructions.size() - 1) continue block2;
                    MethodInsnNode insnNode = (MethodInsnNode)instruction;
                    String key = insnNode.name + insnNode.desc;
                    if (!METHOD_MODIFY.containsKey((Object)key) && !METHOD_REDIRECT.containsKey((Object)key)) continue;
                    try {
                        Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> bridge;
                        Class<?> cl = Class.forName(insnNode.owner.replace('/', '.'));
                        org.objectweb.asm.Type[] argumentTypes = org.objectweb.asm.Type.getMethodType((String)insnNode.desc).getArgumentTypes();
                        Class[] paramTypes = new Class[argumentTypes.length];
                        for (org.objectweb.asm.Type type : argumentTypes) {
                            paramTypes[i] = ClassUtils.getClass((String)type.getClassName());
                        }
                        Method target = ArclightRedirectAdapter.methodOf(cl, insnNode.name, paramTypes);
                        if (target == null || (bridge = METHOD_TO_HANDLER.get(ArclightRedirectAdapter.methodToString(target))) == null) continue;
                        ArclightMod.LOGGER.debug(MARKER, "Creating bridge handler {}/{}{} to {}", (Object)node.name, (Object)method.name, (Object)method.desc, (Object)ArclightRedirectAdapter.methodToString(target));
                        METHOD_TO_HANDLER.put(node.name + "/" + method.name + method.desc, new BridgeHandler(bridge, target));
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                if (opcode >= 21 && opcode <= 25) continue;
                continue block2;
            }
        }
    }

    @Override
    public void handleClass(ClassNode node, ClassLoaderRemapper remapper, ArclightRemapConfig config) {
        if (config.remap()) {
            ArclightRedirectAdapter.redirect(node, remapper);
        }
    }

    private static void redirect(ClassNode classNode, ClassLoaderRemapper remapper) {
        for (MethodNode methodNode : classNode.methods) {
            for (AbstractInsnNode insnNode : methodNode.instructions) {
                if (insnNode instanceof MethodInsnNode) {
                    MethodInsnNode from = (MethodInsnNode)insnNode;
                    if (from.getOpcode() == 183 && Objects.equals(from.owner, classNode.superName) && Objects.equals(from.name, methodNode.name) && Objects.equals(from.desc, methodNode.desc)) continue;
                    ArclightRedirectAdapter.process(from, methodNode.instructions, remapper, classNode);
                    continue;
                }
                if (insnNode.getOpcode() != 186) continue;
                InvokeDynamicInsnNode invokeDynamic = (InvokeDynamicInsnNode)insnNode;
                Object[] bsmArgs = invokeDynamic.bsmArgs;
                for (int i = 0; i < bsmArgs.length; ++i) {
                    Handle handle;
                    Object bsmArg = bsmArgs[i];
                    if (!(bsmArg instanceof Handle) || ArclightRedirectAdapter.toOpcode((handle = (Handle)bsmArg).getTag()) == -1) continue;
                    bsmArgs[i] = ArclightRedirectAdapter.processHandle(handle, remapper);
                }
            }
        }
    }

    private static Handle processHandle(Handle handle, ClassLoaderRemapper remapper) {
        String key = handle.getName() + handle.getDesc();
        Collection col = METHOD_REDIRECT.get((Object)key);
        for (Product2 methodRedirect : col) {
            if (!ArclightRedirectAdapter.isSuperType(handle.getOwner(), (String)methodRedirect._1)) continue;
            MethodInsnNode node = (MethodInsnNode)methodRedirect._2;
            String owner = REPLACED_NAME.equals(node.owner) ? remapper.getGeneratedHandler() : node.owner;
            return new Handle(ArclightRedirectAdapter.toHandle(node.getOpcode()), owner, node.name, node.desc, node.itf);
        }
        return handle;
    }

    private static void process(MethodInsnNode node, InsnList insnList, ClassLoaderRemapper remapper, ClassNode classNode) {
        String key = node.name + node.desc;
        Collection modifyArgsCol = METHOD_MODIFY.get((Object)key);
        for (Product2 modifyArgs : modifyArgsCol) {
            MethodInsnNode handlerNode;
            if (!ArclightRedirectAdapter.isSuperType(node.owner, (String)modifyArgs._1)) continue;
            if (REPLACED_NAME.equals(((MethodInsnNode)modifyArgs._2).owner)) {
                handlerNode = (MethodInsnNode)((MethodInsnNode)modifyArgs._2).clone((Map)ImmutableMap.of());
                handlerNode.owner = remapper.getGeneratedHandler();
            } else {
                handlerNode = (MethodInsnNode)modifyArgs._2;
            }
            ArclightRedirectAdapter.processModify(node, insnList, handlerNode, classNode);
            return;
        }
        Collection methodRedirectCol = METHOD_REDIRECT.get((Object)key);
        for (Product2 methodRedirect : methodRedirectCol) {
            MethodInsnNode handlerNode;
            if (!ArclightRedirectAdapter.isSuperType(node.owner, (String)methodRedirect._1)) continue;
            if (REPLACED_NAME.equals(((MethodInsnNode)methodRedirect._2).owner)) {
                handlerNode = (MethodInsnNode)((MethodInsnNode)methodRedirect._2).clone((Map)ImmutableMap.of());
                handlerNode.owner = remapper.getGeneratedHandler();
            } else {
                handlerNode = (MethodInsnNode)methodRedirect._2;
            }
            ArclightRedirectAdapter.processMethodRedirect(node, insnList, handlerNode);
            return;
        }
    }

    private static boolean isSuperType(String sub, String sup) {
        return sub.equals(sup) || GlobalClassRepo.inheritanceProvider().getAll(sub).contains(sup);
    }

    private static void processMethodRedirect(MethodInsnNode node, InsnList insnList, MethodInsnNode handlerNode) {
        insnList.set((AbstractInsnNode)node, (AbstractInsnNode)handlerNode);
    }

    private static void processModify(MethodInsnNode node, InsnList insnList, MethodInsnNode handlerNode, ClassNode classNode) {
        InsnList list = new InsnList();
        list.add((AbstractInsnNode)handlerNode);
        org.objectweb.asm.Type methodType = org.objectweb.asm.Type.getMethodType((String)node.desc);
        org.objectweb.asm.Type[] types = methodType.getArgumentTypes();
        if (node.getOpcode() != 184) {
            org.objectweb.asm.Type selfType = node.getOpcode() == 183 ? org.objectweb.asm.Type.getObjectType((String)classNode.name) : org.objectweb.asm.Type.getObjectType((String)node.owner);
            types = ArrayUtil.prepend(types, selfType, org.objectweb.asm.Type[]::new);
        }
        if (types.length == 1) {
            if (node.desc.startsWith("()")) {
                String retDesc = methodType.getReturnType().getDescriptor();
                if (handlerNode.desc.equals("(" + retDesc + ")" + retDesc)) {
                    insnList.insert((AbstractInsnNode)node, (AbstractInsnNode)handlerNode);
                    return;
                }
            } else {
                String desc = types[0].getDescriptor();
                if (handlerNode.desc.equals("(" + desc + ")" + desc)) {
                    insnList.insertBefore((AbstractInsnNode)node, (AbstractInsnNode)handlerNode);
                    return;
                }
            }
        }
        int argumentTypesLength = types.length;
        for (int i = 0; i < argumentTypesLength; ++i) {
            org.objectweb.asm.Type type = types[i];
            if (i > 0) {
                ArclightRedirectAdapter.swap(list, types[i - 1]);
            }
            if (argumentTypesLength > 1 && i != argumentTypesLength - 1) {
                list.add((AbstractInsnNode)new InsnNode(89));
            }
            list.add(ArclightRedirectAdapter.loadInt(i));
            list.add((AbstractInsnNode)new InsnNode(50));
            ArclightRedirectAdapter.cast(list, type);
        }
        insnList.insertBefore((AbstractInsnNode)node, list);
    }

    private static void swap(InsnList list, org.objectweb.asm.Type top) {
        if (top.getSize() == 1) {
            list.add((AbstractInsnNode)new InsnNode(95));
        } else {
            list.add((AbstractInsnNode)new InsnNode(93));
            list.add((AbstractInsnNode)new InsnNode(88));
        }
    }

    private static void cast(InsnList list, org.objectweb.asm.Type type) {
        if (type.getSort() == 10 || type.getSort() == 9) {
            String internalName = type.getInternalName();
            if (!"java/lang/Object".equals(internalName)) {
                list.add((AbstractInsnNode)new TypeInsnNode(192, internalName));
            }
        } else {
            String boxingType = Bytecode.getBoxingType((org.objectweb.asm.Type)type);
            String unboxingMethod = Bytecode.getUnboxingMethod((org.objectweb.asm.Type)type);
            list.add((AbstractInsnNode)new TypeInsnNode(192, boxingType));
            list.add((AbstractInsnNode)new MethodInsnNode(182, boxingType, unboxingMethod, "()" + type.getDescriptor(), false));
        }
    }

    private static void modify(Class<?> owner, String name, Class<?> ... args) {
        ArclightRedirectAdapter.modify(owner, name, name, args);
    }

    private static void modify(Class<?> owner, String name, String handlerName, Class<?> ... args) {
        ArclightRedirectAdapter.addRule(true, owner, name, handlerName, args);
    }

    private static void redirect(Class<?> owner, String name, String handlerName, Class<?> ... args) {
        ArclightRedirectAdapter.addRule(false, owner, name, handlerName, args);
    }

    private static void addRule(boolean modifyArgs, Class<?> owner, String name, String handlerName, Class<?> ... args) {
        if (owner == null) {
            return;
        }
        Method original = ArclightRedirectAdapter.methodOf(owner, name, args);
        if (original == null) {
            return;
        }
        Class<?>[] handlerArgs = !Modifier.isStatic(original.getModifiers()) ? ArrayUtil.prepend(args, owner, Class[]::new) : args;
        Method handler = ArclightRedirectAdapter.methodOf(ArclightReflectionHandler.class, "redirect" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs);
        while (handler == null) {
            handlerArgs[0] = handlerArgs[0].getSuperclass();
            handler = ArclightRedirectAdapter.methodOf(ArclightReflectionHandler.class, "redirect" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs);
        }
        METHOD_REDIRECT.put((Object)(name + org.objectweb.asm.Type.getMethodDescriptor((Method)original)), (Object)Product.of((Object)org.objectweb.asm.Type.getInternalName(owner), (Object)ArclightRedirectAdapter.methodNodeOf(handler)));
        String key = ArclightRedirectAdapter.methodToString(original);
        if (modifyArgs) {
            Method modifyHandler = ArclightRedirectAdapter.methodOf(ArclightReflectionHandler.class, "handle" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs);
            if (modifyHandler == null) {
                handlerArgs[0] = original.getReturnType();
                modifyHandler = ArclightRedirectAdapter.methodOf(ArclightReflectionHandler.class, "handle" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs);
            }
            if (modifyHandler == null) {
                throw new RuntimeException("No handler for " + String.valueOf(original));
            }
            METHOD_MODIFY.put((Object)(name + org.objectweb.asm.Type.getMethodDescriptor((Method)original)), (Object)Product.of((Object)org.objectweb.asm.Type.getInternalName(owner), (Object)ArclightRedirectAdapter.methodNodeOf(modifyHandler)));
            METHOD_TO_HANDLER.put(key, new ModifyHandler("handle" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs));
        } else {
            METHOD_TO_HANDLER.put(key, new RedirectHandler("redirect" + ArclightRedirectAdapter.capitalize(handlerName), handlerArgs));
        }
    }

    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private static Class<?> classOf(String cl) {
        try {
            return Class.forName(cl);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Method methodOf(Class<?> owner, String name, Class<?> ... args) {
        try {
            return owner.getMethod(name, args);
        }
        catch (Exception e) {
            try {
                return owner.getDeclaredMethod(name, args);
            }
            catch (NoSuchMethodException e2) {
                return null;
            }
        }
    }

    private static MethodInsnNode methodNodeOf(Method method) {
        String owner = org.objectweb.asm.Type.getInternalName(method.getDeclaringClass());
        String name = method.getName();
        String desc = org.objectweb.asm.Type.getMethodDescriptor((Method)method);
        return new MethodInsnNode(184, owner, name, desc);
    }

    private static String methodToString(Method method) {
        return org.objectweb.asm.Type.getInternalName(method.getDeclaringClass()) + "/" + method.getName() + org.objectweb.asm.Type.getMethodDescriptor((Method)method);
    }

    private static int toOpcode(int handleType) {
        return switch (handleType) {
            case 9 -> 185;
            case 5 -> 182;
            case 6 -> 184;
            default -> -1;
        };
    }

    private static int toHandle(int opcode) {
        return switch (opcode) {
            case 185 -> 9;
            case 184 -> 6;
            case 182 -> 5;
            default -> -1;
        };
    }

    static AbstractInsnNode loadInt(int i) {
        if (i >= -1 && i < 6) {
            return new InsnNode(3 + i);
        }
        if (i >= -128 && i < 128) {
            return new IntInsnNode(16, i);
        }
        if (i >= Short.MIN_VALUE && i < 32768) {
            return new IntInsnNode(17, i);
        }
        return new LdcInsnNode((Object)i);
    }

    static {
        ArclightRedirectAdapter.redirect(Field.class, "getName", "fieldGetName", new Class[0]);
        ArclightRedirectAdapter.redirect(Method.class, "getName", "methodGetName", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "getCanonicalName", "classGetCanonicalName", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "getSimpleName", "classGetSimpleName", new Class[0]);
        ArclightRedirectAdapter.modify(Class.class, "getName", "classGetName", new Class[0]);
        ArclightRedirectAdapter.modify(Package.class, "getName", "packageGetName", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "forName", "classForName", String.class);
        ArclightRedirectAdapter.redirect(Class.class, "forName", "classForName", String.class, Boolean.TYPE, ClassLoader.class);
        ArclightRedirectAdapter.modify(Class.class, "getField", "classGetField", String.class);
        ArclightRedirectAdapter.modify(Class.class, "getDeclaredField", "classGetDeclaredField", String.class);
        ArclightRedirectAdapter.modify(Class.class, "getMethod", "classGetMethod", String.class, Class[].class);
        ArclightRedirectAdapter.modify(Class.class, "getDeclaredMethod", "classGetDeclaredMethod", String.class, Class[].class);
        ArclightRedirectAdapter.redirect(Class.class, "getDeclaredMethods", "getDeclaredMethods", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "getMethods", "getMethods", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "getDeclaredFields", "getDeclaredFields", new Class[0]);
        ArclightRedirectAdapter.redirect(Class.class, "getFields", "getFields", new Class[0]);
        ArclightRedirectAdapter.modify(MethodType.class, "fromMethodDescriptorString", "fromDescStr", String.class, ClassLoader.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findStatic", "lookupFindStatic", Class.class, String.class, MethodType.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findVirtual", "lookupFindVirtual", Class.class, String.class, MethodType.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findSpecial", "lookupFindSpecial", Class.class, String.class, MethodType.class, Class.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findGetter", "lookupFindGetter", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findSetter", "lookupFindSetter", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findStaticGetter", "lookupFindStaticGetter", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findStaticSetter", "lookupFindStaticSetter", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.redirect(MethodHandles.Lookup.class, "findClass", "lookupFindClass", String.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findVarHandle", "lookupFindVarHandle", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "findStaticVarHandle", "lookupFindStaticVarHandle", Class.class, String.class, Class.class);
        ArclightRedirectAdapter.modify(ClassLoader.class, "loadClass", "classLoaderLoadClass", String.class);
        ArclightRedirectAdapter.redirect(Class.class, "getResource", "classGetResource", String.class);
        ArclightRedirectAdapter.redirect(Class.class, "getResourceAsStream", "classGetResourceAsStream", String.class);
        ArclightRedirectAdapter.redirect(ClassLoader.class, "getResource", "classLoaderGetResource", String.class);
        ArclightRedirectAdapter.redirect(ClassLoader.class, "getResources", "classLoaderGetResources", String.class);
        ArclightRedirectAdapter.redirect(ClassLoader.class, "getResourceAsStream", "classLoaderGetResourceAsStream", String.class);
        ArclightRedirectAdapter.modify(Method.class, "invoke", "methodInvoke", Object.class, Object[].class);
        ArclightRedirectAdapter.modify(ClassLoader.class, "defineClass", byte[].class, Integer.TYPE, Integer.TYPE);
        ArclightRedirectAdapter.modify(ClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
        ArclightRedirectAdapter.modify(ClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
        ArclightRedirectAdapter.modify(ClassLoader.class, "defineClass", String.class, ByteBuffer.class, ProtectionDomain.class);
        ArclightRedirectAdapter.modify(SecureClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, CodeSource.class);
        ArclightRedirectAdapter.modify(SecureClassLoader.class, "defineClass", String.class, ByteBuffer.class, CodeSource.class);
        ArclightRedirectAdapter.modify(ArclightRedirectAdapter.classOf("sun.misc.Unsafe"), "defineClass", "unsafeDefineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
        ArclightRedirectAdapter.modify(ArclightRedirectAdapter.classOf("jdk.internal.misc.Unsafe"), "defineClass", "unsafeDefineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
        ArclightRedirectAdapter.modify(ArclightRedirectAdapter.classOf("jdk.internal.misc.Unsafe"), "defineClass0", "unsafeDefineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "defineClass", "lookupDefineClass", byte[].class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "defineHiddenClass", "lookupDefineHiddenClass", byte[].class, Boolean.TYPE, MethodHandles.Lookup.ClassOption[].class);
        ArclightRedirectAdapter.modify(MethodHandles.Lookup.class, "defineHiddenClassWithClassData", "lookupDefineHiddenClassWithClassData", byte[].class, Object.class, Boolean.TYPE, MethodHandles.Lookup.ClassOption[].class);
        ArclightRedirectAdapter.redirect(Type.class, "getTypeName", "typeGetName", new Class[0]);
    }

    private static class BridgeHandler
    implements Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> {
        private final Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> bridge;
        private final Method targetMethod;

        private BridgeHandler(Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> bridge, Method targetMethod) {
            this.bridge = bridge;
            this.targetMethod = targetMethod;
        }

        public Object[] apply4(ClassLoaderRemapper remapper, Method method, Object src, Object[] param) {
            boolean bridgeStatic = Modifier.isStatic(this.targetMethod.getModifiers());
            if (bridgeStatic) {
                Object[] ret = (Object[])this.bridge.apply((Object)remapper, (Object)this.targetMethod, null, (Object)param);
                return new Object[]{method, src, ret[2]};
            }
            Object[] ret = (Object[])this.bridge.apply((Object)remapper, (Object)this.targetMethod, param[0], (Object)Arrays.copyOfRange(param, 1, param.length));
            return new Object[]{method, src, ArrayUtil.prepend((Object[])ret[2], ret[1])};
        }
    }

    private static class ModifyHandler
    implements Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> {
        private final String handlerName;
        private final Class<?>[] handlerArgs;

        public ModifyHandler(String handlerName, Class<?>[] handlerArgs) {
            this.handlerName = handlerName;
            this.handlerArgs = handlerArgs;
        }

        public Object[] apply4(ClassLoaderRemapper remapper, Method method, Object src, Object[] param) {
            try {
                Method handleMethod = remapper.getGeneratedHandlerClass().getMethod(this.handlerName, this.handlerArgs);
                if (method.getParameterCount() > 0) {
                    if (handleMethod.getReturnType().isArray() && !Modifier.isStatic(method.getModifiers())) {
                        Object[] invoke = (Object[])handleMethod.invoke(null, ArrayUtil.prepend(param, src));
                        return new Object[]{method, invoke[0], Arrays.copyOfRange(invoke, 1, invoke.length)};
                    }
                    return new Object[]{method, src, handleMethod.invoke(null, param)};
                }
                return new Object[]{handleMethod, null, new Object[]{method.invoke(src, param)}};
            }
            catch (Exception e) {
                Unsafe.throwException((Throwable)e);
                return null;
            }
        }
    }

    private static class RedirectHandler
    implements Func4<ClassLoaderRemapper, Method, Object, Object[], Object[]> {
        private final String handlerName;
        private final Class<?>[] handlerArgs;

        public RedirectHandler(String handlerName, Class<?>[] handlerArgs) {
            this.handlerName = handlerName;
            this.handlerArgs = handlerArgs;
        }

        public Object[] apply4(ClassLoaderRemapper remapper, Method method, Object src, Object[] param) {
            try {
                Method redirectMethod = remapper.getGeneratedHandlerClass().getMethod(this.handlerName, this.handlerArgs);
                return new Object[]{redirectMethod, null, Modifier.isStatic(method.getModifiers()) ? param : ArrayUtil.prepend(param, src)};
            }
            catch (Exception e) {
                Unsafe.throwException((Throwable)e);
                return null;
            }
        }
    }
}

