/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.generator;

import java.util.List;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeParameterPoint;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

public abstract class BiomeProvider {
    @NotNull
    public abstract Biome getBiome(@NotNull WorldInfo var1, int var2, int var3, int var4);

    @NotNull
    public Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z, @NotNull BiomeParameterPoint biomeParameterPoint) {
        return this.getBiome(worldInfo, x, y, z);
    }

    @NotNull
    public abstract List<Biome> getBiomes(@NotNull WorldInfo var1);
}

