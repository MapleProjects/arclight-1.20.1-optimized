/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.api;

import org.objectweb.asm.tree.ClassNode;

public interface PluginPatcher {
    public void handleClass(ClassNode var1, ClassRepo var2);

    default public int priority() {
        return 0;
    }

    default public String version() {
        String implVersion = this.getClass().getPackage().getImplementationVersion();
        return implVersion == null ? "unknown" : implVersion;
    }

    public static interface ClassRepo {
        public ClassNode findClass(String var1, int var2);
    }
}

