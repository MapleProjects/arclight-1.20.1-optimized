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

@Deprecated
public enum TreeSpecies {
    GENERIC(0),
    REDWOOD(1),
    BIRCH(2),
    JUNGLE(3),
    ACACIA(4),
    DARK_OAK(5);

    private final byte data;
    private static final Map<Byte, TreeSpecies> BY_DATA;

    static {
        BY_DATA = Maps.newHashMap();
        TreeSpecies[] treeSpeciesArray = TreeSpecies.values();
        int n = treeSpeciesArray.length;
        int n2 = 0;
        while (n2 < n) {
            TreeSpecies species = treeSpeciesArray[n2];
            BY_DATA.put(species.data, species);
            ++n2;
        }
    }

    private TreeSpecies(int data) {
        this.data = (byte)data;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Deprecated
    @Nullable
    public static TreeSpecies getByData(byte data) {
        return BY_DATA.get(data);
    }
}

