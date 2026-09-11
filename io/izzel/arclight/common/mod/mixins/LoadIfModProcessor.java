/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 */
package io.izzel.arclight.common.mod.mixins;

import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.mixins.annotation.LoadIfMod;
import java.util.List;
import java.util.Objects;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

public class LoadIfModProcessor {
    private static final String TYPE = Type.getDescriptor(LoadIfMod.class);

    static boolean shouldApply(ClassNode node) {
        for (AnnotationNode ann : node.invisibleAnnotations) {
            if (!ann.desc.equals(TYPE)) continue;
            LoadIfModData loadIfModData = LoadIfModProcessor.parse(ann);
            return switch (loadIfModData.condition()) {
                default -> throw new IncompatibleClassChangeError();
                case LoadIfMod.ModCondition.ABSENT -> {
                    for (String modid : loadIfModData.modids()) {
                        if (!ArclightMod.isModLoaded(modid)) continue;
                        yield false;
                    }
                    yield true;
                }
                case LoadIfMod.ModCondition.PRESENT -> {
                    for (String modid : loadIfModData.modids()) {
                        if (!ArclightMod.isModLoaded(modid)) continue;
                        yield true;
                    }
                    yield false;
                }
            };
        }
        return true;
    }

    private static LoadIfModData parse(AnnotationNode ann) {
        LoadIfMod.ModCondition condition = null;
        List modids = null;
        block8: for (int i = 0; i < ann.values.size(); i += 2) {
            String name = (String)ann.values.get(i);
            Object value = ann.values.get(i + 1);
            switch (name) {
                case "condition": {
                    String condName = ((String[])value)[1];
                    condition = LoadIfMod.ModCondition.valueOf(condName);
                    continue block8;
                }
                case "modid": {
                    modids = (List)value;
                }
            }
        }
        return new LoadIfModData(Objects.requireNonNull(condition, "condition"), Objects.requireNonNull(modids, "modid"));
    }

    private record LoadIfModData(LoadIfMod.ModCondition condition, List<String> modids) {
    }
}

