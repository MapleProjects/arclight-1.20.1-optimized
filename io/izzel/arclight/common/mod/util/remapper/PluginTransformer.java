/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import io.izzel.arclight.common.mod.util.remapper.ClassLoaderRemapper;
import org.objectweb.asm.tree.ClassNode;

public interface PluginTransformer {
    public void handleClass(ClassNode var1, ClassLoaderRemapper var2, ArclightRemapConfig var3);

    default public int priority() {
        return 0;
    }
}

