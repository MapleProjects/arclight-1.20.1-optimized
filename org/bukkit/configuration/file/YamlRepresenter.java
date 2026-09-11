/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.yaml.snakeyaml.DumperOptions
 *  org.yaml.snakeyaml.nodes.Node
 *  org.yaml.snakeyaml.representer.Representer
 *  org.yaml.snakeyaml.representer.SafeRepresenter
 *  org.yaml.snakeyaml.representer.SafeRepresenter$RepresentMap
 */
package org.bukkit.configuration.file;

import java.util.LinkedHashMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.representer.SafeRepresenter;

public class YamlRepresenter
extends Representer {
    @Deprecated
    public YamlRepresenter() {
        this(new DumperOptions());
    }

    public YamlRepresenter(@NotNull DumperOptions options) {
        super(options);
        this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection());
        this.multiRepresenters.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable());
        this.multiRepresenters.remove(Enum.class);
    }

    private class RepresentConfigurationSection
    extends SafeRepresenter.RepresentMap {
        private RepresentConfigurationSection() {
            super((SafeRepresenter)YamlRepresenter.this);
        }

        @NotNull
        public Node representData(@NotNull Object data) {
            return super.representData(((ConfigurationSection)data).getValues(false));
        }
    }

    private class RepresentConfigurationSerializable
    extends SafeRepresenter.RepresentMap {
        private RepresentConfigurationSerializable() {
            super((SafeRepresenter)YamlRepresenter.this);
        }

        @NotNull
        public Node representData(@NotNull Object data) {
            ConfigurationSerializable serializable = (ConfigurationSerializable)data;
            LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
            values.put("==", ConfigurationSerialization.getAlias(serializable.getClass()));
            values.putAll(serializable.serialize());
            return super.representData(values);
        }
    }
}

