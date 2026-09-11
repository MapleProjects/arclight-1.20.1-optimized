/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.PalettedContainer
 *  net.minecraft.world.level.chunk.PalettedContainerRO
 *  net.minecraft.world.level.levelgen.Heightmap
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public class CraftChunkSnapshot
implements ChunkSnapshot {
    private final int x;
    private final int z;
    private final int minHeight;
    private final int maxHeight;
    private final String worldname;
    private final PalettedContainer<BlockState>[] blockids;
    private final byte[][] skylight;
    private final byte[][] emitlight;
    private final boolean[] empty;
    private final Heightmap hmap;
    private final long captureFulltime;
    private final Registry<net.minecraft.world.level.biome.Biome> biomeRegistry;
    private final PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[] biome;

    CraftChunkSnapshot(int x, int z, int minHeight, int maxHeight, String wname, long wtime, PalettedContainer<BlockState>[] sectionBlockIDs, byte[][] sectionSkyLights, byte[][] sectionEmitLights, boolean[] sectionEmpty, Heightmap hmap, Registry<net.minecraft.world.level.biome.Biome> biomeRegistry, PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[] biome) {
        this.x = x;
        this.z = z;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.worldname = wname;
        this.captureFulltime = wtime;
        this.blockids = sectionBlockIDs;
        this.skylight = sectionSkyLights;
        this.emitlight = sectionEmitLights;
        this.empty = sectionEmpty;
        this.hmap = hmap;
        this.biomeRegistry = biomeRegistry;
        this.biome = biome;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getZ() {
        return this.z;
    }

    @Override
    public String getWorldName() {
        return this.worldname;
    }

    @Override
    public boolean contains(BlockData block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"Block cannot be null");
        com.google.common.base.Predicate nms = Predicates.equalTo((Object)((CraftBlockData)block).getState());
        PalettedContainer<BlockState>[] palettedContainerArray = this.blockids;
        int n = this.blockids.length;
        int n2 = 0;
        while (n2 < n) {
            PalettedContainer<BlockState> palette = palettedContainerArray[n2];
            if (palette.m_63109_((Predicate)nms)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean contains(Biome biome) {
        Preconditions.checkArgument((biome != null ? 1 : 0) != 0, (Object)"Biome cannot be null");
        com.google.common.base.Predicate nms = Predicates.equalTo(CraftBlock.biomeToBiomeBase(this.biomeRegistry, biome));
        PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[] palettedContainerROArray = this.biome;
        int n = this.biome.length;
        int n2 = 0;
        while (n2 < n) {
            PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>> palette = palettedContainerROArray[n2];
            if (palette.m_63109_((Predicate)nms)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public Material getBlockType(int x, int y, int z) {
        this.validateChunkCoordinates(x, y, z);
        return CraftMagicNumbers.getMaterial(((BlockState)this.blockids[this.getSectionIndex(y)].m_63087_(x, y & 0xF, z)).m_60734_());
    }

    @Override
    public final BlockData getBlockData(int x, int y, int z) {
        this.validateChunkCoordinates(x, y, z);
        return CraftBlockData.fromData((BlockState)this.blockids[this.getSectionIndex(y)].m_63087_(x, y & 0xF, z));
    }

    @Override
    public final int getData(int x, int y, int z) {
        this.validateChunkCoordinates(x, y, z);
        return CraftMagicNumbers.toLegacyData((BlockState)this.blockids[this.getSectionIndex(y)].m_63087_(x, y & 0xF, z));
    }

    @Override
    public final int getBlockSkyLight(int x, int y, int z) {
        this.validateChunkCoordinates(x, y, z);
        int off = (y & 0xF) << 7 | z << 3 | x >> 1;
        return this.skylight[this.getSectionIndex(y)][off] >> ((x & 1) << 2) & 0xF;
    }

    @Override
    public final int getBlockEmittedLight(int x, int y, int z) {
        this.validateChunkCoordinates(x, y, z);
        int off = (y & 0xF) << 7 | z << 3 | x >> 1;
        return this.emitlight[this.getSectionIndex(y)][off] >> ((x & 1) << 2) & 0xF;
    }

    @Override
    public final int getHighestBlockYAt(int x, int z) {
        Preconditions.checkState((this.hmap != null ? 1 : 0) != 0, (Object)"ChunkSnapshot created without height map. Please call getSnapshot with includeMaxblocky=true");
        this.validateChunkCoordinates(x, 0, z);
        return this.hmap.m_158368_(x, z);
    }

    @Override
    public final Biome getBiome(int x, int z) {
        return this.getBiome(x, 0, z);
    }

    @Override
    public final Biome getBiome(int x, int y, int z) {
        Preconditions.checkState((this.biome != null ? 1 : 0) != 0, (Object)"ChunkSnapshot created without biome. Please call getSnapshot with includeBiome=true");
        this.validateChunkCoordinates(x, y, z);
        PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>> biome = this.biome[this.getSectionIndex(y)];
        return CraftBlock.biomeBaseToBiome(this.biomeRegistry, (Holder<net.minecraft.world.level.biome.Biome>)((Holder)biome.m_63087_(x >> 2, (y & 0xF) >> 2, z >> 2)));
    }

    @Override
    public final double getRawBiomeTemperature(int x, int z) {
        return this.getRawBiomeTemperature(x, 0, z);
    }

    @Override
    public final double getRawBiomeTemperature(int x, int y, int z) {
        Preconditions.checkState((this.biome != null ? 1 : 0) != 0, (Object)"ChunkSnapshot created without biome. Please call getSnapshot with includeBiome=true");
        this.validateChunkCoordinates(x, y, z);
        PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>> biome = this.biome[this.getSectionIndex(y)];
        return ((net.minecraft.world.level.biome.Biome)((Holder)biome.m_63087_(x >> 2, (y & 0xF) >> 2, z >> 2)).m_203334_()).m_47505_(new BlockPos(this.x << 4 | x, y, this.z << 4 | z));
    }

    @Override
    public final long getCaptureFullTime() {
        return this.captureFulltime;
    }

    @Override
    public final boolean isSectionEmpty(int sy) {
        return this.empty[sy];
    }

    private int getSectionIndex(int y) {
        return y - this.minHeight >> 4;
    }

    private void validateChunkCoordinates(int x, int y, int z) {
        CraftChunk.validateChunkCoordinates(this.minHeight, this.maxHeight, x, y, z);
    }
}

