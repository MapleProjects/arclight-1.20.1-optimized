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

public enum GameMode {
    CREATIVE(1),
    SURVIVAL(0),
    ADVENTURE(2),
    SPECTATOR(3);

    private final int value;
    private static final Map<Integer, GameMode> BY_ID;

    static {
        BY_ID = Maps.newHashMap();
        GameMode[] gameModeArray = GameMode.values();
        int n = gameModeArray.length;
        int n2 = 0;
        while (n2 < n) {
            GameMode mode = gameModeArray[n2];
            BY_ID.put(mode.getValue(), mode);
            ++n2;
        }
    }

    private GameMode(int value) {
        this.value = value;
    }

    @Deprecated
    public int getValue() {
        return this.value;
    }

    @Deprecated
    @Nullable
    public static GameMode getByValue(int value) {
        return BY_ID.get(value);
    }
}

