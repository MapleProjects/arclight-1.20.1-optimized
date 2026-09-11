/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.material.types;

import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

public enum MushroomBlockTexture {
    ALL_PORES(0, null),
    CAP_NORTH_WEST(1, BlockFace.NORTH_WEST),
    CAP_NORTH(2, BlockFace.NORTH),
    CAP_NORTH_EAST(3, BlockFace.NORTH_EAST),
    CAP_WEST(4, BlockFace.WEST),
    CAP_TOP(5, BlockFace.UP),
    CAP_EAST(6, BlockFace.EAST),
    CAP_SOUTH_WEST(7, BlockFace.SOUTH_WEST),
    CAP_SOUTH(8, BlockFace.SOUTH),
    CAP_SOUTH_EAST(9, BlockFace.SOUTH_EAST),
    STEM_SIDES(10, null),
    ALL_CAP(14, BlockFace.SELF),
    ALL_STEM(15, null);

    private static final Map<Byte, MushroomBlockTexture> BY_DATA;
    private static final Map<BlockFace, MushroomBlockTexture> BY_BLOCKFACE;
    private final Byte data;
    private final BlockFace capFace;

    static {
        BY_DATA = Maps.newHashMap();
        BY_BLOCKFACE = Maps.newHashMap();
        MushroomBlockTexture[] mushroomBlockTextureArray = MushroomBlockTexture.values();
        int n = mushroomBlockTextureArray.length;
        int n2 = 0;
        while (n2 < n) {
            MushroomBlockTexture type = mushroomBlockTextureArray[n2];
            BY_DATA.put(type.data, type);
            BY_BLOCKFACE.put(type.capFace, type);
            ++n2;
        }
    }

    private MushroomBlockTexture(int data, BlockFace capFace) {
        this.data = (byte)data;
        this.capFace = capFace;
    }

    @Deprecated
    public byte getData() {
        return this.data;
    }

    @Nullable
    public BlockFace getCapFace() {
        return this.capFace;
    }

    @Deprecated
    @Nullable
    public static MushroomBlockTexture getByData(byte data) {
        return BY_DATA.get(data);
    }

    @Nullable
    public static MushroomBlockTexture getCapByFace(@Nullable BlockFace face) {
        return BY_BLOCKFACE.get((Object)face);
    }
}

