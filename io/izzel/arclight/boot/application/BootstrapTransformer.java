/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.cl.ModuleClassLoader
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.boot.application;

import cpw.mods.cl.ModuleClassLoader;
import java.io.IOException;
import java.io.InputStream;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class BootstrapTransformer
extends ClassLoader {
    private static final String cpwClass = "cpw.mods.bootstraplauncher.BootstrapLauncher";
    private final ProtectionDomain domain = this.getClass().getProtectionDomain();

    public static void onInvoke$BootstrapLauncher(String[] args, ModuleClassLoader moduleCl) {
        try {
            Class arclightBootClz = moduleCl.loadClass("io.izzel.arclight.boot.application.ApplicationBootstrap");
            Object instance = arclightBootClz.getConstructor(new Class[0]).newInstance(new Object[0]);
            arclightBootClz.getMethod("accept", String[].class).invoke(instance, new Object[]{args});
        }
        catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BootstrapTransformer(ClassLoader appClassLoader) {
        super("arclight_bootstrap", appClassLoader);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Object object = this.getClassLoadingLock(name);
        synchronized (object) {
            Class<?> clz;
            Class<?> c = this.findLoadedClass(name);
            if (c != null) {
                return c;
            }
            if (!name.contains(cpwClass)) {
                return super.loadClass(name, resolve);
            }
            try {
                clz = this.loadTransform(name);
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException("Unexpected exception loading " + name);
            }
            if (resolve) {
                this.resolveClass(clz);
            }
            return clz;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }

    public Class<?> loadTransform(String className) throws IOException {
        if (className.equals(cpwClass)) {
            String file = cpwClass.replace('.', '/').concat(".class");
            try (InputStream inputStream = this.getResourceAsStream(file);){
                if (inputStream == null) {
                    throw new RuntimeException("getResourceAsStream can't read BootstrapLauncher.class");
                }
                byte[] transformed = this.transformBootstrapLauncher(inputStream);
                Class<?> clazz = this.defineClass(cpwClass, transformed, 0, transformed.length, this.domain);
                return clazz;
            }
        }
        if (className.contains(cpwClass)) {
            String file = className.replace('.', '/').concat(".class");
            try (InputStream inputStream = this.getResourceAsStream(file);){
                if (inputStream == null) {
                    throw new RuntimeException("getResourceAsStream can't read " + file.substring(file.lastIndexOf(47)));
                }
                byte[] bytes = inputStream.readAllBytes();
                Class<?> clazz = this.defineClass(className, bytes, 0, bytes.length, this.domain);
                return clazz;
            }
        }
        throw new UnsupportedOperationException("Transformation for " + className + " is not supported");
    }

    public byte[] transformBootstrapLauncher(InputStream inputStream) throws IOException {
        System.out.println("Transforming cpw.mods.bootstraplauncher.BootstrapLauncher");
        ClassNode asmClass = new ClassNode();
        new ClassReader(inputStream).accept((ClassVisitor)asmClass, 0);
        MethodNode asmMain = null;
        for (MethodNode asmMethod : asmClass.methods) {
            if (!"main".equals(asmMethod.name)) continue;
            asmMain = asmMethod;
            break;
        }
        if (asmMain == null) {
            throw new RuntimeException("Cannot find main(String[]) in BootstrapLauncher");
        }
        InsnList insns = asmMain.instructions;
        MethodInsnNode injectionPoint = null;
        for (int i = 0; i < insns.size(); ++i) {
            AbstractInsnNode abstractInsnNode = insns.get(i);
            if (!(abstractInsnNode instanceof MethodInsnNode)) continue;
            MethodInsnNode invoke = (MethodInsnNode)abstractInsnNode;
            if (!"java/util/function/Consumer".equals(invoke.owner) || !"accept".equals(invoke.name)) continue;
            injectionPoint = invoke;
            break;
        }
        if (injectionPoint == null) {
            throw new RuntimeException("BootstrapTransformer failed to transform BootstrapLauncher: Consumer.accept(String[]) not found");
        }
        InsnList createArclightBoot = new InsnList();
        InsnNode popArgsThenService = new InsnNode(88);
        VarInsnNode aloadArgs = new VarInsnNode(25, 0);
        VarInsnNode aloadModuleCl = new VarInsnNode(25, 15);
        MethodInsnNode onInvoke = new MethodInsnNode(184, "io/izzel/arclight/boot/application/BootstrapTransformer", "onInvoke$BootstrapLauncher", "([Ljava/lang/String;Lcpw/mods/cl/ModuleClassLoader;)V");
        createArclightBoot.add((AbstractInsnNode)popArgsThenService);
        createArclightBoot.add((AbstractInsnNode)aloadArgs);
        createArclightBoot.add((AbstractInsnNode)aloadModuleCl);
        createArclightBoot.add((AbstractInsnNode)onInvoke);
        insns.insert((AbstractInsnNode)injectionPoint, createArclightBoot);
        insns.remove((AbstractInsnNode)injectionPoint);
        ClassWriter cw = new ClassWriter(1);
        asmClass.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }
}

