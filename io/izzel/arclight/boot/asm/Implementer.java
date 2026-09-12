/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.boot.asm;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import org.objectweb.asm.tree.ClassNode;

public interface Implementer {
    public boolean processClass(ClassNode var1, ILaunchPluginService.ITransformerLoader var2);
}

