/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.io;

import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NotNull;

final class Wrapper<T extends Map<String, ?> & Serializable>
implements Serializable {
    private static final long serialVersionUID = -986209235411767547L;
    final T map;

    static Wrapper<ImmutableMap<String, ?>> newWrapper(@NotNull ConfigurationSerializable obj) {
        return new Wrapper(ImmutableMap.builder().put((Object)"==", (Object)ConfigurationSerialization.getAlias(obj.getClass())).putAll(obj.serialize()).build());
    }

    private Wrapper(@NotNull T map) {
        this.map = map;
    }
}

