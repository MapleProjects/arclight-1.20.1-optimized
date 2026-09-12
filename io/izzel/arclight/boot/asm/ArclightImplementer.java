/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.api.NamedPath
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$Phase
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.jul.LogManager
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.izzel.arclight.boot.asm;

import cpw.mods.modlauncher.api.NamedPath;
import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import io.izzel.arclight.boot.asm.AsyncCatcher;
import io.izzel.arclight.boot.asm.EnumDefinalizer;
import io.izzel.arclight.boot.asm.Implementer;
import io.izzel.arclight.boot.asm.InventoryImplementer;
import io.izzel.arclight.boot.asm.LoggerTransformer;
import io.izzel.arclight.boot.asm.SwitchTableFixer;
import io.izzel.arclight.boot.log.ArclightI18nLogger;
import io.izzel.arclight.boot.mod.ModBootstrap;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.jul.LogManager;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ArclightImplementer
implements ILaunchPluginService {
    static final Logger LOGGER = ArclightI18nLogger.getLogger("Implementer");
    private static final EnumSet<ILaunchPluginService.Phase> OH_YES_SIR = EnumSet.of(ILaunchPluginService.Phase.AFTER);
    private static final EnumSet<ILaunchPluginService.Phase> NOT_TODAY = EnumSet.noneOf(ILaunchPluginService.Phase.class);
    private final Map<String, Implementer> implementers = new HashMap<String, Implementer>();
    private volatile Consumer<String[]> auditAcceptor;
    private ILaunchPluginService.ITransformerLoader transformerLoader;
    private final boolean logger;

    public ArclightImplementer() {
        this(ArclightImplementer.detectTransformLogger());
    }

    public ArclightImplementer(boolean logger) {
        this.logger = logger;
    }

    private static boolean detectTransformLogger() {
        boolean transformLogger;
        boolean bl = transformLogger = !(java.util.logging.LogManager.getLogManager() instanceof LogManager);
        if (transformLogger && !System.getProperties().contains("log4j.jul.LoggerAdapter")) {
            System.setProperty("log4j.jul.LoggerAdapter", "io.izzel.arclight.boot.log.ArclightLoggerAdapter");
        }
        return transformLogger;
    }

    public String name() {
        return "arclight_implementer";
    }

    public void initializeLaunch(ILaunchPluginService.ITransformerLoader transformerLoader, NamedPath[] specialPaths) {
        ModBootstrap.postRun();
        this.transformerLoader = transformerLoader;
        this.implementers.put("inventory", new InventoryImplementer());
        this.implementers.put("switch", SwitchTableFixer.INSTANCE);
        this.implementers.put("async", AsyncCatcher.INSTANCE);
        this.implementers.put("enum", new EnumDefinalizer());
        if (this.logger) {
            this.implementers.put("logger", new LoggerTransformer());
        }
    }

    public EnumSet<ILaunchPluginService.Phase> handlesClass(Type classType, boolean isEmpty, String reason) {
        if ("mixin".equals(reason)) {
            return NOT_TODAY;
        }
        return isEmpty ? NOT_TODAY : OH_YES_SIR;
    }

    public EnumSet<ILaunchPluginService.Phase> handlesClass(Type classType, boolean isEmpty) {
        throw new IllegalStateException("Outdated ModLauncher");
    }

    public void customAuditConsumer(String className, Consumer<String[]> auditDataAcceptor) {
        this.auditAcceptor = auditDataAcceptor;
    }

    public boolean processClass(ILaunchPluginService.Phase phase, ClassNode classNode, Type classType, String reason) {
        if ("mixin".equals(reason)) {
            return false;
        }
        ArrayList<String> trails = new ArrayList<String>();
        for (Map.Entry<String, Implementer> entry : this.implementers.entrySet()) {
            String key = entry.getKey();
            Implementer implementer = entry.getValue();
            if (!implementer.processClass(classNode, this.transformerLoader)) continue;
            trails.add(key);
        }
        if (this.auditAcceptor != null && !trails.isEmpty()) {
            this.auditAcceptor.accept(new String[]{String.join((CharSequence)",", trails)});
        }
        return !trails.isEmpty();
    }

    public boolean processClass(ILaunchPluginService.Phase phase, ClassNode classNode, Type classType) {
        throw new IllegalStateException("Outdated ModLauncher");
    }

    public static void loadArgs(InsnList list, MethodNode methodNode, Type[] types, int i) {
        if (!Modifier.isStatic(methodNode.access)) {
            list.add((AbstractInsnNode)new VarInsnNode(25, i));
            ++i;
        }
        for (Type type : types) {
            list.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), i));
            i += type.getSize();
        }
    }
}

