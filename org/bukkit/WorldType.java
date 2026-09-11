/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum WorldType {
    NORMAL("DEFAULT"),
    FLAT("FLAT"),
    LARGE_BIOMES("LARGEBIOMES"),
    AMPLIFIED("AMPLIFIED");

    private static final Map<String, WorldType> BY_NAME;
    private final String name;

    static {
        BY_NAME = Maps.newHashMap();
        WorldType[] worldTypeArray = WorldType.values();
        int n = worldTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            WorldType type = worldTypeArray[n2];
            BY_NAME.put(type.name, type);
            ++n2;
        }
    }

    private WorldType(String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public static WorldType getByName(@NotNull String name) {
        return BY_NAME.get(name.toUpperCase(Locale.ENGLISH));
    }
}

