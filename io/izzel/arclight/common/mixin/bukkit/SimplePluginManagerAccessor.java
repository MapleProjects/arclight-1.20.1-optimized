/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.graph.MutableGraph
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.graph.MutableGraph;
import org.bukkit.plugin.SimplePluginManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SimplePluginManager.class}, remap=false)
public interface SimplePluginManagerAccessor {
    @Accessor(value="dependencyGraph")
    public MutableGraph<String> arclight$dependencyGraph();
}

