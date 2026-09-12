/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Label
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.boot.asm;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.boot.asm.ArclightImplementer;
import io.izzel.arclight.boot.asm.Implementer;
import io.izzel.arclight.i18n.ArclightConfig;
import io.izzel.arclight.i18n.conf.AsyncCatcherSpec;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class AsyncCatcher
implements Implementer {
    public static final AsyncCatcher INSTANCE = new AsyncCatcher();
    private static final Marker MARKER = MarkerManager.getMarker((String)"ASYNC_CATCHER");
    private static final CallbackInfoReturnable<?> NOOP = new CallbackInfoReturnable("noop", false);
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private final boolean dump;
    private final boolean warn;
    private final AsyncCatcherSpec.Operation defaultOp;
    private final Map<String, Map<String, String>> reasons;
    private final ClassLoader classLoader;

    public AsyncCatcher() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        this.reasons = (Map)gson.fromJson((Reader)new InputStreamReader(AsyncCatcher.class.getResourceAsStream("/async_catcher.json")), new TypeToken<Map<String, Map<String, String>>>(){}.getType());
        this.defaultOp = ArclightConfig.spec().getAsyncCatcher().getDefaultOp();
        this.dump = ArclightConfig.spec().getAsyncCatcher().isDump();
        this.warn = ArclightConfig.spec().getAsyncCatcher().isWarn();
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Override
    public boolean processClass(ClassNode node, ILaunchPluginService.ITransformerLoader transformerLoader) {
        Map<String, String> map = this.reasons.get(node.name);
        if (map != null) {
            boolean found = false;
            List methods = node.methods;
            int methodsSize = methods.size();
            for (int i = 0; i < methodsSize; ++i) {
                MethodNode method = (MethodNode)methods.get(i);
                String reason = map.get(method.name + method.desc);
                if (reason == null) continue;
                found = true;
                this.injectCheck(node, method, reason);
            }
            return found;
        }
        return false;
    }

    private void injectCheck(ClassNode node, MethodNode methodNode, String reason) {
        boolean hasReturn;
        ArclightImplementer.LOGGER.debug(MARKER, "Injecting {}/{}{} for reason {}", (Object)node.name, (Object)methodNode.name, (Object)methodNode.desc, (Object)reason);
        AsyncCatcherSpec.Operation operation = ArclightConfig.spec().getAsyncCatcher().getOverrides().getOrDefault(reason, this.defaultOp);
        InsnList insnList = new InsnList();
        LabelNode labelNode = new LabelNode(new Label());
        LabelNode labelNode1 = new LabelNode(new Label());
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, "io/izzel/arclight/common/mod/server/ArclightServer", "isPrimaryThread", "()Z"));
        insnList.add((AbstractInsnNode)new JumpInsnNode(154, labelNode));
        this.instantiateCallback(node, methodNode, insnList);
        insnList.add((AbstractInsnNode)new FieldInsnNode(178, Type.getType(AsyncCatcherSpec.Operation.class).getInternalName(), operation.name(), Type.getType(AsyncCatcherSpec.Operation.class).getDescriptor()));
        insnList.add((AbstractInsnNode)new LdcInsnNode((Object)reason));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, "io/izzel/arclight/common/mod/server/ArclightServer", "getMainThreadExecutor", "()Ljava/util/concurrent/Executor;", false));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getType(AsyncCatcher.class).getInternalName(), "checkOp", "(Ljava/util/function/Supplier;Lio/izzel/arclight/i18n/conf/AsyncCatcherSpec$Operation;Ljava/lang/String;Ljava/util/concurrent/Executor;)Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;"));
        Type returnType = Type.getMethodType((String)methodNode.desc).getReturnType();
        boolean bl = hasReturn = !returnType.equals((Object)Type.VOID_TYPE);
        if (hasReturn) {
            insnList.add((AbstractInsnNode)new InsnNode(89));
        }
        insnList.add((AbstractInsnNode)new MethodInsnNode(182, Type.getType(CallbackInfoReturnable.class).getInternalName(), "isCancelled", "()Z"));
        insnList.add((AbstractInsnNode)new JumpInsnNode(153, hasReturn ? labelNode1 : labelNode));
        if (hasReturn) {
            insnList.add((AbstractInsnNode)new MethodInsnNode(182, Type.getType(CallbackInfoReturnable.class).getInternalName(), AsyncCatcher.getReturnAccessor(returnType), AsyncCatcher.getReturnDescriptor(returnType)));
            if (returnType.getSort() > 8) {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, returnType.getInternalName()));
            }
            insnList.add((AbstractInsnNode)new InsnNode(returnType.getOpcode(172)));
        } else {
            insnList.add((AbstractInsnNode)new InsnNode(177));
        }
        insnList.add((AbstractInsnNode)labelNode1);
        insnList.add((AbstractInsnNode)new InsnNode(87));
        insnList.add((AbstractInsnNode)labelNode);
        methodNode.instructions.insert(insnList);
    }

    private void instantiateCallback(ClassNode node, MethodNode methodNode, InsnList insnList) {
        MethodNode bridge = Modifier.isPrivate(methodNode.access) ? this.createBridge(node, methodNode) : methodNode;
        ClassNode classNode = new ClassNode();
        String desc = this.createImplType(node, methodNode, classNode, bridge);
        insnList.add((AbstractInsnNode)new TypeInsnNode(187, classNode.name));
        insnList.add((AbstractInsnNode)new InsnNode(89));
        Type methodType = Type.getMethodType((String)methodNode.desc);
        ArclightImplementer.loadArgs(insnList, methodNode, methodType.getArgumentTypes(), 0);
        insnList.add((AbstractInsnNode)new MethodInsnNode(183, classNode.name, "<init>", desc));
    }

    private String createImplType(ClassNode node, MethodNode methodNode, ClassNode classNode, MethodNode bridge) {
        classNode.version = 52;
        classNode.name = node.name + "$AsyncCatcher$" + COUNTER.getAndIncrement();
        classNode.access = 4144;
        classNode.superName = "java/lang/Object";
        classNode.interfaces.add(Type.getType(Supplier.class).getInternalName());
        ArrayList<Type> types = new ArrayList<Type>();
        if (!Modifier.isStatic(methodNode.access)) {
            types.add(Type.getObjectType((String)node.name));
        }
        types.addAll(Arrays.asList(Type.getArgumentTypes((String)methodNode.desc)));
        for (int i = 0; i < types.size(); ++i) {
            FieldNode fieldNode = new FieldNode(18, "x" + i, ((Type)types.get(i)).getDescriptor(), null, null);
            classNode.fields.add(fieldNode);
        }
        MethodNode init = new MethodNode();
        init.name = "<init>";
        init.desc = Type.getMethodType((Type)Type.VOID_TYPE, (Type[])types.toArray(new Type[0])).getDescriptor();
        init.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
        init.instructions.add((AbstractInsnNode)new MethodInsnNode(183, "java/lang/Object", "<init>", "()V"));
        int offset = 1;
        for (int i = 0; i < types.size(); ++i) {
            init.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
            init.instructions.add((AbstractInsnNode)new VarInsnNode(((Type)types.get(i)).getOpcode(21), offset));
            init.instructions.add((AbstractInsnNode)new FieldInsnNode(181, classNode.name, "x" + i, ((Type)types.get(i)).getDescriptor()));
            offset += ((Type)types.get(i)).getSize();
        }
        init.instructions.add((AbstractInsnNode)new InsnNode(177));
        classNode.methods.add(init);
        MethodNode get = new MethodNode();
        get.name = "get";
        get.access = 17;
        get.desc = "()Ljava/lang/Object;";
        GeneratorAdapter adapter = new GeneratorAdapter((MethodVisitor)get, 1, get.name, get.desc);
        for (int i = 0; i < types.size(); ++i) {
            adapter.loadThis();
            adapter.getField(Type.getObjectType((String)classNode.name), "x" + i, (Type)types.get(i));
        }
        get.instructions.add((AbstractInsnNode)new MethodInsnNode(Modifier.isStatic(methodNode.access) ? 184 : 182, node.name, bridge.name, bridge.desc));
        adapter.valueOf(Type.getReturnType((String)bridge.desc));
        adapter.returnValue();
        classNode.methods.add(get);
        ClassWriter writer = new ClassWriter(1);
        classNode.accept((ClassVisitor)writer);
        byte[] bytes = writer.toByteArray();
        Unsafe.defineClass(Type.getObjectType((String)classNode.name).getClassName(), bytes, 0, bytes.length, this.classLoader, AsyncCatcher.class.getProtectionDomain());
        ArclightImplementer.LOGGER.debug(MARKER, "Defined impl callback class {}", (Object)classNode.name);
        return init.desc;
    }

    private MethodNode createBridge(ClassNode node, MethodNode methodNode) {
        MethodNode ret = new MethodNode();
        ret.name = methodNode.name + "$asyncCatcher$" + COUNTER.getAndIncrement();
        ret.desc = methodNode.desc;
        ret.access = 4161;
        if (Modifier.isStatic(methodNode.access)) {
            ret.access |= 8;
        }
        Type methodType = Type.getMethodType((String)methodNode.desc);
        ArclightImplementer.loadArgs(ret.instructions, methodNode, methodType.getArgumentTypes(), 0);
        int invokeCode = Modifier.isStatic(methodNode.access) ? 184 : 183;
        ret.instructions.add((AbstractInsnNode)new MethodInsnNode(invokeCode, node.name, methodNode.name, methodNode.desc));
        ret.instructions.add((AbstractInsnNode)new InsnNode(methodType.getReturnType().getOpcode(172)));
        node.methods.add(ret);
        ArclightImplementer.LOGGER.debug(MARKER, "Bridge method {}/{}{} created", (Object)node.name, (Object)ret.name, (Object)ret.desc);
        return ret;
    }

    static String getReturnAccessor(Type returnType) {
        if (returnType.getSort() == 10 || returnType.getSort() == 9) {
            return "getReturnValue";
        }
        return String.format("getReturnValue%s", returnType.getDescriptor());
    }

    static String getReturnDescriptor(Type returnType) {
        if (returnType.getSort() == 10 || returnType.getSort() == 9) {
            return String.format("()%s", "Ljava/lang/Object;");
        }
        return String.format("()%s", returnType.getDescriptor());
    }

    public static <T> CallbackInfoReturnable<T> checkOp(Supplier<T> method, AsyncCatcherSpec.Operation operation, String reason, Executor executor) throws Throwable {
        if (AsyncCatcher.INSTANCE.warn) {
            ArclightImplementer.LOGGER.warn(MARKER, "Async " + reason);
        }
        IllegalStateException exception = new IllegalStateException("Asynchronous " + reason + "!");
        if (AsyncCatcher.INSTANCE.dump) {
            ArclightImplementer.LOGGER.debug(MARKER, "Async " + reason, (Throwable)exception);
        }
        switch (operation) {
            case NONE: {
                return NOOP;
            }
            case EXCEPTION: {
                throw exception;
            }
            case BLOCK: {
                CallbackInfoReturnable cir = new CallbackInfoReturnable(reason, true);
                CompletableFuture<T> future = CompletableFuture.supplyAsync(method, executor);
                try {
                    cir.setReturnValue(future.get(5L, TimeUnit.SECONDS));
                }
                catch (TimeoutException e) {
                    Thread thread = (Thread)((Supplier)((Object)executor)).get();
                    Exception ex = new Exception("Server thread");
                    ex.setStackTrace(thread.getStackTrace());
                    ArclightImplementer.LOGGER.error(MARKER, "Async catcher timeout", (Throwable)ex);
                    throw e;
                }
                return cir;
            }
            case DISPATCH: {
                executor.execute(method::get);
                CallbackInfoReturnable cir = new CallbackInfoReturnable(reason, true);
                cir.cancel();
                return cir;
            }
        }
        throw new IllegalStateException("how this can happen?");
    }
}

