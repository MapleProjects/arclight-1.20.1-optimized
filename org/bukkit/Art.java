/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Locale;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Art implements Keyed
{
    KEBAB(0, 1, 1),
    AZTEC(1, 1, 1),
    ALBAN(2, 1, 1),
    AZTEC2(3, 1, 1),
    BOMB(4, 1, 1),
    PLANT(5, 1, 1),
    WASTELAND(6, 1, 1),
    POOL(7, 2, 1),
    COURBET(8, 2, 1),
    SEA(9, 2, 1),
    SUNSET(10, 2, 1),
    CREEBET(11, 2, 1),
    WANDERER(12, 1, 2),
    GRAHAM(13, 1, 2),
    MATCH(14, 2, 2),
    BUST(15, 2, 2),
    STAGE(16, 2, 2),
    VOID(17, 2, 2),
    SKULL_AND_ROSES(18, 2, 2),
    WITHER(19, 2, 2),
    FIGHTERS(20, 4, 2),
    POINTER(21, 4, 4),
    PIGSCENE(22, 4, 4),
    BURNING_SKULL(23, 4, 4),
    SKELETON(24, 4, 3),
    DONKEY_KONG(25, 4, 3),
    EARTH(26, 2, 2),
    WIND(27, 2, 2),
    WATER(28, 2, 2),
    FIRE(29, 2, 2);

    private final int id;
    private final int width;
    private final int height;
    private final NamespacedKey key;
    private static final HashMap<String, Art> BY_NAME;
    private static final HashMap<Integer, Art> BY_ID;

    static {
        BY_NAME = Maps.newHashMap();
        BY_ID = Maps.newHashMap();
        Art[] artArray = Art.values();
        int n = artArray.length;
        int n2 = 0;
        while (n2 < n) {
            Art art = artArray[n2];
            BY_ID.put(art.id, art);
            BY_NAME.put(art.toString().toLowerCase(Locale.ENGLISH), art);
            ++n2;
        }
    }

    private Art(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ENGLISH));
    }

    public int getBlockWidth() {
        return this.width;
    }

    public int getBlockHeight() {
        return this.height;
    }

    @Deprecated
    public int getId() {
        return this.id;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @Deprecated
    @Nullable
    public static Art getById(int id) {
        return BY_ID.get(id);
    }

    @Nullable
    public static Art getByName(@NotNull String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Name cannot be null");
        return BY_NAME.get(name.toLowerCase(Locale.ENGLISH));
    }
}

