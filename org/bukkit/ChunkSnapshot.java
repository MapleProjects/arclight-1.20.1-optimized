/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface ChunkSnapshot {
    public int getX();

    public int getZ();

    @NotNull
    public String getWorldName();

    @NotNull
    public Material getBlockType(int var1, int var2, int var3);

    @NotNull
    public BlockData getBlockData(int var1, int var2, int var3);

    @Deprecated
    public int getData(int var1, int var2, int var3);

    public int getBlockSkyLight(int var1, int var2, int var3);

    public int getBlockEmittedLight(int var1, int var2, int var3);

    public int getHighestBlockYAt(int var1, int var2);

    @Deprecated
    @NotNull
    public Biome getBiome(int var1, int var2);

    @NotNull
    public Biome getBiome(int var1, int var2, int var3);

    @Deprecated
    public double getRawBiomeTemperature(int var1, int var2);

    public double getRawBiomeTemperature(int var1, int var2, int var3);

    public long getCaptureFullTime();

    public boolean isSectionEmpty(int var1);

    public boolean contains(@NotNull BlockData var1);

    public boolean contains(@NotNull Biome var1);
}

