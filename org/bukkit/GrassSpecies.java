/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public enum GrassSpecies {
    DEAD(0),
    NORMAL(1),
    FERN_LIKE(2);

    private final byte data;
    private static final Map<Byte, GrassSpecies> BY_DATA;

    static {
        BY_DATA = Maps.newHashMap();
        GrassSpecies[] grassSpeciesArray = GrassSpecies.values();
        int n = grassSpeciesArray.length;
        int n2 = 0;
        while (n2 < n) {
            GrassSpecies grassSpecies = grassSpeciesArray[n2];
            BY_DATA.put(grassSpecies.getData(), grassSpecies);
            ++n2;
        }
    }

    private GrassSpecies(int data) {
        this.data = (byte)data;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Deprecated
    @Nullable
    public static GrassSpecies getByData(byte data) {
        return BY_DATA.get(data);
    }
}

