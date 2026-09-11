/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.PluginPatcher
 *  io.izzel.arclight.api.PluginPatcher$ClassRepo
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.util.remapper.patcher.integrated;

import io.izzel.arclight.api.PluginPatcher;
import io.izzel.arclight.common.mod.util.remapper.patcher.integrated.WorldEdit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import org.objectweb.asm.tree.ClassNode;

public class IntegratedPatcher
implements PluginPatcher {
    private static final Map<String, BiConsumer<ClassNode, PluginPatcher.ClassRepo>> SPECIFIC = new HashMap<String, BiConsumer<ClassNode, PluginPatcher.ClassRepo>>(){};
    private static final List<BiConsumer<ClassNode, PluginPatcher.ClassRepo>> GENERAL = new ArrayList<BiConsumer<ClassNode, PluginPatcher.ClassRepo>>();

    public void handleClass(ClassNode node, PluginPatcher.ClassRepo classRepo) {
        BiConsumer<ClassNode, PluginPatcher.ClassRepo> consumer = SPECIFIC.get(node.name);
        if (consumer != null) {
            consumer.accept(node, classRepo);
        } else {
            for (BiConsumer<ClassNode, PluginPatcher.ClassRepo> general : GENERAL) {
                general.accept(node, classRepo);
            }
        }
    }

    static {
        SPECIFIC.put("com/sk89q/worldedit/bukkit/BukkitAdapter", WorldEdit::handleBukkitAdapter);
        SPECIFIC.put("com/sk89q/worldedit/bukkit/adapter/Refraction", WorldEdit::handlePickName);
        GENERAL.add(WorldEdit::handleWatchdog);
    }
}

