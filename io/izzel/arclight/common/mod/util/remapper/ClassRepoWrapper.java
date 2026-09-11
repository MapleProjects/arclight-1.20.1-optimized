/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.specialsource.repo.ClassRepo
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper;

import io.izzel.arclight.common.mod.util.remapper.ArclightRemapConfig;
import net.md_5.specialsource.repo.ClassRepo;
import org.objectweb.asm.tree.ClassNode;

public record ClassRepoWrapper(ClassRepo inner, ArclightRemapConfig config) implements ClassRepo
{
    public ClassNode findClass(String internalName) {
        return this.inner.findClass(internalName);
    }
}

