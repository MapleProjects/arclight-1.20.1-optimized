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

public enum Difficulty {
    PEACEFUL(0),
    EASY(1),
    NORMAL(2),
    HARD(3);

    private final int value;
    private static final Map<Integer, Difficulty> BY_ID;

    static {
        BY_ID = Maps.newHashMap();
        Difficulty[] difficultyArray = Difficulty.values();
        int n = difficultyArray.length;
        int n2 = 0;
        while (n2 < n) {
            Difficulty diff = difficultyArray[n2];
            BY_ID.put(diff.value, diff);
            ++n2;
        }
    }

    private Difficulty(int value) {
        this.value = value;
    }

    @Deprecated
    public int getValue() {
        return this.value;
    }

    @Deprecated
    @Nullable
    public static Difficulty getByValue(int value) {
        return BY_ID.get(value);
    }
}

