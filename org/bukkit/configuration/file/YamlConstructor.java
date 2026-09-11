/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.yaml.snakeyaml.LoaderOptions
 *  org.yaml.snakeyaml.constructor.SafeConstructor
 *  org.yaml.snakeyaml.constructor.SafeConstructor$ConstructYamlMap
 *  org.yaml.snakeyaml.error.YAMLException
 *  org.yaml.snakeyaml.nodes.MappingNode
 *  org.yaml.snakeyaml.nodes.Node
 *  org.yaml.snakeyaml.nodes.Tag
 */
package org.bukkit.configuration.file;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class YamlConstructor
extends SafeConstructor {
    @Deprecated
    public YamlConstructor() {
        this(new LoaderOptions());
    }

    public YamlConstructor(@NotNull LoaderOptions loaderOptions) {
        super(loaderOptions);
        this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject());
    }

    public void flattenMapping(@NotNull MappingNode node) {
        super.flattenMapping(node);
    }

    @Nullable
    public Object construct(@NotNull Node node) {
        return this.constructObject(node);
    }

    private class ConstructCustomObject
    extends SafeConstructor.ConstructYamlMap {
        private ConstructCustomObject() {
            super((SafeConstructor)YamlConstructor.this);
        }

        @Nullable
        public Object construct(@NotNull Node node) {
            if (node.isTwoStepsConstruction()) {
                throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
            }
            Map raw = (Map)super.construct(node);
            if (raw.containsKey("==")) {
                LinkedHashMap typed = new LinkedHashMap(raw.size());
                for (Map.Entry entry : raw.entrySet()) {
                    typed.put(entry.getKey().toString(), entry.getValue());
                }
                try {
                    return ConfigurationSerialization.deserializeObject(typed);
                }
                catch (IllegalArgumentException ex) {
                    throw new YAMLException("Could not deserialize object", (Throwable)ex);
                }
            }
            return raw;
        }

        public void construct2ndStep(@NotNull Node node, @NotNull Object object) {
            throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
        }
    }
}

