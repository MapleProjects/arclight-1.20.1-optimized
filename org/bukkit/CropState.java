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

public enum CropState {
    SEEDED(0),
    GERMINATED(1),
    VERY_SMALL(2),
    SMALL(3),
    MEDIUM(4),
    TALL(5),
    VERY_TALL(6),
    RIPE(7);

    private final byte data;
    private static final Map<Byte, CropState> BY_DATA;

    static {
        BY_DATA = Maps.newHashMap();
        CropState[] cropStateArray = CropState.values();
        int n = cropStateArray.length;
        int n2 = 0;
        while (n2 < n) {
            CropState cropState = cropStateArray[n2];
            BY_DATA.put(cropState.getData(), cropState);
            ++n2;
        }
    }

    private CropState(int data) {
        this.data = (byte)data;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Deprecated
    @Nullable
    public static CropState getByData(byte data) {
        return BY_DATA.get(data);
    }
}

