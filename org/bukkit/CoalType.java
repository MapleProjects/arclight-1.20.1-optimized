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

public enum CoalType {
    COAL(0),
    CHARCOAL(1);

    private final byte data;
    private static final Map<Byte, CoalType> BY_DATA;

    static {
        BY_DATA = Maps.newHashMap();
        CoalType[] coalTypeArray = CoalType.values();
        int n = coalTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            CoalType type = coalTypeArray[n2];
            BY_DATA.put(type.data, type);
            ++n2;
        }
    }

    private CoalType(int data) {
        this.data = (byte)data;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Deprecated
    @Nullable
    public static CoalType getByData(byte data) {
        return BY_DATA.get(data);
    }
}

