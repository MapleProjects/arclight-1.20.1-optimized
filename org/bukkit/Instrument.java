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

public enum Instrument {
    PIANO(0),
    BASS_DRUM(1),
    SNARE_DRUM(2),
    STICKS(3),
    BASS_GUITAR(4),
    FLUTE(5),
    BELL(6),
    GUITAR(7),
    CHIME(8),
    XYLOPHONE(9),
    IRON_XYLOPHONE(10),
    COW_BELL(11),
    DIDGERIDOO(12),
    BIT(13),
    BANJO(14),
    PLING(15),
    ZOMBIE,
    SKELETON,
    CREEPER,
    DRAGON,
    WITHER_SKELETON,
    PIGLIN,
    CUSTOM_HEAD;

    private final byte type;
    private static final Map<Byte, Instrument> BY_DATA;

    static {
        BY_DATA = Maps.newHashMap();
        Instrument[] instrumentArray = Instrument.values();
        int n = instrumentArray.length;
        int n2 = 0;
        while (n2 < n) {
            Instrument instrument = instrumentArray[n2];
            BY_DATA.put(instrument.getType(), instrument);
            ++n2;
        }
    }

    private Instrument() {
        this(-1);
    }

    private Instrument(int type) {
        this.type = (byte)type;
    }

    @Deprecated
    public byte getType() {
        return this.type;
    }

    @Deprecated
    @Nullable
    public static Instrument getByType(byte type) {
        return BY_DATA.get(type);
    }
}

