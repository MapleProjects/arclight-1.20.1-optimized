/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ChunkGenerator {
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
    }

    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
    }

    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
    }

    public void generateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
    }

    @Nullable
    public BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
        return null;
    }

    public int getBaseHeight(@NotNull WorldInfo worldInfo, @NotNull Random random, int x, int z, @NotNull HeightMap heightMap) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Deprecated
    @NotNull
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
        throw new UnsupportedOperationException("Not implemented, no longer needed");
    }

    @Deprecated
    @NotNull
    protected final ChunkData createChunkData(@NotNull World world) {
        return Bukkit.getServer().createChunkData(world);
    }

    public boolean canSpawn(@NotNull World world, int x, int z) {
        Block highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);
        switch (world.getEnvironment()) {
            case NETHER: {
                return true;
            }
            case THE_END: {
                return highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA;
            }
        }
        return highest.getType() == Material.SAND || highest.getType() == Material.GRAVEL;
    }

    @NotNull
    public List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return new ArrayList<BlockPopulator>();
    }

    @Nullable
    public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return null;
    }

    @Deprecated
    public boolean isParallelCapable() {
        return false;
    }

    public boolean shouldGenerateNoise() {
        return false;
    }

    public boolean shouldGenerateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateNoise();
    }

    public boolean shouldGenerateSurface() {
        return false;
    }

    public boolean shouldGenerateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateSurface();
    }

    @Deprecated
    public boolean shouldGenerateBedrock() {
        return false;
    }

    public boolean shouldGenerateCaves() {
        return false;
    }

    public boolean shouldGenerateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateCaves();
    }

    public boolean shouldGenerateDecorations() {
        return false;
    }

    public boolean shouldGenerateDecorations(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateDecorations();
    }

    public boolean shouldGenerateMobs() {
        return false;
    }

    public boolean shouldGenerateMobs(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateMobs();
    }

    public boolean shouldGenerateStructures() {
        return false;
    }

    public boolean shouldGenerateStructures(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ) {
        return this.shouldGenerateStructures();
    }

    @Deprecated
    public static interface BiomeGrid {
        @Deprecated
        @NotNull
        public Biome getBiome(int var1, int var2);

        @NotNull
        public Biome getBiome(int var1, int var2, int var3);

        @Deprecated
        public void setBiome(int var1, int var2, @NotNull Biome var3);

        public void setBiome(int var1, int var2, int var3, @NotNull Biome var4);
    }

    public static interface ChunkData {
        public int getMinHeight();

        public int getMaxHeight();

        @NotNull
        public Biome getBiome(int var1, int var2, int var3);

        public void setBlock(int var1, int var2, int var3, @NotNull Material var4);

        public void setBlock(int var1, int var2, int var3, @NotNull MaterialData var4);

        public void setBlock(int var1, int var2, int var3, @NotNull BlockData var4);

        public void setRegion(int var1, int var2, int var3, int var4, int var5, int var6, @NotNull Material var7);

        public void setRegion(int var1, int var2, int var3, int var4, int var5, int var6, @NotNull MaterialData var7);

        public void setRegion(int var1, int var2, int var3, int var4, int var5, int var6, @NotNull BlockData var7);

        @NotNull
        public Material getType(int var1, int var2, int var3);

        @NotNull
        public MaterialData getTypeAndData(int var1, int var2, int var3);

        @NotNull
        public BlockData getBlockData(int var1, int var2, int var3);

        @Deprecated
        public byte getData(int var1, int var2, int var3);
    }
}

